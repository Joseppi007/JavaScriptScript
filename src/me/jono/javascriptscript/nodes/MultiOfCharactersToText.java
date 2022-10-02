package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.TextValue;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Turns MultiValues of character TextValues into TextValues
 */
public class MultiOfCharactersToText extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public MultiOfCharactersToText(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        StringBuilder text = new StringBuilder();
        MultiValue mv = (MultiValue) getInput("value").getValue();
        for (int i = 0; i < mv.getValue().size(); i++) {
            text.append(mv.getValue(i).toString());
        }
        getOutput("value").setValue(new TextValue(text.toString()));
        sendOutputs(toDoList);
    }

}
