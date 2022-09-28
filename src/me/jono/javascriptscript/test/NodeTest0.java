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
            public void run() {
                System.out.println(getInputs());
                System.out.println(getProperties());
                int acc = 0;
                for (Node input : getInputs()) {
                    input.runUnlessAlreadyRun();
                    acc += (int)((IntValue)input.getOutputSockets().get("value").getValue()).getValue();
                }
                getOutputSockets().set("value", new IntValue(acc));
            }
        };
        Node const0 = new Node("const0"){
            @Override
            public void run() {
                getOutputSockets().set("value", new IntValue(9));
            }
        };
        const0.getOutputSockets().set("value", new IntValue(9));
        Node const1 = new Node("const1"){
            @Override
            public void run() {
                getOutputSockets().set("value", new IntValue(10));
            }
        };
        const1.getOutputSockets().set("value", new IntValue(10));
        const0.linkTo("value", "value", adder);
        const1.linkTo("value", "value", adder);
        System.out.println(adder);
        adder.runUnlessAlreadyRun();
        System.out.println(adder.getOutputSockets().get("value"));
    }
}
