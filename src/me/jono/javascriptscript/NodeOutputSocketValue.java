package me.jono.javascriptscript;

public class NodeOutputSocketValue extends Value {
    public final Node node;
    public final String socket;

    public NodeOutputSocketValue(Node node, String socket) {
        super(null);
        this.node = node;
        this.socket = socket;
    }

    @Override
    public Object getValue() {
        //return node.
    }

}
