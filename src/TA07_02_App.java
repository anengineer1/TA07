import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class TA07_02_App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Hacer con Hashmaps de hashmaps?
		final double IVA4 = 1.04;
		final double IVA21 = 1.21;

		// Scanner para obtener la información
		Scanner sc = new Scanner(System.in);
		Hashtable<String, Hashtable<String, Double>> lista_articulos = new Hashtable<String, Hashtable<String, Double>>();

		// Obtener Nombre del producto
		System.out.println("Introduce el nombre del producto (-1 para no introducir más):");
		// String nombre_producto = sc.nextLine();

		while (!lista_articulos.containsKey("-1")) {
			lista_articulos = addNuevoADb(lista_articulos, sc);
		}
		// Borrar el último elemento de la lista que tiene el carácter de
		// fin de lista (-1)
		lista_articulos.remove("-1");
		if (!lista_articulos.isEmpty()) {
			// tratar datos
			System.out.println("Qué IVA desea aplicar?");
			System.out.println("[1] 4%");
			System.out.println("[2] 21%");
			System.out.println("Introduzca cualquier otro valor para no aplicar IVA");

			double IVA = 1;
			switch (sc.nextLine()) {
			case "1":
				IVA = IVA4;
				break;

			case "2":
				IVA = IVA21;
				break;

			default:
				break;
			}
			// cantidad en bruto
			double total_en_bruto = precioTotal(lista_articulos);
			double total_en_neto = total_en_bruto * IVA;
			System.out.println("Cantidad a pagar en bruto : " + total_en_bruto);
			System.out.println("Cantidad a pagar en neto : " + total_en_neto);
			// print stuff
			printDatos(lista_articulos);
			System.out.println("¿Qué cantidad ha pagado el cliente?");
			double cantidad_pagada = sc.nextDouble();
			double cantidad_a_devolver = cantidad_pagada - total_en_neto;

			System.out.println("Cantidad a devolver (Cantidades negativas es que el cliente ha de pagar más)");
			System.out.println(cantidad_a_devolver);

		}
		sc.close();
	}

	public static Hashtable<String, Hashtable<String, Double>> addNuevoADb(
			Hashtable<String, Hashtable<String, Double>> lista_productos, Scanner sc) {
		// Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca el nombre del nuevo producto:");
		Double cantidad_producto = 0.0;
		Double precio_producto = 0.0;
		Hashtable<String, Double> cantidad_precio = new Hashtable<String, Double>();

		String nombre_producto = sc.nextLine();
		if (!nombre_producto.contains("-1")) {
			System.out.println("Introduzca la cantidad:");
			cantidad_producto = (double) sc.nextInt();

			System.out.println("Introduzca el precio del producto:");
			precio_producto = sc.nextDouble();
			sc.nextLine();
			// sc.close();

			cantidad_precio.put("Cantidad", cantidad_producto);
			cantidad_precio.put("Precio", precio_producto);

			lista_productos.put(nombre_producto, cantidad_precio);
		} else {
			cantidad_producto = 0.0;
			precio_producto = 0.0;
			cantidad_precio.put("Cantidad", cantidad_producto);
			cantidad_precio.put("Precio", precio_producto);

			lista_productos.put(nombre_producto, cantidad_precio);
		}

		return lista_productos;
	}

	public static double precioTotal(Hashtable<String, Hashtable<String, Double>> lista_productos) {

		double sum = 0;

		Set<String> keys = lista_productos.keySet();

		for (String key : keys) {
			sum += (lista_productos.get(key).get("Precio") * lista_productos.get(key).get("Cantidad"));
		}

		return sum;
	}

	public static void printDatos(Hashtable<String, Hashtable<String, Double>> almacen_db) {
		for (String nombre : almacen_db.keySet()) {
			System.out.println("Producto " + nombre + " :");

			System.out.println("Cantidad: " + almacen_db.get(nombre).get("Cantidad"));
			System.out.println("Precio: " + almacen_db.get(nombre).get("Precio"));

		}
	}
}
