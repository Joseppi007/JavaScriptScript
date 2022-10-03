package me.jono.javascriptscript;

/**
 * @author jono
 * A Value is just a super class for the value types that can be passed from Node to Node.
 */
public abstract class Value {
    private Object value;

    /**
     * Creates a new Value
     * @param value the value of the value
     */
    public Value(Object value) {
        this.value = value;
    }

    /**
     * Gets the value as an Object
     * @return the value
     */
    public Object getValue() {return value;}

    @Override
    public boolean equals(Object other) {
        if (other instanceof Value) {
            return ((Value) other).getValue().equals(getValue());
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * Gets weather or not the value is truthy
     * @return the truthiness
     */
    public abstract boolean isTruthy();

    /**
     * Returns true if this is an empty MultiValue, and false otherwise
     * @return is this the empty MultiValue?
     */
    public boolean isNull() {
        return false;
    }
}
