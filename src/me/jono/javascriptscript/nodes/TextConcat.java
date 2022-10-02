package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

/**
 * @author jono
 * Concatinates the text of the input values
 */
public class TextConcat extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public TextConcat(String name) {
        super(name);
        newInput("value0");
        newInput("value1");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        String r = "";
        r += getInput("value0").getValue();
        r += getInput("value1").getValue();
        getOutput("value").setValue(new TextValue(r));
        if (!hasNullInput()) {
            sendOutputs(toDoList);
        }
    }

}
