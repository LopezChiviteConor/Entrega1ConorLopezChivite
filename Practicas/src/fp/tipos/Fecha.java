/* Creador: Conor López Chivite
 * Fecha: 20/03/2024
 * Entrega 1 Java
 */

package fp.tipos;

import java.util.List;
import java.util.Objects;

import us.lsi.tools.Preconditions;

public record Fecha(Integer año, Integer mes, Integer dia) {
	
	//Propiedades derivadas
	public String nombreMes() {
		List<String> meses= List.of("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
		return meses.get(this.mes() - 1);
	}
	
	public String diaSemana() {
		List<String> dias = List.of("Sabado", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
		return dias.get(Fecha.congruenciaZeller(this.año(), this.mes(), this.dia()));
	}
	
	public Fecha sumarDias(Integer num) {
		Integer nuevoDia = this.dia();
		Integer nuevoMes = this.mes();
		Integer nuevoAño = this.año();
		while (num >= 0) {
			Integer diasEnMes = Fecha.diasEnMes(nuevoAño, nuevoMes);
			if (this.dia() + num <= diasEnMes) {
                nuevoDia = nuevoDia + num;
                break;
			} else {
				num = num - (diasEnMes - nuevoDia + 1);
				nuevoDia = 1;
				if (nuevoMes == 12) {
					nuevoMes = 1;
					nuevoAño = nuevoAño + 1;
				} else {
					nuevoMes = nuevoMes + 1;
				}
			}
		}
		return Fecha.of(nuevoAño, nuevoMes, nuevoDia);
	}
	
	public Fecha restarDias(Integer num) {
		Integer nuevoDia = this.dia();
		Integer nuevoMes = this.mes();
		Integer nuevoAño = this.año();
		while (num >= 0) {
			if (this.dia() - num >= 1) {
                nuevoDia = nuevoDia - num - 1;
                break;
			} else {
				num = num - (nuevoDia - 1);
				if (nuevoMes == 1) {
					nuevoMes = 12;
					nuevoAño = nuevoAño - 1;
				} else {
					nuevoMes = nuevoMes - 1;
				}
				nuevoDia = Fecha.diasEnMes(nuevoAño, nuevoMes - 1);
			}
		}
		return Fecha.of(nuevoAño, nuevoMes, nuevoDia);
	}
		
	public Integer diferenciaEnDias(Fecha otraFecha) {
		Integer diasFecha1 = 0;
		Integer diasFecha2 = 0;
		Integer diasAños = 0;
		Integer resultado = 0;
		for (Integer i = 1; i < this.mes(); i++) {
			diasFecha1 = diasFecha1 + Fecha.diasEnMes(this.año(), i);
			resultado = resultado - diasFecha1;
		}
		
		for (Integer i = 1; i < otraFecha.mes(); i++) {
			diasFecha2 = diasFecha2 + Fecha.diasEnMes(otraFecha.año(), i);
		}
		
		for (int i = this.año() + 1; i < otraFecha.año(); i++) {
			if (Fecha.esAñoBisiesto(i) == true) {
				diasAños = diasAños + 366;
			} else {
				diasAños = diasAños + 365;
			}
        }
		return diasAños + diasFecha2 - diasFecha1 + otraFecha.dia() - this.dia();
	}
	
	//Metodos de factoria
	
	public static Fecha of(Integer año, Integer mes, Integer dia) {
		Preconditions.checkArgument((mes > 0) & (mes <= 12), "Mes no valido");
		Integer numDias = Fecha.diasEnMes(año, mes);
		Preconditions.checkArgument((dia > 0) & (dia <= numDias), "Dia no valido");	
		return new Fecha(año, mes, dia);
	}
	
	public static Fecha parse(String text) {
		String[] partes = text.split("-");
		Integer año = Integer.parseInt(partes[0]);
		Integer mes = Integer.parseInt(partes[1]);
		Integer dia = Integer.parseInt(partes[2]);
		return Fecha.of(año,mes,dia);
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
		Fecha other = (Fecha) obj;
		return Objects.equals(año, other.año) && Objects.equals(dia, other.dia) && Objects.equals(mes, other.mes);
	}
	
	//Metodos
	
	public static boolean esAñoBisiesto(Integer año) {
		if ((año % 4 == 0) & (año % 100 != 0)) {
			return true;
		} else if(año % 400 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Integer diasEnMes(Integer año, Integer mes) {
		Integer dias = 0;
		switch (mes) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dias = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				dias = 30;
				break;
			case 2:
				if (Fecha.esAñoBisiesto(año) == true) {
					dias = 29;
				} else {
					dias = 28;
				}
				break;
		}
		return dias;
	}
	
	public static Integer congruenciaZeller(Integer año, Integer mes, Integer dia) {
		if (mes < 3) {
            mes = mes + 12;
            año = año - 1;
        }
		Integer k = año % 100;
        Integer j = año / 100;
        
        Integer h = (dia + ((13 * (mes + 1)) / 5) + k + (k / 4) + (j / 4) + (5 * j)) % 7;
        return h;
	}
}
