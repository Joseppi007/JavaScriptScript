package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * The EmbedOutput Node is used by Embedded Nodes as a way of outputting the results. Each of the Embedded Node's output
 * sockets is represented by a EmbedOutput with the same name. Pass whatever you want to be spit out into this. Note
 * that only the final thing passed to this Node will be spat out of the Embedded Node.
 */
public class EmbedOutput extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public EmbedOutput(String name) {
        super(name);
        newInput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {

    }
}
