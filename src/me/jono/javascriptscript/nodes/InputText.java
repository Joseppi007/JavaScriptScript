package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author jono
 * Inputs a TextValue
 */
public class InputText extends Node {

    private final Scanner input;

    /**
     * Creates an InputText Node
     * @param name The unique name of the Node
     */
    public InputText(String name, Scanner input) {
        super(name);
        newOutput("value");
        this.input = input;
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        String line = input.nextLine();
        Value value = new TextValue(line);
        getOutput("value").setValue(value);
        sendOutputs(toDoList);
    }

}
