package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class Resta extends OperacionBinaria {

    private static Resta o = new Resta();

    private Resta() {}
    public static Resta getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato1, BigDecimal dato2) {
        return dato1.subtract(dato2).round(MathContext.DECIMAL64);
    }

}