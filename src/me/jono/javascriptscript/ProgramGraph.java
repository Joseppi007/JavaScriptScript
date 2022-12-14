package me.jono.javascriptscript;

import javafx.scene.paint.Color;
import me.jono.javascriptscript.gui.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ProgramGraph extends Value {
    public static final int UNIQUE_NAME_LENGTH = 10;

    private ToDoList toDoList;

    private String source;

    /**
     * Gets where the ProgramGraph came from--"unknown source" if not from a file
     * @return a filepath to the file where this program came form--"unknown source" if not from a file
     */
    public String getSource() {return source;}

    public boolean isFromFile() {return !(getSource().equals("unknown source"));}

    /**
     * Creates an empty program
     */
    public ProgramGraph(ToDoList.Ordering ordering) {
        super(new HashMap<String, Node>());
        toDoList = new ToDoList(ordering);
        source = "unknown source";
    }

    public ProgramGraph(File file) {
        this(ToDoList.Ordering.STACK);
        source = file.getPath();
        try {
            Scanner fileScanner = new Scanner(file);
            creation(fileScanner);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public ProgramGraph(String string) {
        this(ToDoList.Ordering.STACK);
        Scanner scanner = new Scanner(string);
        creation(scanner);
    }

    /**
     * Some re-used constructor code
     * @param scanner the scanner
     */
    private void creation(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tokens = {};
            tokens = FormatTools.separateBySpacesNotInBrackets(line).toArray(tokens);
            switch (tokens[0]) {
                case ("ORDERING") -> {
                    toDoList.setOrdering(ToDoList.Ordering.valueOf(tokens[1]));
                }
                case ("NUMBER_LENGTH") -> {
                    NumberValue.updateContexts(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                }
                case ("NODE") -> {
                    try {
                        String[] args = new String[tokens.length-1];
                        System.arraycopy(tokens, 1, args, 0, args.length);
                        addNode(NodeCreator.makeNode(args));
                    }
                    catch(Exception e) {e.printStackTrace();}
                }
                case ("CONNECTION") -> {
                    if (tokens.length > 5) {
                        makeConnection(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]));
                    } else {
                        makeConnection(tokens[1], tokens[2], tokens[3], tokens[4]);
                    }
                }
                case ("INPUT") -> {
                    getNode(tokens[1]).getInput(tokens[2]).setValue(ValueCreator.makeValue(tokens[3]));
                }
                case ("OUTPUT") -> {
                    getNode(tokens[1]).getOutput(tokens[2]).setValue(ValueCreator.makeValue(tokens[3]));
                }
                case ("POSITION") -> {
                    getNode(tokens[1]).setRectangle(new Rectangle(
                            Double.parseDouble(tokens[2]),
                            Double.parseDouble(tokens[3]),
                            (tokens.length>4)?Double.parseDouble(tokens[4]):1.0,
                            (tokens.length>5)?Double.parseDouble(tokens[5]):1.0
                    ));
                }
                case ("COLOR") -> {
                    getNode(tokens[1]).setColor(Color.color(
                            Double.parseDouble(tokens[2]),
                            Double.parseDouble(tokens[3]),
                            Double.parseDouble(tokens[4]),
                            (tokens.length>5)?Double.parseDouble(tokens[5]):1.0
                    ));
                }
            }
        }
    }

    /**
     * Save the program to a file
     * @param file The file to write to
     */
    public void writeToFile(File file) throws IOException {
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(codeToString());
        fileWriter.close();
    }

    /**
     * Returns a string representation of the program, and not the link
     * @return a string representation of the program
     */
    public String codeToString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ORDERING " + ((toDoList.getOrdering().equals(ToDoList.Ordering.QUEUE)) ? ("QUE") : ("STACK")) +
                "\n");
        stringBuilder.append("NUMBER_LENGTH " + NumberValue.getDigits() + " " + NumberValue.getExtraDigits() + "\n\n");

        for (Node node : listNodes()) {
            stringBuilder.append("NODE ");
            stringBuilder.append(NodeCreator.unmakeNode(node));
            stringBuilder.append('\n');
        }

        stringBuilder.append('\n');

        for (Node node : listNodes()) {
            for (InputSocket socket : node.getInputs().values()) {
                stringBuilder.append("INPUT ");
                stringBuilder.append(node.getName());
                stringBuilder.append(" ");
                stringBuilder.append(socket.getName());
                stringBuilder.append(" ");
                stringBuilder.append(ValueCreator.unmakeValue(socket.getValue()));
                stringBuilder.append("\n");
            }
        }
        for (Node node : listNodes()) {
            for (OutputSocket socket : node.getOutputs().values()) {
                stringBuilder.append("OUTPUT ");
                stringBuilder.append(node.getName());
                stringBuilder.append(" ");
                stringBuilder.append(socket.getName());
                stringBuilder.append(" ");
                stringBuilder.append(ValueCreator.unmakeValue(socket.getValue()));
                stringBuilder.append("\n");
            }
        }

        stringBuilder.append('\n');

        for (Node node : listNodes()) {
            for (OutputSocket socket : node.getOutputs().values()) {
                for (Connection connection : socket.getOutgoingConnections()) {
                    stringBuilder.append("CONNECTION ");
                    stringBuilder.append(node.getName());
                    stringBuilder.append(" ");
                    stringBuilder.append(socket.getName());
                    stringBuilder.append(" ");
                    stringBuilder.append(connection.getInputSocket().getName());
                    stringBuilder.append(" ");
                    stringBuilder.append(connection.getInputSocket().getNode().getName());
                    stringBuilder.append(" "+connection.getPriority());
                    stringBuilder.append('\n');
                }
            }
        }

        stringBuilder.append('\n');

        for (Node node : listNodes()) {
            stringBuilder.append("POSITION ");
            stringBuilder.append(node.getName());
            stringBuilder.append(" ");
            stringBuilder.append(node.getRectangle().toString());
            stringBuilder.append('\n');
        }

        stringBuilder.append('\n');

        for (Node node : listNodes()) {
            stringBuilder.append("COLOR ");
            stringBuilder.append(node.getName());
            stringBuilder.append(" "+node.getColor().getRed());
            stringBuilder.append(" "+node.getColor().getGreen());
            stringBuilder.append(" "+node.getColor().getBlue());
            stringBuilder.append(" "+node.getColor().getOpacity());
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    @Override
    public HashMap<String, Node> getValue() {return (HashMap<String, Node>)super.getValue();}

    /**
     * Runs the program from a specific node<br/>
     * If the node does not exist, nothing happens.
     * @param name The name of the Node to run
     */
    public void runNode(String name) {
        //prepareNodeToBeRun(name);
        getValue().get(name).update("", toDoList);
        toDoList.doList();
    }

    /**
     * Puts a Node's connections onto the ToDoList--Should probably delete this because it's useless
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
            //prepareNodeToBeRun(name);
            getValue().get(name).update("", toDoList);
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

    /**
     * Gets the node with the provided name
     * @param name the name
     * @return the node
     */
    public Node getNode(String name) {
        try {
            return getValue().get(name);
        } catch (Exception e) {
            throw e; // Should handle this at some point
        }
    }

    /**
     * Lists all the Nodes
     * @return the Nodes
     */
    public Node[] listNodes() {
        Node[] nodes = {};
        return getValue().values().toArray(nodes);
    }

    @Override
    public boolean isTruthy() {
        return true;
    }

    @Override
    public String toString() {
        if (isFromFile()) {
            return getSource();
        } else {
            return codeToString();
        }
    }

}
