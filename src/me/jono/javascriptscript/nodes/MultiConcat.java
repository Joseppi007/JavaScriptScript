package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

/**
 * @author jono
 * Groups together Values into a MultiValue
 */
public class MultiConcat extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public MultiConcat(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        MultiValue multiValue = new MultiValue();
        if (getInput("value0").getValue() instanceof MultiValue) {
            for (Value v : (MultiValue)getInput("value0").getValue()) {
                multiValue.getValue().add(v);
            }
        } else {
            multiValue.getValue().add(getInput("value0").getValue());
        }
        if (getInput("value1").getValue() instanceof MultiValue) {
            for (Value v : (MultiValue)getInput("value1").getValue()) {
                multiValue.getValue().add(v);
            }
        } else {
            multiValue.getValue().add(getInput("value1").getValue());
        }
        getOutput("value").setValue(multiValue);
        sendOutputs(toDoList);
    }

}
