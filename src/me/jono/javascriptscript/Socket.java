package me.jono.javascriptscript;

/**
 * @author jono
 * A Socket is used to allow data to flow in and out of {@link Node Nodes}.<br/>
 * {@link InputSocket InputSockets} take data in, and {@link OutputSocket OutputSockets} spit data out.
 */
public abstract class Socket {
    private final Node node;
    private final String name;
    private Value value;

    /**
     * Creates a Socket
     * @param node The Node the Socket is attached to
     * @param initialValue A Value to start out with as a default
     */
    public Socket(Node node, String name, Value initialValue) {
        this.node = node;
        this.name = name;
        this.value = initialValue;
    }

    /**
     * Creates a Socket with an empty MultiValue as an initial value
     * @param node The Node the Socket is attached to
     */
    public Socket(Node node, String name) {
        this(node, name, new MultiValue());
    }

    /**
     * Gets the Node this Socket is attached to
     * @return the Node
     */
    public Node getNode() {return node;}

    /**
     * Gets the name of the socket
     * @return the name of the socket
     */
    public String getName() {return name;}

    /**
     * Gets the value stored in the socket
     * @return the value
     */
    public Value getValue() {return value;}

    /**
     * Changes the value stored in the socket
     * @param value the value to put in the socket
     */
    public void setValue(Value value) {this.value = value;}

    @Override
    public String toString() {
        return getName()+":"+getValue();
    }
}
