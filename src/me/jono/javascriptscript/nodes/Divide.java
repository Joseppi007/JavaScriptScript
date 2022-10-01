package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Divides its {@link InputSocket InputSockets'} {@link Value Values}
 */
public class Divide extends Node {

    /**
     * Creates a Divide Node
     * @param name The unique name of the Node
     */
    public Divide(String name) {
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
                    .divide((BigDecimal)getInput("value1").getValue().getValue(), NumberValue.getCalculationContext());
            getOutput("value").setValue(new NumberValue(num));
            sendOutputs(toDoList);
        }
    }

}
