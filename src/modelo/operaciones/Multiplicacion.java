package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Multiplicacion extends OperacionBinaria {

    private static Multiplicacion o = new Multiplicacion();

    private Multiplicacion() {}
    public static Multiplicacion getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato1, BigDecimal dato2) {
        return dato1.multiply(dato2).round(MathContext.DECIMAL64);
    }

}