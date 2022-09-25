package uo.cpm.p3.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;

public class ConsoleApp {

	private Menu menu;
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ConsoleApp ca = new ConsoleApp();
	}

	public ConsoleApp() {
		this.menu = new Menu();
		processOrder();
	}

	private Product productSelection() {

		ArrayList<Product> products = new ArrayList<Product>(Arrays.asList(menu.getProducts()));

		System.out.println("\nList of items. Please select an item by introducing its code.");
		System.out.println("Code - Type - Name - Prize - Units");

		products.forEach(p -> System.out.println(p.getCode() + " - " + p.toString()));

		String code = scanner.next();

		boolean valid = false;
		final AtomicReference<String> selectedCode = new AtomicReference<String>();

		while (!valid) {
			for (Product p : products) {
				if (p.getCode().toLowerCase().equals(code.toLowerCase())) {
					valid = true;
					selectedCode.set(code);
					break;
				}
			}
		}

		return products.parallelStream().filter(p -> p.getCode().toLowerCase().equals(selectedCode.get().toLowerCase()))
				.collect(Collectors.toList()).get(0);
	}

	private void processOrder() {
		Order order = new Order();

		HashMap<Product, Integer> orderedProducts = new HashMap<Product, Integer>();

		boolean finishedOrdering = false;

		while (finishedOrdering != true) {
			Product product = productSelection();
			int orderedUnits = unitSelection(product);

			order.add(product, orderedUnits);
			orderedProducts.put(product, orderedUnits);

			System.out.println("\n\nWould you like to add more products to your order? y/n");
			String answer = scanner.next();

			while (!(answer.equals("y") || answer.equals("n"))) {
				System.out.println("y/n?");
				answer = scanner.next();
			}

			if (answer.equals("n")) {
				finishedOrdering = true;
			}
		}

		printOrder(order, orderedProducts);
		System.out.println("\n\nThank you for using our services!");
	}

	private int unitSelection(Product product) {
		System.out.println("\n\nSelect the number of units you want to order");
		int selectedUnits = 0;

		while (selectedUnits <= 0) {
			try {
				selectedUnits = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Invalid input. Please specify a valid number of units");
			}
		}

		return selectedUnits;
	}

	private void printOrder(Order order, HashMap<Product, Integer> orderedProducts) {
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("\n\nThis is the information regarding your order:\n");
		sbInfo.append("Type - Name - Prize - Units\n");

		orderedProducts.entrySet().forEach(p -> sbInfo.append(p.getKey() + " - " + p.getValue() + "\n"));

		sbInfo.append("\nTotal prize: " + order.calcTotal());

		System.out.println(sbInfo.toString());
	}

}
