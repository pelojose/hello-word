/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import modelo.*;
import modelo.operaciones.OperacionBinaria;
import modelo.operaciones.OperacionMemoria;
import modelo.operaciones.OperacionUnaria;

/**
 *
 * @author miguel
 */
public abstract class Estado {

    // lo que la maquina de estados trata como "digitos"
    private static final String[] digitos =
        {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "(", ")"};
    protected static final PilaOperacion pila = PilaOperacion.getInstancia();

    // devuelve true si simb representa a un digito y false en otro caso
    protected static boolean esDigito(String simb) {
        for(int i=0; i<digitos.length; i++)
            if(simb.equals(digitos[i]))
                return true;
        return false;
    }

    // devuelve true si la salida de la calculadora tiene la longitud maxima, false en otro caso
    protected static boolean tieneLongitudMaxima(Calculadora cal) {
        return(cal.getSalida().length() >= PilaOperacion.LONGITUD);
    }

    // devuelve true si el ultimo token introducido en la pila es un parentesis derecho, false en otro caso
    protected static boolean ultimoEsParentesisDer() {
        String ultimo = pila.getUltimoToken();
        return(ultimo.equals(")"));
    }

    // devuelve true si simb representa un parentesis, false en otro caso
    protected static boolean esParentesis(String simb) {
        return(simb.equals("(") || simb.equals(")"));
    }

    // mete en la pila simb si se corresponde con un parentesis derecho o izquierdo
    protected static void meterParentesisEnPila(String simb) {
        if(simb.equals("("))
            pila.introducirTokenPARizq(simb, true);
        else if(simb.equals(")"))
            pila.introducirTokenPARder(simb, true);
    }

    // devuelve la instancia unica de la clase (patron singleton).
    // como esta clase es una clase abstracta, no tiene instacias. Sin embargo, devuelve
    // la instancia del estado inicial. De esta forma se encapsula el estado en particular
    // que se corresponde el estado inicial.
    public static Estado getInstancia() {
        return EstadoNumero1.getInstancia();
    }

    // introduce el simbolo indicado en la calculadora.
    // Es un metodo plantilla, que delega los pasos a las subclases.
    // No obstante, en esta clase se dan definiciones para los metodos porque son
    // comunes a muchos estados, y los estados que se comporten de manera diferente
    // simplemente los reescriben.
    // El metodo se declara final para que no pueda ser sobreescrito por las subclases
    public final void intro(String simb, Calculadora cal) {
        if(pila.hayError()) { //hubo un error en al operacion anterior
            if(simb.equals("AC")) {
                pila.introducirTokenAC(true);
                cal.setSalida("0");
            }
        }
        else {    //no hubo un error en la ultima operacion
            if(esDigito(simb))
                introDigito(simb, cal);
            else if(simb.equals("MR"))
                introMR(simb, cal);
            else if(simb.equals("C"))
                introC(simb, cal);
            else if(simb.equals("AC"))
                introAC(simb, cal);
            else if(simb.equals("="))
                introIgual(simb, cal);
            else if(OperacionUnaria.esOpUna(simb))
                introUnaria(simb, cal);
            else if(OperacionBinaria.esOpBin(simb))
                introBinaria(simb, cal);
            else if(OperacionMemoria.esOpMem(simb))
                introMemoria(simb, cal);
        }
    }

    // introduce un digito (que puede ser un punto o un parentesis) en la pila
    protected void introDigito(String simb, Calculadora cal) {
        String salida = cal.getSalida();
        if(simb.equals("("))
            pila.introducirTokenPARizq(simb, true);
        // si es un parentesis derecho, hay que introducir lo que haya en la salida
        // por ejemplo "( 3 )", al introducir ")" hay que introducir el "3" tambien
        else if(simb.equals(")")) {
            // si el en vez de un numero entre parentesis hay otro parentesis, entonces no se introduce.
            // por ejemplo "( ( 3 ) )" con el ultimo parentesis no se entraria en el "if"
            if(!ultimoEsParentesisDer())
                pila.introducirTokenNumero(cal.getSalida(), true);
            pila.introducirTokenPARder(simb, true);
            cal.setSalida(pila.getResultado());
        }
        // si es un punto no se introduce si el numero ya tiene uno
        else if(simb.equals(".")) {
            if(!salida.contains(".") && !tieneLongitudMaxima(cal))
                cal.setSalida(salida.concat(simb));
        }
        // no pueden concatenarse digitos cuando solo hay un cero (por ejemplo "03")
        else if(salida.equals("0"))
            cal.setSalida(simb);
        else if(!tieneLongitudMaxima(cal))
            cal.setSalida(salida.concat(simb));
        
    }

    // simb representa la tecla MR
    protected void introMR(String simb, Calculadora cal) {
        pila.introducirTokenOPmemoria(simb, true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResultado.getInstancia());
    }

    // simb representa la tecla C
    protected void introC(String simb, Calculadora cal) {
        pila.introducirTokenNumero(cal.getSalida(), true);
        pila.introducirTokenC(true);
        cal.setSalida("0");
    }

    // simb representa la tecla AC
    protected void introAC(String simb, Calculadora cal) {
        if(cal.getSalida() != null)
            pila.introducirTokenNumero(cal.getSalida(), false);
        pila.introducirTokenAC(true);
        cal.setSalida("0");
    }

    // simb representa la tecla =
    protected void introIgual(String simb, Calculadora cal) {
        if(!ultimoEsParentesisDer())
            pila.introducirTokenNumero(cal.getSalida(), true);
        pila.introducirTokenIGUAL(true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResultado.getInstancia());
    }

    // simb representa a una operacion unaria
    protected void introUnaria(String simb, Calculadora cal) {
        if(!ultimoEsParentesisDer())
            pila.introducirTokenNumero(cal.getSalida(), true);
        pila.introducirTokenOPunitario(simb, true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResultado.getInstancia());
    }

    // simb representa a una operacion binaria
    protected void introBinaria(String simb, Calculadora cal) {
        if(!ultimoEsParentesisDer())
            pila.introducirTokenNumero(cal.getSalida(), true);
        pila.introducirTokenOPbinario(simb, true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoOpBin.getInstancia());
    }

    // simb representa a una operacion de memoria
    protected void introMemoria(String simb, Calculadora cal) {
        pila.introducirTokenNumero(cal.getSalida(), false);
        pila.introducirTokenOPmemoria(simb, true);
        cal.setEstado(EstadoResultado.getInstancia());
    }
}
