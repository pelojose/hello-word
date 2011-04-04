package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class MemoriaSuma extends OperacionMemoria {

    private static MemoriaSuma o = new MemoriaSuma();

    private MemoriaSuma() {}
    public static MemoriaSuma getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato2) {
        if (!getEstadoMemoria()) {
            setDato(dato2);
            return dato2;
        }
        Suma s = Suma.getOp();
        BigDecimal r = s.realizar(getDato(), dato2);
        setDato(r);
        return r.round(MathContext.DECIMAL64);
    }

}