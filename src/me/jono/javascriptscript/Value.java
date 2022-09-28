package me.jono.javascriptscript;

/**
 * @author jono
 * A Value is just a super class for the value types that can be passed from Node to Node.
 */
public abstract class Value {
    private final Object value;
    public Value(Object value) {
        this.value = value;
    }
    public Object getValue() {return value;}
}
