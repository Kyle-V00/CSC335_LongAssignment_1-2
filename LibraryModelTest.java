import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibraryModelTest {
	
	LibraryModel lib = new LibraryModel();

	@Test
	void testAllSongTitles_NoSongs() {
		assertEquals(lib.allSongTitles(), "Library is empty.\n");
	}
	
	@Test
	void testAllArists_Albums() {
		assertEquals(lib.allArtists(), "Library is empty.\n");
	}
	
	@Test
	void testAllAlbumTitles_NoAlbums() {
		assertEquals(lib.allAlbumTitles(), "Library is empty.\n");
	}
	
	@Test
	void testAllPlaylists_NoPlaylists() {
		assertEquals(lib.allPlaylists(), "Library contains no playlists.\n");
	}
	
	@Test
	void testAllFavorites_NoSongs() {
		assertEquals(lib.allFavorites(), "No favorite songs.\n");
	}
	
	@Test
	void testLibSearchSongTitle_NoSongs() {
		assertEquals(lib.libSearchSongTitle("Hello"), null);
	}
	
	@Test
	void testStoreSearchSongTitle_NoSongs() {
		// TODO: Create test
		assertTrue(true);
	}
	
	@Test
	void testLibSearchSongArtist_NoSongs() {
		assertEquals(lib.libSearchSongArtist("The Weeknd"), null);
	}
	
	@Test
	void testStoreSearchSongArtist() {
		// TODO: Create test
				assertTrue(true);
	}
	
	@Test
	void testLibSearchAlbumTitle() {
		assertEquals(lib.libSearchAlbumTitle("Hurry Up Tomorrow"), null);
	}
	
	@Test
	void testStoreSearchAlbumTitle() {
		// TODO: Create test
				assertTrue(true);
	}
	
	@Test
	void testLibSearchAlbumArtist() {
		assertEquals(lib.libSearchAlbumArtist("The Weeknd"), null);
	}
	
	@Test
	void testStoreSearchAlbumArtist() {
		// TODO: Create test
				assertTrue(true);
	}
	
//	@Test
//	void testLibSearchPlaylist() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void testAddSong() {
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		assertEquals(lib.libSearchSongTitle("In My Place"), 
				"In My Place by Coldplay from A Rush of Blood to the Head\n");
		assertEquals(lib.allAlbumTitles(), "Album: A Rush of Blood to the Head by Coldplay\n");
	}
	
	@Test
	void testAddSong2() {
		System.out.print(lib.addSong("Hello", "Coldplay"));
		assertEquals(lib.libSearchSongTitle("In My Place"), null);
	}
	
	@Test
	void testAddAlbum() {
		System.out.print(lib.addAlbum("A Rush of Blood to the Head", "Coldplay"));
		assertEquals(lib.allAlbumTitles(), "Album: A Rush of Blood to the Head by Coldplay\n");
	}
	
	@Test
	void testAddAlbumAfterSong() {
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		System.out.print(lib.addAlbum("A Rush of Blood to the Head", "Coldplay"));
		assertEquals(lib.allAlbumTitles(), "Album: A Rush of Blood to the Head by Coldplay\n");
	}
	
	@Test
	void testAddPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		assertEquals(lib.allPlaylists(), "Playlist: Good vibes | 1 songs\n");
	}
	
	@Test
	void testAddSongToPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		assertEquals(lib.allPlaylists(), "Playlist: Good vibes | 2 songs\n");
		assertEquals(lib.libSearchPlaylist("Good vibes"), "Playlist: Good vibes | 2 songs\nSongs in playlist:\n"
				+ "Politik by Coldplay\nAmsterdam by Coldplay\n");
	}
	
	@Test
	void testFavorite() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		assertEquals(lib.allPlaylists(), "Playlist: Good vibes | 2 songs\n");
		assertEquals(lib.libSearchPlaylist("Good vibes"), "Playlist: Good vibes | 2 songs\nSongs in playlist:\n"
				+ "Politik by Coldplay\nAmsterdam by Coldplay\n");
	}
//	
//	@Test
//	void testRate() {
//		fail("Not yet implemented");
//	}
}
