package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Splits a MultiValue into its last value and the rest of them
 */
public class LastAndRest extends Node {

    /**
     * Creates the Node
     * @param name The unique name of the Node
     */
    public LastAndRest(String name) {
        super(name);
        newInput("value");
        newOutput("last");
        newOutput("rest");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value").getValue() instanceof MultiValue) {
            MultiValue value = (MultiValue)getInput("value").getValue();
            if (value.getValue().size() == 0) {
                getOutput("last").setValue(new MultiValue());
                getOutput("rest").setValue(new MultiValue());
            } else {
                getOutput("last").setValue(value.getValue(value.getValue().size()-1));
                MultiValue rest = new MultiValue();
                for (int i = 1; i < value.getValue().size(); i++) {
                    rest.getValue().add(value.getValue().get(i-1));
                }
                getOutput("rest").setValue(rest);
            }
        } else {
            getOutput("last").setValue(getInput("value").getValue());
            getOutput("rest").setValue(new MultiValue());
        }
        sendOutputs(toDoList);
    }

}
