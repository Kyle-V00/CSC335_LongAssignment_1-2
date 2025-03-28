/*
 * Class Playlist
 * Author: Liam Mohajeri Norris
 * Purpose: simulate a playllist,
 * containing an ArrayList of Song
 * objects. Includes methods to add songs
 * and return string representations of
 * all songs contained in playlist.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
	private String name;
	private ArrayList<Song> songs;

	public Playlist(String name) {
		this.name = name;
		songs = new ArrayList<Song>();
	}

	// Getter methods:
	public String getName() {
		return name;
	}

	public String getSongs() {
		String songList = "Songs in playlist:\n";
		if (this.songs.size() == 0) {
			return "Playlist is empty.\n";
		}
		else {
			for (int i = 0; i < this.songs.size(); i++) {
				songList += "\t" + songs.get(i).toString() + "\n";
			}
		}
		return songList;
	}

	public String getShuffledSongs() {
		String songList = "Shuffled songs in playlist:\n";
		if (this.songs.size() == 0) {
			return "Playlist is empty.\n";
		}
		else {
			Object shuffled = songs.clone();
			Collections.shuffle((List<?>) shuffled);
			for (int i = 0; i < this.songs.size(); i++) {
				songList += "\t" + ((ArrayList<Song>) shuffled).get(i).toString() + "\n";
			}
		}
		return songList;
	}

	public boolean containsSong(String title, String artist) {
		// Check if a Song object with title <title> 
		// and artist <artist> is contained in the playlist.
		for (int i = 0; i < this.songs.size(); i ++) {
			if (this.songs.get(i).getName().equals(title) && this.songs.get(i).getArtist().equals(artist)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * public void addSong(String songName, String songTitle)
	 * Purpose: Add a song to the ArrayList
	 * of songs in the playlist.
	 * Input:	String songName		name of the song to add
	 * 			String songTitle	title of the song to add
	 * Output:	void
	 * 
	 * @pre: songName != null, songTitle != null
	 */
	public boolean addSong(String songName, String artist) {
		// Create and add a song name
		if (this.containsSong(songName, artist)) return false;
		this.songs.add(new Song(songName, artist));
		return true;
	}

	/*
	 * public boolean removeSong(String songName, String songTitle)
	 * Purpose: Remove a song from the ArrayList
	 * of songs in the playlist.
	 * Input:	String songName		name of the song to add
	 * 			String songTitle	title of the song to add
	 * Output:	boolean				true if song removed, false otherwise
	 * 
	 * @pre: songName != null, songTitle != null
	 */
	public boolean removeSong(String songName, String artist) {
		// Search ArrayList for song
		for (int i = 0; i < songs.size(); i ++) {
			Song cur = songs.get(i);
			if (cur.getName().equals(songName) && cur.getArtist().equals(artist)) {
				return songs.remove(cur);
			}
		}
		return false;
	}
	
	public Song incSongCount(String title, String artist) {
//		String[][] songList = new String[songs.size()][2];
//		Song temp = null;
		for (int i = 0; i < songs.size(); i++) {
//			songList[i][0] = songs.get(i).getName();
//			songList[i][1] = songs.get(i).getArtist();
			if ((songs.get(i).getName().equals(title)) && (songs.get(i).getArtist().equals(artist))) {
				songs.get(i).replayCountInc();
				return songs.get(i);
			}
		}
//		songs.get(getNumSongs())
//		return songList;
		return null;
	}

	/*
	 * public String toString()
	 * Purpose: Return a string representation of playlist info.
	 * Input:	None
	 * Output:	String				string representation of this playlist.
	 */
	@Override
	public String toString() {
		return "Playlist: " + this.name + " | " + this.songs.size() + " songs";
	}
}