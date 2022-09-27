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
            public Object run() {
                int acc = 0;
                for (Node input : getInputs()) {
                    acc += (Integer)input.getValueAndRun();
                }
                return acc;
            }
        };
        Node const0 = new Node("const0"){
            @Override
            public Object run() {
                return 5;
            }
        };
        Node const1 = new Node("const1"){
            @Override
            public Object run() {
                return 3;
            }
        };
        adder.getInputs().add(const0);
        adder.getInputs().add(const1);
        const0.getOutputs().add(adder);
        const1.getOutputs().add(adder);
        System.out.println(adder);
        System.out.println(adder.getValueAndRun());
    }
}
