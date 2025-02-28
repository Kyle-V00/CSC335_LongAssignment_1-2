/*
 * Class: view
 * Author: Liam Mohajeri Norris & Kyle Velasco
 * Purpose: The view to our virtual music library,
 * it handles the user input and directs them with options.
 * 
 * Note: ended up coding most of this by hand and not Ai. Since the direction we went had some special nuances 
 * that made training the Ai too tedious.
 */
//package view;

import java.util.Scanner;

public class view {
	private Scanner scanner;
	private LibraryModel lm;
	private MusicStore ms;
	

    public view() {
        scanner = new Scanner(System.in);
        lm = new LibraryModel();
    	ms = new MusicStore();
   
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
//    	System.out.print("\n");
//    	System.out.print("Menu Options:\n");
//		System.out.print("'s' = search store, 'l' = search library, 'b' = back\n");
//		String nxtCmd = scanner.nextLine();
    	String nxtCmd = scanner.nextLine()
		do {
			System.out.print("Menu Options:\n");
    		System.out.print("'s' = search store, 'l' = search library, 'b' = back\n");
    		String nxtCmd = ; 
    		if (nxtCmd.equals("s")) {
    			searchStore();
    		}
    		// todo: need to finish this
    		if (nxtCmd.equals("l")) {
    			searchStore();
    		} 
    	} while(nxtCmd.nextLine().toLowerCase() != "b")
    	return;
	}

	private void searchStore() {
//		System.out.print("\n");
//    	System.out.print("Search Store Options:\n");
//		System.out.print("'st' = search song title, 'sa' = search song "
//						+"artist,\n 'at' = search album title, "
//						+"'aa' = search album artist, 'b' = back\n");
		String nxtCmd = scanner.nextLine();
    	do {
    		System.out.print("Search Store Options:\n");
    		System.out.print("'st' = search song title, 'sa' = search song "
    						+"artist,\n 'at' = search album title, "
    						+"'aa' = search album artist, 'b' = back\n");
    		if (nxtCmd.equals("st")) {
    			System.out.print("song name: ");
    			String songName = scanner.nextLine();
    			String val = lm.storeSearchSongTitle(songName);
    			if (val.equals(null)) {
    				System.out.printf("%s not found in MusicStore\n", songName);
    			}
    			else {
//    				System.out.printf("%s's songs: %s", songName,val);
    				System.out.printf("%s",val);
    			}
    		}
    		if (nxtCmd.equals("sa")) {
    			System.out.print("Type artist's name: ");
    			String artistName = scanner.nextLine();
    			String val = lm.storeSearchSongArtist(artistName);
    			if (val.equals(null)) {
    				System.out.printf("%s not found in MusicStore\n", artistName);
    			}
    			else {
    				System.out.printf("%s's songs: %s", artistName,val);
    			}
    		}
    		if (nxtCmd.equals("at")){
    			System.out.print("Type album title: ");
    			String albumTitle = scanner.nextLine();
    			String val = lm.storeSearchAlbumTitle(albumTitle);
    			if (val.equals(null)) {
    				System.out.printf("%s not found in MusicStore\n", albumTitle);
    			}
    			else {
    				System.out.printf("%s's songs: %s", artistName,val);
    		}
    	} while (nxtCmd != "b");
    	return;
	}

	public void displayUserDetails(String name, int age) {
        System.out.println("User Details:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
	}
}
