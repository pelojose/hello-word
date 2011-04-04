package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Cuadrado extends OperacionUnaria {

    private static Cuadrado o = new Cuadrado();

    private Cuadrado() {}
    public static Cuadrado getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        BigDecimal dd = dato.pow(2);
        return dd.round(MathContext.DECIMAL64);
    }

}