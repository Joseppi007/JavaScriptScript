package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Connection;
import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.OutputSocket;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * A NonUpdater takes in a value and spits it out, but this does NOT cause the nodes outputted to to run.
 */
public class NonUpdater extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public NonUpdater(String name) {
        super(name);
        newInput("value");
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        getOutput("value").setValue(getInput("value").getValue());
        for (OutputSocket output : getOutputs().values()) {
            for (Connection connection : output.getOutgoingConnections()) {
                connection.transfer();
            }
        }
    }
}
