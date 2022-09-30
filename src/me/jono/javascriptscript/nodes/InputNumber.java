package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author jono
 * Inputs a NumberValue
 */
public class InputNumber extends Node {

    private final Scanner input;

    /**
     * Creates an InputNumber Node
     * @param name The unique name of the Node
     */
    public InputNumber(String name, Scanner input) {
        super(name);
        newOutput("value");
        this.input = input;
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        String line = input.nextLine();
        BigDecimal bigDecimal = new BigDecimal(line);
        Value value = new NumberValue(bigDecimal);
        getOutput("value").setValue(value);
        sendOutputs(toDoList);
    }

}
