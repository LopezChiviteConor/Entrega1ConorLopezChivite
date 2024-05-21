/* Creador: Conor López Chivite
 * Fecha: 20/03/2024
 * Entrega 1 Java
 */

package fp.funciones;

import java.lang.Math;
import us.lsi.tools.Preconditions;
import java.util.stream.LongStream;
import java.util.ArrayList;
import java.util.List;

public class Funciones {
	public static Boolean primo(Integer num) {
		Boolean resultado = true;
		Preconditions.checkArgument(num > 1, "El numero debe ser entero");
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				resultado = false;
				break;
			}
		}
		return resultado;
	}
	
	public static Double combinatorio(Integer k, Integer n) {
		Preconditions.checkArgument(n >= k, "k debe ser menor o igual a n");
		Double FactorialN = Double.valueOf((double) LongStream.rangeClosed(1, n).reduce(1, (long x, long y) -> x*y));
		Double FactorialK = Double.valueOf((double) LongStream.rangeClosed(1, k).reduce(1, (long x, long y) -> x*y));
		Double FactorialNmenosK = Double.valueOf((double) LongStream.rangeClosed(1, n - k).reduce(1, (long x, long y) -> x*y));
		return FactorialN/(FactorialK*FactorialNmenosK);
	}
	
	public static Double formula(Integer k, Integer n) {
		Preconditions.checkArgument(n >= k, "k debe ser menor o igual a n");
		Double sumatorio = 0.0;
		for (Integer i = 0; i <= k; i++) {
			Double calculo = Math.pow((-1), i) * Funciones.combinatorio(i, k) * Math.pow(k - i, n);
			sumatorio = sumatorio + calculo;
		}
		Double FactorialK = Double.valueOf((double) LongStream.rangeClosed(1, k).reduce(1, (long x, long y) -> x*y));
		return (1/FactorialK)*sumatorio;
	}
	
	public static List<Integer> ejercicioListas(List<Integer> listaA, List<Integer> listaB) {
		Preconditions.checkArgument(listaA.size() == listaB.size(), "Las listas no tienen el mismo tamaño");
		List<Integer> listaC = new ArrayList<>();
		for (int i = 0; i < listaA.size(); i++) {
			listaC.add(listaA.get(i) - listaB.get(i));
		}
		return listaC;
	}
	
	public static String ejercicioListas2(List<String> ls) {
		String resultado = ls.get(0);
		for (String i : ls) {
			if (i.length() > resultado.length()) {
				resultado = i;
			}
		}
		return resultado;
	}
	
	//Ejercicios defensa.
	//Apartado A
	public static Integer funcionP2(Integer n, Integer k, Integer i) {
		Preconditions.checkArgument(n >= k, "N no es menor o igual que k");
		Preconditions.checkArgument(i < k+1, "i no es menor que k + 1");
		Integer resultado = n - i;
		for (Integer j = i + 1; j < (k-2); j++) {
			resultado = resultado*(n - j);
		}
		return resultado;
	}
	
	//Apartado B
	public static Double funcionC2(Integer n, Integer k) {
		Preconditions.checkArgument(n > k, "n no es mayor que k");
		return Funciones.combinatorio(k + 1, n);
	}
	
	//Apartado C
	public static Double funcionS2(Integer n, Integer k) {
		Preconditions.checkArgument(n >= k, "n no es mayor o igual que k");
		Double FactorialK = Double.valueOf((double) LongStream.rangeClosed(1, k).reduce(1, (long x, long y) -> x*y));
		Double FactorialKmas2 = Double.valueOf((double) LongStream.rangeClosed(1, k + 2).reduce(1, (long x, long y) -> x*y));
		Double sumatorio = 0.;
		for (Integer i = 0; i <= k; i++) {
			Double calculo = Math.pow((-1), i) * Funciones.combinatorio(i, k) * Math.pow(k - i, n);
			sumatorio = sumatorio + calculo;
		}
		return (FactorialK / FactorialKmas2) * sumatorio;
	}
}
