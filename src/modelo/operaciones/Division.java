package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Division extends OperacionBinaria {

    private static Division o = new Division();

    private Division() {}
    public static Division getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato1, BigDecimal dato2) {
        return dato1.divide(dato2, 5, RoundingMode.HALF_UP).round(MathContext.DECIMAL64);
    }

}