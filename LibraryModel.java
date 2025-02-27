/*
 * Class: LibraryModel
 * Author: Liam Mohajeri Norris & Kyle Velasco
 * Purpose: simulate a virtual music library,
 * containing songs within Album objects.
 * Interfaces with View and MusicStore to
 * allow users to add albums and songs, search
 * for albums and songs by artist or name,
 * rate and favorite songs, and create their own
 * playlists, using class Playlist.
 */

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	private MusicStore store;
	
	public LibraryModel() {
		this.albums = new ArrayList<Album>();
		this.playlists = new ArrayList<Playlist>();
		this.store = new MusicStore();
	}
	
	//////////////////////////////
	// List methods (getters)	//
	//////////////////////////////
	public String allSongTitles() {
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			retStr += albums.get(i).getSongsWithoutArtist();
		}
		if (retStr.equals("")) {
			return "Library is empty.\n";
		}
		return retStr;
	}
	
	public String allArtists() {
		ArrayList<String> artists = new ArrayList<String>();
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (!artists.contains(albums.get(i).getArtist())) {
				artists.add(albums.get(i).getArtist());
				retStr += albums.get(i).getArtist() + "\n";
			}
		}
		if (retStr.equals("")) {
			return "Library is empty.\n";
		}
		return retStr;
	}
	
	public String allAlbumTitles() {
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			retStr += albums.get(i).toString() + "\n";
		}
		if (retStr.equals("")) {
			return "Library is empty.\n";
		}
		return retStr;
	}
	
	public String allPlaylists() {
		String retStr = "";
		for (int i = 0; i < playlists.size(); i++) {
			retStr += playlists.get(i).toString() + "\n";
		}
		if (retStr.equals("")) {
			return "Library contains no playlists.\n";
		}
		return retStr;
	}
	
	public String allFavorites() {
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			retStr += albums.get(i).getFavorites();
		}
		if (retStr.equals("")) {
			return "No favorite songs.\n";
		}
		return retStr;
	}
	
	//////////////////////////////
	// Search methods (getters) //
	//////////////////////////////
	// -- Search by Song --
	public String libSearchSongTitle(String title) {
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
	
	public String storeSearchSongTitle(String title) {
		// Return String with title, artist, and album
		// Return null if song not found
		return store.searchSongTitle(title);
	}
	
	public String libSearchSongArtist(String artist) {
		// Return String with title, artist, and album
		// Return null if song not found
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
	
	public String storeSearchSongArtist(String artist) {
		// Return String with title, artist, and album
		// Return null if song not found
		return store.searchSongArtist(artist);
	}
	
	// -- Search by Album --
	
	private Album getAlbum(String title) {
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).getName().equals(title)) {
				return albums.get(i);
			}
		}
		return null;
	}
	
	public String libSearchAlbumTitle(String title) {
		// Return String with title, artist, and songs
		// Return null if album not found
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getName().equals(title)) {
				retStr += albums.get(i).toString() + "\n";
				retStr += albums.get(i).getSongs();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}
	
	public String storeSearchAlbumTitle(String title) {
		// Return String with title, artist, and songs
		// Return null if album not found
		return store.searchAlbumTitle(title);
	}
	
	public String libSearchAlbumArtist(String artist) {
		// Return String with title, artist, and songs
		// Return null if album not found
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist)) {
				retStr += albums.get(i).toString() + "\n";
				retStr += albums.get(i).getSongs();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}
	
	public String storeSearchAlbumArtist(String artist) {
		// Return String with title, artist, and songs
		// Return null if album not found
		return store.searchAlbumArtist(artist);
	}
	
	// -- Search by Playlist --
	private Playlist getPlaylist(String title) {
		for (int i = 0; i < playlists.size(); i ++) {
			if (playlists.get(i).getName().equals(title)) {
				return playlists.get(i);
			}
		}
		return null;
	}
	
	public String libSearchPlaylist(String name) {
		// Return String with title and songs
		// Return null if playlist not found
		String retStr = "";
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getName().equals(name)) {
				retStr += playlists.get(i).toString() + "\n";
				retStr += playlists.get(i).getSongs();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
	}
	
	//////////////////////
	//	Adder Methods:	//
	//	Add songs		//
	//	Add albums		//
	//////////////////////
	public String addSong(String title, String artist) {
		// addSong(String title, String artist)
		// Add song with name <title> to library.
		// If the song not found in store, return
		// a message explaining that the operation
		// was unsuccessful.
		String[] song = store.searchSongTitleAndArtist(title, artist);
		if (song != null) {
			// If the album containing song is not in library, add album
			if (getAlbum(song[2]) == null) {
				Album newAl = new Album(song[2], artist);
				newAl.addSong(title, artist);
				this.albums.add(newAl);
				return "Song " + title + " by " + artist + " added.\n";
			}
			else {
				Album toUpdate = getAlbum(song[2]);
				if (toUpdate.containsSong(title)) {
					return "Song " + title + " by " + artist + " is already in library.\n";
				}
				else {
					toUpdate.addSong(title, artist);
					return "Song " + title + " by " + artist + "added.\n";
				}
			}
			
		}
		else {
			return "Song " + title + " by " + artist + " is not in store.\n";
		}
	}
	
	public String addAlbum(String title, String artist) {
		// addAlbum(String title, String artist)
		// Add album with name <title> to library.
		// If the album is not found in store, return
		// a message explaining that the operation
		// was unsuccessful.
		Album toAdd = store.getAlbum(title, artist);
		if (toAdd != null) {
			boolean contains = false;
			// Check if an album by the same name is already in the library
			for (int i = 0; i < this.albums.size(); i ++) {
				if (this.albums.get(i).getName().equals(toAdd.getName())) {
					contains = true;
				}
			}
			// If so, update the album
			if (contains) {
				Album existing = this.getAlbum(title);
				String[][] songList = toAdd.songList();
				for (int i = 0; i < songList.length; i++) {
					if (!existing.containsSong(songList[i][0])) {
						existing.addSong(songList[i][0], songList[i][1]);
					}
				}
				return "Successfully updated album " + title + " by " + artist + "\n";
			}
			// If not, add the album to albums
			else {
				this.albums.add(toAdd);
				return "Successfully added album " + title + " by " + artist + "\n";
			}
		}
		else {
			return "Album " + title + " by " + artist + " is not in store.\n";
		}
	}
	
	public String addPlaylist(String title) {
		// addPlaylist(String title)
		// Add playlist with name <title> to library.
		// If a playlist with the same name already exists,
		// alert user and do not add a duplicate.
		if (libSearchPlaylist(title) == null) {
			this.playlists.add(new Playlist(title));
			return "Created playlist " + title + "\n";
		}
		return "Playlist " + title + " already exists.\n";
	}
	
	public String addSongToPlaylist(String playlistTitle, String title, String artist) {
		// addSongToPlaylist(String playlistTitle, String title, String artist)
		// Add a song with name <title> by <artist> to a playlist
		// Input:	String	title	title of song to favorite
		//			String	artist	artist of song to favorite
		// Output:	String			Confirmation of action or
		//							alert of failure.
		Playlist playlist = getPlaylist(playlistTitle);
		if (playlist == null) {
			return "Playlist " + playlistTitle + " does not exist. Please create playlist.\n";
		}
		if (playlist.containsSong(title, artist)) {
			return "Playlist " + playlistTitle + " already contains song.\n";
		}
		playlist.addSong(title, artist);
		return "Added song " + title + " by " + artist + " to " + playlistTitle + "\n";
	}
	
	public String favorite(String title, String artist) {
		// favorite(String title, String artist)
		// Favorite a song with name <title> by <artist>
		// Input:	String	title	title of song to favorite
		//			String	artist	artist of song to favorite
		// Output:	String			Confirmation of action or
		//							alert of failure.
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist) && albums.get(i).containsSong(title)) {
				albums.get(i).favorite(title);
				return "Song " + title + " by " + artist + " set to favorite.\n";
			}
		}
		return "Song " + title + " by " + artist + " could not be located.\n";
	}
	
	public String rate(String title, String artist, int rating) {
		// rate(String title, String artist)
		// Rate a song with name <title> by <artist> from 1 to 5.
		// Input:	String	title	title of song to favorite
		//			String	artist	artist of song to favorite
		//			int		rating	integer between 1 and 5.
		// Output:	String			Confirmation of action or
		//							alert of failure.
		if (!(1 <= rating && rating <= 5)) {
			return "Please enter a number from 1 to 5.\n";
		}
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist) && albums.get(i).containsSong(title)) {
				albums.get(i).rate(title, rating);
				return "Song " + title + " by " + artist + " rated.\n";
			}
		}
		return "Song " + title + " by " + artist + " could not be located.\n";
	}
}
