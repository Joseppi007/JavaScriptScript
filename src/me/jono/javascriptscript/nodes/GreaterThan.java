package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * Tests for greater-than-ness
 */
public class GreaterThan extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public GreaterThan(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value0").getValue() instanceof NumberValue &&
                getInput("value1").getValue() instanceof NumberValue) {
            BigDecimal v0 = ((NumberValue)getInput("value0").getValue()).getValue();
            BigDecimal v1 = ((NumberValue)getInput("value1").getValue()).getValue();
            getOutput("value").setValue(new NumberValue(new BigDecimal(v0.compareTo(v1))));
            sendOutputs(toDoList);
        }
    }

}
