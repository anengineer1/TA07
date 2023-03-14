import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class TA07_04_App {

	public static void main(String[] args) {
		final double IVA4 = 1.04;
		final double IVA21 = 1.21;
		double iva = 0.0;
		double cantidad_pagada = 0.0;
		double cantidad_a_devolver = 0.0;
		double total_en_bruto = 0.0;
		double total_en_neto = 0.0;
		ArrayList<Hashtable<String, Hashtable<String, Double>>> contenedor_listas_almacen_ventas;

		// 1) generar base de datos con 10 artículos y distintos precios
		Hashtable<String, Hashtable<String, Double>> lista_articulos_almacen = generateProductDb();

		// 2) Generar una tabla vacía en donde de pondrán las cosas vendidas para luego
		// imprimir la info
		Hashtable<String, Hashtable<String, Double>> lista_articulos_vendidos = new Hashtable<String, Hashtable<String, Double>>();

		// Pensar alguna manera de hacer nueva tabla con las ventas
		System.out.println(
				"Desea [C]onsultar, [I]ntroducir datos, [G]estionar venta o [S]alir?:");
		String opcion;

		Scanner sc = new Scanner(System.in);
		opcion = sc.next().toLowerCase();
		// sc.close();
		while (!opcion.toLowerCase().contains("s")) {
			switch (opcion) {
			case "i":

				lista_articulos_almacen = addNuevoADb(lista_articulos_almacen, sc);

				printDatosAlmacen(lista_articulos_almacen);

				break;
				
			case "c":
				System.out.println("Artículos almacén");
				printDatosAlmacen(lista_articulos_almacen);
				System.out.println("Artículos vendidos:");
				printDatosAlmacen(lista_articulos_vendidos);
				System.out.println("Precio total:");
				System.out.println(precioTotal(lista_articulos_vendidos));

				if (iva != 0.0) {
					total_en_bruto = precioTotal(lista_articulos_vendidos);
					total_en_neto = total_en_bruto * iva;
					System.out.println("Cantidad a pagar en bruto : " + total_en_bruto);
					System.out.println("Cantidad a pagar en neto : " + total_en_neto);


					cantidad_a_devolver = cantidad_pagada - total_en_neto;

					System.out.println("Cantidad a devolver (Cantidades negativas es que el cliente ha de pagar más)");
					System.out.println(cantidad_a_devolver);
				}

				break;

			case "g":
				// Separar deduct producto para generar nueva tabla que contenga
				// Las ventas y así poder devoler el IVA y lo demás
				// Si consigues tiempo para hecerlo
				System.out.println("Desea [A]nadir venta, Realizar [T]ransacción, [N]ueva venta o [S]alir?:");
				String opcion2 = sc.next().toLowerCase();
				while (!opcion2.contains("s")) {
					switch (opcion2) {
					case "a":
						contenedor_listas_almacen_ventas = deductProducto(lista_articulos_almacen,
								lista_articulos_vendidos, sc);
						lista_articulos_almacen = contenedor_listas_almacen_ventas.get(0);
						lista_articulos_vendidos = contenedor_listas_almacen_ventas.get(1);
						break;
						
					case "n":
						lista_articulos_vendidos.clear();
						break;
					case "t":
						if (!lista_articulos_vendidos.isEmpty()) {
							// tratar datos
							System.out.println("Qué IVA desea aplicar?");
							System.out.println("[1] 4%");
							System.out.println("[2] 21%");
							System.out.println("Introduzca cualquier otro valor para no aplicar IVA");
							sc.nextLine();

							switch (sc.nextLine()) {
							case "1":
								iva = IVA4;
								break;

							case "2":
								iva = IVA21;
								break;

							default:
								break;

							}
							System.out.println("¿Qué cantidad ha pagado el cliente? (Vaya a consulta para acceder a la información)");
							cantidad_pagada = sc.nextDouble();
						} else {
							System.out.println("No hay para hacer transacción");
						}

					default:
						break;
					}
					System.out.println("Desea [A]nadir venta, Realizar [T]ransacción [S]alir?:");
					opcion2 = sc.next().toLowerCase();
				}
				break;
			default:

				break;
			}
			System.out.println("Desea [C]onsultar, [I]ntroducir datos, [G]estionar venta o [S]alir?:");
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

	public static ArrayList<Hashtable<String, Hashtable<String, Double>>> deductProducto(
			Hashtable<String, Hashtable<String, Double>> lista_productos,
			Hashtable<String, Hashtable<String, Double>> lista_articulos_vendidos, Scanner sc) {
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
		Double cantidad_producto_vendido = (double) sc.nextInt();

		Hashtable<String, Double> cantidad_precio = lista_productos.get(nombre_producto);
		double cantidad_inicial = lista_productos.get(nombre_producto).get("Cantidad");
		double cantidad_final = cantidad_inicial - cantidad_producto_vendido;
		cantidad_precio.put("Cantidad", cantidad_final);

		lista_productos.put(nombre_producto, cantidad_precio);

		// no incluír algo a la cesta si la cantidad vendida es 0
		if (cantidad_producto_vendido > 0) {
			Hashtable<String, Double> cantidad_precio_cesta = new Hashtable<String, Double>();
			cantidad_precio_cesta.put("Cantidad", cantidad_producto_vendido);
			cantidad_precio_cesta.put("Precio", cantidad_precio.get("Precio"));
			lista_articulos_vendidos.put(nombre_producto, cantidad_precio_cesta);
		}

		ArrayList<Hashtable<String, Hashtable<String, Double>>> output = new ArrayList<Hashtable<String, Hashtable<String, Double>>>();
		output.add(lista_productos);
		output.add(lista_articulos_vendidos);

		return output;
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
