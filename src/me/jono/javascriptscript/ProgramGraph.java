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
     * @param srcCode The source code
     */
    public ProgramGraph(File srcCode) {

    }
}
