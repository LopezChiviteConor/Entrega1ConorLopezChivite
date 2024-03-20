/* Creador: Conor López Chivite
 * Fecha: 20/03/2024
 * Entrega 1 Java
 */

package fp.funciones.test;

import java.util.List;
import fp.funciones.Funciones;


public class FuncionesTest {
	public static void test() {
	Boolean esPrimo = Funciones.primo(5);
	Boolean noEsPrimo = Funciones.primo(10);
	System.out.println("Test de primalidad para 5: " + esPrimo);
	System.out.println("Test de primalidad para el 10: " + noEsPrimo);
	Double numCombinatorio = Funciones.combinatorio(2, 3);
	System.out.println("Numero combinatorio, k = 2, n = 3 -> " + numCombinatorio);
	Double formula = Funciones.formula(2, 3);
	System.out.println("Uso de la formula donde k = 2 y n = 3 -> " + formula);
	List<Integer> listaA = List.of(1, 2, 3, 4);
	List<Integer> listaB = List.of(2, 3, 4, 5);
	List<Integer> listaC = Funciones.ejercicioListas(listaB, listaA);
	System.out.println(String.format("Lista B %s - Lista A %s = %s", listaB, listaA, listaC));
	List<String> listaStrings = List.of("Plantasia", "Symphony for a Spider Plant", "Baby's Tear Blues", "Ode To an African Violet");
	System.out.println(String.format("String mas largo: %s", Funciones.ejercicioListas2(listaStrings)));
	}
	
	public static void main(String[] args) {
		test();
	}
}
