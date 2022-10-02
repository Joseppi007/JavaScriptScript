package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ToDoList;

import java.math.BigDecimal;

/**
 * @author jono
 * Splits a MultiValue into its first value and the rest of them. Outputs an empty MultiValue on "done" when the value
 * has no rest and doesn't output the lack of rest.
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
        newOutput("done", new NumberValue(BigDecimal.ONE));
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (getInput("value").getValue() instanceof MultiValue) {
            MultiValue value = (MultiValue)getInput("value").getValue();
            if (value.getValue().size() == 0) {
                getOutput("first").setValue(new MultiValue());
                getOutput("rest").setValue(new MultiValue());
                getOutput("first").addConnectionsToToDoList(toDoList);
                getOutput("done").addConnectionsToToDoList(toDoList);
            } else {
                getOutput("first").setValue(value.getValue(0));
                MultiValue rest = new MultiValue();
                for (int i = 1; i < value.getValue().size(); i++) {
                    rest.getValue().add(value.getValue().get(i));
                }
                getOutput("rest").setValue(rest);
                getOutput("first").addConnectionsToToDoList(toDoList);
                getOutput("rest").addConnectionsToToDoList(toDoList);
            }
        } else {
            getOutput("first").setValue(getInput("value").getValue());
            getOutput("rest").setValue(new MultiValue());
            getOutput("first").addConnectionsToToDoList(toDoList);
            getOutput("done").addConnectionsToToDoList(toDoList);
        }
    }

}
