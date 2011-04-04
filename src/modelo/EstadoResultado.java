/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author miguel
 */
public class EstadoResultado extends Estado {
    private static final EstadoResultado instancia = new EstadoResultado();
    private  EstadoResultado() {}

    // devuelve la instancia unica de la clase (patron singleton)
    public static Estado getInstancia() {
        return instancia;
    }

    // cuando se introduce un digito despues del resultado de una operacion,
    // es como si se introdujese AC y luego se lee el numero
    @Override
    protected void introDigito(String simb, Calculadora cal) {
        pila.introducirTokenAC(false);
        cal.setSalida("0");
        super.introDigito(simb, cal);
        cal.setEstado(EstadoNumero1.getInstancia());
    }

    // simb representa la tecla AC
    @Override
    protected void introAC(String simb, Calculadora cal) {
        super.introAC(simb, cal);
        pila.introducirTokenNumero(cal.getSalida(), true);
    }

    @Override
    protected void introIgual(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila, asi que no se itroduce
        pila.introducirTokenIGUAL(true);
    }

    @Override
    protected void introUnaria(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila
        pila.introducirTokenOPunitario(simb, true);
        cal.setSalida(pila.getResultado());
    }

    @Override
    protected void introBinaria(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila
        pila.introducirTokenOPbinario(simb, true);
        cal.setEstado(EstadoOpBin.getInstancia());
    }

}
