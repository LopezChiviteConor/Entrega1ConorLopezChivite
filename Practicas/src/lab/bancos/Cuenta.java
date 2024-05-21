package lab.bancos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import us.lsi.tools.Preconditions;

public class Cuenta {
	//Atributos
	private String iban;
	private String dni;
	private LocalDate fechaDeCreacion;
	private Double saldo;
	
	//Constructor
	private Cuenta(String iban, String dni, LocalDate fechaDeCreacion, Double saldo) {
		this.iban = iban;
		this.dni = dni;
		this.fechaDeCreacion = fechaDeCreacion;
		this.saldo = saldo;
	}
	
	public static Cuenta of(String iban, String dni, LocalDate fechaDeCreacion, Double saldo) {
		return new Cuenta(iban,dni,fechaDeCreacion,saldo);
	}
	
	public static Cuenta parse(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String[] partes = data.split(",");
		String iban = partes[0];
		String dni = partes[1];
		LocalDate fechaDeCreacion = LocalDateTime.parse(partes[2], formatter).toLocalDate();
		Double saldo = Double.parseDouble(partes[3]);
		return Cuenta.of(iban, dni, fechaDeCreacion, saldo);
	}
	//getter para iban
	public String iban() {
		return iban;
	}
	
	//getter para dni
	public String dni() {
		return dni;
	}
	
	//getter y setter para saldo (tipo mutable)
	public Double getSaldo() {
        return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	//Operaciones
	
	public void ingresar(Double c) {
		this.setSaldo(this.getSaldo() + c);
	}
	
	public void retirar(Double c) {
		this.setSaldo(this.getSaldo() - c);
	}
	
	//Representación
	@Override
	public String toString() {
		return iban + ", " + saldo;
	}

	//Criterio de igualdad
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(fechaDeCreacion, other.fechaDeCreacion)
				&& Objects.equals(iban, other.iban) && Objects.equals(saldo, other.saldo);
	}
	
}
