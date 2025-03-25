/*
 * Class: MusicStore
 * Authors: Kyle Velasco & Liam Mohajeri Norris
 * Purpose: simulate a music store,
 * based on an albums.txt file that contains
 * a list of albums, and individual album files
 * with songs.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicStore extends Object {

	private ArrayList<Album> albums;

	public MusicStore() {
		albums = new ArrayList<Album>();
		ArrayList<String[]> albumInfo = new ArrayList<String[]>();
//		File albumFile = new File("/Users/liam/Downloads/albums/albums.txt");
		File albumFile = new File("/Users/kivel/Downloads/albums/albums.txt");
		albumFile.getParent();
		Scanner albumNames = null;
		try {
			albumNames = new Scanner(albumFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Construct an ArrayList of String[] objects with [title, artist]
		int j = 0;
		while (albumNames.hasNextLine()) {
			albumInfo.add(albumNames.nextLine().strip().split(","));
			j ++;
		}
		// Loop through the ArrayList, open each file based on [title, artist],
		// and create an Album object with the info from that class.
		Scanner songs;
		for (int i = 0; i< albumInfo.size(); i++) {
			String[] curInfo = albumInfo.get(i);
			Album newAl = new Album(curInfo[0], curInfo[1]);
			File albumDetails = new File(albumFile.getParent(), curInfo[0] + "_" + curInfo[1] + ".txt");
			songs = null;
			// Open the file with this album's songs
			try {
				songs = new Scanner(albumDetails);
			} catch (FileNotFoundException e) {
				System.err.print("Error: File " + curInfo[0] + "_" + curInfo[1] + ".txt not found\n");
				e.printStackTrace();
			}
			// Parse the first line of the album.
			if (songs.hasNextLine()) {
				String[] genreYear = songs.nextLine().strip().split(",");
				newAl.setGenre(genreYear[2]);
				newAl.setYear(genreYear[3]);
			}
			// Loop through the album's songs, adding each to the newAl Album object
			while (songs.hasNextLine()) {
				newAl.addSong(songs.nextLine().strip(), curInfo[1]); ;
			}
			this.albums.add(newAl);
		}
	}

	public String searchSongTitle(String title) {
		// Return String with title, artist, and album
		// Return null if song not found
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			String str = albums.get(i).getSongByTitle(title);
			if (str != null) {
				retStr += str + " from " + albums.get(i).getName() + "\n";
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}

	public String searchSongArtist(String artist) {
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist)) {
				retStr += albums.get(i).albumSongs();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}

	public String searchAlbumTitle(String title) {
		// Return String with title, artist, and songs
		// Return null if album not found
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getName().equals(title)) {
				retStr += albums.get(i).toString() + "\nSongs:\n";
				retStr += albums.get(i).getSongsWithoutArtist();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}

	public String searchAlbumArtist(String artist) {
		// Return String with title, artist, and songs
		// Return null if album not found
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist)) {
				retStr += albums.get(i).toString() + "\nSongs:\n";
				retStr += albums.get(i).getSongsWithoutArtist();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}

	public String[] searchSongTitleAndArtist(String title, String artist) {
		// Return String[title, artist, album]
		// Return null if not found.
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).containsSong(title) && albums.get(i).getArtist().equals(artist)) {
				String[] info = new String[5];
				info[0] = title;
				info[1] = artist;
				info[2] = albums.get(i).getName();
				info[3] = albums.get(i).getGenre();
				info[4] = albums.get(i).getYear();
				return info;
			}
		}
		return null;
	}

	public Album getAlbum(String title, String artist) {
		// return null if album not found
		// Otherwise return a copy of the album object
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).getName().equals(title) && albums.get(i).getArtist().equals(artist)) {
				return albums.get(i).copy();
			}
		}
		return null;
	}

	public Object[] getAlbumInfo(String title, String artist) {
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist) && albums.get(i).containsSong(title)) {
				Object[] info = new Object[2];
				info[0] = albums.get(i).toString();
				info[1] = albums.get(i).songList().length;
				return info;
			}
		}
		return null;
	}

}
