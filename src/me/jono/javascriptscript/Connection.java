package me.jono.javascriptscript;

/**
 * @author jono
 * A Connection goes from one {@link Node Node} to another.<br/>
 * More specifically, it plugs into one {@link OutputSocket output} and one {@link InputSocket input} socket. The data
 * moves from output to input.<br/>
 * Once I add in some graphical stuff, I'll work out coloring the connections and letting them go from point to point
 * between the Nodes to keep organised. For now, these are just the important bits of data for the computations.
 */
public class Connection {
    private OutputSocket outputSocket;
    private InputSocket inputSocket;
    /**
     * The priority only compares itself to the priorities of other Connections from the same OutputSocket. This is done
     * because Connections are added to the ToDoList in the order of creation otherwise, and having to delete and
     * recreate Connections would suck.
     */
    private int priority; // The priority only compares to other Connections from the same
    public static final int DEFAULT_PRIORITY = 3;
    private boolean enabled;
    public static final boolean DEFAULT_ENABLEDNESS = true;

    /**
     * Creates a new Connection
     * @param outputSocket Where the data comes from
     * @param inputSocket Where the data goes
     */
    public Connection(OutputSocket outputSocket, InputSocket inputSocket, int priority) {
        this.outputSocket = outputSocket;
        this.inputSocket = inputSocket;
        this.priority = priority;
        this.enabled = DEFAULT_ENABLEDNESS;
    }

    /**
     * Creates a new Connection
     * @param outputSocket Where the data comes from
     * @param inputSocket Where the data goes
     */
    public Connection(OutputSocket outputSocket, InputSocket inputSocket) {
        this(outputSocket, inputSocket, DEFAULT_PRIORITY);
    }

    /**
     * Gets the Node the connection originates from
     * @return the start of the connection
     */
    public Node getStart() {return outputSocket.getNode();}

    /**
     * Gets the OutputSocket that the data comes from
     * @return the outputSocket
     */
    public OutputSocket getOutputSocket() {return outputSocket;}

    /**
     * Gets the InputSocket that the data goes to
     * @return the inputSocket
     */
    public InputSocket getInputSocket() {return inputSocket;}

    /**
     * Gets the Node the connection goes to
     * @return the end of the connection
     */
    public Node getEnd() {return inputSocket.getNode();}

    /**
     * Transfers the value of the inputSocket to the outputSocket.
     */
    public void transfer() {
        outputSocket.setValue(inputSocket.getValue());
        //getEnd().update(inputSocket.getName());
    }

    /**
     * Gets the priority<br/>
     * The higher it is, the sooner it should be handled
     * @return the priority
     */
    public int getPriority() {return priority;}

    /**
     * Updates the priority
     * @param priority the new priority
     */
    public void setPriority(int priority) {this.priority = priority;}

    @Override
    public String toString() {
        return inputSocket.getNode().getName()+"("+inputSocket.getName()+")->"+
                outputSocket.getNode().getName()+"("+outputSocket.getName()+")";
    }
}
