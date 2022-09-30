package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;

/**
 * @author jono
 * Prints a value
 */
public class Print extends Node {

    /**
     * Creates a Print Node
     * @param name The unique name of the Node
     */
    public Print(String name) {
        super(name);
        newInput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        System.out.println(getInput("value").getValue().toString());
    }

}
