package me.jono.javascriptscript;

import me.jono.javascriptscript.nodes.Add;
import me.jono.javascriptscript.nodes.InputNumber;
import me.jono.javascriptscript.nodes.Print;
import me.jono.javascriptscript.nodes.Subtract;

import java.util.Scanner;

/**
 * @author jono
 * Creates Nodes from Strings for loading .jssppsmm files
 */
public class NodeCreator {
    private static final Scanner input = new Scanner(System.in);
    private NodeCreator() {}
    public static Node makeNode(String[] args) throws ClassNotFoundException {
        switch (args[0]) {
            case ("+"), ("ADD") -> {return new Add(args[1]);}
            case ("-"), ("SUBTRACT") -> {return new Subtract(args[1]);}
            case ("INPUT_NUMBER") -> {return new InputNumber(args[1], input);}
            case ("PRINT") -> {return new Print(args[1]);}
        }
        throw new ClassNotFoundException();
    }
}
