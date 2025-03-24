/*
 * Class: 	UserManagerView
 * Author: 	Liam Mohajeri Norris and Kyle Velasco
 * Purpose: UI for creating accounts and logging into
 * 			MusicLibrary objects for a Music Library application.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserManagerView {
	
	private UserManager manager;
	private Scanner scanner;
	
	public UserManagerView() throws NoSuchAlgorithmException, IOException {
		scanner = new Scanner(System.in);
		this.manager = new UserManager();
		uI();
	}
	
	private void uI() throws FileNotFoundException, NoSuchAlgorithmException {
		/*
		 * void uI() throws FileNotFoundException, NoSuchAlgorithmException
		 * Purpose: Interact with user on a high level, enabling users to
		 * 			create accounts and log in.
		 * Input:	void
		 * Output:	void
		 */
		
		boolean quit = false;
		String input;
		
		System.out.println(" -------------------");
		System.out.println("| Welcome to Music! |");
		System.out.println(" -------------------\n");
		System.out.println("To get started, please log in or create an account.\n");
		
		while (!quit) {
			System.out.println("Music Home:");
			System.out.println("Enter\t'l'to log in");
			System.out.println("\t'c' to create an account");
			System.out.println("\t'q' to quit");
			System.out.print("Enter command: ");
			
			input = scanner.next();
			if (input.toLowerCase().equals("l")) {
				login();
			}
			else if (input.toLowerCase().equals("c")) {
				create();
			}
			else if (input.toLowerCase().equals("q")) {
				quit = true;
			}
			else {
				System.out.println("Please enter a valid command.");
			}
		}
		
		System.out.println("Thank you, for using our service.");
	}
	
	private void login() throws FileNotFoundException {
		/*
		 * void login() throws FileNotFoundException
		 * Purpose: Take user input for username and password,
		 * 			and feed these to UserManager
		 * Input:	void
		 * Output:	void
		 */
		
		System.out.println("Enter username: ");
		String username = scanner.next();
		System.out.println("Enter password: ");
		String password = scanner.next();
		if (!manager.login(username, password, true)) {
			System.out.println("Incorrect username and/or password. Please try again.");
		}
	}
	
	private void create() throws NoSuchAlgorithmException {
		/*
		 * void create() throws NoSuchAlgorithmException
		 * Purpose: Take user input for username and password,
		 * 			and feed these to UserManager. Print updates
		 * 			telling the user whether or not the
		 * 			account creation was successful, and if not,
		 * 			explain why.
		 * Input:	void
		 * Output:	void
		 */
		
		System.out.println("Enter username: ");
		String username = scanner.next();
		if (!manager.addUser(username)) {
			System.out.println("User " + username + " already exists. Pleaes try again.");
			return;
		}
		System.out.println("Enter password: ");
		if (manager.addPassword(username, scanner.next())) {
			System.out.println("User " + username + " successfully created. Please log in.\n");
		}
		else {
			System.out.println("Password error. Please try again.");
		}
	}
}
