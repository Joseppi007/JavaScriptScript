package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Groups together Values into a MultiValue
 */
public class Group extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public Group(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        MultiValue multiValue = new MultiValue();
        int numbersPutTogether = 0;
        for (InputSocket input : getInputs().values()) {
            if (input.getValue() instanceof NumberValue) {
                multiValue.getValue().add(input.getValue());
                numbersPutTogether++;
            }
        }
        getOutput("value").setValue(multiValue);
        if (numbersPutTogether == getInputs().size()) {
            sendOutputs(toDoList);
        }
    }

}
