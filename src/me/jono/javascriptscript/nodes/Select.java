package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

/**
 * @author jono
 * Selects one of the Values in a MultiValue at the index.
 * Also works with characters in TextValues.
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
            } else if (getInput("value").getValue() instanceof TextValue) {
                try {
                    String text = ((TextValue) getInput("value").getValue()).getValue();
                    getOutput("value").setValue(new TextValue(text.substring(index, index+1)));
                } catch (IndexOutOfBoundsException ioobe) {
                    getOutput("value").setValue(new MultiValue());
                }
            } else {
                if (index == 0) {
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
