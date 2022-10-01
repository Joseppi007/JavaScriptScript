package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Multiplies together its {@link InputSocket InputSockets'} {@link Value Values}
 */
public class Multiply extends Node {

    /**
     * Creates a Multiply Node
     * @param name The unique name of the Node
     */
    public Multiply(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        BigDecimal a = BigDecimal.ONE;
        int numbersMultipliedTogether = 0;
        for (InputSocket input : getInputs().values()) {
            if (input.getValue() instanceof NumberValue) {
                a = a.multiply((BigDecimal)input.getValue().getValue());
                numbersMultipliedTogether++;
            }
        }
        getOutput("value").setValue(new NumberValue(a));
        if (numbersMultipliedTogether == getInputs().size()) {
            sendOutputs(toDoList);
        }
    }

}
