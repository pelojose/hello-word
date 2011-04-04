package modelo.operaciones;
import java.math.BigDecimal;

public class MemoryClear extends OperacionMemoria {

    private static MemoryClear o = new MemoryClear();

    private MemoryClear() {}
    public static MemoryClear getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato2) {
        setDato(null);
        return new BigDecimal(0);
    }

}