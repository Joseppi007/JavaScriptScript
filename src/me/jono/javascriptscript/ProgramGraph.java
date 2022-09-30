package me.jono.javascriptscript;

import java.util.HashMap;

public class ProgramGraph extends Value {
    public static final int UNIQUE_NAME_LENGTH = 10;
    private ToDoList toDoList;
    /**
     * Creates an empty program
     */
    public ProgramGraph(ToDoList.Ordering ordering) {
        super(new HashMap<String, Node>());
        toDoList = new ToDoList(ordering);
    }

    @Override
    public HashMap<String, Node> getValue() {return (HashMap<String, Node>)super.getValue();}

    /**
     * Runs the program from a specific node<br/>
     * If the node does not exist, nothing happens.
     * @param name The name of the Node to run
     */
    public void runNode(String name) {
        prepareNodeToBeRun(name);
        toDoList.doList();
    }

    /**
     * Puts a Node's connections onto the ToDoList
     * @param name the Node to use
     */
    private void prepareNodeToBeRun(String name) {
        if (getValue().containsKey(name)) getValue().get(name).sendOutputs(toDoList);
    }

    /**
     * Runs the program from a group of nodes<br/>
     * If the nodes don't exist, nothing happens for those nodes.
     * @param names The names of the Nodes to run
     */
    public void runNode(String... names) {
        for (String name : names) {
            prepareNodeToBeRun(name);
        }
        toDoList.doList();
    }

    /**
     * Adds a Node to the ProgramGraph<br/>
     * Fails if the name is already taken
     * @param node the Node to be put in the program
     * @return the success status
     */
    public boolean addNode(Node node) {
        if (getValue().containsKey(node.getName())) {
            return false;
        }
        getValue().put(node.getName(), node);
        return true;
    }

    /**
     * Generates a unique name for a Node
     * @return a unique name that can be used for a Node
     */
    public String uniqueName() {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < UNIQUE_NAME_LENGTH; i++) {
            a.append(Character.toString(65
                    + (int) (Math.random() * 26)
                    + ((Math.random() > 0.5) ? (32) : (0))));
        }
        return a.toString();
    }

    /**
     * Adds a Node to the ProgramGraph<br/>
     * If the name is taken, a new one is generated
     * @param node the Node to be put in the program
     * @return the name of the Node
     */
    public String addNodeAndGenerateName(Node node) {
        if (getValue().containsKey(node.getName())) {
            String name = uniqueName();
            node.setName(name);
            getValue().put(name, node);
            return name;
        }
        getValue().put(node.getName(), node);
        return node.getName();
    }

    /**
     * Creates a connection between two Nodes in the program by the specified sockets
     * @param start The name of the Node that the connection starts from
     * @param out The socket to come out of
     * @param in The socket to go into
     * @param end The name of the Node that the connection ends at
     * @param priority The priority of the Connection
     * @return The success status
     */
    public boolean makeConnection(String start, String out, String in, String end, int priority) {
        try {
            OutputSocket o = getValue().get(start).getOutput(out);
            InputSocket i = getValue().get(end).getInput(in);
            o.addConnection(i, priority);
        } catch (Exception e) {return false;}
        return true;
    }

    /**
     * Creates a connection between two Nodes in the program by the specified sockets
     * @param start The name of the Node that the connection starts from
     * @param out The socket to come out of
     * @param in The socket to go into
     * @param end The name of the Node that the connection ends at
     * @return The success status
     */
    public boolean makeConnection(String start, String out, String in, String end) {
        return makeConnection(start, out, in, end, Connection.DEFAULT_PRIORITY);
    }

}
