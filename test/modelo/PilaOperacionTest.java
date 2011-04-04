/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jose
 */
public class PilaOperacionTest {

    private static List<String> salidas = new ArrayList();

    public PilaOperacionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("----------------------------------------");
        for (String s : salidas) {
            System.out.println(s);
            System.out.println("----------------------------------------");
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIntroducirToken0() {
        salidas.add("-------  introducirToken AC y C  -------");
        probar(false, "AC", "0");
        probar(false, "AC C", "0");
        probar(false, "AC C =", "0");
        probar(false, "AC =", "0");
        probar(false, "C", "0");
        probar(false, "C =", "0");
        probar(false, "AC 5", "5");
        probar(false, "AC 5 =", "5");
        probar(false, "AC 5 C", "0");
        probar(false, "AC 5 C =", "0");
        probar(false, "AC 2 + =", "E");
        probar(false, "AC 2 + 2 C =", "E");
        probar(false, "AC 2 + 2 C 3", "3");
        probar(false, "AC 2 + 2 C 3 =", "5");
        probar(false, "AC 1 + 4 AC ","0");
        probar(false, "AC 1 + C - 4 = ","-3");
        probar(false, "AC 1 + 5 C 1 =","2");
        probar(false, "AC 1 + 5 qrt C 1 =","2");
    }

    @Test
    public void testIntroducirToken1() {
        salidas.add("-------  introducirToken memoria  ------");
        //operadores que no devuelven nada
        probar(false, "AC M+", "0");
        probar(false, "AC M+ =", "0");
        probar(false, "AC MC", "0");
        probar(false, "AC MC =", "0");
        probar(false, "AC MS", "0");
        probar(false, "AC MS =", "0");
        probar(false, "AC 1 MS", "1");
        probar(false, "AC 1 MS =", "1");
        probar(false, "AC MC", "0");
        //operadores que devuelven algo
        probar(false, "AC MR", "0");
        probar(false, "AC MR =", "0");
        probar(false, "AC 2 M+ ", "2");
        probar(false, "AC MR", "2");
        probar(true, "AC MR C", "0");
        probar(false, "AC MR =", "2");
        probar(false,"AC 3 MS + MR =","6");
        //limpiar memoria para otros test
        probar(false, "AC MC", "0");
    }

    @Test
    public void testIntroducirToken2() {
        salidas.add("-----  introducirToken parentesis  -----");
        probar(false,"AC ( 2 + 2 ) + ( 2 + 2 ) + ( 2 + 2 ) =","12");
        probar(false,"AC ( 2 + 2 ) + ( 2 + 2 qrt ) =","10");
        probar(false,"AC ( 2 M+ + 2 ) + ( 2 + MR qrt ) =","10");
        probar(false,"AC ( 2 ) * ( 2 + 2 ) =","8");
        probar(false,"AC ( 2 + 2 ) * 2 =","8");
        probar(false,"AC 2 * 2 + 2 =","6");
        probar(false,"AC 2 + 2 * 2 =","6");
        probar(false,"AC 2 + 3 * 4 =", "14");
        probar(false,"AC 2 * 3 + 4 =", "10");
        probar(false,"AC 2 + 2 * 3 * 2 + 2 =","16");
        probar(false,"AC ( ( 2 + 2 ) * 3 ) * ( 2 + 2 ) =","48");
        probar(false,"AC 2 + ( 2 * 3 ) * ( 2 + 2 ) =","26");
        probar(false,"AC ( 2 + ( 2 * 3 ) * 2 ) + 2 =","16");
        probar(false,"AC ( 2 + 2 * 3 ) * ( 2 + 2 ) =","32");
        probar(false,"AC 2 * ( ( 2 + 2 * 3 ) qrt ) sqrt + 2 =","18");
        probar(false,"AC ( 3 - 2 ) + 8 = ","9");
        probar(false,"AC ( 3 - 2 ) ) ) ) + 8 = ","9");
        probar(false,"AC 2 + 2 = + 7 =","11");
        probar(false,"AC ( 2 + 2 ) qrt =","16");
        probar(false,"AC 1 / 0 =","E");
        probar(false,"AC 2 + 1 / 0 + 3 =","E");
        probar(false,"AC 2 * 2 ) ) ) =","4");
        probar(false,"AC 2 * 2 ) ) ) + 2 =","6");
        probar(false,"AC ( ( ( 2 * 2 =","4");
        probar(false,"AC 2 inv =","0.5");
        probar(false,"AC 2 + 3 )","5");
        probar(false,"AC ( 3 * ( 2 + 3 ) ) = ","15");
        probar(false,"AC 3 ) ) ) = ","3");
        probar(false,"AC 3 ) ) ) C = ","0");
    }
    @Test
    public void testIntroducirToken3() {
        salidas.add("-------  getResultado  -----------------");
        probar(false,"AC 4 + 2 + 4 sqrt","2");
        probar(false,"AC 4 + 2 + 4 sqrt =","8");
        probar(false,"AC 3 * 3 + 2 qrt","4");
        probar(false,"AC 3 * 3 + 2 qrt =","13");
        probar(false,"AC 4 - 2 -+ ","-2");
        probar(false,"AC 2 + 2 ","2");
        probar(false,"AC 2 + 2 +","4");
        probar(false,"AC 2 * 2 + ","4");
        probar(false,"AC 2 * 2 + 2 ","2");
        probar(false,"AC 2 * 2 + 2 +","6");

    }

    @Test
    public void testErrores(){
        salidas.add("------------  errores  -----------------");   
        probar(false,"AC x ","E");
        probar(false,"AC 3 MS M+ ","3");
        probar(false,"AC x MR E ","E");
        PilaOperacion pop = PilaOperacion.getInstancia();
        String aux = pop.getUltimoToken();
        probar(false,"E",aux);
        probar(false,"1 MS =","1");
        probar(false,"AC C C C =","0");
        probar(false,"1 MS AC 1 + 2 qrt MR C 1 =","E");
        probar(false,"AC 1 MS + 2 =","3");

        //probar(false,"1 MS AC 1 + 2 qrt MR =","2");
        probar(false,"AC 1 MS AC 2 + 2 qrt C MR =","3");
        probar(false,"AC MC 1 MS AC 2 + MR =","3");
        probar(false,"AC MC 1 + 2 qrt C MS 3 =","4");
        probar(false,"AC MC 1 + 2 qrt MS =","5");

        probar(false,"AC 1 + ( 2 ) =","3");
        probar(false,"AC 1 + 2 +","3");
        probar(true,"AC 1 / 0 C qrt 2 =", "0.50000");
        probar(false,"AC 1 / 0 C 1 =", "1.00000");
        probar(false,"AC 1 / 0 + ", "E");
        probar(false,"AC 1 / 0 + 2 =", "E");
        probar(false,"AC 1 / 0 + C =", "E");
        probar(false,"AC 1 / 0 + AC =", "0");
    }

    private void probar(boolean mostrar, String expresion, String esperado) {
        PilaOperacion pop;
        pop = PilaOperacion.getInstancia();
        pop.clearLog();
        if (mostrar) {
            System.out.println("----------------------------------------");
            System.out.println("Expresion: " + expresion);
            System.out.println("----------------------------------------");
        }
        String token[] = expresion.split(" ");
        for (int i = 0; i < token.length; i++) {
            if (mostrar) {
                System.out.println("introducirToken  " + token[i]);
                System.out.println("----------------------------------------");
                pop.imprimirPila("antes     \t");
                pop.imprimirSalida("antes   \t");
                pop.imprimirMemoria("antes  \t");
                System.out.println("----------------------------------------");
            }
            pop.introducirToken(token[i],mostrar);
            if (mostrar) {
                pop.imprimirPila("despues   \t");
                pop.imprimirSalida("despues \t");
                pop.imprimirMemoria("despues\t");
                System.out.println("----------------------------------------");
                System.out.println("resultado      \t" + pop.getResultado());
                System.out.println("----------------------------------------");
            }
        }
        String obtenido = pop.getResultado();
        String salida = "\"" + expresion + "\" devuelve " + obtenido;
        if (mostrar) {
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println(salida);
            System.out.println("----------------------------------------");
            System.out.println();
            System.out.println();
        }
        if (mostrar) System.out.println(pop.getLog());
        salidas.add(salida);
        assertEquals(esperado, obtenido);
    }

}
