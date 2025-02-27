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
	
	private Album searchAlbum(String title) {
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
			if (searchAlbum(song[3]) == null) {
				this.albums.add(new Album(song[2], artist));
				return "Song " + title + " by " + artist + "added.";
			}
			else {
				Album toUpdate = searchAlbum(song[2]);
				if (toUpdate.containsSong(title)) {
					return "Song " + title + " by " + artist + " is already in library.";
				}
				else {
					toUpdate.addSong(artist, title);
					return "Song " + title + " by " + artist + "added.";
				}
			}
			
		}
		else {
			return "Song " + title + " by " + artist + " is not in store.";
		}
	}
}
