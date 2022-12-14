package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
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

	NumberFormat dollarFormat = NumberFormat.getCurrencyInstance();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		String choice = "";
		while (!choice.equals(MAIN_MENU_OPTION_EXIT)) {
			choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				Map<String, Product> productMap = new HashMap<>();
				try (Scanner inventoryScanner = new Scanner(inventoryFile)) {
					while (inventoryScanner.hasNextLine()) {
						String currentLine = inventoryScanner.nextLine();
						String[] splitString = currentLine.split("\\|");
						productMap.put(splitString[0], new Product(splitString[0], splitString[1],
								Double.parseDouble(splitString[2]), splitString[3]));
					}
				} catch (FileNotFoundException e) {
					System.out.println("File not found.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				for (Map.Entry<String, Product> product : productMap.entrySet()) { //Displaying all of the items
					System.out.printf("%s | %s | %.2f\n", product.getValue().getProductLocation(),
							product.getValue().getProductName(), product.getValue().getPrice());
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// Purchase here

				String customerChoice = "";
				while (!customerChoice.equals(CUSTOMER_OPTION_END_TRANSACTION)) { // While "End Transaction" isn't selected
					System.out.println("Current Money Provided: " + dollarFormat.format(balance));
					customerChoice = (String) menu.getChoiceFromOptions(CUSTOMER_OPTIONS);

					if (customerChoice.equals(CUSTOMER_OPTION_FEED_MONEY)) { // Feed Money here
						System.out.println("How much did you deposit?");
						try {
							double depositAmount = menu.getResponseDouble();
							balance += depositAmount;
						} catch (Exception e) {
							System.out.println("Wrong format. Please do X.XX");
						}
					}
					if (customerChoice.equals(CUSTOMER_OPTION_SELECT_PRODUCT)) {
						//Select Product here
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