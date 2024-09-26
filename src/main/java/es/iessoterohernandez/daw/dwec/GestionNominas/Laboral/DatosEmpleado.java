package es.iessoterohernandez.daw.dwec.GestionNominas.Laboral;

import java.io.*;

public class DatosEmpleado implements Serializable  {

	private String DNI;
	private double sueldo;

	public DatosEmpleado(String dni, double sueldo) {
		this.DNI = dni;
		this.sueldo = sueldo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dni) {
		this.DNI = dni;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public String imprime() {
		return DNI + " " + sueldo + "\n";
	}
	
	public void altaEmpleado() {
		
	}
	
}
