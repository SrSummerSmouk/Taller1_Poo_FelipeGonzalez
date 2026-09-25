//Felipe Gonzalez - 21.776.516-1 - ITI
package taller1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	// guardar lectura arch - Alumnos.txt
	static String[] nombresAlumnos = new String[100];
	static String[] apellidosAlumnos = new String[100];
	static String[] rutsAlumnos = new String[100];
	static String[] paralelosAlumnos = new String[100];
	// guardar lectura arch - Solicitud.txt
	static String[] nombresSolicitud = new String[100];
	static String[] apellidosSolicitud = new String[100];

	// guardar Alumnos Aceptados
	static String[] nombresAlumnosAceptados = new String[100];
	static String[] apellidosAlumnosAceptados = new String[100];
	static String[] rutsAlumnosAceptados = new String[100];
	static String[] paralelosAlumnosAceptados = new String[100];

	// guardar Alumnos Rechazados
	static String[] nombresAlumnosRechazados = new String[100];
	static String[] apellidosAlumnosRechazados = new String[100];

	// contadores
	static int cantidadAlumnos = 0;
	static int cantidadSolicitudes = 0;
	static int contadorAdmitidos = 0;
	static int contadorRechazados = 0;
	static int contadorReportesC1 = 0;
	static int contadorReportesC2 = 0;

	static int versionC1 = 1;
	static int versionC2 = 1;
	static int versionRechazados = 1;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		menu();
	}

	/**
	 * lectura del archivo Alumnos.txt
	 */
	private static void lecturaAlumnos() {
		// nombre;apellido;rut;paralelo
		Scanner scanner;
		try {
			scanner = new Scanner(new File("Alumnos.txt"));
			/*
			 * Aclaraciones Capacidad de los vectores: Existirán como máximo 100 personas en
			 * cada archivo. El programa no debe caerse si se alcanza el límite: debe avisar
			 * que no hay espacio. - (esto dice en el taller asique lo tomare como limitante
			 * en lectura)
			 */
			while (scanner.hasNextLine() && cantidadAlumnos < 100) {
				String line = scanner.nextLine().trim();
				String[] partes = line.split(";");

				nombresAlumnos[cantidadAlumnos] = partes[0];
				apellidosAlumnos[cantidadAlumnos] = partes[1];
				rutsAlumnos[cantidadAlumnos] = partes[2];
				paralelosAlumnos[cantidadAlumnos] = partes[3];

				cantidadAlumnos++;
			}
			// en caso de que quede mas lineas de las que puede tener las listas
			if (scanner.hasNextLine()) {
				System.out.println("Se ha alcanzado el limite de espacio (100)\n"
						+ "Aviso: El resto de lineas seran ignoradas.\n");

			} else {
				System.out.println("archivo cargado (Alumnos.txt.)");
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se ha encontrado el archivo Alumnos.txt.");
		}

	}

	/**
	 * lectura del archivo Solicitudes.txt
	 */
	private static void lectorSolicitud() {
		// nombre-apellido
		Scanner scanner;
		try {
			scanner = new Scanner(new File("Solicitudes.txt"));

			while (scanner.hasNextLine() && cantidadSolicitudes < 100) {
				String line = scanner.nextLine().trim();
				String[] partes = line.split("-");

				nombresSolicitud[cantidadSolicitudes] = partes[0];
				apellidosSolicitud[cantidadSolicitudes] = partes[1];
				cantidadSolicitudes++;
			}

			// en caso de que quede mas lineas de las que puede tener las listas
			if (scanner.hasNextLine()) {
				System.out.println("Se ha alcanzado el limite de espacio (100)\n"
						+ "Aviso: El resto de lineas seran ignoradas.\n");
			} else {
				System.out.println("archivo cargado (Solicitudes.txt.)");
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se ha encontrado el archivo Solicitudes.txt.");
			e.printStackTrace();
		}

	}

	/**
	 * sobre escribre el archivo Alumnos.txt
	 */
	private static void sobreEscribirAlumnos() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Alumnos.txt"))) {
			for (int i = 0; i < cantidadAlumnos; i++) {
				String linea = nombresAlumnos[i] + ";" + apellidosAlumnos[i] + ";" + rutsAlumnos[i] + ";"
						+ paralelosAlumnos[i];
				bw.write(linea);
				bw.newLine();

			}
			System.out.println("[STATUS]: Achivo Alumnos.txt actualizado con exito\n");
		} catch (IOException e) {// tengo 3 horas de sueño, disculpa la falta ortografica si hay algo, no veo muy
									// bien ahora
			// TODO: handle exception
			System.out.println("[ERROR]: NO SE PUEDE SOBREESCRIBIR ALUMNOS.TXT\n");
		}

	}

	public static void menu() {
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		boolean archivosCargados = false;
		do {
			System.out.println("\n---Menu de control - Grupo POO---\n");

			System.out.println("1) Cargar Archivos (Alumnos.txt y Solicitudes.txt)\n"
					+ "2) Procesar Solicitudes - Filtro Automatico\n" + "3) Incripcion Manual al Grupo\n"
					+ "4) Administracion Del Curso\n" + "5) Generar Reporte\n" + "6) Analisis Estatico\n"
					+ "7) Salir\n");

			System.out.print("[TU OPCION]: ");
			String in = scanner.nextLine();
			try {
				opcion = Integer.parseInt(in);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				// con suerte esto deberia de hacer que en el default me tire el error para
				// valor deseado
				opcion = -1;
			}

			switch (opcion) {

			case 1:
				// Cargar Archivos
				lectorSolicitud();
				lecturaAlumnos();
				archivosCargados = true;
				break;

			case 2:
				// Procesar Solicitudes - Filtro Automatico
				if (archivosCargados) {
					filtroSolcititud();
				} else {
					System.out.println(
							"[AVISO]: DEBE DE CARGAR ARCHIVOS ANTES PROCESAR SOLICITUDES - FILTRO AUTOMATICO.");
				}
				break;
			case 3:
				inscripcionManual();
				break;
			case 4:
				if (archivosCargados) {
					administracionCurso(scanner);
				} else {
					System.out.println("[AVISO]: DEBE DE CARGAR ARCHIVOS ANTES DE INGRESAR - MENU ADMINISTRACION.");
				}
				break;
			case 5:
				if (archivosCargados) {
					generarReportesMenu(scanner);
				} else {
					System.out.println("[AVISO]: DEBE DE CARGAR ARCHIVOS ANTES DE GENERAR REPORTES.");
				}
				break;

			case 6:
				if (archivosCargados) {
					analisisEstadistico();
				} else {
					System.out.println("[AVISO]: DEBE DE CARGAR ARCHIVOS ANTES DE VER ESTADISTICAS.");
				}
				break;
			case 7:
				// Salir
				System.out.println("[AVISO]: Saliendo del sistema.");
				break;

			default:
				// valor invalido
				System.out.println("ingrese valor valido. [numero entre 1 y 7] ");

			}

		} while (opcion != 7);
		scanner.close();

	}

	private static void filtroSolcititud() {
		System.out.println("---Procesando Solicitudes - Filtrando Automaticamente---\n");

		// for que cuenta solo cantidad de solicitudes.
		for (int i = 0; i < cantidadSolicitudes; i++) {

			String nombSolicitud = nombresSolicitud[i];
			String apeSolicitud = apellidosSolicitud[i];

			int encontrarJ = buscarAlumnoPorNombre(nombSolicitud, apeSolicitud);

			if (encontrarJ != -1) {
				System.out.println("[STATUS]: [OK] - " + nombSolicitud + " " + apeSolicitud + " | Admitido en: "
						+ paralelosAlumnos[encontrarJ]);

				// guardado de los alumnos admitidos.
				nombresAlumnosAceptados[contadorAdmitidos] = nombresAlumnos[encontrarJ];
				apellidosAlumnosAceptados[contadorAdmitidos] = apellidosAlumnos[encontrarJ];
				rutsAlumnosAceptados[contadorAdmitidos] = rutsAlumnos[encontrarJ];
				paralelosAlumnosAceptados[contadorAdmitidos] = paralelosAlumnos[encontrarJ];

				contadorAdmitidos++;
				// cambio de find para saber q se encontro
			} else {
				System.out.println("[STATUS]: [X] - " + nombSolicitud + " " + apeSolicitud
						+ " - [No Pertenece A Ningun Paralelo]");
				nombresAlumnosRechazados[contadorRechazados] = nombSolicitud;
				apellidosAlumnosRechazados[contadorRechazados] = apeSolicitud;

				contadorRechazados++;
			}

		}
		// printeo de 2)Solicitud
		System.out.println("\nSolicitudes | Filtrado - Ha finalizado\n" + "[RECHAZADOS]: " + contadorRechazados + " \n"
				+ "[ADMITIDOS]: " + contadorAdmitidos);

	}

	private static void inscripcionManual() {

		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		/*
		 * si esta parte la estoy reutilizado entera hasta nuevo aviso o hasta que
		 * recuerde como no ser redundante un lunes a las 2:30am
		 */

		do {
			System.out.println(
					"\n---Metodo de Incorporacion---\n" + "[1]: RUT\n" + "[2]: Nombre y Apellido\n" + "[3]: Salir \n");
			System.out.print("[TU OPCION]: ");
			String in = sc.nextLine();
			try {
				opcion = Integer.parseInt(in);
			} catch (NumberFormatException e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
				// RUT
				System.out.print("Ingrese RUT (sin puntos, con guion): ");
				String rut = sc.nextLine().trim();

				// para ver si el rut existe en el grupo (evitar dupli)
				if (!metodoConfirmarRutAmitido(rut)) {
					int bapr = buscarAlumnoPorRUT(rut);

					// si no es -1 entonces encontro al alumno en la lista de alumnos
					if (bapr != -1) {
						nombresAlumnosAceptados[contadorAdmitidos] = nombresAlumnos[bapr];
						apellidosAlumnosAceptados[contadorAdmitidos] = apellidosAlumnos[bapr];
						rutsAlumnosAceptados[contadorAdmitidos] = rutsAlumnos[bapr];
						paralelosAlumnosAceptados[contadorAdmitidos] = paralelosAlumnos[bapr];

						System.out.println("\n[STATUS]: SE HA REGISTRADO EL RUT DEL ESTUDIANTE CON EXITO."
								+ "\n[ESTUDIANTE]: " + nombresAlumnos[bapr] + "\n[RUT]: " + rutsAlumnos[bapr]
								+ "\n[PARALELO]: " + paralelosAlumnos[bapr]);
						contadorAdmitidos++;
					} else {
						System.out.println("[STATUS]: NO SE HA ENCONTRADO EL RUT EN LA LISTA DE ALUMNOS.");
						nombresAlumnosRechazados[contadorRechazados] = "Sin nombre registrado, RUT: " + rut;
						apellidosAlumnosRechazados[contadorRechazados] = "";
						contadorRechazados++;
					}

				}

				break;
			case 2:
				// NOMBRE Y APELLIDO
				System.out.print("Ingrese Nombre y Apellido separa por guion (ejemplo: Alex-Vicente):  ");
				String nombre = "";
				String apellido = "";
				while (true) {
					String entrada = sc.nextLine().trim();

					if (!entrada.contains("-")) {
						System.out.println("[ERROR]: REALICE LA SEPARACION COMO ES DEBIDO (ejemplo: Alex-Vicente)");
						continue;
					}

					String[] partes = entrada.split("-");
					if (partes[0].trim().isEmpty() || partes[1].trim().isEmpty() || partes.length < 2
							|| partes.length > 2) {
						System.out.println("[ERROR]: DEBE DE INGRESAR NOMBRE Y APELLIDO PRIMARIO.");
						continue;
					}

					nombre = partes[0].trim();
					apellido = partes[1].trim();
					break;
				}
				int encontrarJ = buscarAlumnoPorNombre(nombre, apellido);

				if (encontrarJ != -1) {
					if (!metodoConfirmarRutAmitido(rutsAlumnos[encontrarJ])) {
						System.out.println("[STATUS]: [OK] - " + nombre + " " + apellido + " | Admitido en: "
								+ paralelosAlumnos[encontrarJ]);

						// guardado de los alumnos admitidos.
						nombresAlumnosAceptados[contadorAdmitidos] = nombresAlumnos[encontrarJ];
						apellidosAlumnosAceptados[contadorAdmitidos] = apellidosAlumnos[encontrarJ];
						rutsAlumnosAceptados[contadorAdmitidos] = rutsAlumnos[encontrarJ];
						paralelosAlumnosAceptados[contadorAdmitidos] = paralelosAlumnos[encontrarJ];

						contadorAdmitidos++;
					}

					// cambio de find para saber q se encontro
				} else {
					System.out.println(
							"[STATUS]: [X] - " + nombre + " " + apellido + " - [No Pertenece A Ningun Paralelo]");
					nombresAlumnosRechazados[contadorRechazados] = nombre;
					apellidosAlumnosRechazados[contadorRechazados] = apellido;

					contadorRechazados++;
				}

				break;

			case 3:
				// SALIR

				break;

			default:
				System.out.println("[ERROR]: Opcion invalida\n");
				break;
			}

		} while (opcion != 3);
	}

	/**
	 * metodo usado para buscar Alumnos por nombre y apellido comparando con
	 * "equalsIgnoreCase" en listas.
	 * 
	 * @param nombre
	 * @param apellido
	 * @return: Retorna el indice donde se ubica el Alumno encontrado o -1 en caso
	 *          de no encontrarlo.
	 */
	private static int buscarAlumnoPorNombre(String nombre, String apellido) {
		for (int j = 0; j < cantidadAlumnos; j++) {
			if (nombre.equalsIgnoreCase(nombresAlumnos[j]) && apellido.equalsIgnoreCase(apellidosAlumnos[j])) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * metodo usado para buscar Alumnos por RUT. Se verifica si lista rutsAlumnos[i]
	 * !=null y compara el RUT con el RUT en indice en la Lista
	 * 
	 * @param rut
	 * 
	 * @return Retorna el indice donde se ubica el Alumno encontrado o -1 en caso de
	 *         no encontrarlo.
	 */
	private static int buscarAlumnoPorRUT(String rut) {
		for (int i = 0; i < cantidadAlumnos; i++) {
			if (rutsAlumnos[i] != null && rut.equalsIgnoreCase(rutsAlumnos[i])) {
				return i;
			}
		}
		return -1;
	}

	// ya vere si dejo el nombre asi o lo cambio despues
	// es lo mismo que el case 1, solo lo pase a metodo porque queria hacerlo como
	// if y honestamente no me salia.
	private static boolean metodoConfirmarRutAmitido(String rut) {
		for (int i = 0; i < contadorAdmitidos; i++) {
			if (rutsAlumnosAceptados[i] != null && rut.equalsIgnoreCase(rutsAlumnosAceptados[i])) {
				System.out.println("El RUT ingresado ya se encuentra en el grupo: \n" + "[RUT]: " + rut
						+ "\n[NOMBRE-APELLIDO]: " + nombresAlumnosAceptados[i] + " - " + apellidosAlumnosAceptados[i]
						+ "\n[PARALELO]: " + paralelosAlumnosAceptados[i]);
				return true;
			}
		}
		return false;
	}

	private static void administracionCurso(Scanner scanner) {
		int opcion = 0;
		do {
			System.out.println("\n---Menu de Administracion - Grupo POO---\n" + "1) Agregar nuevo Alumno\n"
					+ "2) Editar Alumno existente\n" + "3) Eliminar Alumno\n" + "4) Volver al menu principal\n");
			String in = scanner.nextLine();

			try {
				opcion = Integer.parseInt(in);
			} catch (NumberFormatException e) {
				opcion = -1;
			}
			switch (opcion) {
			case 1:
				// AGREGAR ALUMNO
				if (cantidadAlumnos >= 100) {
					System.out.println("[ERROR]: NO ES POSIBLE AGREGAR A UN ALUMNO NUEVO - (LIMITE 100)\n"
							+ "[Cantidad de alumnos]: " + cantidadAlumnos);
					break;
				}
				// en caso de que si hay espacio:
				System.out.print("Ingrese el rut del alumno (con guion): ");
				String rut = scanner.nextLine().trim();

				if (buscarAlumnoPorRUT(rut) != -1) {
					System.out.println("[ERROR]: Este RUT ya le pertenece a un alumno del curso.\n");
					break;
				}
				System.out.print("Ingrese Nombre: ");
				String nombre = scanner.nextLine().trim();
				System.out.print("Ingrese Apellido: ");
				String apellido = scanner.nextLine().trim();
				System.out.print("Ingrese Paralelo (ej: C1): ");
				String paralelo = scanner.nextLine().trim();

				// nota para recordarme que se me olvida despues:
				// cantidadAlumnos es el contador = indice que uso para la lista
				nombresAlumnos[cantidadAlumnos] = nombre;
				apellidosAlumnos[cantidadAlumnos] = apellido;
				paralelosAlumnos[cantidadAlumnos] = paralelo;
				rutsAlumnos[cantidadAlumnos] = rut;
				cantidadAlumnos++;

				System.out.println("");
				sobreEscribirAlumnos();
				break;
			case 2:
				// PARA EDITAR ALUMNOS
				System.out.print("Ingrese RUT del alumno que desea identificar (EDITAR): ");
				String rutParaEditar = scanner.nextLine().trim();

				int indiceEditor = buscarAlumnoPorRUT(rutParaEditar);
				if (indiceEditor != -1) {
					System.out.println(
							"Alumno actual: " + nombresAlumnos[indiceEditor] + " " + apellidosAlumnos[indiceEditor]
									+ "\n Ingrese el NUEVO paralelo (ENTER para no cambiarlo)\n");
					String nuevoParaleloCambio = scanner.nextLine().trim();
					if (!nuevoParaleloCambio.isEmpty()) {
						paralelosAlumnos[indiceEditor] = nuevoParaleloCambio;
						sobreEscribirAlumnos();

					} else {
						System.out.println("[STATUS]: No se ha realizado cambios\n");
					}

				} else {
					System.out.println("[ERROR]: Alumno no encontrado\n");

				}

				break;
			case 3:
				// eliminar alumnos
				System.out.print("Ingrese RUT del alumno que desea ELIMINAR: ");
				String rutParaEliminar = scanner.nextLine().trim();

				int indiceRutEliminar = buscarAlumnoPorRUT(rutParaEliminar);
				if (indiceRutEliminar != -1) {
					for (int i = indiceRutEliminar; i < cantidadAlumnos - 1; i++) {
						nombresAlumnos[i] = nombresAlumnos[i + 1];
						apellidosAlumnos[i] = apellidosAlumnos[i + 1];
						rutsAlumnos[i] = rutsAlumnos[i + 1];
						paralelosAlumnos[i] = paralelosAlumnos[i + 1];
					}

					nombresAlumnos[cantidadAlumnos - 1] = null;
					apellidosAlumnos[cantidadAlumnos - 1] = null;
					paralelosAlumnos[cantidadAlumnos - 1] = null;
					rutsAlumnos[cantidadAlumnos - 1] = null;
					cantidadAlumnos--;

					sobreEscribirAlumnos();

					System.out.println("Alumno eliminado con exito\n");
					break;

				} else {
					System.out.println("[ERROR]: Alumno no encontrado. \n");
					break;
				}
			case 4:
				// SALIR
				break;
			default:
				System.out.println("[ERROR]: Opcion invalida\n");
				break;
			}

		} while (opcion != 4);
	}

	private static void generarReportesMenu(Scanner sc) {

		System.out.print("[TU OPCION]: ");
		int opcion = 0;

		do {
			System.out.println("\n---Generar Reportes---\n" + "1) Reportes del paralelo C1\n"
					+ "2) Reportes del paralelo C2\n" + "3) Reportes de Rechazados\n" + "4) Salir\n");
			String in = sc.nextLine();

			try {
				opcion = Integer.parseInt(in);
			} catch (NumberFormatException e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
				generarRParalelo("C1");
				break;
			case 2:
				generarRParalelo("C2");
				break;
			case 3:
				generarRRechazos();
				break;
			case 4:
				// salir

				break;

			default:
				break;
			}
		} while (opcion != 4);

	}

	private static void generarRParalelo(String paraleloString) {
		int versionActual;

		if (paraleloString.equalsIgnoreCase("C1")) {
			versionActual = versionC1;
		} else {
			versionActual = versionC2;
		}
		String nombreArchivo = "Reporte" + paraleloString + "-V" + versionActual + ".txt";
		// aqui tuve que ayudarme mucho con metodos externos, odio bw
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
			bw.write("--- Miembros del grupo - Paralelo " + paraleloString + " ---");
			bw.newLine();

			for (int i = 0; i < contadorAdmitidos; i++) {
				if (paralelosAlumnosAceptados[i] != null
						&& paralelosAlumnosAceptados[i].equalsIgnoreCase(paraleloString)) {
					bw.write(nombresAlumnosAceptados[i] + " " + apellidosAlumnosAceptados[i] + " - "
							+ rutsAlumnosAceptados[i]);
					bw.newLine();
				}
			}

			System.out.println("[STATUS]: Reporte generado exitosamente: " + nombreArchivo);

			if (paraleloString.equalsIgnoreCase("C1")) {
				versionC1++;
			} else {
				versionC2++;
			}

		} catch (IOException e) {
			System.out.println("[ERROR]: No se pudo escribir el archivo de reporte.");
		}

	}

	private static void generarRRechazos() {
		String nombreArchivo = "Rechazados-V" + versionRechazados + ".txt";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
			bw.write("=== Solicitudes rechazadas ===");
			bw.newLine();

			for (int i = 0; i < contadorRechazados; i++) {
				if (apellidosAlumnosRechazados[i] == null || apellidosAlumnosRechazados[i].isEmpty()) {
					bw.write(nombresAlumnosRechazados[i]);
				} else {
					bw.write(nombresAlumnosRechazados[i] + " " + apellidosAlumnosRechazados[i]
							+ " - No pertenece a ningun paralelo del curso");
				}
				bw.newLine();
			}

			System.out.println("[STATUS]: Reporte de rechazados generado exitosamente: " + nombreArchivo);
			versionRechazados++;

		} catch (IOException e) {
			System.out.println("[ERROR]: No se pudo escribir el archivo de rechazados.");
		}
	}

	private static void analisisEstadistico() {
		System.out.println("\n--- Analisis estadistico - Menu POO ---\n");

		int totalC1 = 0;
		int totalC2 = 0;

		for (int i = 0; i < cantidadAlumnos; i++) {
			if (paralelosAlumnos[i] != null) {
				if (paralelosAlumnos[i].equalsIgnoreCase("C1")) {
					totalC1++;
				} else if (paralelosAlumnos[i].equalsIgnoreCase("C2")) {
					totalC2++;
				}
			}
		}

		int aceptadosC1 = 0;
		int aceptadosC2 = 0;

		for (int i = 0; i < contadorAdmitidos; i++) {
			if (paralelosAlumnosAceptados[i] != null) {
				if (paralelosAlumnosAceptados[i].equalsIgnoreCase("C1")) {
					aceptadosC1++;
				} else if (paralelosAlumnosAceptados[i].equalsIgnoreCase("C2")) {
					aceptadosC2++;
				}
			}
		}
		//basicamente los printeos....
		System.out.println("[1] Alumnos cargados en Alumnos.txt: " + cantidadAlumnos + "\n- Paralelo C1: " + totalC1
				+ " alumnos" + "\n- Paralelo C2: " + totalC2 + " alumnos");

		System.out.println("\n[2] Estado de Solicitudes:" + "\n- Total Admitidos: " + contadorAdmitidos + " (C1: "
				+ aceptadosC1 + " - C2: " + aceptadosC2 + ")" + "\n- Total Rechazados: " + contadorRechazados);
		System.out.println("- Total Rechazados: " + contadorRechazados);

		int totalProcesados = contadorAdmitidos + contadorRechazados;
		if (totalProcesados > 0) {
			double porcAdmitidos = ((double) contadorAdmitidos / totalProcesados) * 100;
			double porcRechazados = ((double) contadorRechazados / totalProcesados) * 100;
			System.out.printf("- Tasa de Aceptacion: %.2f%%\n", porcAdmitidos);
			System.out.printf("- Tasa de Rechazo: %.2f%%\n", porcRechazados);
		}
	}

}
