/*
 * Class: LibraryModel
 * Authors: Kyle Velasco & Liam Mohajeri Norris
 * Purpose: simulate a virtual music library,
 * containing songs within Album objects.
 * Interfaces with View and MusicStore to
 * allow users to add albums and songs, search
 * for albums and songs by artist or name,
 * rate and favorite songs, and create their own
 * playlists, using class Playlist.
 */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class LibraryModel {
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	private MusicStore store;
	private Playlist favorites;
	private Playlist topRated;
	
	public LibraryModel() {
		this.albums = new ArrayList<Album>();
		this.playlists = new ArrayList<Playlist>();
		this.store = new MusicStore();
		this.favorites = new Playlist("Favorites");
		this.topRated = new Playlist("Top Rated");
		this.playlists.add(favorites);
		this.playlists.add(topRated);
	}
	
	//////////////////////////////
	// List methods (getters)	//
	//////////////////////////////
	public String allSongTitles(String param) {
		Hashtable<String, String> table = new Hashtable<String, String>();
		if (param.equals("title")) {
			for (int i = 0; i < albums.size(); i++) {
				Hashtable<String, String> temp = albums.get(i).getSongsByTitle();
				mergeTables(table, temp);
			}
		}
		else if (param.equals("artist")) {
			for (int i = 0; i < albums.size(); i++) {
				Hashtable<String, String> temp = albums.get(i).getSongsByArtist();
				mergeTables(table, temp);
			}
		}
		else {
			for (int i = 0; i < albums.size(); i++) {
				Hashtable<String, String> temp = albums.get(i).getSongsByRating();
				mergeTables(table, temp);
			}
		}
		
		Object[] keys = table.keySet().toArray();
		Arrays.sort(keys);
		
		String retStr = "";
		String temp;
		for (int i = 0; i < keys.length; i ++) {
			temp = table.get(keys[i]);
			retStr += "\t" + temp;
		}
	
		if (retStr.equals("")) {
			return "Library is empty.\n";
		}
		return retStr;
	}
	
	public String shuffleAllSongs() {
		ArrayList<String> songNames = new ArrayList<String>();
		for (int i = 0; i < this.albums.size(); i ++) {
			songNames.addAll(this.albums.get(i).getSongList());
		}
		Collections.shuffle(songNames);
		
		String songs = "";
		for (int i = 0; i < songNames.size(); i++) {
			songs += songNames.get(i);
		}
		if (songs == "") {
			return "Library is empty.\n";
		}
		return songs;
	}
	
	private void mergeTables(Hashtable<String, String> table, Hashtable<String, String> temp) {
		/*
		 * Purpose: Merge two hash tables. When keys are equal,
		 * append the data from temp to table.
		 */
		
		Object[] keys = temp.keySet().toArray();
		for (int i = 0; i < keys.length; i ++) {
			if (table.containsKey(keys[i])) {
				table.put(keys[i].toString(), table.get(keys[i]) + "\t" + temp.get(keys[i]));
			}
			else {
				table.put(keys[i].toString(), temp.get(keys[i]));
			}
		}
	}
	
	public String allArtists() {
		ArrayList<String> artists = new ArrayList<String>();
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (!artists.contains(albums.get(i).getArtist())) {
				artists.add(albums.get(i).getArtist());
				retStr += "\t" + albums.get(i).getArtist() + "\n";
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
			retStr += "\t" + albums.get(i).toString() + "\n";
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
			retStr += playlists.get(i).getSongs();
		}
		if (retStr.equals("")) {
			return "Library contains no playlists.\n";
		}
		return retStr;
	}
	
	public String allPlaylistsShuffled() {
		String retStr = "";
		for (int i = 0; i < playlists.size(); i++) {
			retStr += playlists.get(i).toString() + "\n";
			retStr += playlists.get(i).getShuffledSongs();
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
	
	public int getAlbumLength(String title, String artist) {
		// Get the length of a given album in the library
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist) && albums.get(i).getName().equals(title)) {
				return albums.get(i).songList().length;
			}
		}
		return 0;
	}
	
	// New Functionality
	public String libSearchSongGenre(String genre) {
		// Return String with list of songs of given genre
		String retStr = "";
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getGenre().equals(genre)) {
				retStr += albums.get(i).getSongsGenres();
			}
		}
		if (retStr.equals("")) {
			return null;
		}
		return retStr;
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
				retStr += albums.get(i).toString() + "\nSongs:\n";
				retStr += albums.get(i).getSongsWithoutArtist();
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
				retStr += albums.get(i).toString() + "\nSongs:\n";
				retStr += albums.get(i).getSongsWithoutArtist();
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
				newAl.setGenre(song[3]);
				newAl.setYear(song[4]);
				newAl.addSong(title, artist);
				this.albums.add(newAl);
				updateGenre(newAl.getGenre());
				return "Song " + title + " by " + artist + " added.\n";
			}
			else {
				Album toUpdate = getAlbum(song[2]);
				if (toUpdate.containsSong(title)) {
					return "Song " + title + " by " + artist + " is already in library.\n";
				}
				else {
					toUpdate.addSong(title, artist);
					updateGenre(toUpdate.getGenre());
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
				updateGenre(toAdd.getGenre());
				return "Successfully updated album " + title + " by " + artist + "\n";
			}
			// If not, add the album to albums
			else {
				this.albums.add(toAdd);
				updateGenre(toAdd.getGenre());
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
		// Input:	String	playlistTitle	title of playlist to add to
		//			String	title	title of song to add to the playlist
		//			String	artist	artist of song to add to the playlist
		// Output:	String			Confirmation of action or
		//							alert of failure.
		Playlist playlist = getPlaylist(playlistTitle);
		if (playlist == null) {
			return "Playlist " + playlistTitle + " does not exist. Please create playlist.\n";
		}
		// TODO: Add a catch for incorrect artist
		if (searchSongTitleAndArtist(title, artist) != null) {
			if (playlist.addSong(title, artist)) return "Added song " + title + " by " + artist + " to " + playlistTitle + "\n";
			else return "Playlist " + playlistTitle + " already contains song.\n";
		}
		else {
			return "Song " + title + " by " + artist + " not found in library.\n";
		}
	}
	
	public String removeSongFromPlaylist(String playlistTitle, String title, String artist) {
		// removeSongFromPlaylist(String playlistTitle, String title, String artist)
		// Remove a song with name <title> by <artist> from a playlist
		// Input:	String	playlistTitle	title of playlist to remove from
		//			String	title	title of song to remove
		//			String	artist	artist of song to remove
		// Output:	String			Confirmation of action or
		//							alert of failure.
		Playlist playlist = getPlaylist(playlistTitle);
		if (playlist == null) {
			return "Playlist " + playlistTitle + " does not exist. Please create playlist.\n";
		}
		if (playlist.removeSong(title, artist)) {
			return "Song " + title + " by " + artist + " successfully removed from " 
						+ playlistTitle + "\n";
		}
		else {
			return "Playlist " + playlistTitle + " does not contain song.\n";
		}
	}
	
	public String removeSongFromLibrary(String title, String artist) {
		// removeSongFromLibrary(String title, String artist)
		// Remove a song with name <title> by <artist> from the library
		// Input:	String	title	title of song to remove
		//			String	artist	artist of song to remove
		// Output:	String			Confirmation of action or
		//							alert of failure.
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist)) {
				if (albums.get(i).remove(title)) {
					if (albums.get(i).getNumSongs() == 0) {
						removeAlbumFromLibrary(albums.get(i).getName(), albums.get(i).getArtist());
					}
					for (int j = 0; j < this.playlists.size(); j++) {
						this.playlists.get(j).removeSong(title, artist);
					}
					updateGenre(albums.get(i).getGenre());
					return "Song " + title + " by " + artist + " successfully removed from library.\n";
				}
			}
		}
		return "Library does not contain song " + title + " by " + artist + ".\n";
	}
	
	public String removeAlbumFromLibrary(String title, String artist) {
		// removeAlbumFromLibrary(String title, String artist)
		// Remove an album with name <title> by <artist> from the library
		// Input:	String	title	title of album to remove
		//			String	artist	artist of album to remove
		// Output:	String			Confirmation of action or
		//							alert of failure.
		for (int i = 0; i < albums.size(); i++) {
			Album a = this.albums.get(i);
			if (a.getName().equals(title) && a.getArtist().equals(artist)) {
				albums.remove(a);
				for (int j = 0; j < this.playlists.size(); j++) {
					String[][] songs = a.songList();
					for (int k = 0; k < songs.length; k++) {
						this.playlists.get(i).removeSong(songs[k][0], songs[k][1]);
					}
				}
				updateGenre(a.getGenre());
				return "Album " + title + " by " + artist + " successfully removed from library.\n";
			}
		}
		return "Library does not contain album " + title + " by " + artist + ".\n";
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
				this.favorites.addSong(title, artist);
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
			return "Please enter an integer from 1 to 5.\n";
		}
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getArtist().equals(artist) && albums.get(i).containsSong(title)) {
				// Check if rating is 5
				if (rating == 5) {
					this.favorites.addSong(title, artist);
				}
				if (rating >= 4) {
					this.topRated.addSong(title, artist);
				}
				int prevRating = albums.get(i).getSongRating(title);
				if (prevRating > rating) {
					// Remove the song from the proper playlists
					favorites.removeSong(title, artist);
					if (rating < 4) {
						this.topRated.removeSong(title, artist);
					}
				}
				else if (rating <= 4 && this.favorites.containsSong(title, artist)) {
					this.favorites.removeSong(title, artist);
				}
				albums.get(i).rate(title, rating);
				return "Song " + title + " by " + artist + " rated.\n";
			}
		}
		return "Song " + title + " by " + artist + " could not be located.\n";
	}
	
	
	public void updateGenre(String genre) {
		// Loop through all the albums, count
		// the number of songs with genre "genre",
		// and update a genre-specific playlist accordingly.
		int genreSongs = 0;
		for (int i = 0; i < this.albums.size(); i ++) {
			if (albums.get(i).getGenre().equals(genre)) {
				genreSongs += albums.get(i).getNumSongs();
			}
		}
		// If there are at least 10 songs of this genre, create/add to genre playlist
		if (genreSongs >= 10) {
			// Remove the playlist
			this.playlists.remove(getPlaylist(genre));
			
			// Create new playlist
			Playlist genrePlaylist = new Playlist(genre);
			this.playlists.add(genrePlaylist);
			
			// Add all songs of this genre to the playlist
			for (int i = 0; i < this.albums.size(); i ++) {
				// Check if the album has the correct genre
				if (albums.get(i).getGenre().equals(genre)) {
					String[][] songs = albums.get(i).getSongInfo();
					// For each song in the album, add it to the playlist.
					for (int j = 0; j < songs.length; j++) {
						genrePlaylist.addSong(songs[j][0], songs[j][1]);
					}
				}
			}
		}
		else {
			// Remove the playlist (if it exists)
			this.playlists.remove(getPlaylist(genre));
		}
	}
	
}
