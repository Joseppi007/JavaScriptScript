package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * Inverts the truthiness of a value
 */
public class Not extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public Not(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        getOutput("value").setValue(
                (getInput("value").getValue().isTruthy())?
                        (new NumberValue(BigDecimal.ONE)):
                        (new NumberValue(BigDecimal.ZERO))
        );
        if (!hasNullInput()) {
            sendOutputs(toDoList);
        }
    }

}
