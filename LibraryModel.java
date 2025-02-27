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
	// Search methods (getters) //
	//////////////////////////////
	// -- Search by Song --
	public String libSearchSongTitle(String title) {
		// Return String with title, artist, and album
		// Return null if album not found
		// TODO: create method
		return "";
	}
	
	public String storeSearchSongTitle(String title) {
		// Return String with title, artist, and album
		// Return null if album not found
		return store.searchSongTitle(title);
	}
	
	public String libSearchSongArtist(String artist) {
		// Return String with title, artist, and album
		// Return null if album not found
		// TODO: create method
		return "";
	}
	
	public String storeSearchSongArtist(String artist) {
		// Return String with title, artist, and album
		// Return null if album not found
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
		// TODO: create method
		return "";
	}
	
	public String storeSearchAlbumTitle(String title) {
		// Return String with title, artist, and songs
		// Return null if album not found
		return store.searchAlbumTitle(title);
	}
	
	public String libSearchAlbumArtist(String artist) {
		// Return String with title, artist, and songs
		// Return null if album not found
		// TODO: create method
		return "";
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
		// Return null if album not found
		// TODO: create method
		return "";
	}
	
	//////////////////////
	//	Adder Methods:	//
	//	Add songs		//
	//	Add albums		//
	//////////////////////
	public String addSong(String title, String artist) {
		// Add song with name <title> to library.
		// If the song not found in store, return
		// a message explaining that the operation
		// was unsuccessful.
		String[] song = store.searchSongTitleAndArtist(title, artist);
		if (song != null) {
			// If the album containing song is not in library, add album
			if (getAlbum(song[3]) == null) {
				this.albums.add(new Album(song[2], artist));
				return "Song " + title + " by " + artist + "added.\n";
			}
			else {
				Album toUpdate = getAlbum(song[2]);
				if (toUpdate.containsSong(title)) {
					return "Song " + title + " by " + artist + " is already in library.\n";
				}
				else {
					toUpdate.addSong(artist, title);
					return "Song " + title + " by " + artist + "added.\n";
				}
			}
			
		}
		else {
			return "Song " + title + " by " + artist + " is not in store.\n";
		}
	}
	
	public String addAlbum(String title, String artist) {
		// Add album with name <title> to library.
		// If the album is not found in store, return
		// a message explaining that the operation
		// was unsuccessful.
		Album toAdd = store.getAlbum(title, artist);
		if (toAdd != null) {
			this.albums.add(toAdd);
			return "Successfully added album " + title + " by " + artist + "\n";
		}
		else {
			return "Album " + title + " by " + artist + " is not in store.\n";
		}
	}
	
	public String addPlaylist(String title) {
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
		// Add a song with name <title> by <artist> to a playlist
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
}
