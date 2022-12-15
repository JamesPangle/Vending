package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			} else {
				System.out.println("Incorrect input.");
			}
		} catch (NumberFormatException e) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***"
					+ System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public Double getResponseDouble() {
		double newdub = 0;
		try {
			newdub = in.nextDouble();
			if (in.hasNextLine()) {
				in.nextLine();
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

		return newdub;
	}

	public String getResponseString() {
		String string = in.nextLine();
		string = string.toUpperCase();
		return string;
	}

}
