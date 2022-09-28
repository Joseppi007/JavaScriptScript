package me.jono.javascriptscript.test;

import me.jono.javascriptscript.Value;

public class IntValue extends Value {

    public IntValue(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return "" + (int)getValue();
    }

}
