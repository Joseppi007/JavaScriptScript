package me.jono.javascriptscript;

import java.io.File;
import java.util.HashMap;

/**
 * @author jono
 * A ProgramGraph keeps track of all the Nodes in your program.
 * It has functions to save and load code from .jssppsmm files.
 */
public class ProgramGraph {
    private HashMap<String, Node> nodes;

    /**
     * Creates an empty program
     */
    public ProgramGraph() {
        nodes = new HashMap<>();
    }

    /**
     * Loads a program from a file
     * <b><i><u>Yet to be implemented</u></i></b>
     * @param srcCode The source code
     */
    public ProgramGraph(File srcCode) {
        nodes = new HashMap<>();
    }

    /**
     * Adds a Node to the ProgramGraph.
     * The Node must have a unique name.
     * Use nameNode to replace a Node's name with a unique name.
     * @param node the Node to add
     */
    public void addNode(Node node) {
        assert(nodes.containsKey(node.getName()));
    }

    /**
     * Replaces the name of a node with a randomly generated String
     * @param node the Node to rename
     */
    public void nameNode(Node node) {

    }

    /**
     * Makes a pseudo-random String of the given length
     * @param length The length to make the String
     * @return A pseudo-random String
     */
    public static String randomString(int length) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < length; i++) {
            r.append(Character.toString(65 + (int) Math.floor(26.0 * Math.random())));
        }
        return r.toString();
    }
}
