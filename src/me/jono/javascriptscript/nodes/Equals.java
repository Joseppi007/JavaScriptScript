package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Tests for equality
 */
public class Equals extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public Equals(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        getOutput("value").setValue(
                (getInput("value0").getValue().equals(getInput("value1").getValue())?
                        (new NumberValue(BigDecimal.ONE)):
                        (new NumberValue(BigDecimal.ZERO))
        ));
        if (!hasNullInput()) {
            sendOutputs(toDoList);
        }
    }

}
