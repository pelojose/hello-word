package modelo;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;
import modelo.operaciones.OperacionBinaria;
import modelo.operaciones.OperacionMemoria;
import modelo.operaciones.OperacionUnaria;

enum Caso {
    NUMERO,
    OPunitario,
    OPbinario,
    OPmemoria,
    PARder,//")"
    PARizq,//"("
    IGUAL,
    ERROR,
    C,
    AC
}

public class PilaOperacion {

    private static PilaOperacion instancia = new PilaOperacion();
    private Stack<String> pila = new Stack();
    private Stack<BigDecimal> salida = new Stack();
    private String resultado = new String();
    private String ultimoT=null;
    private Caso ultimoC=null;
    private Log log = Log.getInstancia();
    public static final int LONGITUD = 20;

    private PilaOperacion() {
    }

    public static PilaOperacion getInstancia(){
        return PilaOperacion.instancia;
    }

    public String getUltimoToken(){
        return ultimoT;
    }

    public boolean hayError(){
        return 0 == resultado.compareTo("E");
    }

    public void clearLog(){
        log.clear();
    }

    public String getResultado(){
        try{
            if (!hayError())
                return salida.peek().toString();
            else
                return resultado;
        }
        catch (Exception e){
            return resultado;
        }
    }

    public String getLog(){
        return log.getLog();
    }

    public void introducirToken(String token,boolean addLog){
        Caso c = matchToken(token);
        switch (c){
            case NUMERO:
                introducirTokenNumero(token,addLog);
                break;
            case OPunitario:
                introducirTokenOPunitario(token,addLog);
                break;
            case OPbinario:
                introducirTokenOPbinario(token,addLog);
                break;
            case OPmemoria:
                introducirTokenOPmemoria(token,addLog);
                break;
            case PARizq:
                introducirTokenPARizq(token,addLog);
                break;
            case PARder:
                introducirTokenPARder(token,addLog);
                break;
            case IGUAL:
                introducirTokenIGUAL(addLog);
                break;
            case C:
                introducirTokenC(addLog);
                break;
            case AC:
                introducirTokenAC(addLog);
                break;
            default:
                introducirTokenERROR(addLog);
        }
    }

    public void introducirTokenNumero(String token, boolean addLog){
        //System.out.println("  pila: "+token);
        if (addLog) log.add(token);
        ultimoT = token;
        salida.push(new BigDecimal(token));
        ultimoC = Caso.NUMERO;
    }


    public void introducirTokenOPunitario(String token, boolean addLog){
        //System.out.println("  pila: "+token);
        if (addLog) log.add(token);
        ultimoT = token;
        procesarSalida(Caso.OPunitario,token);
        if (addLog) log.add(resultado);
        ultimoC = Caso.NUMERO;
    }


    public void introducirTokenOPbinario(String token, boolean addLog){
        //System.out.println("  pila: "+token);
        if (addLog) log.add(token);
        ultimoT = token;
        String aux = null;
        Caso c = matchToken(token);
        if (!pila.empty()) {
            aux = (String) pila.peek();
            Caso caso = matchOperador(aux);
            while (true){
                if ( (!esAsociativoDer(aux) && precedencia(token,"<=",aux)) ||
                     (esAsociativoDer(aux) && precedencia(token,"<=",aux)) ){
                    procesarSalida(caso,aux);
                    if (false) log.add(resultado);
                    pila.pop();
                    if (!pila.empty()) {
                        aux=(String) pila.peek();
                        caso = matchOperador(aux);
                    }
                    else break;
                }
                else break;
            }
        }
        pila.push(token);
        ultimoC = c;
    }


    public void introducirTokenOPmemoria(String token, boolean addLog){
        //System.out.println("  pila: "+token);
        if (addLog) log.add(token);
        ultimoT = token;
        procesarSalida(Caso.OPmemoria,token);
        
    }


    public void introducirTokenPARizq(String token, boolean addLog){
        //System.out.println("  pila: "+token);
        if (addLog) log.add(token);
        ultimoT = token;
        pila.push(token);
        ultimoC = Caso.PARizq;
    }


    public void introducirTokenPARder(String token, boolean addLog){
        //System.out.println("  pila: "+token);
        if (addLog) log.add(token);
        ultimoT = token;
        String aux = null;
        Caso c = matchToken(token);
        try {
            while ( 0 != (aux =  (String) pila.pop()).compareTo("(") ){
                Caso caso = matchOperador(aux);
                procesarSalida(caso,aux);
            }
            ultimoC = c;
            return;
        }
        catch (EmptyStackException e){
            //resultado = "E";
        }
    }


    public void introducirTokenIGUAL(boolean addLog){
        //System.out.println("  pila: =");
        if (addLog) log.add("=");
        ultimoT = "=";
        resultado = obtenerResultado();
        if (addLog) log.add(resultado);
        pila.clear();
    }


    public void introducirTokenC(boolean addLog){
        //System.out.println("  pila: C");
        if (addLog) log.add("C");
        ultimoT = "C";
        switch(ultimoC){
            case NUMERO:
                salida.pop();
                if (!salida.empty())
                    resultado = salida.peek().toString();
                else
                    resultado = "0";
                break;
            case OPbinario:
                pila.pop();
                break;
            case OPmemoria:
                if (!salida.empty()) salida.pop();
                break;
            case AC:
                introducirTokenAC(addLog);
                break;
            default:
                break;
        }
        ultimoC = Caso.C;
    }


    public void introducirTokenAC(boolean addLog){
        //System.out.println("  pila: AC");
        if (addLog) log.add("AC");
        ultimoT = "AC";
        salida.clear();
        pila.clear();
        resultado = "0";
        ultimoC = Caso.AC;
    }


    public void introducirTokenERROR(boolean addLog){
        //System.out.println("  pila: E");
        if (addLog) log.add("E");
        ultimoT = "E";
        resultado = "E";
        pila.clear();
        salida.clear();
    }


    private String obtenerResultado(){
        while (!pila.empty()){
            String aux = (String) pila.pop();
            Caso caso = matchOperador(aux);
            procesarSalida(caso,aux);
        }
        procesarSalida();
        return resultado;
    }

    private void procesarSalida(){
        if (1 == salida.size() ) {
           resultado = salida.peek().toString();
        }
        else{
            if (ultimoC != Caso.AC && ultimoC != Caso.OPmemoria && ultimoC != Caso.C)
            resultado = "E";
        }
    }

    private void procesarSalida(Caso caso, String op){
        BigDecimal op1;
        BigDecimal op2;
        BigDecimal res = null;
        try {
        switch (caso){
            case OPbinario:
                op2 = salida.pop();
                op1 = salida.pop();
                res = OperacionBinaria.stringToOp(op).realizar(op1,op2);
                salida.add(res);
                break;
            case OPunitario:
                op1 = salida.pop();
                res = OperacionUnaria.stringToOp(op).realizar(op1);
                salida.add(res);
                ultimoC = Caso.NUMERO;
                break;
            case OPmemoria:
                if (!salida.empty())
                    op1 = salida.peek();
                else
                    op1 = new BigDecimal(0);
                if (OperacionMemoria.devuelveAlgo(op)){
                    res = OperacionMemoria.stringToOp(op).realizar(op1);
                    salida.add(res);
                    ultimoC = Caso.NUMERO;
                }
                else{
                    if (salida.empty()) op1 = new BigDecimal(0);
                    OperacionMemoria.stringToOp(op).realizar(op1);
                    if (!OperacionMemoria.getEstadoMemoria())
                        resultado = "0";
                    else
                        resultado = OperacionMemoria.getDato().toString();
                    ultimoC = Caso.OPmemoria;
                }
                break;
        }
        if (!salida.empty() && !hayError())
            resultado = salida.peek().toString();
    
        if (res != null && !enRango(res)){
            resultado = "E";
        }
        }
        catch (Exception e){
            //e.printStackTrace();
            resultado = "E";
        }
    }

    private boolean enRango(BigDecimal x){
        BigDecimal max = new BigDecimal("10E20");
        return -1 == x.abs().compareTo(max);
    }

    private boolean esAsociativoDer(String op){
       return OperacionBinaria.esAsociativoDer(op);
    }

    private boolean precedencia(String op1, String p, String op2){
        if (0==p.compareTo("<=")){
            return OperacionBinaria.getPrioridad(op1)<=OperacionBinaria.getPrioridad(op2);
        }
        else if (0==p.compareTo("<")){
            return OperacionBinaria.getPrioridad(op1)<OperacionBinaria.getPrioridad(op2);
        }
        else if (0==p.compareTo("==")){
            return OperacionBinaria.getPrioridad(op1)==OperacionBinaria.getPrioridad(op2);
        }
        return OperacionBinaria.getPrioridad(op1)>OperacionBinaria.getPrioridad(op2);
    }

    private Caso matchToken(String token){
        Caso caso;
        if (matchNumero(token)) return Caso.NUMERO;
        else if (Caso.ERROR != (caso = matchOperador(token)) ) return caso;
        else if (0==token.compareTo("(")) return Caso.PARizq;
        else if (0==token.compareTo(")")) return Caso.PARder;
        else if (0==token.compareTo("=")) return Caso.IGUAL;
        else if (0==token.compareTo("error")) return Caso.ERROR;
        else if (0==token.compareTo("C")) return Caso.C;
        else if (0==token.compareTo("AC")) return Caso.AC;
        return Caso.ERROR;
    }

    private boolean matchNumero(String token){
        try {
            new BigDecimal(token);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
   
    private Caso matchOperador(String token){
        if (OperacionBinaria.esOpBin(token)) return Caso.OPbinario;
        else if (OperacionUnaria.esOpUna(token)) return Caso.OPunitario;
        else if (OperacionMemoria.esOpMem(token)) return Caso.OPmemoria;
        else return Caso.ERROR;
    }

    //metodos para hacer pruebas:
    public void imprimirPila(String s){
        System.out.print("Pila "+s);
        for(int i=pila.size()-1;i>=0;i--){
            System.out.print(pila.get(i)+ " ");
        }
        System.out.println();
    }
    public void imprimirSalida(String s){
        System.out.print("Salida "+s);
        for(int i=salida.size()-1;i>=0;i--){
            System.out.print(salida.get(i)+ " ");
        }
        System.out.println();
    }

    public void imprimirMemoria(String s){
        System.out.print("Memoria "+s);
        if (null != OperacionMemoria.getDato())
        System.out.println(OperacionMemoria.getDato().toString());
        else
            System.out.println();
    }
}
