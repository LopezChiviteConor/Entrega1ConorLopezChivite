package lab.bancos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Preconditions;
import us.lsi.tools.Stream2;

public class Questions {

	//	Vencimiento de los prestamos de un cliente
	public static Set<LocalDate> vencimientoDePrestamosDeCliente(Banco banco,String dni) {
		Set<String> dniPrestamos = banco.prestamos().todos().stream()
									.map(c -> c.dniCliente())
									.collect(Collectors.toSet());
		Preconditions.checkArgument(dniPrestamos.contains(dni), "No existen prestamos con este dni");
		Set<Prestamo> prestamos = banco.prestamosDeCliente(dni);
		return prestamos.stream().map(a -> a.fechaVencimiento()).collect(Collectors.toSet());
	}
	//	Persona con más prestamos
	public static Persona clienteConMasPrestamos(Banco banco) {
		Set<String> dnis = banco.personas().dnis();
		Set<String> dnisClientes = banco.prestamos().todos().stream()
				.map(c->c.dniCliente())
				.filter(d->dnis.contains(d))
				.collect(Collectors.toSet());
		
		Optional<Persona> clienteEstrella = null;
		Integer maximoNumCuentas = 0;
		for (String a: dnisClientes) {
			Integer num = banco.prestamosDeCliente(a).size();
			if (num > maximoNumCuentas) {
				maximoNumCuentas = num;
				clienteEstrella = banco.personas().personaDni(a);
				}
			}
		return clienteEstrella.get();
		}
		


	//	Cantidad total de los crétditos gestionados por un empleado
	public static Double cantidadPrestamosEmpledado(Banco banco,String dni) {
		Set<String> dniEmpleados = banco.empleados().todos().stream()
				.map(c -> c.dni())
				.collect(Collectors.toSet());
		Preconditions.checkArgument(dniEmpleados.contains(dni), "No existe el empleado con el dni dado.");
		return banco.prestamosDeEmpleado(dni).stream()
			   .map(c -> c.cantidad())
			   .collect(Collectors.summingDouble(Double::doubleValue));
	}
	
	//	Empleado más longevo
	public static Persona empleadoMasLongevo(Banco banco) {
		Set<Persona> empleados = banco.empleados().todos().stream()
				.map(c -> c.persona()).collect(Collectors.toSet());
		return empleados.stream().min(Comparator.comparing(Persona::fechaDeNacimiento)).get();
	}
	//	Interés mínimo, máximo y medio de los préstamos
	public static record Info(Double min, Double max, Double mean) {
		public String toString() {
			return String.format("(%.2f,%.2f,%.2f)",this.min(),this.max(),this.mean());
		}
	}
	public static  Info rangoDeIntereseDePrestamos(Banco banco) {
		Set<Prestamo> prestamos = banco.prestamos().todos();
		Double min = prestamos.stream().min(Comparator.comparing(Prestamo::cantidad)).get().cantidad();
		Double max = prestamos.stream().max(Comparator.comparing(Prestamo::cantidad)).get().cantidad();
		Double mean = (prestamos.stream()
				     .map(c -> c.cantidad())
				     .collect(Collectors.summingDouble(Double::doubleValue)))/prestamos.size();
		return new Info(min, max, mean);
	}

	//	Número de préstamos por mes y año
	public static record Info2(Integer mes, Integer año) {
		public String toString() {
			return String.format("(%d,%d)",this.mes(),this.año());
		}
	}
	
	public static Map<Info2,Integer> numPrestamosPorMesAño(Banco banco) {
		Set<Prestamo> prestamos = banco.prestamos().todos();
		return Stream2.groupingReduce(prestamos.stream(), c -> new Info2(c.fechaComienzo().getMonth().getValue(), c.fechaComienzo().getYear()), (a, b) -> a + b, d -> 1 );
	}
	
	//Metodos Defensa
	
	
	public static void test(Banco banco) {
		System.out.println(Questions.vencimientoDePrestamosDeCliente(banco, "70952354E"));
		System.out.println(Questions.clienteConMasPrestamos(banco));
		System.out.println(Questions.cantidadPrestamosEmpledado(banco, "34759012D"));
		System.out.println(Questions.rangoDeIntereseDePrestamos(banco));
		System.out.println(Questions.numPrestamosPorMesAño(banco));
	}
	
	public static void main(String[] args) {
		Banco banco = Banco.of();
		Questions.test(banco);
	}
}


