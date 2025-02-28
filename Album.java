/*
 * Class Album
 * Author: Liam Mohajeri Norris
 * Purpose: simulate an album,
 * containing an ArrayList of Song
 * objects. Includes methods to search
 * and return string representations of
 * songs contained in album.
 */

import java.util.ArrayList;

public class Album extends Object {
	private String artist;
	private String name;
	private String genre;
	private String year;
	private ArrayList<Song> songs;
	
	public Album(String name, String artist) {
		this.artist = artist;
		this.name = name;
		songs = new ArrayList<Song>();
	}
	
	// Setter methods:
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	// Getter methods:
	public String getArtist() {
		return artist;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSongs() {
		String songList = "Songs:\n";
		for (int i = 0; i < this.songs.size(); i++) {
			songList += songs.get(i).toString() + "\n";
		}
		return songList;
	}
	
	public String getSongsWithoutArtist() {
		String songList = "";
		for (int i = 0; i < this.songs.size(); i++) {
			songList += songs.get(i).getName() + "\n";
		}
		return songList;
	}
	
	public String getFavorites() {
		String favorites = "";
		for (int i = 0; i < this.songs.size(); i++) {
			if (songs.get(i).getFavorite()) {
				favorites += songs.get(i).toString() + "\n";
			}
		}
		return favorites;
	}
	
	public boolean containsSong(String title) {
		if (this.getSong(title) == null) {
			return false;
		}
		return true;
	}
	
	public String getSongByTitle(String title) {
		Song query = getSong(title);
		if (query != null) {
			return query.toString();
		}
		return null;
	}
	
	/*
	 * public void addSong(String songName, String songTitle)
	 * Purpose: Add a song to the ArrayList
	 * of songs in the album.
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
	
//	/*
//	 * public String songSearch(String songName)
//	 * Purpose: Search for a song in the album by song name.
//	 * If the song is found, return a string representation
//	 * of the song.
//	 * Input:	String songName		name of the song to find
//	 * Output:	String				string representation of song,
//	 * 								or null if song not found.
//	 */
//	public String songSearch(String songName) {
//		for (int i = 0; i < this.songs.size(); i ++) {
//			if (this.songs.get(i).getName().equals(songName)) {
//				return this.songs.get(i).toString();
//			}
//		}
//		return null;
//	}
//	
//	/*
//	 * public String songSearch(String songName, songArtist)
//	 * Purpose: Search for a song in the album by song name AND artist.
//	 * If the song is found, return a string representation
//	 * of the song.
//	 * Input:	String songName		name of the song to find
//	 * 			String songArtist	artist of the song to find
//	 * Output:	String				string representation of song,
//	 * 								or null if song not found.
//	 */
//	public String songSearch(String songName, String songArtist) {
//		if (this.artist.equals(songArtist)) {
//			for (int i = 0; i < this.songs.size(); i ++) {
//				if (this.songs.get(i).getName().equals(songName)) {
//					return this.songs.get(i).toString();
//				}
//			}
//		}
//		return null;
//	}
	
	/*
	 * public String albumSongs(String songName)
	 * Purpose: Return every song in this album.
	 * Input:	None
	 * Output:	String				string representation of every song
	 * 								in the album, separated by newlines.
	 */
	public String albumSongs() {
		String songsString = "";
		for (int i = 0; i < this.songs.size(); i ++) {
			songsString += this.songs.get(i).toString() + "\n";
		}
		return songsString;
	}
	
	public String[][] songList() {
		// Return an array of songs, each with
		// its own array denoting [title, artist].
		String[][] songList = new String[songs.size()][2];
		for (int i = 0; i < songs.size(); i++) {
			String[] newSong = new String[2];
			Song cur = songs.get(i);
			newSong[0] = cur.getName();
			newSong[1] = cur.getArtist();
			songList[i] = newSong;
		}
		return songList;
	}
	
	/*
	 * public Song getSong(String songName)
	 * Purpose: Search for a song in the album by song name.
	 * If the song is found, return the Song object.
	 * Input:	String songName		name of the song to find
	 * Output:	Song				song with name songName
	 */
	private Song getSong(String songName) {
		for (int i = 0; i < this.songs.size(); i ++) {
			if (this.songs.get(i).getName().equals(songName)) {
				return songs.get(i);
			}
		}
		return null;
	}
	
	/*
	 * public boolean rate(String songName, int rating)
	 * Purpose: Rate a song based on a song name and an
	 * integer rating. Return true if song was found and rated.
	 * Otherwise, return false.
	 * Input:	String 	songName	name of the song to rate
	 * 			int		rating		Rating between 1 and 5, inclusive.
	 * Output:	boolean				true if found and rated; else false
	 */
	public boolean rate(String songName, int rating) {
		Song songToRate = getSong(songName);
		if (songToRate != null) {
			songToRate.rate(rating);
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * public boolean favorite(String songName)
	 * Purpose: Favorite a song based on a song name. If song is
	 * found, return true. Otherwise, return false.
	 * Input:	String 	songName	name of the song to favorite
	 * Output:	boolean				true if found, set favorite; 
	 * 								else false
	 */
	public boolean favorite(String songName) {
		Song songToFav = getSong(songName);
		if (songToFav != null) {
			songToFav.favorite();
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * public Album copy()
	 * Purpose: Return a copy of this album,
	 * with copies of all the songs
	 * Input:	None
	 * Output:	Album				copy of this album
	 */
	public Album copy() {
		Album newAlbum = new Album(this.name, this.artist);
		newAlbum.setGenre(this.genre);
		newAlbum.setYear(this.year);
		for (int i = 0; i < songs.size(); i ++) {
			newAlbum.addSong(songs.get(i).getName(), songs.get(i).getArtist());
		}
		return newAlbum;
	}
	
	/*
	 * public String toString()
	 * Purpose: Return a string representation of album info.
	 * Input:	None
	 * Output:	String				string representation of this album.
	 */
	@Override
	public String toString() {
		return "Album: " + this.name + " by " + artist + " | " + genre + " | " + year;
	}
}
