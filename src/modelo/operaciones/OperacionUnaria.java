package modelo.operaciones;
import java.math.BigDecimal;

public abstract class OperacionUnaria {

    private static String [] stringOpUna = {"-+","qrt","sqrt","inv","ln","sin","cos","tg"};
    private static OperacionUnaria [] objOpUna = {
                    CambioSigno.getOp(), Cuadrado.getOp(), RaizCuadrada.getOp(),
                    Inversa.getOp(), LogNeperiano.getOp(),
                    Seno.getOp(), Coseno.getOp(), Tangente.getOp()};
    private static String [] asociativosDer = {"-+","qrt","sqrt","inv","ln","sin","cos","tg"};
    private static int [] prioridades = {3,3,3,3,3,3,3,3};//max prioridad 3 y min 1

    public static boolean esOpUna(String s){
        for(String ss:stringOpUna){
            if (0==s.compareTo(ss)) return true;
        }
        return false;
    }

    public static OperacionUnaria stringToOp(String op){
        for (int i=0;i<stringOpUna.length;i++){
            if (0==op.compareTo(stringOpUna[i])){
                return objOpUna[i];
            }
        }
        return null;
    }

    public static boolean esAsociativoDer(String op){
        for (int i=0;i<asociativosDer.length;i++){
            if (0==op.compareTo(asociativosDer[i])){
                return true;
            }
        }
        return false;
    }

    public static int getPrioridad(String op){
        for (int i=0;i<stringOpUna.length;i++){
            if (0==op.compareTo(stringOpUna[i])){
                return prioridades[i];
            }
        }
        return -1;
    }

    public abstract BigDecimal realizar(BigDecimal dato);

}