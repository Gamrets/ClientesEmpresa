package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	
	private Consola() {

	}
	
	public static void mostrarMenu() {

		System.out.println("0. Salir.");
		System.out.println("1. Insertar cliente.");
		System.out.println("2. Buscar cliente.");
		System.out.println("3. Borrar cliente.");
		System.out.println("4. Mostrar clientes que hayan nacido en una fecha concreta.");
		System.out.println("5. Mostrar todos los clientes.");
	}
	
	public static Opcion elegirOpcion() {
		int opcion = 0;
		// values​() devuelve un array que contiene una lista de las constantes de
		// enumeración.
		Opcion[] opciones = Opcion.values();
		do {
			System.out.print("Elegir opción: ");
			opcion = Entrada.entero();
		} while (opcion < 0 || opcion > opciones.length - 1);

		return opciones[opcion];
	}
	
	
	public static Cliente leerClientes() {

		System.out.print("Introduzca el nombre del cliente: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduzca el dni del cliente: ");
		String dni = Entrada.cadena();
		System.out.print("Introduzca el teléfono del cliente: ");
		String telefono = Entrada.cadena();
		System.out.print("Introduzca el correo del cliente: ");
		String correo = Entrada.cadena();
		System.out.print("Introduzca fecha de nacimiento del cliente: ");
		LocalDate fecha = leerFechaNacimiento(); 
		return new Cliente(nombre, dni, correo,telefono,fecha);
	}
	
	public static Cliente leerDniCliente() {
		
		System.out.print("Introduzca el dni del cliente: ");
		String dni = Entrada.cadena();
		

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Cliente.FORMATO_FECHA.replace(" HH:mm", ""));

        LocalDate fecha =   LocalDate.parse("29/07/2023", dtf);

        Cliente cliente = new Cliente("Juan",dni,"juan@gmail.com","633658895",fecha);

        return cliente;	

	}
	
	
	public static LocalDate  leerFechaNacimiento() {
		
		LocalDate fecha = null;
		// Eliminamos la hora del patron FORMATO_FECHA con el método replace.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Cliente.FORMATO_FECHA);

		System.out.printf("Introduza una fecha(dd/MM/yyyy):");
		String fechaStr = Entrada.cadena();
		try {
			fecha = LocalDate.parse(fechaStr, dtf);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la fecha no es correcto. Formato correcto (dd/MM/yyyy)");
		}

		return fecha;
		
	}
}