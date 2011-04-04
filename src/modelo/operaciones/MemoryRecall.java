package modelo.operaciones;
import java.math.BigDecimal;

public class MemoryRecall extends OperacionMemoria {

    private static MemoryRecall o = new MemoryRecall();

    private MemoryRecall() {}
    public static MemoryRecall getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato2) {
        if (!getEstadoMemoria()) return (new BigDecimal(0));
        return getDato();
    }

}