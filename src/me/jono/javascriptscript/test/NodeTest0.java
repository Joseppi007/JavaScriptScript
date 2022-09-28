package me.jono.javascriptscript.test;
import me.jono.javascriptscript.*;

/**
 * @author jono
 * This will test some stuff from the Node class.
 * It's not all to well documented, but it exists.
 */
public class NodeTest0 {
    /**
     * This is a debug method to test things.
     * It tests Node stuff specifically.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        Node adder = new Node("adder"){
            @Override
            public Value run() {
                System.out.println(getInputs());
                System.out.println(getProperties());
                int acc = 0;
                for (Node input : getInputs()) {
                    acc += (int)((IntValue)input.getValueAndRun()).getValue();
                }
                return new IntValue(acc);
            }
        };
        Node const0 = new Node("const0"){
            @Override
            public Value run() {
                return new IntValue(5);
            }
        };
        const0.getOutputSockets().set("value", new IntValue(5));
        Node const1 = new Node("const1"){
            @Override
            public Value run() {
                return new IntValue(3);
            }
        };
        const1.getOutputSockets().set("value", new IntValue(3));
        const0.linkTo("value", "value", adder);
        const1.linkTo("value", "value", adder);
        System.out.println(adder);
        System.out.println(adder.getValueAndRun());
    }
}
