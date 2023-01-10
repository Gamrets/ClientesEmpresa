package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.util.Date;
import java.util.StringTokenizer;

public class Cliente {
	
	
	private static final String ER_CORREO = "";
	private static final String ER_DNI = "";
	private static final String ER_TELEFONO = "";
	public static final String FORMATO_FECHA = "";
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private Date fechaNacimiento;
	
	
	
	private String formateaNombre(String nombre) {
		
		StringTokenizer st = new StringTokenizer(nombre);
		StringBuilder stringBuilder = new StringBuilder();

		while (st.hasMoreElements()) {

			String siguienteElemento = (String) st.nextElement();

			if (siguienteElemento.length() > 0) {

				stringBuilder.append(siguienteElemento.substring(0, 1).toUpperCase());
				stringBuilder.append(siguienteElemento.substring(1).toLowerCase());
				stringBuilder.append(' ');
			}
		}

		return stringBuilder.toString();
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
	
	

}
