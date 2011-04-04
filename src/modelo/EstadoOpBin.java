/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author miguel
 */
public class EstadoOpBin extends Estado {
    private static final EstadoOpBin instancia = new EstadoOpBin();
    private  EstadoOpBin() {}

    // devuelve la instancia unica de la clase (patron singleton)
    public static Estado getInstancia() {
        return instancia;
    }

    // si se introduce un digito, se mete en la salida (si procede) y se pasa a leer el resto del numero
    @Override
    protected void introDigito(String simb, Calculadora cal) {
        if(esParentesis(simb))
            meterParentesisEnPila(simb);
        else {
            if(simb.equals("."))
                cal.setSalida("0.");
            else
                cal.setSalida(simb);
            cal.setEstado(EstadoNumero2.getInstancia());
        }
    }

    // simb representa la tecla C
    protected void introC(String simb, Calculadora cal) {
        cal.setSalida("0");
    }

    // el comportamiento de una calculadora normal (que hemos imitado) es el siguiente:
    // con "1 + =" va al estado resultado dejando la salida "1"
    @Override
    protected void introIgual(String simb, Calculadora cal) {
        pila.introducirTokenC(false);       //se borra la ultima operacion binaria de la pila
        pila.introducirTokenIGUAL(true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResultado.getInstancia());
    }

    @Override
    protected void introUnaria(String simb, Calculadora cal) {
        pila.introducirTokenNumero(cal.getSalida(), false);
        pila.introducirTokenOPunitario(simb, true);
        cal.setSalida(pila.getResultado());
        cal.setEstado(EstadoResParcial.getInstancia());
    }

    @Override
    protected void introBinaria(String simb, Calculadora cal) {
        // Si se introducen 2 operaciones binarias, se borra la anterior (con C) y
        // se introduce la nueva que la sustituye
        // por ejemplo: "5 + - 2" da "3" porque el "-" sustituye al "+"
        pila.introducirTokenC(false);   
        pila.introducirTokenOPbinario(simb, true);  
    }

    @Override
    protected void introMR(String simb, Calculadora cal) {
        super.introMR(simb, cal);
        cal.setEstado(EstadoResParcial.getInstancia());
    }
}
