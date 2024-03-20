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
}
