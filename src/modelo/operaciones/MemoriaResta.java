package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class MemoriaResta extends OperacionMemoria {

    private static MemoriaResta o = new MemoriaResta();

    private MemoriaResta() {}
    public static MemoriaResta getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato2) {
        BigDecimal r;
        if (!getEstadoMemoria()) {
            r = dato2.negate();
        } else {
            Resta s = Resta.getOp();
            r = s.realizar(getDato(), dato2);
        }
        setDato(r);
        return r.round(MathContext.DECIMAL64);
    }

}