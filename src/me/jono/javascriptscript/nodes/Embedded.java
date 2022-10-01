package me.jono.javascriptscript.nodes;

import me.jono.javascriptscript.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author jono
 * An Embedded Node has its own little ProgramGraph inside it. The input and output sockets are connected to EmbedInput
 * and EmbedOutput Nodes respectively.
 * You should make sure to set the input and output nodes to match the program in the "program" input socket.
 */
public class Embedded extends Node {
    /**
     * Creates the Node.<br/>
     * The ProgramGraph file creation expects a reference to another file within curly brackets.
     * @param name The unique name of the Node
     */
    public Embedded(String name) {
        super(name);
        newInput("program");
    }

    @Override
    public void update(String inputUpdated, ToDoList toDoList) {
        ProgramGraph programGraph = (ProgramGraph)getInput("program").getValue();
        ArrayList<String> nodesToRunFrom = new ArrayList<>();

        for (InputSocket input : getInputs().values()) {
            if (input.getName().equals("program")) continue;
            programGraph.getNode(input.getName()).getOutput("value").setValue(input.getValue());
            nodesToRunFrom.add(input.getName());
        }

        String[] template = {};
        programGraph.runNode(nodesToRunFrom.toArray(template));

        for (OutputSocket output : getOutputs().values()) {
            output.setValue(programGraph.getNode(output.getName()).getInput("value").getValue());
        }

        sendOutputs(toDoList);
    }

    /**
     * Prints out each of the Outputs to the standard output
     */
    public void printOutputs() {
        Collection<OutputSocket> outputs = getOutputs().values();
        if (outputs.size() > 0) {
            System.out.println("Outputs:");
            for (OutputSocket outputSocket : outputs) {
                System.out.println("\t" + outputSocket.getName() + ": " + outputSocket.getValue());
            }
        }
    }

    /**
     * Takes in inputs via the standard input
     * They're passed in as TextValues
     */
    public void takeInputs() {
        Collection<InputSocket> inputs = getInputs().values();
        if (inputs.size() > 0) {
            System.out.println("Inputs:");
            Scanner input = NodeCreator.INPUT;
            for (InputSocket inputSocket : inputs) {
                if (inputSocket.getName().equals("program")) continue;
                System.out.print("\t" + inputSocket.getName() + ": ");
                inputSocket.setValue(new TextValue(input.nextLine()));
            }
        }
    }

    /**
     * Makes the Sockets that the EmbedInput and EmbedOutputs link to
     */
    public void makeSockets() {
        ProgramGraph programGraph = (ProgramGraph)getInput("program").getValue();
        for (Node node : programGraph.listNodes()) {
            if (node instanceof EmbedInput) {
                newInput(node.getName(), node.getOutput("value").getValue());
            }
            if (node instanceof EmbedOutput) {
                newOutput(node.getName(), node.getInput("value").getValue());
            }
        }
    }

    /**
     * Creates an Embedded Node from a file to run it.
     * The outputs are printed when done, and the inputs are prompted one at a time by standard input.
     * @param args
     */
    public static void main(String[] args) {
        Embedded embedded = new Embedded("main");
        embedded.getInput("program").setValue(new ProgramGraph(new File(args[0])));
        embedded.makeSockets();
        embedded.takeInputs();
        System.out.println("===== Running  Program =====");
        embedded.update("", new ToDoList(ToDoList.Ordering.STACK));
        System.out.println("===== Program Complete =====");
        embedded.printOutputs();
    }

}
