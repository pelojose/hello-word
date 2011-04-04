package modelo.operaciones;
import java.math.BigDecimal;

public class MemoryStore extends OperacionMemoria {

    private static MemoryStore o = new MemoryStore();

    private MemoryStore() {}
    public static MemoryStore getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato2) {
        setDato(dato2);
        return dato2;
    }

}