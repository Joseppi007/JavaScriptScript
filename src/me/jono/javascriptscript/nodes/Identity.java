package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * The Identity Node takes in a value and spits it back out.
 * You can use it as a constant.
 * If you make this Node's rectangle have 0 width and 0 height, it helps you organise connections.
 */
public class Identity extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public Identity(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
            getOutput("value").setValue(getInput("value").getValue());
            sendOutputs(toDoList);
    }
}
