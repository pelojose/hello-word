package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Coseno extends OperacionUnaria {

    private static Coseno o = new Coseno();

    private Coseno() {}
    public static Coseno getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        double d = Math.cos(Math.toRadians(dato.doubleValue()));
        BigDecimal dd = new BigDecimal( d  );
        return dd.round(MathContext.DECIMAL64 );
    }

}