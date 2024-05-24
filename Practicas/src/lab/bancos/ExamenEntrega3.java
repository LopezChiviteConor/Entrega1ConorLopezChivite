package lab.bancos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lab.bancos.Questions.Info2;
import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Preconditions;
import us.lsi.tools.Stream2;

public class ExamenEntrega3 {
	
	public static Map<Character, List<Empleado>> empleadosConLetraDNI (Banco banco, Set<Character> letras) {
		Map<Character, List<Empleado>> resultado = new HashMap<>();
		Set<Empleado> empleados = banco.empleados().todos();
		for (Character a: letras) {
			List<Empleado> empleadosPorLetra = empleados.stream()
					.filter(d -> d.dni().charAt(8) == a).collect(Collectors.toList());
			resultado.put(a, empleadosPorLetra);
		}
		return resultado;
	}
	
	public static Double clientesEdadMedia (Banco banco, Integer m) {
		Preconditions.checkArgument(m > 0, "m es negativo");
		Preconditions.checkArgument((m == null) == false, "m es null");
		Set<String> dnis = banco.personas().dnis();
		Set<String> dnisClientes = banco.prestamos().todos().stream()
				.map(c->c.dniCliente())
				.filter(d->dnis.contains(d))
				.collect(Collectors.toSet());
		Set<Persona> clientes = banco.personas().todos().stream()
				.filter(d->dnisClientes.contains(d.dni()))
				.collect(Collectors.toSet());
		
		Set<Integer> EdadesMayor = clientes.stream()
				.map(d -> d.edad()).filter(d -> d > m).collect(Collectors.toSet());
		
		Integer suma = 0;
		for (Integer a: EdadesMayor) {
			suma = suma + a;
		}
		
		return suma.doubleValue()/EdadesMayor.size(); 
	}
	
	public static Set<String> clientesConMenosPrestamos (Banco banco, int n) {
		Preconditions.checkArgument(n > 0, "n es negativo");
		Set<String> resultado = new HashSet<>();
		Set<String> dnis = banco.personas().dnis();
		Set<String> dnisClientes = banco.prestamos().todos().stream()
				.map(c->c.dniCliente())
				.filter(d->dnis.contains(d))
				.collect(Collectors.toSet());
		for (Integer i = 0; i < n; i++) {
			Optional<Persona> clienteEstrella = null;
			Integer minNumCuentas = banco.cuentas().size();
			for (String a: dnisClientes) {
				Integer num = banco.prestamosDeCliente(a).size();
				if (num < minNumCuentas) {
					minNumCuentas = num;
					clienteEstrella = banco.personas().personaDni(a);
					}
				}
			 resultado.add(clienteEstrella.get().dni());
			 dnisClientes.remove(clienteEstrella.get().dni());
			}
		return resultado;
		}
	
	public static void test(Banco b) {
		Set<Character> letras = new HashSet<>();
		letras.add('A');
		System.out.println(ExamenEntrega3.empleadosConLetraDNI(b, letras));
		System.out.println(ExamenEntrega3.clientesEdadMedia(b, 10));
		// Para verificar que no puede aceptar numeros negativos
		//System.out.println(ExamenEntrega3.clientesEdadMedia(b, -10));
		System.out.println(ExamenEntrega3.clientesConMenosPrestamos(b, 3));
		// Para verificar que no puede aceptar numeros negativos
		//System.out.println(ExamenEntrega3.clientesConMenosPrestamos(b, -3));
	}
	public static void main(String[] args) {
		Banco banco = Banco.of();
		ExamenEntrega3.test(banco);
	}
}
