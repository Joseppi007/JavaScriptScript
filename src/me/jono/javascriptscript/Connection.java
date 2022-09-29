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
     * Creates a new Connection
     * @param outputSocket Where the data comes from
     * @param inputSocket Where the data goes
     */
    public Connection(OutputSocket outputSocket, InputSocket inputSocket) {
        this.outputSocket = outputSocket;
        this.inputSocket = inputSocket;
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


}
