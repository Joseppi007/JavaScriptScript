package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Adds together its {@link InputSocket InputSockets'} {@link Value Values}
 */
public class Add extends Node {

    /**
     * Creates an Add Node
     * @param name The unique name of the Node
     */
    public Add(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        BigDecimal a = BigDecimal.ZERO;
        int numbersAddedTogether = 0;
        for (InputSocket input : getInputs().values()) {
            if (input.getValue() instanceof NumberValue) {
                a = a.add((BigDecimal)input.getValue().getValue());
                numbersAddedTogether++;
            }
        }
        getOutput("value").setValue(new NumberValue(a));
        if (numbersAddedTogether == getInputs().size()) {
            sendOutputs(toDoList);
        }
    }

}
