package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.TextValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * Does And on the truthiness of two values
 */
public class And extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public And(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        getOutput("value").setValue(
                (getInput("value0").getValue().isTruthy() && getInput("value1").getValue().isTruthy())?
                        (new NumberValue(BigDecimal.ZERO)):
                        (new NumberValue(BigDecimal.ONE))
        );
        if (!hasNullInput()) {
            sendOutputs(toDoList);
        }
    }

}
