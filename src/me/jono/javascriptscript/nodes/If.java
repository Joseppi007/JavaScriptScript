package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Takes in a value and, depending on it's truthiness, either it out through "true" or "false".
 */
public class If extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public If(String name) {
        super(name);
        newInput("value");
        newOutput("true");
        newOutput("false");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value").getValue().isTruthy()) {
            getOutput("true").setValue(getInput("value").getValue());
            getOutput("true").addConnectionsToToDoList(toDoList);
        } else {
            getOutput("false").setValue(getInput("value").getValue());
            getOutput("false").addConnectionsToToDoList(toDoList);
        }
    }
}
