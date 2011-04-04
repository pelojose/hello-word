package modelo.operaciones;
import java.math.BigDecimal;
import java.math.MathContext;

public class RaizCuadrada extends OperacionUnaria {

    private static RaizCuadrada o = new RaizCuadrada();

    private RaizCuadrada() {}
    public static RaizCuadrada getOp() {
        return o;
    }
    public BigDecimal realizar(BigDecimal dato) {
        double d = Math.sqrt(dato.doubleValue());
        return (new BigDecimal(d)).round(MathContext.DECIMAL64);
    }

}