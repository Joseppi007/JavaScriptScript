package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

/**
 * @author jono
 * Selects one of the Values in a MultiValue at the index
 */
public class Select extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public Select(String name) {
        super(name);
        newInput("value");
        newInput("index");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("index").getValue() instanceof NumberValue) {
            int index = ((NumberValue)getInput("index").getValue()).getValue().intValue();
            if (getInput("value").getValue() instanceof MultiValue) {
                getOutput("value").setValue(((MultiValue)getInput("value").getValue()).getValue(index));
            } else {
                if (index == 1) {
                    getOutput("value").setValue(getInput("value").getValue());
                } else {
                    getOutput("value").setValue(new MultiValue());
                }
            }
            if (!hasNullInput()) {
                sendOutputs(toDoList);
            }
        }
    }

}
