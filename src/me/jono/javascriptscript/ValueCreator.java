package me.jono.javascriptscript;

import org.w3c.dom.Text;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author jono
 * A ValueCreator is like a {@link NodeCreator NodeCreator}, but for {@link Value Values}. It takes in a String and
 * spits out a value.
 * <br/>
 * Here's an example of some strings that should make each value:<br/>
 * NUMBER:3.14159265<br/>
 * TEXT:[Hello, World!]<br/>
 * MULTI:(NUMBER:5,TEXT:Examples,MULTI:())
 */
public class ValueCreator {
    /**
     * This is here to keep you from making instances of this thing.
     */
    private ValueCreator() {}

    /**
     * Makes a Value from a String
     * The String is assumed to be valid
     * @param source the String to use to make the Value
     * @return the created Value
     */
    public static Value makeValue(String source) {
        int splitAt = source.indexOf(':');
        String type = source.substring(0, splitAt);
        String data = source.substring(splitAt+1);
        switch (type) {
            case ("NUMBER"), ("#") -> {
                return new NumberValue(new BigDecimal(data));
            }
            case ("TEXT"), ("\"") -> {
                return new TextValue(FormatTools.customStringUnescape(data.substring(1,data.length()-1)));
            }
            case ("MULTI"), ("()") -> {
                if (data.length() <= 2) return new MultiValue();
                ArrayList<Value> values = new ArrayList<>();
                for (String valueString : FormatTools.separateByCharNotInBrackets(
                                          data.substring(1,data.length()-1), ',')) {
                    values.add(makeValue(valueString));
                }
                return new MultiValue(values);
            }
            case ("PROGRAM"), ("PROGRAM_LINK") -> { // Backwards Compatibility ("PROGRAM")
                return new ProgramGraph(new File(data.substring(1,data.length()-1)));
            }
            case ("PROGRAM_CODE"), ("{}") -> {
                return new ProgramGraph(data.substring(1,data.length()-1));
            }
            default -> {
                return new MultiValue();
            }
        }
    }

    /**
     * Does {@link ValueCreator#makeValue makeValue} in reverse
     * @param value the Value to un-create
     * @return the uncreated Value String
     */
    public static String unmakeValue(Value value) {
        if (value instanceof NumberValue) {
            return "NUMBER:"+value.toString();
        }
        if (value instanceof TextValue) {
            return "TEXT:["+FormatTools.customStringEscape(value.toString())+"]";
        }
        if (value instanceof MultiValue) {
            StringBuilder ret = new StringBuilder("MULTI:(");
            for (Value v : ((MultiValue) value).getValue()) {
                ret.append(unmakeValue(v));
                ret.append(',');
            }
            if (((MultiValue) value).getValue().size() > 0)
                ret = ret.delete(ret.length()-1, ret.length());
            ret.append(")");
            return ret.toString();
        }
        if (value instanceof ProgramGraph) {
            if (((ProgramGraph)value).isFromFile()) {
                return "PROGRAM_LINK:{" + value + "}";
            } else {
                return "PROGRAM_CODE:{\n" + value + "}";
            }
        }
        return "UNKNOWN:?";
    }
}
