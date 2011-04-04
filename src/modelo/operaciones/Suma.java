package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Suma extends OperacionBinaria {

    private static Suma o = new Suma();

    private Suma() {}
    public static Suma getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato1, BigDecimal dato2) {
        return dato1.add(dato2).round(MathContext.DECIMAL64);

    }

}