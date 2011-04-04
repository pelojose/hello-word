/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author miguel
 */
public class EstadoTest {
    public EstadoTest() {}

    // funcion auxiliar que crea una calculadora, le introduce las teclas pasadas como parametro
    // y comprueba que la salida es la deseada
    private static void probar(String teclas, String salida) {    
        String token[];
        token = teclas.split(" ");
        System.out.println("  teclas: " + teclas + "    resultado: "+salida);
        Calculadora cal = new Calculadora();
        cal.inicializar();
        
        //vacia la pila
        cal.introducirSimbolo("AC");
        //borra la memoria si esta llena
        if(modelo.operaciones.OperacionMemoria.getEstadoMemoria())
            PilaOperacion.getInstancia().introducirTokenOPmemoria("MC", true);
        //introduce cada tecla en la calculadora
        for (int i = 0; i < token.length; i++) {
            cal.introducirSimbolo(token[i]);
        }
        
        assertEquals(salida, cal.getSalida());
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println();
        System.out.println("---------------------- Clase Estado ----------------");
        System.out.println();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEstadoNumero1() {
        System.out.println();
        System.out.println("--------- test de EstadoNumero1");
        System.out.println("lee un digito");
        probar("", "0");
        probar(".", "0.");
        probar("0", "0");
        probar("0 .", "0.");
        probar("0 0", "0");
        probar("1", "1");
        probar("1 2", "12");
        probar("1 .", "1.");
        probar(". .", "0.");
        probar("1 . .", "1.");
        probar("1 . 2 . 3", "1.23");
        System.out.println("lee un igual");
        probar("=", "0");
        probar("0 =", "0");
        probar("1 =", "1");
        probar("1 2 =", "12");
        probar(". =", "0");
        probar("1 . 2 =", "1.2");
        System.out.println("lee operacion unaria");
        probar("qrt", "0");
        probar("0 qrt", "0");
        probar("2 qrt", "4");
        probar("1 0 qrt", "100");
        probar(". 1 qrt", "0.01");
        System.out.println("lee operacion de memoria");
        probar("MS", "0");
        probar("0 MS", "0");
        probar("2 MS", "2");
        System.out.println("lee MR");
        probar("MR", "0");
        probar("0 MR", "0");
        probar("2 MR", "0");
        probar("1 MS MR", "1");
        probar("1 MS 2 MR", "1");
        System.out.println("lee operacion binaria");
        probar(" +", "0");
        probar("0 +", "0");
        probar("2 +", "2");
        probar("1 . 1 +", "1.1");
        probar(". 1 +", "0.1");
    }

    @Test
    public void testEstadoOpBin() {
        System.out.println();
        System.out.println("--------- test de EstadoOpBin");
        System.out.println("lee un digito");
        probar("+ 2", "2");
        probar("+ 2 =", "2");
        probar("1 + 2", "2");
        probar("1 + 2 = ", "3");
        probar("1 + .", "0.");
        probar("1 + . =", "1");
        System.out.println("lee un igual");
        probar("+ =", "0");
        probar("0 + =", "0");
        probar("1 + =", "1");
        probar("1 2 + =", "12");
        probar(". + =", "0");
        System.out.println("lee operacion unaria");
        probar("+ qrt", "0");
        probar("2 + qrt", "4");
        probar("2 + qrt =", "6");
        probar("2 + 2 qrt =", "6");
        probar(". 1 + qrt", "0.01");
        probar(". 1 + qrt =", "0.11");
        System.out.println("lee operacion de memoria");
        probar("+ MS", "0");
        probar("0 + MS", "0");
        probar("0 + MS = ", "0");
        probar("2 + MS", "2");
        probar("2 + MS =", "2");
        System.out.println("lee MR");
        probar("+ MR", "0");
        probar("0 + MR", "0");
        probar("2 + MR", "0");
        probar("2 + MR = ", "2");
        probar("1 MS 2 + MR", "1");
        probar("1 MS 2 + MR =", "3");
        System.out.println("lee operacion binaria");
        probar("+ -", "0");
        probar("+ - =", "0");
        probar("0 + -", "0");
        probar("2 + -", "2");
        probar("2 + - =", "2");
        probar(". 1 + - =", "0.1");
        probar("3 + - 1 =", "2");
        probar("3 + - + 1 =", "4");
    }

    @Test
    public void testEstadoNumero2() {
        System.out.println();
        System.out.println("--------- test de EstadoNumero2");
        System.out.println("lee un digito");
        probar("+ 2 3", "23");
        probar("+ 2 3 =", "23");
        probar("1 + 2 3", "23");
        probar("1 + 2 3 = ", "24");
        probar("1 + . 1", "0.1");
        probar("1 + . 1 =", "1.1");
        probar("1 + . 1 2 =", "1.12");
        //System.out.println("lee un igual");
        System.out.println("lee operacion unaria");
        probar("+ 2 qrt", "4");
        probar("+ 2 qrt =", "4");
        probar("2 + 2 qrt", "4");
        probar("2 + 2 qrt =", "6");
        probar("1 + 0 . 1 qrt", "0.01");
        probar("1 + 0 . 1 qrt =", "1.01");
        System.out.println("lee operacion de memoria");
        probar("+ 1 MS", "1");
        probar("0 + 1 MS", "1");
        probar("0 + 1 MS = ", "1");
        probar("2 + 3 MS", "3");
        probar("2 + 3 MS =", "5");
        System.out.println("lee MR");
        probar("+ 1 MR", "0");
        probar("0 + 1 MR", "0");
        probar("2 + 1 MR", "0");
        probar("2 + 1 MR = ", "2");
        probar("1 MS 2 + MR", "1");

        System.out.println("lee operacion binaria");
        probar("+ + =", "0");
        probar("1 + 2 +", "3");
        probar("1 + 2 + 3", "3");
        probar("1 + 2 + 3 =", "6");
        probar("1 . 1 + 1 . 2 + 1 . 3 =", "3.6");

        probar("1 + 2 +", "3");
        probar("1 + 2 + 4", "4");
        probar("1 + 2 + 4 =", "7");
    }

    @Test
    public void testEstadoResParcial() {
        System.out.println();
        System.out.println("--------- test de EstadoNumero2");
        System.out.println("lee un digito");
        probar("1 + 2 qrt 3", "3");
        probar("1 + 2 qrt 3 =", "4");
        probar("1 + 2 qrt 3 4", "34");
        probar("1 + 2 qrt 3 4 =", "35");
        probar("1 + 2 qrt .", "0.");
        probar("1 + 2 qrt . =", "1");
        probar("1 + 2 qrt . 1 2 =", "1.12");
        probar("1 + 2 qrt 1 . 1 2 =", "2.12");
        //System.out.println("lee un igual");
        System.out.println("lee operacion unaria");
        probar("1 + 2 qrt qrt", "16");
        probar("1 + 2 qrt qrt =", "17");
        probar("1 + 0 . 1 qrt qrt", "0.0001");
        probar("1 + 0 . 1 qrt qrt =", "1.0001");
        System.out.println("lee operacion de memoria");
        probar("1 + 2 qrt MS", "4");
        probar("1 + 2 qrt MS = ", "5");
        probar("1 + 2 qrt MS 3", "3");
        probar("1 + 2 qrt MS 3 =", "4");
        probar("1 + 2 qrt MS MS", "4");
        probar("1 + 2 qrt MS MS = ", "5");
        probar("1 + 2 qrt MS MS 3", "3");
        probar("1 + 2 qrt MS MS 3 =", "4");
        System.out.println("lee MR");
        probar("1 + 2 qrt MR", "0");
        probar("1 + 2 qrt MR = ", "1");
        probar("1 + 2 qrt MR 3", "3");
        probar("1 + 2 qrt MR 3 =", "4");
        probar("1 + 2 qrt MR MR", "0");
        probar("1 + 2 qrt MR MR = ", "1");
        probar("1 MS 2 + 2 qrt MR", "1");
        probar("1 MS 2 + 2 qrt MR =", "3");
        probar("1 MS 2 + MR =", "3");
        System.out.println("lee operacion binaria");
        probar("1 + 2 qrt +", "5");
        probar("1 + 2 qrt + 3", "3");
        probar("1 + 2 qrt + 3 =", "8");
        probar("1 + 2 +", "3");
        probar("1 + 2 + 3", "3");
        probar("1 + 2 + 3 =", "6");
        probar("1 . 1 + 1 . 1 qrt + 1 =", "3.31");
    }

    @Test
    public void testEstadoResultado() {
        System.out.println();
        System.out.println("--------- test de EstadoNumero2");
        System.out.println("lee un digito");
        probar("1 + 2 =", "3");
        probar("1 + 2 = 4", "4");
        probar("1 + 2 = 4 5", "45");
        probar("1 + 2 = 4 5 =", "45");
        probar("1 + 2 = .", "0.");
        probar("1 + 2 = 0", "0");
        probar("1 + 2 = 0 1", "1");
        probar("1 + 2 = 0 .", "0.");
        probar("1 + 2 = 0 . 2", "0.2");
        probar("1 + 2 = . 5", "0.5");
        //System.out.println("lee un igual");
        System.out.println("lee operacion unaria");
        probar("1 + 2 = qrt", "9");
        probar("1 + 2 = qrt =", "9");
        probar("1 + 2 = qrt qrt", "81");
        probar("0 + . 1 qrt = qrt", "0.0001");
        System.out.println("lee operacion de memoria");
        probar("1 + 2 qrt = MS", "5");
        probar("1 + 2 qrt = MS = ", "5");
        probar("1 + 2 qrt = MS MS", "5");
        probar("1 + 2 qrt = MS MS = ", "5");
        System.out.println("lee MR");
        probar("1 + 2 qrt = MR", "0");
        probar("1 + 2 qrt = MR =", "0");
        probar("1 + 2 qrt = MR MR", "0");
        probar("1 + 2 qrt = MR MR =", "0");
        probar("1 MS 2 + 2 qrt = MR", "1");
        probar("1 MS 2 + 2 qrt = MR =", "1");
        probar("1 MS 2 + = MR =", "1");
        System.out.println("lee operacion binaria");
        probar("1 + 2 qrt = +", "5");
        probar("1 + 2 qrt = + 3", "3");
        probar("1 + 2 qrt = + 3 =", "8");
        probar("1 + 2 qrt = + . =", "5");
        probar("1 + 2 qrt = + . 1 =", "5.1");
    }

    @Test
    public void testParentesis() {
        System.out.println();
        System.out.println("--------- test de parentesis");
        probar("(", "0");
        probar("( )", "0");
        probar("( ) =", "0");
        probar("( 2 ) =", "2");
        probar("( . 1 ) =", "0.1");

        probar("( ) + 1", "1");
        probar("( ) + 1 =", "1");
        probar("( 3 ) + 1", "1");
        probar("( 3 ) + 1 =", "4");
        probar("( . 1 ) + 2 =", "2.1");

        probar("( ) qrt", "0");
        probar("( ) qrt =", "0");
        probar("( 3 ) qrt", "9");
        probar("( 3 ) qrt =", "9");
        probar("( . 1 ) qrt =", "0.01");
        
        probar("( ) MS", "0");
        probar("( ) MS =", "0");
        probar("( 3 ) MS", "3");
        probar("( 3 ) MS =", "3");
        probar("( . 1 ) MS =", "0.1");

        probar("( ) MR", "0");
        probar("( ) MR =", "0");
        probar("( 3 ) MR", "0");
        probar("( 3 ) MR =", "0");
        probar("( . 1 ) MR =", "0");
        probar("4 MS ( . 1 ) MR =", "4");

        probar("1 + ( 2 )", "2");
        probar("1 + ( 2 ) =", "3");
        probar("1 + ( . 1 ) =", "1.1");

        probar("1 + ( 2 ) + 3", "3");
        probar("1 + ( 2 ) + 3 =", "6");
        probar("1 + ( . 1 ) + . 2 =", "1.3");

        probar("1 + ( 2 ) qrt", "4");
        probar("1 + ( 2 ) qrt =", "5");

        probar("1 + 2 = (", "0");
        probar("1 + 2 = ( 3", "3");
        probar("1 + 2 = ( 3 )", "3");
        probar("1 + 2 = ( 3 ) =", "3");

        probar("1 + ( 3 ) +", "4");
        probar("1 + ( 3 ) + 5", "5");
        probar("1 + ( 3 ) + 5 =", "9");

        probar("1 + 2 qrt (", "0");
        probar("1 + 2 qrt ( 3", "3");
        probar("1 + 2 qrt ( 3 )", "3");
        probar("1 + 2 qrt ( 3 ) =", "4");

        probar("2 + ( 3 qrt ( 3 ) ) =", "5");
        probar("( ( 2 ) ) =", "2");
    }

    @Test
    public void testErrores() {
        System.out.println();
        System.out.println("--------- test de operaciones con errores");
        probar("1 / 0 =", "E");
        probar("1 / 0 = 3", "E");
        probar("1 / 0 = 3 AC", "0");
        probar("1 / 0 + 2 = AC", "0");
        probar("2 MS 1 / 0 MR", "2");

        probar("E MS + 2 MR C", "0");
        probar("1 MS E MR", "1");

        probar("1 + 1 = AC + 3 =", "3");
        probar("1 + 1 = AC qrt =", "0");

        probar("2 + 2 = sqrt + 2 = ", "4");
    }

    @Test
    public void testEntrada() {
        System.out.println();
        System.out.println("--------- test de los controles sobre lo que se introduce");

        System.out.println("se comprueba que no se puede introducir un numero mas grande que el permitido");
        String longitudMaxima = "";
        String objetivo = "";
        for(int i=0; i<PilaOperacion.LONGITUD; i++) {
            longitudMaxima = longitudMaxima.concat(" 1");
            objetivo = objetivo.concat("1");
        }
        String longitudExcesiva = longitudMaxima.concat(" 1");
        probar(longitudExcesiva+" =", objetivo);

        System.out.println("lo mismo que antes pero para un numero decimal");
        longitudMaxima = "";
        objetivo = "";
        for(int i=0; i<PilaOperacion.LONGITUD; i++) {
            longitudMaxima = longitudMaxima.concat(" 1");
            objetivo = objetivo.concat("1");
        }
        longitudExcesiva = longitudMaxima.concat(" .");
        probar(longitudExcesiva+" =", objetivo);
        
    }

    @Test
    public void testC() {
        System.out.println();
        System.out.println("--------- test de C");
        probar("1 C", "0");
        probar("1 C =", "0");

        probar("1 + C", "0");
        probar("1 + C =", "1");
        probar("1 + C 2", "2");
        probar("1 + C 2 =", "3");

        probar("1 + 2 C", "0");
        probar("1 + 2 C 3", "3");
        probar("1 + 2 C 3 =", "4");

        probar("1 + 2 qrt C", "0");
        probar("1 + 2 qrt C 5", "5");
        probar("1 + 2 qrt C 5 =", "6");

        probar("1 + 2 qrt = C", "0");
        probar("1 + 2 qrt = C =", "0");
    }

}
