package me.jono.javascriptscript;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author jono
 * A ValueGroup is an ordered collection of Values
 * It can be iterated through
 */
public class ValueGroup extends Value implements Iterable<Value> {
    /**
     * Creates a new ValueGroup
     * @param value The values
     */
    public ValueGroup(ArrayList<Value> value) {
        super(value);
    }

    /**
     * Creates a new ValueGroup
     */
    public ValueGroup() {
        super(new ArrayList<Value>());
    }

    /**
     * Creates a new ValueGroup
     * @param values The values
     */
    public ValueGroup(Value... values) {
        super(new ArrayList<Value>(Arrays.asList(values)));
    }

    @Override
    public Iterator<Value> iterator() {
        return new Iterator<Value>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < getValue().size();
            }

            @Override
            public Value next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return getValue().get(index++);
            }
        };
    }

    /**
     * Gets the value of the ValueGroup
     * @return An ArrayList of the Values in this ValueGroup
     */
    @Override
    public ArrayList<Value> getValue() {
        if (super.getValue() instanceof ArrayList) {
            return (ArrayList<Value>) super.getValue();
        }
        return new ArrayList<>(); // This shouldn't happen, but it's here just to be safe.
    }

    /**
     * Makes a string representation of the ValueGroup
     * @return A string representation of the ValueGroup
     */
    @Override
    public String toString() {
        if (getValue().size() == 0) {
            return "()";
        }
        StringBuilder a = new StringBuilder("(");
        for (Value value : this) {
            a.append(value).append(", ");
        }
        a.delete(a.length()-2, a.length());
        a.append(")");
        return a.toString();
    }

    /**
     * Add something to the ValueGroup
     * @param value The thing to be added
     */
    public void add(Value value) {
        getValue().add(value);
    }
}
