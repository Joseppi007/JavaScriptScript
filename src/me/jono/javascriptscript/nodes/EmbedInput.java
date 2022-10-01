package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.Node;
import me.jono.javascriptscript.ToDoList;

/**
 * @author jono
 * The EmbedInput Node is used by Embedded Nodes as a way of pulling in its inputs. Each of the Embedded Node's input
 * sockets is represented by a EmbedInput with the same name. It will output whatever is inputted into the Embedded Node
 */
public class EmbedInput extends Node {
    /**
     * Creates the Node
     * @param name the name of the Node
     */
    public EmbedInput(String name) {
        super(name);
        newOutput("value");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
            //getOutput("value").setValue();
            sendOutputs(toDoList);
    }
}
