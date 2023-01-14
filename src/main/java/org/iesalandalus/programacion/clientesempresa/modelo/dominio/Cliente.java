package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	
	
	private static final String ER_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String ER_DNI = "([0-9]{8})([a-zA-Z])";
	
	private static final String ER_TELEFONO = "(9|6)[0-9]{8}";
	
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	
	
	
	public Cliente(String nombre,String dni,String correo,String telefono,LocalDate fechaNacimiento) {
		
		
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento); 	
		
	}
	
	
	public Cliente(Cliente cliente) {
		
		if(cliente == null) {
			
			 throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setCorreo(cliente.getCorreo());
		setTelefono(cliente.getTelefono());
		setFechaNacimiento(cliente.getFechaNacimiento());
	}
	
	
	private String formateaNombre(String nombre) {

		// Paso 1 :Paso: Creamos un objeto del método StringTokenizer
		StringTokenizer st = new StringTokenizer(nombre);
		/*
		 * Paso 2: Contamos la cantidad de palabras que tiene la variable que contenga
		 * el texto y la guardamos en una variable entera.
		 */

		int cantidadPalabras = st.countTokens();
		// Paso 3: Creamos un ciclo for que corra por cada una de las palabras.
		String nombreCompleto = " ";
		for (int i = 0; i < cantidadPalabras; i++) {
			String PalabraIndividual = st.nextToken();
			/*
			 * Nombre formateado (+=) primera letra de cada palabra en mayuscula, mas resto
			 * de letras de cada palabra en miniscu
			 */

			nombreCompleto += PalabraIndividual.substring(0, 1).toUpperCase()
					+ PalabraIndividual.substring(1).toLowerCase() + " ";
		}
		nombre = nombreCompleto.trim();
		return nombre;
	}
	
	
	
	private boolean comprobarLetraDni(String dni) {

		char tempLetraDNI = dni.charAt(dni.length() - 1);
		
		String dniFixed = dni.replaceAll("\\D", "");
		int dniNumber = Integer.parseInt(dniFixed);

		char[] LETRAS_DNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };

		if (LETRAS_DNI[dniNumber % 23] == tempLetraDNI) {
			return true;
		} else
			return false;
	}


	public String getNombre() {
		return this.nombre;
	}


	public void setNombre(String nombre) {
		
		if(nombre == null) {
			
             throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		}
		
		if(nombre.trim().isEmpty()) {
			
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		}
		
		this.nombre = formateaNombre(nombre);
	}


	public String getDni() {
		return dni;
	}


	private void setDni(String dni) {
		
		if(dni == null) {
			
			 throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		}
		
		if(!dni.matches(ER_DNI)) {
			
			throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
		}
		
		if(comprobarLetraDni(dni) == false) {
			
			throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
		}
		
		
		this.dni = dni;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		
		if(correo == null) {
			
			 throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		}
		
		Pattern pattern = Pattern.compile(ER_CORREO); 
        Matcher mather = pattern.matcher(correo);
        
        if(mather.find() == false) {
        	
        	throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
        }
		
		
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		
		if(telefono == null) {
			
			 throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		}
		
		if(!telefono.matches(ER_TELEFONO)) {
			
			throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
		}
		
		
		this.telefono = telefono;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		
		if(fechaNacimiento == null) {
			
			 throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		}
		
		this.fechaNacimiento = fechaNacimiento;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	private String getIniciales() {
		StringBuilder iniciales = new StringBuilder();
		for (String s : this.nombre.split(" ")) {
			iniciales.append(s.charAt(0));
		}
		return iniciales.toString();

	}
	
	
	@Override
	public String toString() {
		return  "nombre=" + nombre + " (" + getIniciales() + ")" + ", DNI=" + dni + ", correo=" + correo + ", teléfono="
				+ telefono + ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
	}

}
