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
				songList += songs.get(i).toString() + "\n";
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
	public void addSong(String songName, String artist) {
		// Create and add a song name
		this.songs.add(new Song(songName, artist));
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