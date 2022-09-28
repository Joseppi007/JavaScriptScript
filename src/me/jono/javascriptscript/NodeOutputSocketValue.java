package me.jono.javascriptscript;

import java.util.ArrayList;

/**
 * @author jono
 * A NodeOutputSocketValue is an output socket of NodeOutputSockets.
 * It contains some information to make
 */
public class NodeOutputSocketValue extends Value {
    private final Node node;
    private final String socket;
    private Value value;
    private final ArrayList<Node> nodesOutputTo;

    /**
     * Creates a NodeOutputSocketValue
     * @param node
     * @param socket
     * @param value
     */
    public NodeOutputSocketValue(Node node, String socket, Value value) {
        super(null);
        this.node = node;
        this.socket = socket;
        this.value = value;
        this.nodesOutputTo = new ArrayList<>();
    }

    @Override
    public Object getValue() {
        return value;
    }

    /**
     * Replaces the value with a new value
     * @param value The new value
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Gets the node
     * @return the node
     */
    public Node getNode() {return node;}

    /**
     * Gets the socket name
     * @return the socket name
     */
    public String getSocket() {return socket;}

    /**
     * Gets the nodesOutputTo
     * @return the nodesOutputTo
     */
    public ArrayList<Node> getNodesOutputTo() {return nodesOutputTo;}

}
