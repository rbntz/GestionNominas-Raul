package es.iessoterohernandez.daw.dwec.GestionNominas.Laboral;

public class Persona {
	
	public String nombre, DNI;
	public char sexo;
	
	public Persona() {}

	public Persona(String nombre, String dni, char sexo) {
		this.nombre = nombre;
		this.DNI = dni;
		this.sexo = sexo;
	}
	
	public Persona(String nombre, char sexo) {
		this.nombre = nombre;
		this.sexo = sexo;
	}

	public void setDNI(String dniNuevo) {
		DNI = dniNuevo;
	}

	public String imprime() {
		return "Persona [nombre=" + nombre + ", DNI=" + DNI + "]";
	}
	
}
