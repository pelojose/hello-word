package modelo.operaciones;
import java.math.BigDecimal;

public abstract class OperacionBinaria {

    private static String [] stringOpBin = {"+","-","*","/","%"};
    private static OperacionBinaria [] objOpBin = {
                    Suma.getOp(), Resta.getOp(), Multiplicacion.getOp(), Division.getOp(), Modulo.getOp()};
    private static String [] asociativosDer = {"+","-","*","/","%"};
    private static int [] prioridades = {1,1,2,2,2};//max prioridad 3 y min 1
    
    public static boolean esOpBin(String s){
        for(String ss:stringOpBin){
            if (0==s.compareTo(ss)) return true;
        }
        return false;
    }

    public static OperacionBinaria stringToOp(String op){
        for (int i=0;i<stringOpBin.length;i++){
            if (0==op.compareTo(stringOpBin[i])){
                return objOpBin[i];
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
        for (int i=0;i<stringOpBin.length;i++){
            if (0==op.compareTo(stringOpBin[i])){
                return prioridades[i];
            }
        }
        return -1;
    }

    public abstract BigDecimal realizar(BigDecimal dato1, BigDecimal dato2);

}