/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author jose
 */
public class Log {

    private static Log instancia = new Log();
    private String log = new String();

    Log(){
    }

    public static Log getInstancia() {
        return instancia;
    }

    void add(String token) {
        log += token + "\r\n";
    }

    public String getLog() {
        return log;
    }

    public void clear(){
        log = "";
    }

}
