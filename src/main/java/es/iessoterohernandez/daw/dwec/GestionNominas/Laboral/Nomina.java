package es.iessoterohernandez.daw.dwec.GestionNominas.Laboral;

public class Nomina {

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };

	private String dniEmpleado;

	private double sueldoCalculado;

	public Nomina() {
	}

	public Nomina(String dniEmpleado, double sueldo) {
		this.sueldoCalculado = sueldoCalculado;
		this.dniEmpleado = dniEmpleado;
	}

	public double sueldo(Empleado e) {
		int categoriaSueldo = e.getCategoria();

		this.sueldoCalculado = SUELDO_BASE[categoriaSueldo - 1] + 5000 * e.anyos;

		return this.sueldoCalculado;
	}

	public String getDniEmpleado() {
		return dniEmpleado;
	}

	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}

}
