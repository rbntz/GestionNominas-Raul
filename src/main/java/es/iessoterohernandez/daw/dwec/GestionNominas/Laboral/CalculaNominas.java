package es.iessoterohernandez.daw.dwec.GestionNominas.Laboral;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.opencsv.CSVWriter;

public class CalculaNominas {

	public static void main(String[] args) throws DatosNoCorrectosException {

		/*
		 * 
		 * GESTIÓN DE NÓMINAS - PARTE 1 (comentar/descomentar para probar código de cada)
		 * 
		 * 
		 * 
		 */

		/*try {

			Empleado e = new Empleado("James Cosling", "32000032G", 'M', 4, 7);

			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');

			escribe(e);
			
			escribe(e2);

			e2.incrAnyo();

			e.setCategoria(9);

			escribe(e);
			
			escribe(e2);

		} catch (DatosNoCorrectosException e) {

			System.out.println(e);

		}*/
		
		/*
		 * 
		 * GESTIÓN DE NÓMINAS - PARTE 2
		 * 
		 */
		
		// Crear archivo .DAT con el DNI de cada empleado y su sueldo en base a "empleados.txt"
		
		// crearArchivoDAT();
		
		// Insertamos empleados y su nómina a la base de datos.
		
		// Empleado e = new Empleado("Raúl", "47267215F", 'M', 5, 9);

		// altaEmpleado(e);

		// Insertamos empleados a través de un fichero "empleadosNuevos.txt"

		// String datosEmpleadosNuevos = "empleadosNuevos.txt";
		
		// altaEmpleado(datosEmpleadosNuevos);
		
		/*
		 * 
		 * MENÚ OPCIONES
		 * 
		 */

		Scanner scannerString = new Scanner(System.in);
		Scanner scannerInt = new Scanner(System.in);

		boolean salir = false;
		int opcion;

		do {

			System.out.println("Menú de opciones:");
			System.out.println("1.- Mostrar a todos los empleados.");
			System.out.println("2.- Mostrar salario de un empleado por su DNI");
			System.out.println("3.- Modificar datos de los empleados.");
			System.out.println("4.- Salir");
			System.out.print("Elige una opción: ");

			opcion = scannerInt.nextInt();

			System.out.println("");

			switch (opcion) {
			case 1:
				obtenerEmpleados();
				break;
			case 2:
				System.out.println("Introduce el DNI del empleado que quieras saber su sueldo:");

				String dni;

				dni = scannerString.nextLine();

				obtenerSalarioPorDNI(dni);
				break;
			case 3:
				boolean salir2 = false;
				int opcion2;

				do {
					System.out.println("3.- Modificar datos de un empleado:");
					System.out.println("	1.- Cambiar nombre de un empleado");
					System.out.println("	2.- Cambiar sexo de un empleado");
					System.out.println("	3.- Cambiar categoria de un empleado");
					System.out.println("	4.- Cambiar años trabajados de un empleado");
					System.out.println("	5.- Recalcular y actualizar el sueldo de un empleado.");
					System.out.println("	6.- Recalcular y actualizar los sueldos de todos los empleados.");
					System.out.println("	7.- Realizar una copia de seguridad en un fichero.");
					System.out.println("	8.- Volver atrás.");
					System.out.print("Elige una opción: ");

					opcion2 = scannerInt.nextInt();

					String dniBuscado;

					switch (opcion2) {

					case 1:
						System.out.println("Introduce el DNI del empleado que quieras cambiar su nombre:");

						dniBuscado = scannerString.nextLine();

						cambiarNombre(dniBuscado);

						break;
					case 2:
						System.out.println("Introduce el DNI del empleado que quieras cambiar su nombre:");

						dniBuscado = scannerString.nextLine();

						cambiarSexo(dniBuscado);

						break;
					case 3:
						System.out
								.println("Introduce el DNI del empleado que quieras cambiar su categoria profesional:");

						dniBuscado = scannerString.nextLine();

						cambiarCategoria(dniBuscado);

						break;
					case 4:
						System.out.println("Introduce el DNI del empleado que quieras cambiar sus años trabajados:");

						dniBuscado = scannerString.nextLine();

						cambiarAnyosTrabajados(dniBuscado);

						break;
					case 5:
						System.out.println("Introduce el DNI del empleado que quieras actualizar su sueldo:");

						dniBuscado = scannerString.nextLine();

						actualizarSueldoEmpleado(dniBuscado);

						break;
					case 6:
						actualizarSueldoTodos();

						break;
					case 7:
						String nombreCopiaSeguridad;

						System.out.println("Especifica un nombre para la copia de seguridad:");

						nombreCopiaSeguridad = scannerString.nextLine();

						backUpBaseDatos(nombreCopiaSeguridad);

						break;
					case 8:
						salir2 = true;
						System.out.println("Volviendo atrás...");
						break;
					default:

						System.out.println("Opción no válida, por favor elige una opción entre 1 y 8.");
					}

					System.out.println("");

				} while (!salir2);
				break;
			case 4:
				salir = true;
				System.out.println("Saliendo del programa...");
				break;
			default:

				System.out.println("Opción no válida, por favor elige una opción entre 1 y 4.");
			}

			System.out.println("");

		} while (!salir);

		scannerString.close();
		scannerInt.close();

	}
	
	/*
	 * 
	 * MÉTODOS ESTÁTICOS UTILIZADOS EN LA PARTE 1
	 * 
	 */

	private static void escribe(Empleado e) {

		Nomina n = new Nomina();

		System.out.println(e.imprime());
		System.out.println("El sueldo del empleado es " + n.sueldo(e));
		System.out.println("");

	}
	
	/*
	 * 
	 * MÉTODOS ESTÁTICOS UTILIZADOS EN LA PARTE 2
	 * 
	 */
		
	private static void crearArchivoDAT() throws DatosNoCorrectosException {
		
		Nomina nomina = new Nomina();
		
		String archivo = "empleados.txt";

		List<Empleado> empleados = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

			String linea;
			String nombre = "";
			String dni = "";
			char sexo = 0;
			int categoria = 1;
			int anyos = 0;

			while ((linea = br.readLine()) != null) {

				System.out.println("Leyendo línea: '" + linea + "'");

				if (linea.startsWith("Nombre:")) {

					nombre = linea.split(":")[1].trim();

				} else if (linea.startsWith("DNI:")) {

					dni = linea.split(":")[1].trim();

				} else if (linea.startsWith("Sexo:")) {

					sexo = linea.split(":")[1].trim().charAt(0);

				} else if (linea.startsWith("Categoria:")) {

					categoria = Integer.parseInt(linea.split(":")[1].trim());

				} else if (linea.startsWith("Anyos:")) {

					anyos = Integer.parseInt(linea.split(":")[1].trim());
				}

				// Si encontramos una línea en blanco, añadimos la persona
				if (linea.isEmpty()) {

					Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
					empleado.imprime();
					empleados.add(empleado);

					// Reiniciamos las variables para la siguiente persona
					nombre = "";
					dni = "";
					sexo = 0;
					categoria = 1;
					anyos = 0;
				}
			}

			// Añadimos la última persona si el archivo no termina con una línea en blanco
			if (!nombre.isEmpty() || !dni.isEmpty() || sexo != 0 || categoria != 1 || anyos != 0) {

				Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleado.imprime();
				empleados.add(empleado);

			}

			// Imprimir todas las personas
			for (Empleado e : empleados) {
				System.out.println(e.imprime());
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

		guardarEmpleados(empleados, "empleados.dat", nomina);
	}
	
	private static void guardarEmpleados(List<Empleado> empleados, String archivo, Nomina nomina) {

		try (BufferedWriter guardar = new BufferedWriter(new FileWriter(archivo))) {
			
			double sueldo;

			for (Empleado empleado : empleados) {

				sueldo = nomina.sueldo(empleado);

				DatosEmpleado datosEmpleado = new DatosEmpleado(empleado.DNI, sueldo);

				guardar.write(datosEmpleado.imprime());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void altaEmpleado(Empleado e) throws DatosNoCorrectosException {
		
		insertarEmpleado(e);
		insertarNominaEmpleado(e);
		
	}
	
	private static void insertarEmpleado(Empleado e) {

		final String SQL = "INSERT INTO Empleado VALUES ('" + e.nombre + "', '" + e.DNI + "', '" + e.sexo + "', " + e.getCategoria() + ", " + e.getAnyos() + ")";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			int rowsAffected = st.executeUpdate(SQL);

			if (rowsAffected > 0) {
				System.out.println("Empleado con DNI " + e.DNI + " añadido a los empleados con éxito.");
			}

		} catch (SQLException error) {
			System.out.println(error);
		}
	}
	
	private static void insertarNominaEmpleado(Empleado e) {
		
		Nomina n = new Nomina();

		final String SQL = "INSERT INTO Nomina (empleado_dni, sueldoCalculado) VALUES ('" + e.DNI + "', "
				+ n.sueldo(e) + ")";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			int rowsAffected = st.executeUpdate(SQL);

			if (rowsAffected > 0) {
				System.out.println("Empleado con DNI " + e.DNI + " añadido a la nómina con éxito.");
			}

		} catch (SQLException error) {
			System.out.println(error);
		}
	}

	
	private static void altaEmpleado(String datosEmpleadosNuevos) throws DatosNoCorrectosException {
		
		Nomina nomina = new Nomina();

		List<Empleado> empleadosNuevos = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(datosEmpleadosNuevos))) {

			String linea;
			String nombre = "";
			String dni = "";
			char sexo = 0;
			int categoria = 1;
			int anyos = 0;

			while ((linea = br.readLine()) != null) {

				System.out.println("Leyendo línea: '" + linea + "'");

				if (linea.startsWith("Nombre:")) {

					nombre = linea.split(":")[1].trim();

				} else if (linea.startsWith("DNI:")) {

					dni = linea.split(":")[1].trim();

				} else if (linea.startsWith("Sexo:")) {

					sexo = linea.split(":")[1].trim().charAt(0);

				} else if (linea.startsWith("Categoria:")) {

					categoria = Integer.parseInt(linea.split(":")[1].trim());

				} else if (linea.startsWith("Anyos:")) {

					anyos = Integer.parseInt(linea.split(":")[1].trim());
				}

				// Si encontramos una línea en blanco, añadimos la persona
				if (linea.isEmpty()) {

					Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
					empleado.imprime();
					empleadosNuevos.add(empleado);

					// Reiniciamos las variables para la siguiente persona
					nombre = "";
					dni = "";
					sexo = 0;
					categoria = 1;
					anyos = 0;
				}
			}

			// Añadimos la última persona si el archivo no termina con una línea en blanco
			if (!nombre.isEmpty() || !dni.isEmpty() || sexo != 0 || categoria != 1 || anyos != 0) {

				Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleado.imprime();
				empleadosNuevos.add(empleado);

			}

			for (Empleado empleado : empleadosNuevos) {

				final String SQL = "INSERT INTO Empleado VALUES ('"
						+ empleado.nombre + "', '" + empleado.DNI + "', '" + empleado.sexo + "', "
						+ empleado.getCategoria() + ", " + empleado.anyos + ")";
				
				final String SQL2 = "INSERT INTO Nomina VALUES ('"
						+ empleado.DNI + "', " + nomina.sueldo(empleado) + ")";

				try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

					int rowsAffected = st.executeUpdate(SQL);

					if (rowsAffected > 0) {
						System.out.println("Empleado con DNI " + empleado.DNI + " añadido a los empleados con éxito.");
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
				
				try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

					int rowsAffected = st.executeUpdate(SQL2);

					if (rowsAffected > 0) {
						System.out.println("Empleado con DNI " + empleado.DNI + " añadido a la nomina con éxito.");
					}

				} catch (SQLException e) {
					System.out.println(e);
				}

			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

	}

	private static void cambiarNombre(String dniEmpleado) throws DatosNoCorrectosException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce el nuevo nombre para el empleado:");

		String nuevoNombre = sc.nextLine();

		Empleado empleado = obtenerEmpleadoPorDNI(dniEmpleado);

		final String SQL_UPDATE = "UPDATE Empleado SET nombre = '" + nuevoNombre + "' WHERE dni = '" + dniEmpleado
				+ "'";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			st.executeUpdate(SQL_UPDATE);

			System.out.println("Nombre actualizado del empleado " + empleado.DNI + " a " + nuevoNombre);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static void cambiarSexo(String dniEmpleado) throws DatosNoCorrectosException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce el nuevo sexo para el empleado:");

		char sexoNuevo = sc.next().charAt(0);

		Empleado empleado = obtenerEmpleadoPorDNI(dniEmpleado);

		final String SQL_UPDATE = "UPDATE Empleado SET sexo = '" + sexoNuevo + "' WHERE dni = '" + dniEmpleado + "'";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			st.executeUpdate(SQL_UPDATE);

			System.out.println("Sexo actualizado del empleado " + empleado.DNI + " a " + sexoNuevo);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static void cambiarCategoria(String dniEmpleado) throws DatosNoCorrectosException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce la nueva categoria para el empleado:");

		int nuevaCategoria = sc.nextInt();

		Empleado empleado = obtenerEmpleadoPorDNI(dniEmpleado);

		final String SQL_UPDATE = "UPDATE Empleado SET categoria = '" + nuevaCategoria + "' WHERE dni = '" + dniEmpleado
				+ "'";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			st.executeUpdate(SQL_UPDATE);

			System.out.println("Categoria actualizada del empleado " + empleado.DNI + " a " + nuevaCategoria);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static void cambiarAnyosTrabajados(String dniEmpleado) throws DatosNoCorrectosException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce los nuevos años trabajados para el empleado:");

		int nuevosAnyosTrabajados = sc.nextInt();

		Empleado empleado = obtenerEmpleadoPorDNI(dniEmpleado);

		final String SQL_UPDATE = "UPDATE Empleado SET anyos = '" + nuevosAnyosTrabajados + "' WHERE dni = '"
				+ dniEmpleado + "'";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			st.executeUpdate(SQL_UPDATE);

			System.out
					.println("Años trabajados actualizado al empleado " + empleado.DNI + " a " + nuevosAnyosTrabajados);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static Empleado obtenerEmpleadoPorDNI(String dniBuscado) throws DatosNoCorrectosException {

		Empleado empleadoEncontrado = new Empleado();

		final String SQL = "SELECT * FROM Empleado WHERE dni = '" + dniBuscado + "'";

		try (Connection conn = DBUtils.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(SQL)) {
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String dni = rs.getString("DNI");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				int anyos = rs.getInt("anyos");

				empleadoEncontrado = new Empleado(nombre, dni, sexo, categoria, anyos);

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return empleadoEncontrado;
	}

	private static List<Empleado> obtenerEmpleados() throws DatosNoCorrectosException {

		List<Empleado> empleados = new ArrayList<>();
		
		final String SQL = "SELECT * FROM Empleado";

		try (Connection conn = DBUtils.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(SQL)) {
			
			while (rs.next()) {
				
				String nombre = rs.getString("nombre");
				String dni = rs.getString("DNI");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				int anyos = rs.getInt("anyos");

				Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleados.add(empleado); // Lo hago por reutilizar el código para otros métodos que necesitan un array de empleados
				
				System.out.println(empleado.imprime());

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return empleados;
	}

	private static void actualizarSueldoEmpleado(String dniBuscado) throws DatosNoCorrectosException {

		Nomina nomina = new Nomina();
		double nuevoSueldo = 0;

		Empleado empleado = obtenerEmpleadoPorDNI(dniBuscado);

		nuevoSueldo = nomina.sueldo(empleado);

		final String SQL_UPDATE = "UPDATE Nomina SET sueldoCalculado = " + nuevoSueldo + " WHERE empleado_dni = '"
				+ dniBuscado + "'";

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			st.executeUpdate(SQL_UPDATE);

			System.out.println("Sueldo actualizado al empleado " + empleado.DNI + " a " + nuevoSueldo);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static void actualizarSueldoTodos() throws DatosNoCorrectosException {

		Nomina nomina = new Nomina();

		String dniEmpleado = "";
		double nuevoSueldo = 0;

		List<Empleado> empleados = obtenerEmpleados();

		for (Empleado e : empleados) {

			nuevoSueldo = nomina.sueldo(e);

			final String SQL_UPDATE = "UPDATE Nomina SET sueldoCalculado = " + nuevoSueldo + " WHERE empleado_dni = '"
					+ e.DNI + "'";

			try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

				st.executeUpdate(SQL_UPDATE);

				System.out.println("Sueldo actualizado al empleado " + e.DNI + " a " + nuevoSueldo);

			} catch (SQLException error) {
				System.out.println(error);
			}
		}
	}

	private static void obtenerSalarioPorDNI(String dni) throws DatosNoCorrectosException {

		double sueldo;

		final String SQL = "SELECT * FROM Nomina WHERE empleado_dni = '" + dni + "'";

		try (Connection conn = DBUtils.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(SQL)) {
			while (rs.next()) {

				sueldo = rs.getDouble("sueldoCalculado");

				System.out.println("El empleado con DNI " + dni + " tiene un sueldo de " + sueldo);

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	private static void backUpBaseDatos(String nombreArchivo) {

		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement()) {

			ResultSet rs = st.executeQuery("SHOW TABLES");

			// Crear la carpeta 'backUp' si no existe
			File directory = new File("backUp");
			if (!directory.exists()) {
				directory.mkdir();
			}

			while (rs.next()) {
				String tableName = rs.getString(1);
				String SQL2 = "SELECT * FROM " + tableName;
				try (ResultSet rsTable = st.executeQuery(SQL2);
						CSVWriter writer = new CSVWriter(
								new FileWriter("backUp/" + nombreArchivo + "_" + tableName + ".csv"))) {

					writer.writeAll(rsTable, true);
				} catch (IOException e) {
					System.out.println("Error al escribir el archivo: " + e.getMessage());
				}
			}

			System.out.println("Copia de seguridad realizada con éxito.");

		} catch (SQLException e) {

			System.out.println("Error al conectar con la base de datos: " + e.getMessage());

		}

	}

}
