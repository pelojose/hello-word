/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author miguel
 */
public class EstadoResParcial extends Estado {
    private static final EstadoResParcial instancia = new EstadoResParcial();
    private  EstadoResParcial() {}

    // devuelve la instancia unica de la clase (patron singleton)
    public static Estado getInstancia() {
        return instancia;
    }

    // cuando llega un nuevo numero hay que borrar el resultado parcial de la pila,
    // leer añadir el primer digito a la salida de la calculadora (si procede) y
    // transitar al estado que lee el segundo numero de la operacion
    @Override
    protected void introDigito(String simb, Calculadora cal) {
        pila.introducirTokenC(false);
        if(esParentesis(simb)) {
            meterParentesisEnPila(simb);
            cal.setEstado(EstadoNumero2.getInstancia());
            cal.setSalida("0");
        }
        else {
            if(simb.equals("."))
                cal.setSalida("0.");
            else
                cal.setSalida(simb);
            cal.setEstado(EstadoNumero2.getInstancia());
        }
    }

    // simb representa la tecla C
    @Override
    protected void introC(String simb, Calculadora cal) {
        pila.introducirTokenC(true);
        cal.setSalida("0");
    }

    @Override
    protected void introIgual(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila, asi que no se itroduce
        pila.introducirTokenIGUAL(true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResultado.getInstancia());
    }

    @Override
    protected void introUnaria(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila, asi que no se itroduce
        pila.introducirTokenOPunitario(simb, true);
        cal.setSalida(pila.getResultado());
    }

    @Override
    protected void introBinaria(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila, asi que no se itroduce
        pila.introducirTokenOPbinario(simb, true);  
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoOpBin.getInstancia());
    }

    @Override
    protected void introMR(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila, asi que no se itroduce 
        pila.introducirTokenC(false);
        super.introMR(simb, cal);
        cal.setEstado(EstadoResParcial.getInstancia());
    }

    @Override
    protected void introMemoria(String simb, Calculadora cal) {
        // a diferencia de otros estados, en este estado el numero ya esta en la pila, asi que no se itroduce 
        pila.introducirTokenOPmemoria(simb, true);
    }

}
