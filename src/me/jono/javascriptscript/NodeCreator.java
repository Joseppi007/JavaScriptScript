package me.jono.javascriptscript;

import me.jono.javascriptscript.nodes.Add;
import me.jono.javascriptscript.nodes.InputNumber;
import me.jono.javascriptscript.nodes.Print;
import me.jono.javascriptscript.nodes.Subtract;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.IntSummaryStatistics;
import java.util.Scanner;

/**
 * @author jono
 * Creates Nodes from Strings for loading .jssppsmm files
 */
public class NodeCreator {
    private static final Scanner input = new Scanner(System.in);
    private NodeCreator() {}

    /**
     * Makes a Node from a group of Strings.
     * Use {@link FormatTools#separateBySpacesNotInBrackets FormatTools.separateBySpacesNotInBrackets} to make the list.
     * @param args The group of Strings
     * @return A Node of the type and with the name provided in the arguments
     * @throws ClassNotFoundException If a name for a Node is a typo or doesn't exist, an exception is thrown.
     */
    public static Node makeNode(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        /*switch (args[0]) {
            case ("+"), ("Add") -> {return new Add(args[1]);}
            case ("-"), ("Subtract") -> {return new Subtract(args[1]);}
            case ("InputNumber") -> {return new InputNumber(args[1], input);}
            case ("Print") -> {return new Print(args[1]);}
        }
        throw new ClassNotFoundException();*/
        Class c = Class.forName("me.jono.javascriptscript.nodes."+args[0]);
        Constructor<Node>[] constructors = c.getConstructors();
        Constructor<Node> simplest = constructors[0];
        for (int i = 1; i < constructors.length; i++) {
            if (constructors[i].getParameterCount() < simplest.getParameterCount()) simplest = constructors[i];
        }
        Object[] params = simplest.getParameters();
        Object[] p = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            Parameter param = (Parameter)params[i];
            if (param.getType().equals(String.class)) {
                p[i] = args[1];
            }
            if (param.getType().equals(Scanner.class)) {
                p[i] = input;
            }
        }
        Node r = simplest.newInstance(p);
        return r;
    }

    /**
     * Makes a Node into a String for saving a program to a file
     * @param node The Node to Stringify
     * @return A String for saving Nodes for later
     */
    public static String unmakeNode(Node node) {
        /*if (node instanceof Add) {return "ADD "+node.getName();}
        else if (node instanceof Subtract) {return "SUBTRACT "+node.getName();}
        else if (node instanceof InputNumber) {return "INPUT_NUMBER "+node.getName();}
        else if (node instanceof Print) {return "PRINT "+node.getName();}
        else {

        }*/
        return node.getClass().getSimpleName()+" "+node.getName();
    }
}
