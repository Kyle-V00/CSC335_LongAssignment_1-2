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
	}
	
//	@Test
//	void testAddAlbum() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testAddPlaylist() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testAddSongToPlaylist() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testFavorite() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testRate() {
//		fail("Not yet implemented");
//	}
}
