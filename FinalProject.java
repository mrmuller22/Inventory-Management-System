package cit270;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FinalProject {
		
	private Scanner input = new Scanner(System.in);
	private Item[] items = new Item[100];

	// Variables 
	int index; 
	
	public FinalProject() throws FileNotFoundException {
		
		try {
			File readFile = new File("./data/items.txt");
			Scanner inputFile = new Scanner(readFile);
			int i = 0;
			
			while(inputFile.hasNext()) {
				// Reading the file
				int id = Integer.parseInt(inputFile.nextLine());
				String name = inputFile.nextLine();
				Double price = Double.parseDouble(inputFile.nextLine());
				int quantity = Integer.parseInt(inputFile.nextLine());
				
				// Adding variables to new instance of object
				items[i] = new Item(id, name, price, quantity);
				
				// Incrementing i by one
				i++;
			}
		}
		
		catch (FileNotFoundException e){
			System.out.println("File not found");
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		FinalProject foo = new FinalProject();
		foo.mainMenu();
	}

	public void mainMenu() {
		String password;
		
		System.out.println("Welcome to Wayne Industries, Inventory Managment System.");
		System.out.println("Please select an option:");
		System.out.println("1) Admin Login");
		System.out.println("2) User Login");
		System.out.println("3) Exit");
		
		// display menu and ask for user selection (validate)
		int option = input.nextInt();
		
		while (option < 1 || option > 3) {
			System.out.println("Please enter a valid choice.");
			option = input.nextInt();
		}

		switch (option) {
		case 1:
			int attempts = 3;
			// verify the admin login
			input.nextLine();

			do {
				System.out.println("Attempts left: " + attempts);
				System.out.println("Please enter the Password:");
				password = input.nextLine();
				
				// if login correct go to the admin menu
				if(password.equals("MustSaveGotham")) {
					adminMenu();
					break;
				}
				
				else {
					attempts -= 1;
					System.out.println("Wrong Credentials, please try again");
				}
			} while (attempts > 0);
			
			if(attempts == 0) {
				System.out.println("Login failed");
				System.out.println();
				this.mainMenu();
				break;
			}
			break;
		case 2:
			userMenu();
			break;

		case 3:
			// confirm they want to exit and then exit
			System.out.println("Type '3' to Confirm Exit");
			option = input.nextInt();
			
			// set variable to break out of loop
			if(option == 3) {
				break;
			}
			
			else
				this.mainMenu();
		}
	}

	public void adminMenu() {
		boolean exit = false;
		
		do {
			// display menu and ask for user selection (validate)
			System.out.println("Please select an option:");
			System.out.println("1) Add new Item");
			System.out.println("2) Search and Update Item");
			System.out.println("3) Search and Delete Item");
			System.out.println("4) Display All Items");
			System.out.println("5) Exit");
			
			int option = input.nextInt();
			
			while (option < 1 || option > 5) {
				System.out.println("Please enter a valid choice.");
				option = input.nextInt();
			}
			
			int found = -1;
			switch (option) {
			case 1:
				addItem();
				break;

			case 2:
				System.out.println("What item woulld you like to update?\n");

				found = searchForItem();

				if (found != -1) {
					updateItem(found);
					break;
				}

				break;
				
			case 3:
				found = searchForItem();
				
				if(found != -1) {
					deleteItem(found);
					break;
				}

				break;
			
			case 4:
				displayAllItems();
				break;
				
			case 5:
				
				System.out.println("Type '5' to Confirm Exit");
				int userExit = input.nextInt();
				
				// set variable to break out of loop
				if (userExit == 5) {
					exit = true;
					break;
				}
				
				else {
					break;
				}	
			} 	
		} while(!exit);
		mainMenu();
	}



	public void userMenu() {
		boolean exit = false;
		ArrayList<Integer> cart = new ArrayList<Integer>();
		
		// display menu and ask for user selection (validate)
		do {
			System.out.println("Please select an option:");
			System.out.println("1) Display All Items");
			System.out.println("2) Search For Item");
			System.out.println("3) Purchase Item");
			System.out.println("4) Exit");
			
			int option = input.nextInt();
			
			while (option < 1 || option > 4) {
				System.out.println("Please enter a valid choice.");
				option = input.nextInt();
			}
			
			int found = -1;
			
			switch (option) {
			case 1:
				displayAllItems();
				break;
				
			case 2:
				
				searchForItem();
				break; 
			
			case 3:
				
				found = searchForItem();
				
				if(found != -1) {
					purchaseItem(found);
					break;
				}
				
				break;
				
			case 4:
				
				System.out.println("Type '4' to Confirm Exit");
				int userExit = input.nextInt();
				
				// set variable to break out of loop
				if (userExit == 4) {
					exit = true;
					break;
				}
				
				else {
					break;
				}	
			} 	
		} while (!exit);
		
		mainMenu();
	}


	private void displayAllItems() {
		System.out.println("Here is our inventory:");
		System.out.println("_________________________________________");
		System.out.println();
		
		// Looping through our array
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				System.out.println("ID: " + (items[i].GetID() + 1) );
				System.out.println("Name: " + items[i].GetName());
				System.out.println("Price: $" + items[i].GetPrice());
				System.out.println("Quantity: " + items[i].GetQuantity());
				System.out.println("_________________________________________");
				System.out.println();
			}
		}
	}


	public int searchForItem() {
		boolean found;
		
		do {
			found = false;
			
			System.out.println("Please select if you would like to search the product by name or ID:");
			System.out.println("1) Product Name");
			System.out.println("2) Product ID");
			
			int selection; 
			selection = input.nextInt();
			
			while(selection < 0 || selection > 2) {
				System.out.println("Please enter a valid option: ");
				selection = input.nextInt();
			}
			
			switch (selection) {
				case 1:
					String productName;
					System.out.println("Please enter a valid product name you would like to search: ");
					input.nextLine();
					
					productName = input.nextLine();
					
					for(int i = 0; i < items.length; i++) {
						if(items[i] != null && ((items[i].GetName()).toUpperCase().equals(productName.toUpperCase()))) {
							System.out.println("Product is found");
							found = true;
							return items[i].GetID();
						}
						
					}
					break;
					
				case 2:
					System.out.println("Please enter a valid product ID that you would like to search for: ");
					int productID;
					productID = input.nextInt();
					
					for(int i = 0; i < items.length; i++) {
						if(items[i] != null && items[i].GetID() == (productID - 1)) {
							System.out.println("Product is found");
							found = true;
							return items[i].GetID();
						}
						
					}
					break;
			}
			return -1;
		} while (!found);
	}

	private void deleteItem(int index) {
		
		// loop through array
		for(int i = 0; i < items.length; i++) {
			// if this is a valid object and the correct object
			if(items[i] != null && items[i].GetID() == index) {
				// delete object
				items[i] = null;

				// break out of loop
				break;
			}
		}
			
	}

	private void updateItem(int index) {
		// set found to false
		Boolean found = false;
		String updatedName;
		double updatedPrice;
		int updatedQuantity;
		
		System.out.println("Please enter the updated name:");
		
		updatedName = input.nextLine();
		
		
		System.out.println("Please enter the updated price:");
		updatedPrice = input.nextDouble();
		
		System.out.println("Please enter the updated quantity: ");
		updatedQuantity = input.nextInt();
		
		// loop through array
		for (int i = 0; i < items.length; i++) {
			// if this is not a valid object
			if (items[i] != null && items[i].GetID() == index) {
				
				items[i].SetName(updatedName);
				items[i].SetPrice(updatedPrice);
				items[i].SetQuantity(updatedQuantity);

				// set found to true
				found = true;

				// break out of loop
				break;
			}
		}

		// if not found, give error message
		if (!found) {
			System.out.println("Memory is full. Cannot create new item, please try again.");
			System.out.println();
		}

	}

	private void addItem() {
		String name;
		double price;
		int quantity;
		boolean found;
		
		// Add New Item
		
		System.out.println("Enter the Name for the item:");
		input.nextLine();
		name = input.nextLine();
		

		System.out.println("Enter the Price");
		price = input.nextDouble();
		
		while (price <= 0) {
			System.out.println("Invalid price, please enter valid price");
			price = input.nextDouble();
		}

		System.out.println("Enter the quantity");
		quantity = input.nextInt();
		
		while (quantity < 0) {
			System.out.println("Invalid number, please enter positive number or zero");
			quantity = input.nextInt();
		}
	
		// set found to false
		found = false;

		// loop through array
		for (int i = 0; i < items.length; i++) {
			// if this is not a valid object
			if (items[i] == null) {
				
				items[i] = new Item(i, name, price, quantity);

				// set found to true
				found = true;

				// break out of loop
				break;
			}
		}

		// if not found, give error message
		if (!found) {
			System.out.println("Memory is full. Cannot create new item, please try again.");
			System.out.println();
		}
	}

	private void purchaseItem(int index) {
		
		double totalPrice = 0.0; 
		double salesTax;
		double finalPrice;
		
		
		System.out.println("Please enter a Quantity of the Item you would like to purchase: ");
		int quantity = input.nextInt();
		int remainQuantity;
			
		while (quantity < 0 || quantity > items[index].GetQuantity()) {
			System.out.println("Please enter a valid quantity");
			quantity = input.nextInt();
		}
			
		remainQuantity = items[index].GetQuantity() - quantity;
			
		items[index].SetQuantity(remainQuantity);
			
		totalPrice += quantity * items[index].GetPrice();
			
		salesTax = totalPrice * .07;
		
		finalPrice = totalPrice + salesTax;
		
		System.out.println("Total Price: $" + finalPrice);
		
	}
	
}

