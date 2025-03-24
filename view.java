/*
 * Class: view
 * Author: Liam Mohajeri Norris & Kyle Velasco
 * Purpose: The view to our virtual music library,
 * it handles the user input and directs them with options.
 * 
 * Note: ended up coding most of this by hand and not Ai. Since the direction we went had some special nuances 
 * that made training the Ai too tedious.
 */

import java.util.Scanner;

public class view {
	private Scanner scanner;
	private LibraryModel lm;
	private MusicStore ms;
	private String username;

    public view(LibraryModel userLibrary, String username) {
        scanner = new Scanner(System.in);
        lm = userLibrary;
    	ms = new MusicStore();
    	this.username = username;
    	System.out.println("Welcome to your Music Library, " + username + "!\n");
    	userInput();

    }

    private void userInput() {
    	String cmd = "";
    	do {
            if (!cmd.equals("e")) {
            	checkCommand(cmd.toLowerCase());
            }
            else {
            	break;
            }
            System.out.print("\n");
            System.out.print("Library Options\n");
            System.out.print("'s' = search, 'a' = add, 'l' = list,\n"
            		+ 		 "'p' = playlist, 'f' = favorite, 'r' = rate,\n"
            		+ 		 "'x' = remove, 'e' = exit\n" );
            System.out.print("Enter command: ");
            cmd = scanner.nextLine();
    	} while(!cmd.toLowerCase().equals("e"));
  
//        return scanner.nextLine();
    	System.out.println();
	    System.out.print("User " + this.username + " successfully logged out.\n");
	    System.out.println();
        return;
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
			listMenu();
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
	    case "x":
	    	removeMenu();
	    	break;
		case "e":
			// blank();
			break;
		}
    }
    
    private void searchMenu() {
    	String nxtCmd = "";
		do {
			System.out.print("\n");
			System.out.print("Menu Options:\n");

    		System.out.print("'s' = search store, 'l' = search library, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine(); 
    		if (nxtCmd.equals("s")) {
    			searchStore();
    		}
    		// TODO: need to finish this
    		if (nxtCmd.equals("l")) {
    			searchLibrary();
    		} 
    	} while(!nxtCmd.equals("b"));
    	return;
	}

	private void searchStore() {
//		System.out.print("\n");
//    	System.out.print("Search Store Options:\n");
//		System.out.print("'st' = search song title, 'sa' = search song "
//						+"artist,\n 'at' = search album title, "
//						+"'aa' = search album artist, 'b' = back\n");
		String nxtCmd = "";
		do {
			System.out.print("\n");
    		System.out.print("Search Store Options:\n");
    		System.out.print("'st' = search song title, 'sa' = search song "
    						+"artist,\n 'at' = search album title, "
    						+"'aa' = search album artist, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine();
    		if (nxtCmd.equals("st")) {
    			System.out.print("song name: ");
    			String songName = scanner.nextLine();
    			String val = lm.storeSearchSongTitle(songName);
    			if (val == null) {
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
    			if (val == null) {
    				System.out.printf("%s not found in MusicStore\n", artistName);
    			}
    			else {
    				System.out.printf("%s's songs:\n%s", artistName,val);
    			}
    		}
    		if (nxtCmd.equals("aa")){
    			System.out.print("Type album artist: ");
    			String albumArtist = scanner.nextLine();
    			String val = lm.storeSearchAlbumArtist(albumArtist);
    			if (val == null) {
    				System.out.printf("Artist %s not found in MusicStore\n", albumArtist);
    			}
    			else {
    				System.out.printf(val);
    			}
    		}
    		if (nxtCmd.equals("at")){
    			System.out.print("Type album title: ");
    			String albumTitle = scanner.nextLine();
    			String val = lm.storeSearchAlbumTitle(albumTitle);
    			if (val == null) {
    				System.out.printf("%s not found in MusicStore\n", albumTitle);
    			}
    			else {
    				System.out.printf(val);
    			}
    		}
    	} while (!nxtCmd.equals("b"));
    	return;
	}
	
// some of this copied straight from search store
	private void searchLibrary() {
		String nxtCmd = "";
		do {
			System.out.print("\n");
    		System.out.print("Search Library Options:\n");
    		System.out.print("'st' = search song title, 'sa' = search song "
    						+"artist,\n 'at' = search album title, "
    						+"'aa' = search album artist, "
    						+ "'pt' = search playlist title, "
    						+ "'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine();
    		if (nxtCmd.equals("st")) {
    			System.out.print("song name: ");
    			String songName = scanner.nextLine();
    			String val = lm.libSearchSongTitle(songName);
    			if (val == null) {
    				System.out.printf("%s not found in LibraryModel\n", songName);
    			}
    			else {
//    				System.out.printf("%s's songs: %s", songName,val);
    				System.out.printf("%s",val);
    			}
    		}
    		if (nxtCmd.equals("sa")) {
    			System.out.print("Type artist's name: ");
    			String artistName = scanner.nextLine();
    			String val = lm.libSearchSongArtist(artistName);
    			if (val == null) {
    				System.out.printf("%s not found in LibraryModel\n", artistName);
    			}
    			else {
    				System.out.printf("%s's songs:\n%s", artistName,val);
    			}
    		}
    		if (nxtCmd.equals("aa")){
    			System.out.print("Type album artist: ");
    			String albumArtist = scanner.nextLine();
    			String val = lm.libSearchAlbumArtist(albumArtist);
    			if (val == null) {
    				System.out.printf("Artist %s not found in LibraryModel\n", albumArtist);
    			}
    			else {
    				System.out.printf(val);
    			}
    		}
    		if (nxtCmd.equals("at")){
    			System.out.print("Type album title: ");
    			String albumTitle = scanner.nextLine();
    			String val = lm.libSearchAlbumTitle(albumTitle);
    			if (val == null) {
    				System.out.printf("%s not found in libraryModel\n", albumTitle);
    			}
    			else {
    				System.out.printf(val);
    			}
    		}
    		if (nxtCmd.equals("pt")){
    			System.out.print("Type playlist title: ");
    			String playlistTitle = scanner.nextLine();
    			String val = lm.libSearchPlaylist(playlistTitle);
    			if (val == null) {
    				System.out.printf("%s not found in libraryModel\n", playlistTitle);
    			}
    			else {
    				System.out.printf(val);
    			}
    		}
    	} while (!nxtCmd.equals("b"));
    	return;
	}
	
	private void addMenu() {
		// Give user opportunity to add song or album
		System.out.print("\n");
		System.out.print("Add Options:\n");
		System.out.print("'s' = add song, 'a' = add album, 'b' = back\nEnter command: ");
		String nxtCmd = scanner.nextLine().strip();
    	while (!nxtCmd.equals("b")){
    		// Check for add song
    		if (nxtCmd.equals("s")) {
    			System.out.print("Enter song title:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter song artist:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.addSong(title, artist));
    		}
    		// Check for add album
    		else if(nxtCmd.equals("a")) {
    			System.out.print("Enter album title:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter album artist:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.addAlbum(title, artist));
    		}
    		System.out.print("\n");
    		System.out.print("Add Options:\n");
    		System.out.print("'s' = add song, 'a' = add album, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine().strip();
    	}
    	return;
	}
	
	private void listMenu() {
		// Give user opportunity to see list of songs, artists,
		// albums, playlists, and favorite songs.
		System.out.print("\n");
		System.out.print("List Options:\n");
		System.out.print("List songs:\t'st' = songs by title, 'sa' = songs by artist, 'sr' = songs by rating,"
				+ "\nOther Lists:\t'r' = artists, 'a' = albums, 'p' = playlists, 'f' = favorite songs, 'b' = back\nEnter command: ");
		String nxtCmd = scanner.nextLine().strip();
    	while (!nxtCmd.equals("b")){
    		// Check for song title
    		if (nxtCmd.equals("st")) {
    			System.out.print("Songs by title:\n");
    			System.out.print(lm.allSongTitles("title"));
    		}
    		// Check for song artist
    		if (nxtCmd.equals("sa")) {
    			System.out.print("Songs by artist:\n");
    			System.out.print(lm.allSongTitles("artist"));
    		}
    		// Check for song rating
    		if (nxtCmd.equals("sr")) {
    			System.out.print("Songs by rating:\n");
    			System.out.print(lm.allSongTitles("rating"));
    		}
    		// Check for artists
    		else if (nxtCmd.equals("r")) {
    			System.out.print("Artists:\n");
    			System.out.print(lm.allArtists());
    		}
    		// Check for albums
    		else if (nxtCmd.equals("a")) {
    			System.out.print("Albums:\n");
    			System.out.print(lm.allAlbumTitles());
    		}
    		// Check for playlists
    		else if (nxtCmd.equals("p")) {
    			System.out.print("Playlists:\n");
    			System.out.print(lm.allPlaylists());
    		}
    		// Check for favorite songs
    		else if (nxtCmd.equals("f")) {
    			System.out.print("Favorite songs:\n");
    			System.out.print(lm.allFavorites());
    		}
    		System.out.print("\n");
    		System.out.print("List Options:\n");
    		System.out.print("List songs:\t'st' = songs by title, 'sa' = songs by artist, 'sr' = songs by rating,"
    				+ "\nOther Lists:\t'r' = artists, 'a' = albums, 'p' = playlists, 'f' = favorite songs, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine().strip();
    	}
    	return;
	}
	
	private void playlistMenu() {
		// Can create playlist or add song to playlist.
		System.out.print("\n");
		System.out.print("Playlist Options:\n");
		System.out.print("'c' = create playlist, 'a' = add song to playlist,"
				+ "'r' = remove song from playlist, 'b' = back\nEnter command: ");
		String nxtCmd = scanner.nextLine().strip();
    	while (!nxtCmd.equals("b")){
    		// Check for create playlist
    		if (nxtCmd.equals("c")) {
    			System.out.print("Enter new playlist name:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print(lm.addPlaylist(title));
    		}
    		// Check for add song
    		else if(nxtCmd.equals("a")) {
    			System.out.print("Enter name of playlist to add to:\n");
    			String playlist = scanner.nextLine().strip();
    			System.out.print("Enter song title:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter song artist:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.addSongToPlaylist(playlist, title, artist));
    		}
    		// Check for remove song
    		else if(nxtCmd.equals("r")) {
    			System.out.print("Enter name of playlist to remove from:\n");
    			String playlist = scanner.nextLine().strip();
    			System.out.print("Enter title of song to remove:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter artist of song to remove:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.removeSongFromPlaylist(playlist, title, artist));
    		}
    		System.out.print("\n");
    		System.out.print("Playlist Options:\n");
    		System.out.print("'c' = create playlist, 'a' = add song to playlist,"
    				+ "'r' = remove song from playlist, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine().strip();
    	}
    	return;
	}
	
	private void favMenu() {
		// Let user favorite songs
		System.out.print("\n");
		System.out.print("Favorite Options:\n");
		System.out.print("'f' = favorite song, 'b' = back\nEnter command: ");
		String nxtCmd = scanner.nextLine().strip();
    	while (!nxtCmd.equals("b")){
    		// Check for favorite
    		if (nxtCmd.equals("f")) {
    			System.out.print("Enter title of song to favorite:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter artist of song to favorite:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.favorite(title, artist));
    		}
    		System.out.print("\n");
    		System.out.print("Favorite Options:\n");
    		System.out.print("'f' = favorite song, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine().strip();
    	}
    	return;
	}
	
	private void rateMenu() {
		// Give user opportunity to add song or album
		System.out.print("\n");
		System.out.print("Rate Options:\n");
		System.out.print("'r' = rate song, 'b' = back\nEnter command: ");
		String nxtCmd = scanner.nextLine().strip();
    	while (!nxtCmd.equals("b")){
    		// Check for favorite
    		if (nxtCmd.equals("r")) {
    			System.out.print("Enter title of song to favorite:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter artist of song to favorite:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print("Enter rating from 1 to 5:\n");
    			String rating = scanner.nextLine().strip();
    			int rate = 0;
    			if (rating.equals("1")) {
    				rate = 1;
    			}
    			else if (rating.equals("2")) {
    				rate = 2;
    			}
    			else if (rating.equals("3")) {
    				rate = 3;
    			}
    			else if (rating.equals("4")) {
    				rate = 4;
    			}
    			else if (rating.equals("5")) {
    				rate = 5;
    			}
    			if (rate <= 0) {
    				System.out.print("Please enter an integer from 1 to 5\n");
    			}
    			else {
    				System.out.print(lm.rate(title, artist, rate));
    			}
    		}
    		System.out.print("\n");
    		System.out.print("Rate Options:\n");
    		System.out.print("'r' = rate song, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine().strip();
    	}
    	return;
	}
	
	private void removeMenu() {
		// Remove a song or album from the library.
		System.out.print("\n");
		System.out.print("Remove Options:\n");
		System.out.print("'s' = remove song, 'a' = remove album, 'b' = back\nEnter command: ");
		String nxtCmd = scanner.nextLine().strip();
    	while (!nxtCmd.equals("b")){
    		// Check for remove song
    		if (nxtCmd.equals("s")) {
    			System.out.print("Enter title of song to remove:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter artist of song to remove:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.removeSongFromLibrary(title, artist));
    		}
    		// Check for remove album
    		else if (nxtCmd.equals("a")) {
    			System.out.print("Enter title of album to remove:\n");
    			String title = scanner.nextLine().strip();
    			System.out.print("Enter artist of album to remove:\n");
    			String artist = scanner.nextLine().strip();
    			System.out.print(lm.removeAlbumFromLibrary(title, artist));
    		}
    		System.out.print("\n");
    		System.out.print("Remove Options:\n");
    		System.out.print("'s' = remove song, 'a' = remove album, 'b' = back\nEnter command: ");
    		nxtCmd = scanner.nextLine().strip();
    	}
    	return;
	}
}
