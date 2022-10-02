package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.TextValue;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Turns TextValues into MultiValues of character TextValues
 */
public class TextToMultiOfCharacters extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public TextToMultiOfCharacters(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        MultiValue mv = new MultiValue();
        String text = getInput("value").getValue().toString();
        for (int i = 0; i < text.length(); i++) {
            mv.getValue().add(new TextValue(""+text.charAt(i)));
        }
        getOutput("value").setValue(mv);
        sendOutputs(toDoList);
    }

}
