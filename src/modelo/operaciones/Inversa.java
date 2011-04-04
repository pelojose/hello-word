package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Inversa extends OperacionUnaria {

    private static Inversa o = new Inversa();

    private Inversa() {}
    public static Inversa getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        return (new BigDecimal(1)).divide(dato,MathContext.DECIMAL64);
    }

}