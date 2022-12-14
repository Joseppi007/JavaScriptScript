package me.jono.javascriptscript;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A MultiValue is a bunch
 */
public class MultiValue extends Value implements Iterable<Value> {

    /**
     * Creates a new MultiValue
     * @param value the value of the value
     */
    public MultiValue(ArrayList<Value> value) {
        super(value);
    }

    /**
     * Creates an empty MultiValue
     */
    public MultiValue() {super(new ArrayList<Value>());}

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
                assert(hasNext());
                return getValue().get(index++);
            }
        };
    }

    @Override
    public ArrayList<Value> getValue() {return (ArrayList<Value>)super.getValue();}

    @Override
    public boolean isTruthy() {
        return getValue().size() > 0;
    }

    /**
     * Gets the value at the index<br/>
     * An empty MultiValue if there isn't anything to return
     * @param index the index
     * @return the value
     */
    public Value getValue(int index) {
        if (index >= 0 && index < getValue().size()) {
            return getValue().get(index);
        }
        return new MultiValue();
    }

    @Override
    public boolean isNull() {
        return getValue().size() == 0;
    }

    @Override
    public String toString() {return ValueCreator.valueToString(this);}
}
