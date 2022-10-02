package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * Keeps taking in input, combining everything it gets in a big MultiValue, until the "ready" input is activated. This
 * will cause the accumulation to be output, then reset to an empty MultiValue. It can be emptied using "empty".
 */
public class Accumulator extends Node {
    private MultiValue accumulator;

    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public Accumulator(String name) {
        super(name);
        newInput("value");
        newInput("ready");
        newInput("empty");
        newOutput("value");
        accumulator = new MultiValue();
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        if (inputUpdated.equals("value")) accumulator.getValue().add(getInput("value").getValue());
        if (inputUpdated.equals("empty")) accumulator = new MultiValue();
        getOutput("value").setValue(accumulator);
        if (inputUpdated.equals("ready") && accumulator.getValue().size() > 0) {
            sendOutputs(toDoList);
            accumulator = new MultiValue();
        }
    }
}
