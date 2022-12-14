package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT };

	private static final String CUSTOMER_OPTION_FEED_MONEY = "Feed Money?";

	private static final String CUSTOMER_OPTION_SELECT_PRODUCT = "Select Product";

	private static final String CUSTOMER_OPTION_END_TRANSACTION = "End Transaction";

	private static final String[] CUSTOMER_OPTIONS = { CUSTOMER_OPTION_FEED_MONEY, CUSTOMER_OPTION_SELECT_PRODUCT,
			CUSTOMER_OPTION_END_TRANSACTION };

	private static File inventoryFile = new File("vendingmachine.csv");

	private double balance = 0.0;

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		HashMap<String, Product> productMap = new HashMap<>(); // Map with key being location 
		ArrayList<String> locations = new ArrayList<>(); // To make the Display cleaner

		try (Scanner inventoryScanner = new Scanner(inventoryFile)) {
			while (inventoryScanner.hasNextLine()) {
				String currentLine = inventoryScanner.nextLine();
				String[] splitString = currentLine.split("\\|");
				productMap.put(splitString[0], new Product(splitString[0], splitString[1], //putting all of the items into a map
						Double.parseDouble(splitString[2]), splitString[3]));
				locations.add(splitString[0]); // adding the locations in order for a cleaner look
			}
		} catch (FileNotFoundException e) { //Stops the program if anything goes wrong
			System.out.println("File Not Found. File must be named vendingmachine.csv");
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}

		String choice = ""; // initialized out here so we can use it in the while condition
		while (!choice.equals(MAIN_MENU_OPTION_EXIT)) { // While exit isn't chosen
			choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { // display vending machine items

				for (String location : locations) { // using locations variable to display the items, their prices, their quantity, and their location.
					System.out.printf("%s: %s | $%.2f | Quantity: %s\n", location,
							productMap.get(location).getProductName(),
							productMap.get(location).getPrice(), productMap.get(location).getQuantity());
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { // Purchase here

				String customerChoice = "";
				while (!customerChoice.equals(CUSTOMER_OPTION_END_TRANSACTION)) { // While "End Transaction" isn't selected
					System.out.printf("Current Money Provided: $%.2f\n", balance);
					customerChoice = (String) menu.getChoiceFromOptions(CUSTOMER_OPTIONS);

					if (customerChoice.equals(CUSTOMER_OPTION_FEED_MONEY)) { // Feed Money here
						System.out.println("How much did you deposit?");
						try {
							double depositAmount = menu.getResponseDouble();
							balance += depositAmount;
						} catch (Exception c) {
							System.out.println("Wrong format. Please do #.##");
						}
					}
					if (customerChoice.equals(CUSTOMER_OPTION_SELECT_PRODUCT)) { //*********************Select Product here*******************************

					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}