package me.jono.javascriptscript;

import java.math.BigDecimal;

/**
 * @author jono
 * A NumerValue is a BigDecimal as a Value
 */
public class NumberValue extends Value {

    /**
     * Creates a new NumberValue
     * @param value the value of the value
     */
    public NumberValue(BigDecimal value) {
        super(value);
    }

    @Override
    public BigDecimal getValue() {return (BigDecimal)super.getValue();}

}
