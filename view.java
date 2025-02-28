/*
 * Class: view
 * Author: Liam Mohajeri Norris & Kyle Velasco
 * Purpose: The view to our virtual music library,
 * it handles the user input and directs them with options.
 * 
 * Note: ended up coding this by hand and not Ai. Since the direction we went had some special nuances 
 * that made training the Ai too tedious.
 */
package view;

import java.util.Scanner;

public class view {
	private Scanner scanner;
	private LibraryModel lm;
	private MusicStore ms;
	

    public view() {
        scanner = new Scanner(System.in);
        LibraryModel lm = new LibraryModel();
    	MusicStore ms = new MusicStore();
    	
    }

    public String userInput() {
    	do {
    		System.out.print("Options\n");
            System.out.print("'s' = search, 'a' = add, 'l' = list,\n"
            		+ 		 "'p' = playlist, 'f' = favorite, 'r' = rate,\n"
            		+ 		 "'e' = exit\n" );
            System.out.print("Enter command: ");
            String cmd = scanner.nextLine();
            checkCommand(cmd.toLowerCase());
    	} while(scanner.nextLine().toLowerCase() != "b");
  
//        return scanner.nextLine();
        return "Thank you, for using our service";
    }
    private void checkCommand(String cmd_name){
    	String cmd = cmd_name.toLowerCase();
    	switch (cmd) {
    	case "s":
			searchMenu();
			break;
    	case "a":
			addMenu();
			break;
	    case "l":
			addMenu();
			break;
		case "p":
			playlistMenu();
			break;
		case "f":
			favMenu();
			break;
	    case "r":
			rateMenu();
			break;
		case "e":
			blank();
			break;
		}
    }
    
    private void searchMenu() {
    	System.out.print("\n");
    	System.out.print("Menu Options:\n");
		System.out.print("'s' = search store, 'l' = search library, 'b' = back\n");
    	;
    	while (nxtCmd != "b"){
    		System.out.print("Menu Options:\n");
    		System.out.print("'s' = search store, 'l' = search library, 'b' = back\n");
    		if (nxtCmd == "s") {
    			searchStore();
    		}
    	}
    	return;
	}

	private void searchStore() {
		System.out.print("\n");
    	System.out.print("Search Store Options:\n");
		System.out.print("'st' = search song title, 'sa' = search song "
						+"artist,\n 'at' = search album title, "
						+"'aa' = search album artist, 'b' = back\n");
    	String nxtCmd = scanner.nextLine();
    	while (nxtCmd != "b"){
    		System.out.print("Search Store Options:\n");
    		System.out.print("'st' = search song title, 'sa' = search song "
    						+"artist,\n 'at' = search album title, "
    						+"'aa' = search album artist, 'b' = back\n");
    		if (nxtCmd == "st") {
//    			searchStore();
    		}
    		if (nxtCmd == "sa") {
    			System.out.print("Type artist's name:\n");
    			String nxtCmd = scanner.nextLine();
    			ms.searchAlbumArtist(nxtCmd);
    		}
    	}
    	return;
	}

	public void displayUserDetails(String name, int age) {
        System.out.println("User Details:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
	}
}
