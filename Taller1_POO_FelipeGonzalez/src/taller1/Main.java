//Felipe Gonzalez - 21.776.516-1 - ITI
package taller1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
	}

	/**
	 * lectura del archivo Alumnos.txt
	 * 
	 * @throws FileNotFoundException
	 */
	private static void lecturaAlumnos() {
		// nombre;apellido;rut;paralelo
		Scanner scanner;
		try {
			scanner = new Scanner(new File("Alumnos.txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				String[] partes = line.split(";");
				String nombreAlumno = partes[0];
				String apellidoAlumno = partes[1];
				String rutAlumno = partes[2];
				String paralelo = partes[3];
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se ha encontrado el archivo Alumnos.txt.");
			e.printStackTrace();
		}

	}

	/**
	 * lectura del archivo Solicitudes.txt
	 * 
	 * @throws FileNotFoundException
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
				opcion = -1;
			}

		} while (opcion != 7);

	}
}
