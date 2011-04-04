/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author miguel
 */
public class EstadoNumero1 extends Estado{
    private static final EstadoNumero1 instancia = new EstadoNumero1();
    private  EstadoNumero1() {}

    // devuelve la instancia unica de la clase (patron singleton)
    public static Estado getInstancia() {
        return instancia;
    }

    @Override
    protected void introMR(String simb, Calculadora cal) {
        pila.introducirTokenAC(false);
        super.introMR(simb, cal);
    }

    @Override
    protected void introMemoria(String simb, Calculadora cal) {
        String numero = cal.getSalida();
        super.introMemoria(simb, cal);
        pila.introducirTokenAC(false);
        pila.introducirTokenNumero(numero, false);
    }
    

}
