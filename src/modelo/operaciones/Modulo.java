package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Modulo extends OperacionBinaria {

    private static Modulo o = new Modulo();

    private Modulo() {}
    public static Modulo getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato1, BigDecimal dato2) {
        int d1 = dato1.intValue();
        int d2 = dato2.intValue();
        return (new BigDecimal((d1%d2))).round(MathContext.DECIMAL64);
    }

}