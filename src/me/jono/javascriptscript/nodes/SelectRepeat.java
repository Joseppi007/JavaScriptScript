package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

/**
 * @author jono
 * Selects one of the Values in a MultiValue at the index, looping back to the start once the index goes too high.
 * Also works with characters in TextValues.
 */
public class SelectRepeat extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public SelectRepeat(String name) {
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
                MultiValue multiValue = (MultiValue)getInput("value").getValue();

                // Left and Right rap-around
                index = index % multiValue.getValue().size();
                index = index + multiValue.getValue().size();
                index = index % multiValue.getValue().size();

                getOutput("value").setValue(multiValue.getValue(index));
            } else if (getInput("value").getValue() instanceof TextValue) {
                try {
                    String text = ((TextValue) getInput("value").getValue()).getValue();

                    // Left and Right rap-around
                    index = index % text.length();
                    index = index + text.length();
                    index = index % text.length();

                    getOutput("value").setValue(new TextValue(text.substring(index, index+1)));
                } catch (IndexOutOfBoundsException ioobe) {
                    getOutput("value").setValue(new MultiValue());
                }
            } else {
                getOutput("value").setValue(getInput("value").getValue());
            }
            if (!hasNullInput()) {
                sendOutputs(toDoList);
            }
        }
    }

}
