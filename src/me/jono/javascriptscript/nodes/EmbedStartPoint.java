package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * An EmbedStartPoint is run at the start of an Embedded Node's program. It's like an EmbedInput, but it doesn't ask for
 * any input. This is for any programs that don't take input, but need to run somehow.
 */
public class EmbedStartPoint extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public EmbedStartPoint(String name) {
        super(name);
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
            sendOutputs(toDoList);
    }
}
