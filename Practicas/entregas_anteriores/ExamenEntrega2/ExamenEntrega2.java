package ExamenEntrega2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lab.dataframe.DataFrame;
import lab.dataframe.DataFrame.*;
import us.lsi.tools.Preconditions;

public class ExamenEntrega2 {
	public static DataFrame emptyDataFrame (DataFrame df) {
		List<String> columnas_nombres = df.columNames();
		Map<String,List<String>> datos = new HashMap<>();
		for (String a: columnas_nombres) {
			List<String> lista_vacia = new ArrayList<>();
			datos.put(a, lista_vacia);
		}
		return DataFrame.of(datos, columnas_nombres);
	}
	
	public static DataFrame addDataFrame (DataFrame df1, DataFrame df2) {
		DataFrame resultado = df1;
		List<String> columnas_nombres_df2 = df2.columNames();
		for (Integer i = 0; i < columnas_nombres_df2.size(); i++) {
			List<String> datos_df2 = df2.colum(i);
			df1.addColum(columnas_nombres_df2.get(i), datos_df2);
		}
		return resultado;
	}
	
	public static DataFrame removeColumIndex (DataFrame df, Integer ci) {
		List<String> columnas_nombres = df.columNames();
		Preconditions.checkArgument(ci < columnas_nombres.size() && ci >= 0, "Index no válido");
		return df.removeColum(columnas_nombres.get(ci));
	}
	
	public static List<DataFrame> divideDataFrame (DataFrame df, int ci) {
		Preconditions.checkArgument(ci > 0 && ci < df.columNumber(), "ci no es válido");
		
		List<String> nombres_columna = df.columNames();
		List<String> nombres1 = nombres_columna.subList(0, ci + 1);
		List<String> nombres2 = nombres_columna.subList(ci + 1, nombres_columna.size());
		
		
		DataFrame parte1 = df;
		DataFrame parte2 = df;
		

		for (String a : nombres1) {
			parte2.removeColum(a);
		}
		
		for (String a : nombres2) {
			parte1.removeColum(a);
		}
		
		return List.of(parte1, parte2);
	}
	
	public static void main(String[] args) {
		//Tests del examen
		DataFrame d_personas = DataFrame.parse("src/ficheros/personas.csv", List.of("Nombre","Apellido","Edad","Altura","Fecha"));
		DataFrame df_vacio = ExamenEntrega2.emptyDataFrame(d_personas);
		System.out.println("DataFrame vacio:");
		System.out.println(df_vacio);
		
		System.out.println("Eliminar columna nº 3 del dataframe personas:");
		System.out.println(ExamenEntrega2.removeColumIndex(d_personas, 2));
		
		//Revision de las restricciones de removeColumIndex (debe dar fallo):
		//System.out.println(ExamenEntrega2.removeColumIndex(d_personas, 8));
		//System.out.println(ExamenEntrega2.removeColumIndex(d_personas, -1));
		
		
		System.out.println("Division a partir de la columna 3 del dataframe Personas:");
		List<DataFrame> df_dividido = ExamenEntrega2.divideDataFrame(d_personas, 2);
		System.out.println(df_dividido);
		/*
		System.out.println("Suma de dataframes: ");
		System.out.println(ExamenEntrega2.addDataFrame(df_dividido.get(0), df_dividido.get(1)));
		*/
	}
}
