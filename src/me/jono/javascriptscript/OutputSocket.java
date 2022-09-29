package me.jono.javascriptscript;

public class OutputSocket extends Socket {
    /**
     * Creates an OutputSocket
     * @param node The Node the Socket is attached to
     */
    public OutputSocket(Node node, String name) {
        super(node, name);
    }
}
