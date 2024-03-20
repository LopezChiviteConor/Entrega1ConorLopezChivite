/* Creador: Conor López Chivite
 * Fecha: 20/03/2024
 * Entrega 1 Java
 */

package fp.tipos.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fp.tipos.Fecha;


public class TestFecha {
    public static void main(String[] args) {
        // Crear una fecha
        Integer anio = 2024;
        Integer mes = 3;
        Integer dia = 8;
 
        // Crear una instancia de Fecha
        Fecha fecha = Fecha.of(anio, mes, dia);
 
        // Crear una instancia de LocalDate
        LocalDate localDate = LocalDate.of(anio, mes, dia);
 
        // Comprobar que los mÃ©todos comunes coinciden
        System.out.println("Fecha: " + fecha);
        System.out.println("LocalDate: " + localDate);
 
        System.out.println("\nFecha.esAnioBisiesto(): " + Fecha.esAñoBisiesto(fecha.año()));
        System.out.println("LocalDate.isLeapYear(): " + localDate.isLeapYear());
 
        System.out.println("\nFecha.diasEnMes(): " + Fecha.diasEnMes(fecha.año(), fecha.mes()));
        System.out.println("LocalDate.lengthOfMonth(): " + localDate.lengthOfMonth());
 
        Fecha fechaSumada = fecha.sumarDias(10);
        LocalDate localDateSumada = localDate.plusDays(10);
 
        System.out.println("\nFecha.sumardias(10): " + fechaSumada);
        System.out.println("LocalDate.plusDays(10): " + localDateSumada);
 
        Fecha fechaRestada = fecha.restarDias(10);
        LocalDate localDateRestada = localDate.minusDays(10);
 
        System.out.println("\nFecha.restarDias(10): " + fechaRestada);
        System.out.println("LocalDate.minusDays(10): " + localDateRestada);
        
        Integer diferenciaEnDias = fecha.diferenciaEnDias(fechaSumada);
        long diferenciaEnDiasLocalDate = ChronoUnit.DAYS.between(localDate, localDateSumada);
 
        System.out.println("\nFecha.diferenciaEnDias(fechaSumada): " + diferenciaEnDias);
        System.out.println("ChronoUnit.DAYS.between(localDate, localDateSumada): " + diferenciaEnDiasLocalDate);
    }
}
