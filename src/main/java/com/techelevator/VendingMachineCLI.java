package com.techelevator;

import com.techelevator.view.Log;
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

	public double currentMoney;

	public double remainingMoney;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	private Log log = new Log();

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
							balance += depositAmount; //balance getting fed
							remainingMoney = balance;
							log.log("FEED MONEY", depositAmount, remainingMoney);

						} catch (Exception c) {
							System.out.println("Wrong format. Please do #.##");
						}
					}
					if (customerChoice.equals(CUSTOMER_OPTION_SELECT_PRODUCT)) { //Select Product here
						for (String location : locations) { // using locations variable to display the items, their prices, their quantity, and their location.
							System.out.printf("%s: %s | $%.2f | Quantity: %s\n", location,
									productMap.get(location).getProductName(),
									productMap.get(location).getPrice(), productMap.get(location).getQuantity());
						}
						System.out.println("Pick from the locations above:"); // picking item 
						String loc = menu.getResponseString();
						if (productMap.containsKey(loc)) { // Checking if the input is in the location list
							if (productMap.get(loc).getQuantity().equals("SOLD OUT")) { // checking quantity
								System.out.println("This item is sold out.");
							} else if (balance < productMap.get(loc).getPrice()) { // checking funds
								System.out.println("Insufficient funds.");
							} else { // going through with the transaction
								currentMoney = balance; //before transaction
								balance -= productMap.get(loc).getPrice();
								remainingMoney = balance; //after transaction

								log.log(productMap.get(loc).getProductName() + " " + productMap
										.get(loc).getProductLocation(), currentMoney, remainingMoney); // logging 

								productMap.get(loc)
										.setQuantity(Integer.parseInt(productMap.get(loc).getQuantity()) - 1); // taking one out of stock

								System.out.printf("%s for %s | Balance left: %.2f\n",
										productMap.get(loc).getProductName(), productMap.get(loc).getPrice(), balance);

								productMap.get(loc).sound(); //make sound
							}
						} else {
							System.out.println("That was an invalid location.");
						}
					}
					if (customerChoice.equals(CUSTOMER_OPTION_END_TRANSACTION)) { // end transaction chosen
						System.out.printf("will now dispense your change of %.2f.\n", balance);
						log.log("GIVE CHANGE", balance, 0);
						balance = 0;

						break;
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