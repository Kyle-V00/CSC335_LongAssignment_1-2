import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicStore extends Object {
	
	private ArrayList<Album> albums;
	
	public MusicStore() {
		albums = new ArrayList<Album>();
		ArrayList<String[]> albumInfo = new ArrayList<String[]>();
		File albumFile = new File("/Users/liam/eclipse-workspace/LA1/src/albums.txt");
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
				newAl.addSong(songs.next().strip(), curInfo[1]); ;
			}
			this.albums.add(newAl);
		}
	}

	public String searchSongTitle(String title) {
		// TODO Auto-generated method stub
		return "";
	}

	public String searchSongArtist(String artist) {
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist)) {
				return albums.get(i).albumSongs();
			}
		}
		return null;
	}

	public String searchAlbumTitle(String title) {
		// TODO Auto-generated method stub
		return "";
	}

	public String searchAlbumArtist(String artist) {
		// TODO Auto-generated method stub
		return "";
	}

	public String[] searchSongTitleAndArtist(String title, String artist) {
		// Return String[title, artist, album]
		// Return null if not found.
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).containsSong(title) && albums.get(i).getArtist().equals(artist)) {
				String[] info = new String[3];
				info[0] = title;
				info[1] = artist;
				info[2] = albums.get(i).getName();
				return info;
			}
		}
		return null;
	}

	public Album getAlbum(String title, String artist) {
		// TODO Auto-generated method stub
		// return null if album not found
		// Otherwise return a copy of the album object
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).getName().equals(title) && albums.get(i).getArtist().equals(artist)) {
				return albums.get(i).copy();
			}
		}
		return null;
	}

}
