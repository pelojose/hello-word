package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Seno extends OperacionUnaria {

    private static Seno o = new Seno();

    private Seno() {}
    public static Seno getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        double d = Math.sin(Math.toRadians(dato.doubleValue()));
        BigDecimal dd = new BigDecimal( d  );
        return dd.round(MathContext.DECIMAL64 );
    }

}