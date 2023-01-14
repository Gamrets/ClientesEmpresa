package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {

	private static final int NUM_MAX_CLIENTES = 3;
	static Clientes clientes = new Clientes(NUM_MAX_CLIENTES);
	
	
	public static void main(String[] args) {
		
		
		Opcion opcion;

		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		} while (opcion != Opcion.SALIR);
		System.out.println("Adios");
	
		
	}
	
	private static void insertarCliente() {
		
		try {
			Cliente cliente = Consola.leerClientes();

			clientes.insertar(cliente);
			System.out.println("El cliente " + cliente + " ha sido insertado con éxito en el sistema.");
			System.out.println("Disponibles " + (clientes.getCapacidad() - clientes.getTamano()) + " clientes para insertar en el sistema.");
	
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void buscarCliente() {
		
		
		try {
			Cliente cliente = Consola.leerDniCliente();

			if ((clientes.buscar(cliente)) == null) {
				System.out.println("Dicho cliente no esta reservado en el sistema.");
			} else {
				System.out.println(cliente);
			}
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void borrarCliente() {
		
		try {
			
			Cliente cliente = Consola.leerDniCliente();
			
			clientes.borrar(cliente);
			
			System.out.println("Cliente: " + cliente + " ha sido borrado");

			System.out.println("Disponibles " + (clientes.getCapacidad() - clientes.getTamano())
					+ " clientes para insertar en el sistema.");
			
			
		}catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void mostrarClientes() {

		// Si el tamaño es 0 no puede haber ningun cliente insertado.
		if (clientes.getTamano() == 0) {
			System.out.println("El sistema no tiene clientes en su registro.");
		}

		for (int i = 0; i < clientes.getTamano(); i++) {
			System.out.println(clientes.get()[i]);

		}

	}
	
	
	private static void mostrarClientesFecha() {
		
		try {
			
			LocalDate fechaCliente = Consola.leerFechaNacimiento();

			boolean encontrado = false;
	
			for (int i = 0; i < clientes.getTamano(); i++) {
				if (clientes.get()[i].getFechaNacimiento().equals(fechaCliente)) {
					
					encontrado = true;
					System.out.println(clientes.get()[i].toString());
				}
			}

			if (clientes.getTamano() == 0) {
				System.out.println("El sistema no tiene clientes en su registro.");
			} 
			
			if (encontrado == false) {
				System.out.println("El sistemo no tiene cliente con dada fecha de nacimiento");
			}
			
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void ejecutarOpcion(Opcion opcion) {
		switch (opcion) {
		case SALIR:
			break;
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case MOSTRAR_CLIENTES_FECHA:
			mostrarClientesFecha();
			break;
		case MOSTRAR_CLIENTES:
			mostrarClientes();
			break;
		default:

		}
	}

}