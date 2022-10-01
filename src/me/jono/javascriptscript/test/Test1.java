package me.jono.javascriptscript.test;

import me.jono.javascriptscript.*;
import me.jono.javascriptscript.nodes.Add;
import me.jono.javascriptscript.nodes.InputNumber;
import me.jono.javascriptscript.nodes.Print;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jono
 * Just a simple little test
 */
public class Test1 {
    public static void main(String[] args) {
        ProgramGraph programGraph = new ProgramGraph(new File(args[0]));
        ArrayList<String> nodesToRun = new ArrayList<>();
        for (Node node : programGraph.listNodes()) {
            if (node.getName().length() > 4 && node.getName().substring(0,5).equals("start")) {
                nodesToRun.add(node.getName());
            }
        }
        String[] formatter = {};
        System.out.println("===Running File===\n");
        programGraph.runNode(nodesToRun.toArray(formatter));
        try {
            programGraph.writeToFile(new File(args[1]));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
