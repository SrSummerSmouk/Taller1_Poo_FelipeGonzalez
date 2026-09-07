//Felipe Gonzalez - 21.776.516-1 - ITI
package taller1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	//guardar lectura arch - Alumnos.txt 
	static String[] nombresAlumnos = new String[100];
	static String[] apellidosAlumnos = new String[100];
	static String[] rutsAlumnos = new String[100];
	static String[] paralelosAlumnos = new String[100];
	//guardar lectura arch - Solicitud.txt
	static String[] nombresSolicitud = new String[100];
	static String[] apellidosSolicitud = new String[100];
	
 
	static int cantidadAlumnos = 0;

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

			if (scanner.hasNextLine()) {
				System.out.println("Se ha alcanzado el maximo de espacio (100)\n"
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

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				String[] partes = line.split("-");
				String alumnoSolicitud = partes[0];
				String apellidoSolicitud = partes[1];
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se ha encontrado el archivo Solicitudes.txt.");
			e.printStackTrace();
		}

	}

	public static void menu() {
		System.out.println("---Menu de control - Grupo POO---\n");
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		do {
			System.out.println("1) Cargar Archivos (Alumnos.txt y Solicitudes.txt)\n"
					+ "2) Procesar Solicitudes - Filtro Automatico\n" + "3) Incripcion Manual al Grupo\n"
					+ "4) Administracion Del Curso\n" + "5) Generar Reporte\n" + "6) Analisis Estatico\n"
					+ "7) Salir\n");
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
				break;

			case 2:
				// Procesar Solicitudes - Filtro Automatico
				break;

			case 7:
				// Salir
				System.out.println("AVISO: Saliendo del sistema.");
				break;

			default:
				// valor invalido
				System.out.println("ingrese valor valido. (numero entre 1 y 7) ");

			}

		} while (opcion != 7);

	}
}
