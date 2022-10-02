package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * A Buffer takes in a value and doesn't output it until it recieves a message in it's "ready" input socket
 */
public class Buffer extends Node {
    private boolean isBuffering;

    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public Buffer(String name) {
        super(name);
        newInput("value");
        newInput("ready");
        newOutput("value");
        isBuffering = false;
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (inputUpdated.equals("value")) isBuffering = true;
        getOutput("value").setValue(getInput("value").getValue());
        if (inputUpdated.equals("ready") && isBuffering) {
            sendOutputs(toDoList);
            isBuffering = false;
        }
    }
}
