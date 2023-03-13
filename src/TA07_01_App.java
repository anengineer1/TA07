import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;

public class TA07_01_App {

	public static void main(String[] args) {
		// read alumnos
		Hashtable<String, ArrayList<Integer>> lista_alumnos_notas = readAlumnosConNotas();
		Hashtable<String, Double> lista_media_notas = getMediaNotas(lista_alumnos_notas);

		// write alumnos
		printAlumnosNotas(lista_alumnos_notas, lista_media_notas);

	}

	public static Hashtable<String, ArrayList<Integer>> readAlumnosConNotas() {
		
		Scanner sc = new Scanner(System.in);

		// Preguntar por el número de exámenes
		System.out.println("Introduzca el número de exámenes/pruebas que realizarán los alumnos (Entero positivo)");
		int numero_de_pruebas = sc.nextInt();

		String mensaje_introducir_alumno = "Introduzca el nombre del alumno (-1 para no introducir más)";
		String mensaje_introducir_nota = "Introduzca la nota de la prueba ";

		System.out.println(mensaje_introducir_alumno);
		String nombre_alumno = sc.next();

		Hashtable<String, ArrayList<Integer>> lista_alumnos = new Hashtable<String, ArrayList<Integer>>();

		// while no -1
		while (!nombre_alumno.contains("-1")) {

			// se podía haber hecho con un Array pero he preferido ejercitar los conceptos
			// dados
			ArrayList<Integer> lista_de_notas = new ArrayList<Integer>();
			lista_alumnos.put(nombre_alumno, lista_de_notas);
			for (int i = 0; i < numero_de_pruebas; i++) {

				System.out.println(mensaje_introducir_nota + (i+1) + ":");
				lista_de_notas.add(sc.nextInt());
			}
			lista_alumnos.put(nombre_alumno, lista_de_notas);
			System.out.println(mensaje_introducir_alumno);
			nombre_alumno = sc.next();
		}
		// instroducir alumno
		// whilse no final (Pedir número de pruebas hechas para calcular la media)
		sc.close();
		return lista_alumnos;
	}

	public static Hashtable<String, Double> getMediaNotas(Hashtable<String, ArrayList<Integer>> lista_alumnos_notas) {
		
		Hashtable<String, Double> lista_alumnos_media = new Hashtable<String, Double>();
		for (String nombre : lista_alumnos_notas.keySet()) {
			Double media = 0.0;
			for (int i = 0; i < lista_alumnos_notas.get(nombre).size(); i++) {
				media += lista_alumnos_notas.get(nombre).get(i);
			}
			media = media / lista_alumnos_notas.get(nombre).size();
			lista_alumnos_media.put(nombre, media);
		}
		return lista_alumnos_media;
	}

	public static void printAlumnosNotas(Hashtable<String, ArrayList<Integer>> lista_alumnos_notas,
			Hashtable<String, Double> lista_media_notas) {
		
		for (String nombre : lista_alumnos_notas.keySet()) {
			System.out.println("Las notas de " + nombre + " son:");
			for (int i = 0; i < lista_alumnos_notas.get(nombre).size(); i++) {
				System.out.println(lista_alumnos_notas.get(nombre).get(i));
				
			}
			System.out.println("La media de notas es:");
			System.out.println(lista_media_notas.get(nombre));
		}
	}
}
