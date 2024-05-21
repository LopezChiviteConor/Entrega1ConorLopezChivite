package lab.bancos;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.tools.File2;

public class Cuentas {
	//Atributos
	private static Cuentas gestorDeCuentas = null;
	private Set<Cuenta> cuentas;
	private Map<String, Cuenta> cuentasIban;
	
	//Constructor
	private Cuentas(Set<Cuenta> cuentas) {
		super();
		this.cuentas = cuentas;
		this.cuentasIban = this.cuentas.stream().collect(Collectors.toMap(e->e.iban(),e->e));
	}
	
	public static Cuentas of() {
		if (gestorDeCuentas == null) 
			gestorDeCuentas = Cuentas.parse("ficherosbanco/empleados.txt");
			return Cuentas.gestorDeCuentas;
	}
	
	public static Cuentas parse(String fichero) {
		Set<Cuenta> cuentas = File2.streamDeFichero(fichero,"UTF-8")
    			.map(ln->Cuenta.parse(ln))
    			.collect(Collectors.toSet());
		Cuentas.gestorDeCuentas = new Cuentas(cuentas);
		return Cuentas.gestorDeCuentas;
	}
	
	public Set<Cuenta> todas() {
		return this.cuentas;
	}
	
	public Optional<Cuenta> cuentaIban(String iban) {
		return Optional.ofNullable(this.cuentasIban.getOrDefault(iban, null));
	}
	
	public Integer size() {
		return this.cuentas.size();
	}
	
	public Cuenta cuentaIndex(Integer index) {
		return this.cuentas.stream().toList().get(index);
	}

	@Override
	public String toString() {
		return this.cuentas.stream()
				.map(p -> p.toString())
				.collect(Collectors.joining("\n\t"));
	}
	
	
}
