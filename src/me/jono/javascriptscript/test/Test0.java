package me.jono.javascriptscript.test;

import me.jono.javascriptscript.ProgramGraph;
import me.jono.javascriptscript.ToDoList;
import me.jono.javascriptscript.nodes.Add;
import me.jono.javascriptscript.nodes.InputNumber;
import me.jono.javascriptscript.nodes.Print;

import java.util.Scanner;

/**
 * @author jono
 * Just a simple little test
 */
public class Test0 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ProgramGraph programGraph = new ProgramGraph(ToDoList.Ordering.STACK);
        programGraph.addNode(new InputNumber("in", input));
        programGraph.addNode(new Add("a"));
        programGraph.addNode(new Print("p"));
        programGraph.makeConnection("in", "value", "value0", "a");
        programGraph.makeConnection("in", "value", "value1", "a");
        programGraph.makeConnection("a", "value", "value", "p");
        programGraph.runNode("in");
    }
}
