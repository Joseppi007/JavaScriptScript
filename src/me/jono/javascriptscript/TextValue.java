package me.jono.javascriptscript;

/**
 * @author jono
 * A TextValue is a Value wrapper for a String.
 */
public class TextValue extends Value {

    /**
     * Creates a new TextValue
     * @param value the value of the value
     */
    public TextValue(String value) {
        super(value);
    }

    @Override
    public String getValue() {return (String)super.getValue();}

}
