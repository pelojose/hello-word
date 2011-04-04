package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Tangente extends OperacionUnaria {

    private static Tangente o = new Tangente();

    private Tangente() {}
    public static Tangente getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        double d = Math.tan(Math.toRadians(dato.doubleValue()));
        BigDecimal dd = new BigDecimal( d  );
        return dd.round(MathContext.DECIMAL64 );
    }

}