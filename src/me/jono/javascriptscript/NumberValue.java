package me.jono.javascriptscript;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author jono
 * A NumerValue is a BigDecimal as a Value
 */
public class NumberValue extends Value {

    private static int digits = 200;
    private static int extraDigits = 20;
    private static MathContext calculationContext = new MathContext(digits + extraDigits, RoundingMode.HALF_UP);
    private static MathContext displayContext = new MathContext(digits, RoundingMode.HALF_UP);

    /**
     * Gets the number of digits NumberValues have
     * @return the number of digits NumberValues have
     */
    public static int getDigits() {return displayContext.getPrecision();}

    /**
     * Gets the number of extra digits used behind the scenes to keep errors hidden
     * @return the number of extra digits
     */
    public static int getExtraDigits() {return calculationContext.getPrecision()-displayContext.getPrecision();}

    /**
     * Gets the calculation MathContext
     * @return the calculation MathContext
     */
    public MathContext getCalculationContext() {return calculationContext;}

    /**
     * Gets the display MathContext
     * @return
     */
    public MathContext getDisplayContext() {return displayContext;}

    public static void updateContexts(int digits, int extraDigits) {
        NumberValue.digits = digits;
        NumberValue.extraDigits = extraDigits;
        NumberValue.calculationContext = new MathContext(digits + extraDigits, RoundingMode.HALF_UP);
        NumberValue.displayContext = new MathContext(digits, RoundingMode.HALF_UP);
    }

    /**
     * Creates a new NumberValue
     * @param value the value of the value
     */
    public NumberValue(BigDecimal value) {
        super(value);
    }

    @Override
    public BigDecimal getValue() {return ((BigDecimal)super.getValue()).divide(BigDecimal.ONE, displayContext);}

}
