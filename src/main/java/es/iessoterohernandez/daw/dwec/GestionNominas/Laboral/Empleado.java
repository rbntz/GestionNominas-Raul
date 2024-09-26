package es.iessoterohernandez.daw.dwec.GestionNominas.Laboral;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable {

	private int categoria;
	public int anyos;

	public Empleado() {}

	public Empleado(String nombre, String DNI, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre, DNI, sexo);

		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.categoria = categoria;
		}

		if (anyos < 0) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.anyos = anyos;
		}
	}

	public Empleado(String nombre, String DNI, char sexo) {
		super(nombre, DNI, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}
	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public int getAnyos() {
		return anyos;
	}

	public void setAnyos(int anyos) {
		this.anyos = anyos;
	}

	public void incrAnyo() {
		this.anyos++;
	}

	public String imprime() {
		return "Empleado " + super.imprime() + "[sexo= " + super.sexo + ", categoria=" + categoria + ", anyos=" + anyos
				+ "]";
	}

}
