/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


/**
 *
 * @author miguel
 */
public class EstadoNumero2 extends Estado {
    private static final EstadoNumero2 instancia = new EstadoNumero2();
    private  EstadoNumero2() {}

    // devuelve la instancia unica de la clase (patron singleton)
    public static Estado getInstancia() {
        return instancia;
    }

    @Override
    protected void introUnaria(String simb, Calculadora cal) {
        // si el ultimo token de la pila es ")" entonces no
        // se introduce el resultado en la pila
        // por ejemplo 1 + ( 3 + 2 ) sqrt
        if(!ultimoEsParentesisDer())
            pila.introducirTokenNumero(cal.getSalida(), true);
        pila.introducirTokenOPunitario(simb, true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResParcial.getInstancia());
    }

    @Override
    protected void introMR(String simb, Calculadora cal) {
        super.introMR(simb, cal);
        cal.setEstado(EstadoResParcial.getInstancia());
    }

    @Override
    protected void introMemoria(String simb, Calculadora cal) {
        super.introMemoria(simb, cal);
        cal.setEstado(EstadoResParcial.getInstancia());
    }
}
