package lab.bancos;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Preconditions;
import us.lsi.tools.Stream2;

public class Banco {
	

	private static Banco gestorDeBanco = null;
	private String nombre;
	private Integer codigoPostal;
	private String email;
	private Personas personas;
	private Empleados empleados;
	private Cuentas cuentas;
	private Prestamos prestamos;

	private Banco(String nombre, Integer codigoPostal, String email, Personas personas, Empleados empleados,
			Cuentas cuentas, Prestamos prestamos) {
		super();
		this.nombre = nombre;
		this.codigoPostal = codigoPostal;
		this.email = email;
		this.personas = personas;
		this.empleados = empleados;
		this.cuentas = cuentas;
		this.prestamos = prestamos;
	}

	public static Banco of() {
		String nombre = "Reina Mercedes";
	    Integer codigoPostal = 41012;
	    String email = "bib@us.es";
		String fp = "src/ficherosbanco/personas.txt";
		String fe = "src/ficherosbanco/empleados.txt";
		String fc = "src/ficherosbanco/cuentas.txt";
		String fpt = "src/ficherosbanco/prestamos.txt";
		return Banco.of(nombre, codigoPostal, email, fp, fe, fc, fpt);
	}

	public static Banco of(String nombre, Integer codigoPostal, String email, String fp, String fe, String fc,
			String fpt) {
		if (Banco.gestorDeBanco == null) {
			Personas personas = Personas.parse(fp);
			Empleados empleados = Empleados.parse(fe);
			Cuentas cuentas = Cuentas.parse(fc);
			Prestamos prestamos = Prestamos.parse(fpt);
			Banco.gestorDeBanco = new Banco(nombre, codigoPostal, email, personas, empleados, cuentas, prestamos);
		}
		return Banco.gestorDeBanco;
	}

	public Personas personas() {
		return this.personas;
	}

	public Empleados empleados() {
		return this.empleados;
	}

	public Cuentas cuentas() {
		return this.cuentas;
	}

	public Prestamos prestamos() {
		return this.prestamos;
	}

//	Préstamos gestionados por un empleado

	public String nombre() {
		return nombre;
	}

	public Integer codigoPostal() {
		return codigoPostal;
	}

	public String email() {
		return email;
	}

	public Set<Prestamo> prestamosDeEmpleado(String dni) {
		Set<String> dniEmpleados = this.empleados().todos().stream()
				.map(c -> c.dni())
				.collect(Collectors.toSet());
		Preconditions.checkArgument(dniEmpleados.contains(dni), "No existe el empleado con el dni dado.");
		Set<Prestamo> resultado = this.prestamos().todos().stream()
								.filter(c -> c.dniEmpleado().contains(dni))
								.collect(Collectors.toSet());
		Preconditions.checkArgument(resultado.isEmpty() == false, "El empleado no tiene ningun prestamo asignado");
		return resultado;
	}

//Préstamos de un cliente

	public Set<Prestamo> prestamosDeCliente(String dni) {
		return this.prestamos().todos().stream()
				.filter(c -> c.dniCliente().contains(dni))
				.collect(Collectors.toSet());
	}
// Empleado más joven
	public Optional<Persona> empleadoMasJoven() {
		Set<Persona> empleados = this.empleados().todos().stream()
				.map(c -> c.persona()).collect(Collectors.toSet());
		return empleados.stream().max(Comparator.comparing(Persona::fechaDeNacimiento));
	}
// Número de cuentas de cada cliente
	public Map<String, Integer> numeroDeCuentasDeCliente() {
		return Stream2.groupingReduce(this.cuentas().todas().stream(), s -> s.dni(), (a, b) -> a + b, c -> 1);
	}
	
	public void test() {
		Set<String> dnis = this.personas().dnis();
		Set<String> dniCuentas = 
				this.cuentas().todas().stream()
				.map(c->c.dni())
				.filter(d->dnis.contains(d))
				.collect(Collectors.toSet());
		System.out.println(dniCuentas);
		Set<String> dniEmpleados = 
				this.empleados().todos().stream()
				.map(c->c.dni())
				.filter(d->!dnis.contains(d))
				.collect(Collectors.toSet());
		System.out.println(dniEmpleados);
		Set<String> dniPrestamosClientes = 
				this.prestamos().todos().stream()
				.map(c->c.dniCliente())
				.filter(d->!dnis.contains(d))
				.collect(Collectors.toSet());
		System.out.println(dniPrestamosClientes);
		Set<String> dniPrestamosEmpleados = 
				this.prestamos().todos().stream()
				.map(c->c.dniEmpleado())
				.filter(d->!dnis.contains(d))
				.collect(Collectors.toSet());
		System.out.println(dniPrestamosEmpleados);
		
		//Test de prestamosDeEmpleado, empleadoMasJoven y numeroDeCuentasDeCliente

		System.out.println(this.prestamosDeEmpleado("86921364V"));
		System.out.println(this.empleadoMasJoven());
		System.out.println(this.numeroDeCuentasDeCliente());
		}
	
	public static void main(String[] args) {
		Banco banco = Banco.of();
		banco.test();
	}
	
}
