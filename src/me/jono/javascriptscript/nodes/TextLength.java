package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.TextValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * Finds the length of TextValues
 */
public class TextLength extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public TextLength(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        getOutput("value").setValue(
                new NumberValue(new BigDecimal(getInput("value").getValue().toString().length()))
        );
        sendOutputs(toDoList);
    }

}
