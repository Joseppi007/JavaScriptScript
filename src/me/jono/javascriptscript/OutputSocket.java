package me.jono.javascriptscript;

public class OutputSocket extends Socket {
    /**
     * Creates an OutputSocket
     * @param node The Node the Socket is attached to
     * @param name The name of the Socket
     */
    public OutputSocket(Node node, String name) {
        super(node, name);
    }

    /**
     * Creates an OutputSocket
     * @param node The Node the Socket is attached to
     * @param name The name of the Socket
     * @param value The initial or default value
     */
    public OutputSocket(Node node, String name, Value value) {
        super(node, name, value);
    }
}
