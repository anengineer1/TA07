import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class TA07_04_App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1 generar base de datos con 10 artículos y distintos precios
		Hashtable<String, Hashtable<String, Double>> lista_articulos_precio = generateProductDb();

		// Pensar alguna manera de hacer nueva tabla con las ventas
		System.out.println("Desea [C]onsultar, [I]ntroducir datos, [G]estionar venta o [S]alir? (C/I):");
		String opcion;

		Scanner sc = new Scanner(System.in);
		opcion = sc.next().toLowerCase();
		// sc.close();
		while (!opcion.toLowerCase().contains("s")) {
			switch (opcion) {
			case "i":

				lista_articulos_precio = addNuevoADb(lista_articulos_precio, sc);

				printDatosAlmacen(lista_articulos_precio);

				break;

			case "c":
				printDatosAlmacen(lista_articulos_precio);
				break;

			case "g":
				// Separar deduct producto para generar nueva tabla que contenga
				// Las ventas y así poder devoler el IVA y lo demás
				// Si consigues tiempo para hecerlo
				lista_articulos_precio = deductProducto(lista_articulos_precio, sc);
				break;
			default:

				break;
			}
			System.out.println("Desea [C]onsultar, [I]ntroducir datos, [G]estionar venta o [S]alir? (C/I):");
			opcion = sc.next().toLowerCase();
		}
		sc.close();
	}

	public static Hashtable<String, Hashtable<String, Double>> generateProductDb() {
		Hashtable<String, Hashtable<String, Double>> lista_articulos = new Hashtable<String, Hashtable<String, Double>>();

		Hashtable<String, Double> cantidad_precio1 = new Hashtable<String, Double>();
		cantidad_precio1.put("Cantidad", 10.0);
		cantidad_precio1.put("Precio", 1.0);
		lista_articulos.put("Queso", cantidad_precio1);

		Hashtable<String, Double> cantidad_precio2 = new Hashtable<String, Double>();
		cantidad_precio2.put("Cantidad", 10.0);
		cantidad_precio2.put("Precio", 1.0);
		lista_articulos.put("Ciruelas", cantidad_precio2);

		Hashtable<String, Double> cantidad_precio3 = new Hashtable<String, Double>();
		cantidad_precio3.put("Cantidad", 10.0);
		cantidad_precio3.put("Precio", 1.0);
		lista_articulos.put("Almendras", cantidad_precio3);

		Hashtable<String, Double> cantidad_precio4 = new Hashtable<String, Double>();
		cantidad_precio4.put("Cantidad", 10.0);
		cantidad_precio4.put("Precio", 1.0);
		lista_articulos.put("Galletas", cantidad_precio4);

		Hashtable<String, Double> cantidad_precio5 = new Hashtable<String, Double>();
		cantidad_precio5.put("Cantidad", 10.0);
		cantidad_precio5.put("Precio", 1.0);
		lista_articulos.put("Desodorante", cantidad_precio5);

		Hashtable<String, Double> cantidad_precio6 = new Hashtable<String, Double>();
		cantidad_precio6.put("Cantidad", 10.0);
		cantidad_precio6.put("Precio", 1.0);
		lista_articulos.put("Almohadas", cantidad_precio6);

		Hashtable<String, Double> cantidad_precio7 = new Hashtable<String, Double>();
		cantidad_precio7.put("Cantidad", 10.0);
		cantidad_precio7.put("Precio", 1.0);
		lista_articulos.put("Munición", cantidad_precio7);

		Hashtable<String, Double> cantidad_precio8 = new Hashtable<String, Double>();
		cantidad_precio8.put("Cantidad", 10.0);
		cantidad_precio8.put("Precio", 1.0);
		lista_articulos.put("Carro", cantidad_precio8);

		Hashtable<String, Double> cantidad_precio9 = new Hashtable<String, Double>();
		cantidad_precio9.put("Cantidad", 10.0);
		cantidad_precio9.put("Precio", 2.0);
		lista_articulos.put("Cable", cantidad_precio9);

		Hashtable<String, Double> cantidad_precio10 = new Hashtable<String, Double>();
		cantidad_precio10.put("Cantidad", 10.0);
		cantidad_precio10.put("Precio", 1.0);
		lista_articulos.put("Cesta", cantidad_precio10);

		return lista_articulos;
	}

	public static Hashtable<String, Hashtable<String, Double>> addNuevoADb(
			Hashtable<String, Hashtable<String, Double>> lista_productos, Scanner sc) {
		// Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca el nombre del nuevo producto:");
		sc.nextLine();
		String nombre_producto = sc.nextLine();

		System.out.println("Introduzca la cantidad:");
		Double cantidad_producto = (double) sc.nextInt();

		System.out.println("Introduzca el precio de cada producto:");
		Double precio_producto = sc.nextDouble();
		// sc.close();

		Hashtable<String, Double> cantidad_precio = new Hashtable<String, Double>();
		cantidad_precio.put("Cantidad", cantidad_producto);
		cantidad_precio.put("Precio", precio_producto);

		lista_productos.put(nombre_producto, cantidad_precio);

		return lista_productos;
	}

	public static Hashtable<String, Hashtable<String, Double>> deductProducto(
			Hashtable<String, Hashtable<String, Double>> lista_productos, Scanner sc) {
		// Scanner sc = new Scanner(System.in);

		sc.nextLine();
		System.out.println("Introduzca el nombre del producto:");
		String nombre_producto = sc.nextLine();

		while (!lista_productos.containsKey(nombre_producto)) {
			printDatosAlmacen(lista_productos);
			System.out.println("Nombre no en lista, inténtelo de nuevo:");
			nombre_producto = sc.nextLine();
			sc.nextLine();
		}

		System.out.println("Introduzca la cantidad a restar:");
		Double cantidad_producto = (double) sc.nextInt();

		Hashtable<String, Double> cantidad_precio = lista_productos.get(nombre_producto);
		double cantidad_inicial = lista_productos.get(nombre_producto).get("Cantidad");
		double cantidad_final = cantidad_inicial - cantidad_producto;
		cantidad_precio.put("Cantidad", cantidad_final);

		lista_productos.put(nombre_producto, cantidad_precio);

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

	public static void printDatosAlmacen(Hashtable<String, Hashtable<String, Double>> almacen_db) {
		for (String nombre : almacen_db.keySet()) {
			System.out.println("Producto " + nombre + " :");

			System.out.println("Cantidad: " + almacen_db.get(nombre).get("Cantidad"));
			System.out.println("Precio: " + almacen_db.get(nombre).get("Precio"));

		}
	}
}
