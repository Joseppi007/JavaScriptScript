package me.jono.javascriptscript;
import java.util.ArrayList;

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
    private final ArrayList<Node> inputs; // Mild redundancy is very helpful for navigation
    private final ArrayList<Node> outputs; // Mild redundancy is very helpful for navigation

    /**
     * The value is saved to prevent re-running the program when not required. It will change when the program is re-run
     */
    private Object savedValue;
    /**
     * Each Node should have a unique name to make jssppsmm files easier to create. References to each Node can be done
     * with this name. If you want to store many Nodes, I would recommend a HashMap because of this.
     */
    private final String name;

    /**
     * Gets the value of the Node, using inputs if the Node needs them.
     * The value is saved in savedValue.
     * @return The value of the Node
     */
    public Object getValueAndRun() {
        savedValue = run();
        return savedValue;
    }

    /**
     * Finds the value of the node as determined by its inputs
     * @return The value of the node
     */
    public abstract Object run();

    /**
     * Gets the savedValue, ignoring the input Nodes and everything because the program should not be run.
     * @return The value of the Node
     */
    public Object getSavedValue() {return savedValue;}

    /**
     * Replaces the savedValue with a new value.
     * @param value The new value to be saved as savedValue.
     */
    private void setSavedValue(Object value) {this.savedValue = value;}

    /**
     * Gets the input nodes as an ArrayList
     * @return inputs
     */
    public ArrayList<Node> getInputs() {return inputs;}

    /**
     * Gets the output nodes as an ArrayList
     * @return outputs
     */
    public ArrayList<Node> getOutputs() {return outputs;}

    /**
     * Gets the name of this Node. It should be unique.
     * @return The name of the Node
     */
    public String getName() {return this.name;}

    /**
     * Creates a connection between two Nodes, routing the output of the start to the input of the end.
     * @param start The Node that outputs into the connection
     * @param end The Node that takes input from the connection
     */
    public static void link(Node start, Node end) {
        start.inputs.add(end);
        end.inputs.add(start);
    }

    /**
     * Creates a connection from this Node to the provided destination Node.
     * @param destination Where the output of this node in sent to be inputted into
     */
    public void linkTo(Node destination) {
        link(this, destination);
    }

    @Override
    public String toString() {return "< " + getClass().getName() + " : " + this.name + " >";}

    public Node(String name) {
        this.name = name;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.savedValue = "No saved value";
    }
}
