package modelo.operaciones;

import java.math.BigDecimal;

public abstract class OperacionMemoria {

    private static BigDecimal dato;
    private static String [] stringOpMem = {"M+","M-","MR","MS","MC"};
    private static OperacionMemoria [] objOpMem = {
                    MemoriaSuma.getOp(), MemoriaResta.getOp(), MemoryRecall.getOp(),
                    MemoryStore.getOp(), MemoryClear.getOp()};
    private static boolean [] devuelven = {false,false,true,false,false};

    public static boolean esOpMem(String s){
        for(String ss:stringOpMem){
            if (0==s.compareTo(ss)) return true;
        }
        return false;
    }

    public static OperacionMemoria stringToOp(String op){
        for (int i=0;i<stringOpMem.length;i++){
            if (0==op.compareTo(stringOpMem[i])){
                return objOpMem[i];
            }
        }
        return null;
    }

    public static boolean devuelveAlgo(String op){
        for (int i=0;i<stringOpMem.length;i++){
            if (0==op.compareTo(stringOpMem[i])){
                return devuelven[i];
            }
        }
        return false;
    }

    public static BigDecimal getDato() {
        return dato;
    }

    public static void setDato(BigDecimal d) {
        dato = d;
    }

    public static boolean getEstadoMemoria() {
        return (!(dato == null));
    }

    public abstract BigDecimal realizar(BigDecimal dato2);

}