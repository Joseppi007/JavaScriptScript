package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * Finds the length of MultiValues
 * If not a MultiValue, a One is returned
 */
public class MultiLength extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public MultiLength(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value").getValue() instanceof MultiValue) {
            getOutput("value").setValue(
                    new NumberValue(new BigDecimal(((MultiValue) getInput("value").getValue()).getValue().size()))
            );
        } else {
            getOutput("value").setValue(new NumberValue(BigDecimal.ONE));
        }
        sendOutputs(toDoList);
    }

}
