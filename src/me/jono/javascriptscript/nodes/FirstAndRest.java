package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Splits a MultiValue into its first value and the rest of them
 */
public class FirstAndRest extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public FirstAndRest(String name) {
        super(name);
        newInput("value");
        newOutput("first");
        newOutput("rest");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value").getValue() instanceof MultiValue) {
            MultiValue value = (MultiValue)getInput("value").getValue();
            if (value.getValue().size() == 0) {
                getOutput("first").setValue(new MultiValue());
                getOutput("rest").setValue(new MultiValue());
            } else {
                getOutput("first").setValue(value.getValue(0));
                MultiValue rest = new MultiValue();
                for (int i = 1; i < value.getValue().size(); i++) {
                    rest.getValue().add(value.getValue().get(i));
                }
                getOutput("rest").setValue(rest);
            }
        } else {
            getOutput("first").setValue(getInput("value").getValue());
            getOutput("rest").setValue(new MultiValue());
        }
        sendOutputs(toDoList);
    }

}
