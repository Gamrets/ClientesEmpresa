package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
	
	private int capacidad; // cantidad maxima de elementos de array
	private int tamano; // cantidad de elemntos que hay en array
	
	
	private Cliente[] coleccionClientes;
	
	
	// cantidad de espacio que puedo ejercer a la array
		public Clientes(int capacidad) {

			if (capacidad <= 0) { // como la array no puede tener espacio negativo saco el error
				throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
			}
			this.capacidad = capacidad; // inicializo el parametro
			coleccionClientes = new Cliente[capacidad]; // creo la array
		}
	
	public Cliente[] get() {

		return copiaProfundaClientes();
	}
	

	public int getCapacidad() { // devuelve cuantos elementos en total puedo almacenar en el array
		return capacidad;
	}

	public int getTamano() {  // me devuelve numero de elmentos que hay actualmente en el array
		
		return tamano;
	}
	
	
	private boolean tamanoSuperado(int indice) {

		return indice >= tamano;

	}

	private boolean capacidadSuperada(int indice) {

		return indice >= capacidad;

	}

	private Cliente[] copiaProfundaClientes() {

		Cliente[] copiaCliente = new Cliente[capacidad]; // creo una array de la misma capacidad que tiene array que voy a copiar

		for (int i = 0; !tamanoSuperado(i); i++) { // voy recorriendo el array

			copiaCliente[i] = new Cliente(coleccionClientes[i]); // y le voy asignado valores que tiene array original
		}

		return copiaCliente;
	}

	private int buscarIndice(Cliente cliente) { // recorre arry y localiza en que posicion se encuetnra cliente que se esta buscando y la devuelve

		int indice = tamano + 1;
		boolean encontrado = false;

		
    // la variable boleana ayuda a no recorrer el array si se encontro elemento que se esta buscando

		for (int i = 0; !tamanoSuperado(i) && !encontrado; i++) {

			if (coleccionClientes[i].equals(cliente)) {
				encontrado = true;
				indice = i;
			}
		}
		return indice;
	}
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (capacidadSuperada(tamano)) { // si array supera a la capacidad significa que esta lleno
			throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
		}

		int indice = buscarIndice(cliente);

		if (tamanoSuperado(indice)) { // Si supera al tamaño significa que hay huecos y se puede agregar otro
			coleccionClientes[tamano] = new Cliente(cliente);
			tamano++;

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");

		}

	}
	
	public Cliente buscar(Cliente cliente) { // Busco indice/posicion donde esta esta cliente

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}

		int indice = buscarIndice(cliente);// Localizo posicion en que se encuentra cliente

		if (tamanoSuperado(indice)) { // compara tamaño y resultados de la busqueda

			return null; // en caso q lo supera no hay clientes

		} else {

			return new Cliente(coleccionClientes[indice]); // y en otro se devulve una nuevo cliente con ese indice
		}

	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; i < coleccionClientes.length - 1; i++) {

			coleccionClientes[i] = coleccionClientes[i + 1];
		}

		coleccionClientes[i] = null;

	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		if (cliente == null) {

			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}

		int indice = buscarIndice(cliente); // compruebo donde esta cliente, si supera tamaño no esta

		if (tamanoSuperado(indice)) { 

			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		}

	}

}