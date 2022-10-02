package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Convert a Value to a Number if possible
 */
public class ToNumber extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public ToNumber(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        Value value = getInput("value").getValue();
        if (value instanceof NumberValue) {
            getOutput("value").setValue(value);
        } else if (value instanceof TextValue) {
            getOutput("value").setValue(new NumberValue(new BigDecimal(value.toString())));
        } else {
            getOutput("value").setValue(new MultiValue());
        }
        sendOutputs(toDoList);
    }
}
