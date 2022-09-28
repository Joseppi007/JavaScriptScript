package me.jono.javascriptscript;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author jono
 * A Node is a piece of code in JavaScriptScript++#--.jssppsmm. It has properties that can be used as constants or to
 * take input from other Nodes. Each Node may have any number of output sockets to connect to other Nodes' input
 * sockets. An input socket is the visual representation of one of a Node's output sockets being put into another Node's
 * input socket as a Value. Nodes without any input sockets exposed, or no Values that are references to other Nodes'
 * output sockets, are likely the start of a piece of the program. This is because they aren't in the middle or at the
 * end. Likewise, Nodes that have their output sockets unconnected to anything or no output sockets at all will end
 * their piece of the program, but another piece may branch off in another direction, so it does not necissarly mean
 * the end of the program as a whole.
 */
public abstract class Node {
    private final HashMap<String, Value> properties;
    private final NodeOutputSockets outputs;

    /**
     * Each Node should have a unique name to make jssppsmm files easier to create. References to each Node can be done
     * with this name. If you want to store many Nodes, I would recommend a HashMap because of this.
     */
    private final String name;

    private boolean alreadyRun;

    /**
     * This will run the Node to update the output sockets with the parameters unless alreadyRun is true.
     * This will set alreadyRun to true. Nodes that want to achieve looping with control flow or something similar
     * should set alreadyRun to false for its outputs, their outputs, and so on. This can be done with
     * makeUnrunForward().
     */
    public void runUnlessAlreadyRun() {
        if (alreadyRun) return;
        alreadyRun = true;
        run();
    }

    /**
     * This will make this Node's alreadyRun false, as well as it's outputs' alreadyRuns, their outputs, and so on.
     * If it hits a Node that hasn't alreadyRun, it will stop to avoid infinite loops.
     */
    protected void makeUnrunForward() {
        if (alreadyRun)
            for (Node out : getOutputNodes()) {
                out.makeUnrunForward();
            }
        alreadyRun = false;
    }

    /**
     * Updates the {@link NodeOutputSockets outputs} using the parameters.
     * This will call run for nodes that output to this node's inputs.
     */
    public abstract void run();

    /**
     * Gets the input nodes as an ArrayList
     * @return inputs
     */
    public ArrayList<Node> getInputs() {
        ArrayList<Node> inputs = new ArrayList<>();
        for (String key : properties.keySet()) {
            if (properties.get(key) instanceof NodeOutputSocketValue) {
                inputs.add(((NodeOutputSocketValue)(properties.get(key))).getNode());
            } else if (properties.get(key) instanceof ValueGroup) {
                for (Value v : (ValueGroup)(properties.get(key))) {
                    System.out.println(v);
                    if (v instanceof NodeOutputSocketValue) {
                        inputs.add(((NodeOutputSocketValue)(v)).getNode());
                    }
                }
            }
        }
        return inputs;
    }

    /**
     * Gets the output nodes as an ArrayList
     * @return outputs
     */
    public ArrayList<Node> getOutputNodes() {
        ArrayList<Node> out = new ArrayList<>();
        for (NodeOutputSocketValue value : outputs.getValues().values()) {
            out.addAll(value.getNodesOutputTo());
        }
        return out;
    }

    /**
     * Gets the name of this Node. It should be unique.
     * @return The name of the Node
     */
    public String getName() {return this.name;}

    /**
     * Creates a connection between two Nodes, routing the output of the start to the input of the end.
     * This will replace Values that aren't NodeOutputSocketValue, change a NodeOutputSocketValue into a ValueGroup of
     * NodeOutputSocketValues (Old and New), and append to a NodeOutputSocketValue.
     * @param start The Node that outputs into the connection
     * @param outputSocket The name of the socket to pull from
     * @param property The property to plug into
     * @param end The Node that takes input from the connection
     */
    public static void link(Node start, String outputSocket, String property, Node end) {
        NodeOutputSocketValue o = start.outputs.get(outputSocket);
        Value p = end.properties.get(property);
        if (p instanceof ValueGroup) {
            ((ValueGroup)p).add(o);
        } else if (p instanceof NodeOutputSocketValue) {
            end.properties.put(property, new ValueGroup(p, o));
        } else {
            end.properties.put(property, o);
        }

        //start.inputs.add(end);
        //end.inputs.add(start);
    }

    /**
     * Creates a connection from this Node to the provided destination Node.
     * @param outputSocket The name of the socket to pull from
     * @param property The property to plug into
     * @param destination Where the output of this node in sent to be inputted into
     */
    public void linkTo(String outputSocket, String property, Node destination) {
        link(this, outputSocket, property, destination);
    }

    @Override
    public String toString() {return "< " + getClass().getName() + " : " + this.name + " >";}

    public Node(String name) {
        this.name = name;
        this.properties = new HashMap<>();
        this.outputs = new NodeOutputSockets(this);
    }

    /**
     * Gets the properties
     * @return the properties
     */
    public HashMap<String, Value> getProperties() {return properties;}

    /**
     * Get outputs
     * @return outputs
     */
    public NodeOutputSockets getOutputSockets() {return outputs;}
}
