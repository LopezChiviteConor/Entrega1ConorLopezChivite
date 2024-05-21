/* Creador: Conor López Chivite*/

package lab.dataframe;
import lab.dataframe.DataFrame.*;

import java.util.List;
import us.lsi.tools.File2;
import us.lsi.tools.Preconditions;
public class TestDataFrame {
	public static void main(String[] args) {
		//Creamos los DataFrames de los ficheros.
		DataFrame d_personas = DataFrame.parse("src/ficheros/personas.csv", List.of("Nombre","Apellido","Edad","Altura","Fecha"));
		DataFrame d_mascotas = DataFrame.parse("src/ficheros/mascotas.csv", List.of("IdMascota","Nombre","Especie","Sexo","Ubicacion","Estado"));
		DataFrame d_pp = DataFrame.parse("src/ficheros/pp.csv", List.of("Titulo", "Valoracion", "Anyo_estreno"));
		
		//Aqui empiezan los test del DataFrame de Personas
		
		System.out.println("DATAFRAME DE PERSONAS:");
		System.out.println("DataFrame original:");
		System.out.println(d_personas);
		
		//Como el CSV de Personas solo tiene 5 filas, se devolveran las 3 primeras, 3 últimas y 3 filas intermedias.
		System.out.println("3 primeras filas:");
		System.out.println(d_personas.head(3));
		System.out.println("3 últimas filas:");
		System.out.println(d_personas.tail(3));
		System.out.println("3 filas intermedias:");
		System.out.println(d_personas.slice(2,4));
		System.out.println("Eliminación de la columna Nombre:");
		System.out.println(d_personas.removeColum("Nombre"));
		System.out.println("Filtrado de personas de mas de 60 años:");
		System.out.println(d_personas.filter(lista -> DataFrame.parse(lista.get(2), Integer.class) > 60));
		System.out.println("Creacion de columna calculada, donde se calcula el nombre completo:");
		System.out.println(d_personas.addCalculatedColum("Nombre_completo", lista -> lista.get(0)+"" + lista.get(1)));
		System.out.println("Obtenemos la fila de la persona cuya edad es 70 años:");
		System.out.println(d_personas.row("70", "Edad"));
		System.out.println("");
		System.out.println("****************************************************************************************************************");
		System.out.println("");
		//Aqui empieza el test del DataFrame de mascotas
		System.out.println("DATAFRAME DE MASCOTAS:");
		System.out.println("DataFrame original:");
		System.out.println(d_mascotas);
		
		System.out.println("5 primeras filas:");
		System.out.println(d_mascotas.head());
		System.out.println("5 últimas filas:");
		System.out.println(d_mascotas.tail());
		
		System.out.println("10 primeras filas:");
		System.out.println(d_mascotas.head(10));
		System.out.println("10 últimas filas:");
		System.out.println(d_mascotas.tail(10));
		System.out.println("5 filas intermedias:");
		System.out.println(d_mascotas.slice(5,10));
		
		System.out.println("Eliminación de la columna IdMascota:");
		System.out.println(d_mascotas.removeColum("IdMascota"));
		System.out.println("Filtrado de mascotas que sean gatos:");
		System.out.println(d_mascotas.filter(lista -> lista.get(2).equals("G")));
		System.out.println("Creacion de columna calculada, donde se multiplica la columna IdMascota por 2");
		System.out.println(d_mascotas.addCalculatedColum("IdMultiplicado", lista -> String.valueOf((DataFrame.parse(lista.get(0), Integer.class)*2)) ));
		System.out.println("Obtenemos la fila de la mascota llamada Canela:");
		System.out.println(d_mascotas.row("Canela", "Nombre"));
		
		System.out.println("");
		System.out.println("****************************************************************************************************************");
		System.out.println("");
		//Aqui empieza el test del DataFrame de peliculas
		System.out.println("DATAFRAME DE PELICULAS");
		System.out.println("DataFrame original:");
		System.out.println(d_pp);
		
		//Como el fichero solo cuenta con 3 filas, se tomaran la primera, la ultima y la fila intermedia en el testeo de head, tail y slice
		System.out.println("Primera fila:");
		System.out.println(d_pp.head(1));
		System.out.println("Ultima fila:");
		System.out.println(d_pp.tail(1));
		System.out.println("Fila Intermedia:");
		System.out.println(d_pp.slice(1,1));
		
		System.out.println("Eliminación de la columna Valoracion:");
		System.out.println(d_pp.removeColum("Valoracion"));
		System.out.println("Filtrado de peliculas con mas de un 8 de valoraion:");
		System.out.println(d_pp.filter(lista -> DataFrame.parse(lista.get(1), Double.class) > 8.0));
		System.out.println("Creacion de columna calculada, donde se muestra la valoracion sobre 10");
		System.out.println(d_pp.addCalculatedColum("Nombre_completo", lista -> "10/" + lista.get(1)));
		System.out.println("Obtenemos la fila de una pelicula estrenada en 1977");
		System.out.println(d_pp.row("1977", "Anyo_estreno"));
		
	}
}
