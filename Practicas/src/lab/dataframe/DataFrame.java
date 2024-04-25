package lab.dataframe;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface DataFrame {
	//
	// Métodos estáticos o funcionalidades del tipo
	public static DataFrame of(List<String> columNames,List<List<String>> rows) {  
		return DataFrameImpl.of(columNames,rows);
	}
	//
	public static DataFrame of(Map<String,List<String>> data) {
		return DataFrameImpl.of(data);
	}
	//
	public static DataFrame of(Map<String,List<String>> data, List<String> columNames) {
		return DataFrameImpl.of(data, columNames);
	}
	//
	public static DataFrame parse(String file) {
		return DataFrameImpl.parse(file);
	}
	//
	public static DataFrame parse(String file, List<String> columNames) {
		return DataFrameImpl.parse(file, columNames);
	}
	//
	public static DateTimeFormatter dateFormat() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fmt;
	}
	//
	public static DateTimeFormatter timeFormat() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
		return fmt;
	}
	//
	public static DateTimeFormatter dateTimeFormat() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return fmt;
	}
	//
	public static String string(Object r) {
		return DataFrameImpl.string(r);
	}
	//
	public static <R> R parse(String text, Class<R> type) {
		return DataFrameImpl.parse(text, type);
	}
	//
	// Métodos imperativos o propiedades del tipo
	List<String> columNames();
	Integer columNumber();
	List<String> colum(String name);
	List<String> colum(Integer index);
	<R> List<R> colum(String name, Class<R> type);
	<R> List<R> colum(Integer index, Class<R> type);
	Boolean columAllDifferent(String name);
	String propertie(List<String> row, String colum);
	<R> R propertie(List<String> row, String colum, Class<R> type);
	String cell(Integer row,String colum);
	String cell(Integer row,Integer colum);
	String cell(String columValue,String colum, String columCell);
	Integer rowNumber();
	List<String> row(Integer n);
	List<String> row(String row, String colum);
	List<List<String>> rows();
	DataFrame head();
	// Mostrar los primeros n registros (por defecto, n=5)
	DataFrame head(Integer n);
	DataFrame tail();
	// Mostrar los últimos n registros (por defecto, n=5)
	DataFrame tail(Integer n);
	DataFrame slice(Integer n,Integer m);
	DataFrame filter(Predicate<List<String>> p);
	// Ordena según los valores devueltos por la función order
	<E extends Comparable<? super E>> DataFrame sortBy(Function<List<String>,E> order, Boolean reverse);
	<R> DataFrame groupBy(List<String> columNames,String newColumn,BinaryOperator<R> op, Function<List<String>,R> value);
	DataFrame addColum(String newColum, List<String> datos);
	DataFrame addCalculatedColum(String newColum,Function<List<String>,String> f);
	DataFrame removeColum(String colum);
	String toString();	      
}
