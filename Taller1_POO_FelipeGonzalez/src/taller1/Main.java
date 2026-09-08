//Felipe Gonzalez - 21.776.516-1 - ITI
package taller1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
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

			boolean find = false;
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
		System.out.print("[TU OPCION]: ");
		String in = sc.nextLine();
		try {
			opcion = Integer.parseInt(in);
		} catch (NumberFormatException e) {
			opcion = -1;
		}
		do {
			System.out.println(
					"\n---Metodo de Incorporacion---\n" + "[1]: RUT\n" + "[2]: Nombre y Apellido\n" + "[3]: Salir \n");
			switch (opcion) {
			case 1:
				//RUT
				

				break;
			case 2:
				//NOMBRE Y APELLIDO
				break;
			case 3:
				//SALIR
				
				break;

			default:

				break;
			}

		} while (opcion != 3);

	}

	private static int buscarAlumnoPorNombre(String nombre, String apellido) {
		for (int j = 0; j < cantidadAlumnos; j++) {
			/*
			 * act: devido a que necesito esta funcion tanto para case 2 como case 3, derive
			 * esto a "buscarAlumnoPorNombre" para evitar redundancia
			 */
			if (nombre.equalsIgnoreCase(nombresAlumnos[j]) && apellido.equalsIgnoreCase(apellidosAlumnos[j])) {
				return j;

			}
		}
		// en caso de no encontrarlo.
		return -1;

	}
}
