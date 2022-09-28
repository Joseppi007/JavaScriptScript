package me.jono.javascriptscript;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author jono
 * You know how nice BigDecimal is? You can get so many decimal places! Sure it's slow, but this is
 * JavaScriptScript++#--.jssppsmm, after all.
 */
public class DecimalValue extends Value {

    public static final int digits = 200;
    public static final MathContext context = new MathContext(digits+20, RoundingMode.HALF_UP);
    private static final MathContext displayContext = new MathContext(digits, RoundingMode.HALF_UP);

    public DecimalValue(BigDecimal value) {
        super(value);
    }

    @Override
    public BigDecimal getValue() {return (BigDecimal)super.getValue();}

    @Override
    public String toString() {
        return getValue().toPlainString();
    }

}
