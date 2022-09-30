package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Subtracts its {@link InputSocket InputSockets'} {@link Value Values}
 */
public class Subtract extends Node {

    /**
     * Creates a Subtract Node
     * @param name The unique name of the Node
     */
    public Subtract(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value0").getValue() instanceof NumberValue &&
            getInput("value1").getValue() instanceof NumberValue) {
            BigDecimal num = ((BigDecimal)getInput("value0").getValue().getValue())
                    .subtract((BigDecimal)getInput("value1").getValue().getValue());
            getOutput("value").setValue(new NumberValue(num));
            sendOutputs(toDoList);
        }

        BigDecimal a = ((BigDecimal)(getInput("value0").getValue().getValue()))
                        .multiply(new BigDecimal("2.0"));
        int numbersAddedTogether = 0;
        for (InputSocket input : getInputs().values()) {
            if (input.getValue() instanceof NumberValue) {
                a = a.subtract((BigDecimal)input.getValue().getValue());
                numbersAddedTogether++;
            }
        }
        getOutput("value").setValue(new NumberValue(a));
        if (numbersAddedTogether == getInputs().size()) {
            sendOutputs(toDoList);
        }
    }

}
