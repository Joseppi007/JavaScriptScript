package me.jono.javascriptscript;

/**
 * @author jono
 * A Socket is used to allow data to flow in and out of {@link Node Nodes}.<br/>
 * {@link InputSocket InputSockets} take data in, and {@link OutputSocket OutputSockets} spit data out.
 */
public abstract class Socket {
    private Node node;

    /**
     * Creates a Socket
     * @param node The Node the Socket is attached to
     */
    public Socket(Node node) {
        this.node = node;
    }

    /**
     * Gets the Node this Socket is attached to
     * @return the Node
     */
    public Node getNode() {return node;}

    /**
     * Changes which Node this Socket is attached to
     * @param node the Node to attach this to
     */
    private void setNode(Node node) {this.node = node;}
}
