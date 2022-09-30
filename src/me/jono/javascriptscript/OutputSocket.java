package me.jono.javascriptscript;

import java.util.ArrayList;
import java.util.Comparator;

public class OutputSocket extends Socket {
    private final ArrayList<Connection> outgoingConnections;

    /**
     * Creates an OutputSocket
     * @param node The Node the Socket is attached to
     * @param name The name of the Socket
     */
    public OutputSocket(Node node, String name) {
        super(node, name);
        outgoingConnections = new ArrayList<>();
    }

    /**
     * Creates an OutputSocket
     * @param node The Node the Socket is attached to
     * @param name The name of the Socket
     * @param value The initial or default value
     */
    public OutputSocket(Node node, String name, Value value) {
        super(node, name, value);
        outgoingConnections = new ArrayList<>();
    }

    /**
     * Returns the outgoingConnections
     * @return the outgoingConnections
     */
    public ArrayList<Connection> getOutgoingConnections() {return outgoingConnections;}

    /**
     * Appends the connections to the provided ToDoList
     * @param toDoList the ToDoList to add the connections to
     * @return the success status
     */
    public boolean addConnectionsToToDoList(ToDoList toDoList) {
        ArrayList<Connection> sortedForHighPriorityThenOriginalOrder = new ArrayList<>(outgoingConnections);
        sortedForHighPriorityThenOriginalOrder.sort(new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                if (o1.getPriority() < o2.getPriority()) return -1;
                if (o1.getPriority() == o2.getPriority()) return 0;
                return 1;
            }

        });


        // DEBUG here because I dunno if this is right or not. If this is broken, it's probably backwards.
        // System.out.println(sortedForHighPriorityThenOriginalOrder);



        if (toDoList.getOrdering() == ToDoList.Ordering.STACK) {
            toDoList.getConnections().addAll(sortedForHighPriorityThenOriginalOrder);
        } else if (toDoList.getOrdering() == ToDoList.Ordering.QUE) {
            // Add in reversed order
            for (int index = sortedForHighPriorityThenOriginalOrder.size()-1; index >= 0; index--) {
                toDoList.getConnections().add(sortedForHighPriorityThenOriginalOrder.get(index));
            }
        } else {
            return false;
        }
        return true;
    }
}
