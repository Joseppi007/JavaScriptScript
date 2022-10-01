package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.TextValue;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Converts a Value to a TextValue
 */
public class ToText extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public ToText(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        getOutput("value").setValue(new TextValue(getInput("value").getValue().toString()));
        sendOutputs(toDoList);
    }

}
