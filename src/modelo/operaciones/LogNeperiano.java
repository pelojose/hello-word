package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class LogNeperiano extends OperacionUnaria {

    private static LogNeperiano o = new LogNeperiano();

    private LogNeperiano() {}
    public static LogNeperiano getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        double d = Math.log(dato.doubleValue());
        return (new BigDecimal(d)).round(MathContext.DECIMAL64);
    }

}