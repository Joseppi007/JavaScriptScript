package me.jono.javascriptscript;

import java.util.HashMap;

/**
 * @author jono
 * NodeOutputSockets is a HashMap with String keys and {@link NodeOutputSocketValue NodeOutputSocketValue} values.
 * Its job is to handle all the output sockets of a Node.
 * {@link NodeOutputSocketValue NodeOutputSocketValues} contain some redundant information to make navigating the
 * program graph easier.
 */
public class NodeOutputSockets {
    private HashMap<String, NodeOutputSocketValue> values;
    private Node node;

    /**
     * Creates NodeOutputSockets without any sockets
     */
    public NodeOutputSockets(Node node) {
        this.values = new HashMap<>();
        this.node = node;
    }

    /**
     * Creates NodeOutputSockets with the sockets provided
     * @param sockets The sockets to use
     */
    public NodeOutputSockets(Node node, HashMap<String, NodeOutputSocketValue> sockets) {
        this.values = sockets;
        this.node = node;
    }

    /**
     * Gets the values
     * @return the values
     */
    public HashMap<String, NodeOutputSocketValue> getValues() {return values;}

    /**
     * Sets the socket to the value
     * Creates a new socket if it does not exist
     * @param key The key of the parameter
     * @param value The value to place in the parameter
     */
    public void set(String key, Value value) {
        if (values.containsKey(key)) {
            values.get(key).setValue(value);
        } else {
            values.put(key, new NodeOutputSocketValue(node, key, value));
        }
    }

    /**
     * Gets the NodeOutputSocketValue with the key
     * @param key The key to use to find the NodeOutputSocketValue
     * @return The NodeOutputSocketValue
     */
    public NodeOutputSocketValue get(String key) {
        //if (values.containsKey(key)) {
            return values.get(key);
        //}
        //return Null;
    }
}
