package me.jono.javascriptscript;

import java.util.ArrayList;

/**
 * @author jono
 * A ToDoList keeps track of connections, and which connections transfer data in what order. This is done to avoid
 * recursion to make infinite loops possible.
 */
public class ToDoList {
    private ArrayList<Connection> connections;
    public enum Ordering{
        STACK,
        QUE
    }
    private final Ordering ordering;

    /**
     * Creates a ToDoList
     * @param ordering weather the list should be followed as a Stack or Que
     */
    public ToDoList(Ordering ordering) {
        connections = new ArrayList<>();
        this.ordering = ordering;
    }

    /**
     * Do everything in the list, adding to it as required, and halting once there's nothing to do.
     */
    public void doList() {
        while (connections.size() > 0) {
            Connection connection = next();
            connection.transfer();
            connection.getEnd().update(connection.getInputSocket().getName(), this);
        }
    }

    /**
     * Gets the ordering
     * @return the ordering
     */
    public Ordering getOrdering() {return ordering;}

    /**
     * Gets the connections
     * @return the connections
     */
    public ArrayList<Connection> getConnections() {return connections;}

    /**
     * Adds the connections of the output socket to the ToDoList
     * @param outputSocket The socket to add connections from
     */
    public void addOutputSocketConnections(OutputSocket outputSocket) {
        outputSocket.addConnectionsToToDoList(this);
    }

    /**
     * Gets the next connection and removes it.
     * @return the next connection
     */
    private Connection next() {
        if (ordering == Ordering.QUE) {
            return connections.remove(0);
        }
        return connections.remove(connections.size()-1);
    }
}
