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
			// bolean find para asegurarme de jugar entre !find para saber si se encontro o
			// no

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

					String[] partes = entrada.split(" ");
					if (partes[0].trim().isEmpty() || partes[1].trim().isEmpty() || partes.length < 2) {
						System.out.println("[ERROR]: DEBE DE INGRESAR NOMBRE Y APELLIDO.");
						continue;
					}

					nombre = partes[0].trim();
					apellido = partes[1].trim();
					break;
				}
				break;

			case 3:
				// SALIR

				break;

			default:

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
			if (rut.equalsIgnoreCase(rutsAlumnosAceptados[i]) && rutsAlumnosAceptados != null) {
				System.out.println("El RUT ingresado ya se encuentra en el grupo: \n" + "[RUT]: " + rut
						+ "\n[NOMBRE-APELLIDO]: " + nombresAlumnosAceptados[i] + " - " + apellidosAlumnosAceptados[i]
						+ "\n[PARALELO]: " + paralelosAlumnosAceptados[i]);
				return true;
			}
		}
		return false;
	}

}
