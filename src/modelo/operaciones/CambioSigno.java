package modelo.operaciones;
import java.math.BigDecimal;

public class CambioSigno extends OperacionUnaria {

    private static CambioSigno o = new CambioSigno();

    private CambioSigno() {}
    public static CambioSigno getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        return dato.negate();
    }

}