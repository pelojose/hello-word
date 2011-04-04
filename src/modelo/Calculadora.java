/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Observable;

/**
 *
 * @author miguel
 */
public class Calculadora extends Observable {
    private String salida;
    private Estado estado;
   // private Log log = new Log();

    public Calculadora() {
        
    }

    public void inicializar() {      
        estado = Estado.getInstancia();
        this.introducirSimbolo("AC");
        PilaOperacion.getInstancia().clearLog();
        setSalida("0");
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        //System.out.println("intro "+ salida);
        this.salida = salida;
        setChanged();
        notifyObservers(this);
    }

    public void introducirSimbolo(String simb) {
        //System.out.println("  cal: "+simb);
        this.estado.intro(simb, this);
        //log.add(simb);
    }

    public String getLog(){
       return PilaOperacion.getInstancia().getLog();
     //   return "";
    }

}
