package com.techelevator;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

import materials.Candy;
import materials.Chip;
import materials.Drink;
import materials.Funds;
import materials.Gum;
import materials.Vendable;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT };
	// building menu options as array of strings
	private static final String[] PURCHASE_MENU = { "Feed Money", "Select Product", "Finish Transaction", "Back" };
	private static final String[] FEED_MONEY_MENU = { "Add $1", "Add $5", "Add $10", "Back" };

	// also not sure about this list
	private static final List<Vendable> VENDING_MACHINE_ITEMS = new ArrayList<Vendable>();

	// don't know about this one
	// private static final String[] SELECT_PRODUCT_MENU = ;

	public void inventory() {
		File inventorySourceList = new File("vendingmachine.csv");

		try {
			Scanner stockScanner = new Scanner(inventorySourceList);

			while (stockScanner.hasNextLine()) {
				String lineOfData = stockScanner.nextLine();
				String[] lineOfDataArr = lineOfData.split("\\|");
				String productCode = lineOfDataArr[0];
				String produceName = lineOfDataArr[1];
				BigDecimal price = new BigDecimal(lineOfDataArr[2]);
				if (lineOfDataArr[3].equals("Chip")) {
					Vendable chip = new Chip(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
				if (lineOfDataArr[3].equals("Candy")) {
					Vendable chip = new Candy(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
				if (lineOfDataArr[3].equals("Drink")) {
					Vendable chip = new Drink(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
				if (lineOfDataArr[3].equals("Gum")) {
					Vendable chip = new Gum(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
			}

		} catch (Exception e) {
			System.exit(1);
		}
	}

	private Menu menu;

	private Funds funds = new Funds();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	// seems like basically everything happens inside run()
	// so when run requires another method, that should be written somewhere else,
	// and called
	// inside run, to keep it from getting cluttered
	// log needs to be a new class, so that we can make log object
	// need to run function, like writeToLog or something
	// so we can write something like, log.writeToLog, in each purchase/add money/get change action
	// the getDate function will be a part of the writeToLog function, as such will be in the Log class
	// log.txt, needs to be appended to, and never overwritten.
	// 
	public void run() {
		inventory();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			// added here
			System.out.println("You picked: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (Vendable item : VENDING_MACHINE_ITEMS) {
					System.out.println(
							item.getProductCode() + ") " + item.getProductName() + " | $" + item.getPrice() + " | Quantity remaining: " + item.getQuantity());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String selection = "";

				while (!selection.equals("Back")) {
					selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU);

					if (selection.equals("Feed Money")) {
						processMoney();
					} else if (selection.equals("Select Product")) {
						for (Vendable item : VENDING_MACHINE_ITEMS) {
							System.out.println(item.getProductCode() + " " + item.getProductName() + " " + item.getPrice() + " "
									+ item.getQuantity());
						}
						Scanner productChoice = new Scanner(System.in);
						System.out.println("");
						System.out.println("Enter Product Code");
						String productString = productChoice.nextLine().toUpperCase();
						for (Vendable item : VENDING_MACHINE_ITEMS) {
							if (productString.equals(item.getProductCode())) {
								if (funds.getBalance().compareTo(item.getPrice()) < 0) {
									System.out.println("Please Add More Money >>> ");
									processMoney();
								}
								funds.removeMoney(item.getPrice());
								item.setQuantity(item.getQuantity()-1);
								System.out.println("You have " + funds.getBalance() + " remaining" + "\n" + item.makeNoise());
								continue;
							}
						}
					} else if (selection.contentEquals("Finish Transaction")) {
						if (funds.getBalance().equals(BigDecimal.valueOf(0))) {
							System.out.println("Thank You For Your Purchase");
							continue;
						} else if (!funds.getBalance().equals(BigDecimal.valueOf(0))) {
							System.out.println(funds.getChange());	
							continue;
						}
					}

					
				}
			} else if (choice.contentEquals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
			}

		}
	}

	public void processMoney() {

		String selection = "";
		while (!selection.equals("Back")) {

			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU);

			if (selection.equals("Add $1")) {
				funds.setBalance(new BigDecimal(1));
			} else if (selection.equals("Add $5")) {
				funds.setBalance(new BigDecimal(5));
			} else if (selection.equals("Add $10")) {
				funds.setBalance(new BigDecimal(10));
			}

			System.out.println("You have $" + funds.getBalance());
		}

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
