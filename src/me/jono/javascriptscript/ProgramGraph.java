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
        assert(!nodes.containsKey(node.getName()));
        nodes.put(node.getName(), node);
    }

    /**
     * Replaces the name of a node with a randomly generated String
     * Do not use this on a node already in the ProgramGraph. It will break the HashMap.
     * @param node the Node to rename
     */
    public void nameNode(Node node) {
        String newName = randomString(10);
        while (nodes.containsKey(newName)) {
            newName = randomString(10);
        }
        node.setName(newName);
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

    /**
     * Runs the program from left to right (input to output) starting from a single node's output, and going until the
     * outputs of outputs of outputs don't have any outputs of their own.
     * @param name The name of the node to start from
     * @param outputSocketName The output socket to use
     */
    public void runFromNode(String name, String outputSocketName) {
        for (Node node : nodes.values()) {
            node.setAlreadyRun(false);
        }
        Node node = nodes.get(name);
        runFromNodeHelper(node);
    }

    /**
     * Runs the program from left to right (input to output) starting from a single node's output, and going until the
     * outputs of outputs of outputs don't have any outputs of their own.
     * @param node The node to be run
     */
    private void runFromNodeHelper(Node node) {
        node.runUnlessAlreadyRun();
        for (Node otherNode : node.getOutputNodes()) {
            runFromNodeHelper(otherNode);
        }
    }
}
