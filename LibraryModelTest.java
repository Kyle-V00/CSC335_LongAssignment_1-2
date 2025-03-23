import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class LibraryModelTest {
	
	LibraryModel lib = new LibraryModel();
	UserManager manager;

	@Test
	void testAllSongTitles_NoSongs() {
		assertEquals(lib.allSongTitles(), "Library is empty.\n");
	}
	
	@Test
	void testAllSongTitles_2Songs() {
		lib.addSong("In My Place", "Coldplay");
		System.out.print(lib.addSong("Good Life", "OneRepublic"));
		assertEquals(lib.allAlbumTitles(),"Album: A Rush of Blood to the Head by Coldplay | Alternative | 2002\n"
				+ "Album: Waking Up by OneRepublic | Rock | 2009\n");
		assertEquals(lib.allSongTitles(), "\tIn My Place\n\tGood Life\n");
	}
	
	@Test
	void testAllArists_NoAlbums() {
		assertEquals(lib.allArtists(), "Library is empty.\n");
	}
	
	@Test
	void testAllArists_2Albums() {
		lib.addSong("In My Place", "Coldplay");
		lib.addAlbum("Waking Up", "OneRepublic");
		assertEquals(lib.allArtists(), "Coldplay\nOneRepublic\n");
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
	void testLibSearchSongArtist_4Songs() {
		lib.addSong("A Rush of Blood to the Head", "Coldplay");
		lib.addSong("In My Place", "Coldplay");
		lib.addSong("Good Life", "OneRepublic");
		lib.addSong("Daydreamer", "Adele");
		lib.addSong("Lovesong", "Adele");
		assertEquals(lib.libSearchSongArtist("Coldplay"), "A Rush of Blood to the Head by Coldplay\n"
				+ "In My Place by Coldplay\n");
	}
	
	@Test
	void testStoreSearchSongArtist() {
		assertEquals(lib.storeSearchSongArtist("Coldplay"), "Politik by Coldplay\n" + 
				"In My Place by Coldplay\n" + "God Put a Smile Upon Your Face by Coldplay\n" + 
				"The Scientist by Coldplay\n" + "Clocks by Coldplay\n" + "Daylight by Coldplay\n" +
				"Green Eyes by Coldplay\n" + "Warning Sign by Coldplay\n" + "A Whisper by Coldplay\n" + 
				"A Rush of Blood to the Head by Coldplay\n" + "Amsterdam by Coldplay\n");
	}
	
	@Test
	void testStoreSearchSongTitle() {
		assertEquals(lib.storeSearchSongTitle("Lullaby"), "Lullaby by Leonard Cohen from Old Ideas\n" + 
				"Lullaby by OneRepublic from Waking Up\n");
	}
	
	@Test
	void testLibSearchAlbumTitle_NoAlbums() {
		assertEquals(lib.libSearchAlbumTitle("Hurry Up Tomorrow"), null);
	}
	
	@Test
	void testLibSearchAlbumTitle_2Albums() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addSong("Good Life", "OneRepublic");
		assertEquals(lib.libSearchAlbumTitle("Waking Up"), "Album: Waking Up by OneRepublic | Rock | 2009\nSongs:\n"
				+ "\tGood Life\n");
	}
	
	@Test
	void testStoreSearchAlbumTitle() {
		assertEquals(lib.storeSearchAlbumTitle("19"), "Album: 19 by Adele | Pop | 2008\nSongs:\n"
				+ "\tDaydreamer\n"
				+ "\tBest for Last\n"
				+ "\tChasing Pavements\n"
				+ "\tCold Shoulder\n"
				+ "\tCrazy for You\n"
				+ "\tMelt My Heart to Stone\n"
				+ "\tFirst Love\n"
				+ "\tRight as Rain\n"
				+ "\tMake You Feel My Love\n"
				+ "\tMy Same\n"
				+ "\tTired\n"
				+ "\tHometown Glory\n");
	}
	
	@Test
	void testLibSearchAlbumArtist_NoAlbums() {
		assertEquals(lib.libSearchAlbumArtist("The Weeknd"), null);
	}
	
	@Test
	void testLibSearchAlbumArtist_4Albums() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addSong("Good Life", "OneRepublic");
		lib.addSong("Daydreamer", "Adele");
		lib.addSong("Lovesong", "Adele");
		assertEquals(lib.libSearchAlbumArtist("Adele"), "Album: 19 by Adele | Pop | 2008\nSongs:\n"
				+ "\tDaydreamer\nAlbum: 21 by Adele | Pop | 2011\nSongs:\n"
				+ "\tLovesong\n");
	}
	
	@Test
	void testStoreSearchAlbumArtist() {
		assertEquals(lib.storeSearchAlbumArtist("Adele"), "Album: 19 by Adele | Pop | 2008\nSongs:\n"
				+ "\tDaydreamer\n"
				+ "\tBest for Last\n"
				+ "\tChasing Pavements\n"
				+ "\tCold Shoulder\n"
				+ "\tCrazy for You\n"
				+ "\tMelt My Heart to Stone\n"
				+ "\tFirst Love\n"
				+ "\tRight as Rain\n"
				+ "\tMake You Feel My Love\n"
				+ "\tMy Same\n"
				+ "\tTired\n"
				+ "\tHometown Glory\n"
				+ "Album: 21 by Adele | Pop | 2011\nSongs:\n"
				+ "\tRolling in the Deep\n"
				+ "\tRumour Has It\n"
				+ "\tTurning Tables\n"
				+ "\tDon't You Remember\n"
				+ "\tSet Fire to the Rain\n"
				+ "\tHe Won't Go\n"
				+ "\tTake It All\n"
				+ "\tI'll Be Waiting\n"
				+ "\tOne and Only\n"
				+ "\tLovesong\n"
				+ "\tSomeone Like You\n"
				+ "\tI Found a Boy\n");
	}
	
	@Test
	void testLibSearchPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addAlbum("19", "Adele");
		lib.addAlbum("Waking Up", "OneRepublic");
		lib.addAlbum("21", "Adele");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Daydreamer", "Adele");
		lib.addSongToPlaylist("Good vibes", "Good Life", "OneRepublic");
		lib.addSongToPlaylist("Good vibes", "Lovesong", "Adele");
		
		assertEquals(lib.libSearchPlaylist("Good vibes"), "Playlist: Good vibes | 5 songs\nSongs in playlist:\n"
				+ "\tPolitik by Coldplay\n\tAmsterdam by Coldplay\n"
				+ "\tDaydreamer by Adele\n\tGood Life by OneRepublic\n"
				+ "\tLovesong by Adele\n");
	}
	
	@Test
	void testLibSearchPlaylist_DoesntExist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addAlbum("19", "Adele");
		lib.addAlbum("Waking Up", "OneRepublic");
		lib.addAlbum("21", "Adele");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Daydreamer", "Adele");
		lib.addSongToPlaylist("Good vibes", "Good Life", "OneRepublic");
		lib.addSongToPlaylist("Good vibes", "Lovesong", "Adele");
		
		assertEquals(lib.libSearchPlaylist("Good vibes!"), null);
	}
	
	@Test
	void testAddSong() {
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		assertEquals(lib.libSearchSongTitle("In My Place"), 
				"In My Place by Coldplay from A Rush of Blood to the Head\n");
		assertEquals(lib.allAlbumTitles(), "Album: A Rush of Blood to the Head by Coldplay | Alternative | 2002\n");
	}
	
	@Test
	void testAddSong2() {
		System.out.print(lib.addSong("Hello", "Coldplay"));
		assertEquals(lib.libSearchSongTitle("In My Place"), null);
	}
	
	@Test
	void testAddAlbum() {
		System.out.print(lib.addAlbum("A Rush of Blood to the Head", "Coldplay"));
		assertEquals(lib.allAlbumTitles(), "Album: A Rush of Blood to the Head by Coldplay | Alternative | 2002\n");
	}
	
	@Test
	void testAddAlbum_DoesntExist() {
		assertEquals(lib.addAlbum("Hello", "Coldplay"), "Album Hello by Coldplay is not in store.\n");
	}
	
	@Test
	void testAddAlbumAfterSong() {
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		System.out.print(lib.addAlbum("A Rush of Blood to the Head", "Coldplay"));
		assertEquals(lib.allAlbumTitles(), "Album: A Rush of Blood to the Head by Coldplay | Alternative | 2002\n");
	}
	
	@Test
	void testAddPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		assertEquals(lib.allPlaylists(), "Playlist: Good vibes | 1 songs\nSongs in playlist:\n"
				+ "\tPolitik by Coldplay\n");
	}
	
	@Test
	void testAddSongToPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		assertEquals(lib.allPlaylists(), "Playlist: Good vibes | 2 songs\nSongs in playlist:\n"
				+ "\tPolitik by Coldplay\n\tAmsterdam by Coldplay\n");
		assertEquals(lib.libSearchPlaylist("Good vibes"), "Playlist: Good vibes | 2 songs\nSongs in playlist:\n"
				+ "\tPolitik by Coldplay\n\tAmsterdam by Coldplay\n");
	}
	
	@Test
	void testAddSongToPlaylist_NoPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		assertEquals(lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay"), "Playlist Good vibes does not exist. "
				+ "Please create playlist.\n");
	}
	
	@Test
	void testAddSongToPlaylist_DoesntExist() {
		lib.addPlaylist("Good vibes");
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		assertEquals(lib.addSongToPlaylist("Chill", "Politik", "Coldplay"), "Playlist Chill does not exist. "
				+ "Please create playlist.\n");
	}
	
	@Test
	void testAddPlaylist_AlreadyExists() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		assertEquals(lib.addPlaylist("Good vibes"), "Playlist Good vibes already exists.\n");
	}
	
	@Test
	void testRemoveSongFromPlaylist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		assertEquals(lib.removeSongFromPlaylist("Good vibes", "Politik", "Coldplay"), 
				"Song Politik by Coldplay successfully removed from Good vibes\n");
		assertEquals(lib.libSearchPlaylist("Good vibes"), "Playlist: Good vibes | 1 songs\nSongs in playlist:\n"
				+ "\tAmsterdam by Coldplay\n");
	}
	
	@Test
	void testRemoveSongFromPlaylistNoPlaylsit() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		assertEquals(lib.removeSongFromPlaylist("Chill vibes", "Politik", "Coldplay"), 
				"Playlist Chill vibes does not exist. Please create playlist.\n");
	}
	
	@Test
	void testRemoveSongFromPlaylistWrongSong() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.addPlaylist("Good vibes");
		lib.addSongToPlaylist("Good vibes", "Politik", "Coldplay");
		lib.addSongToPlaylist("Good vibes", "Amsterdam", "Coldplay");
		assertEquals(lib.removeSongFromPlaylist("Good vibes", "In My Place", "Coldplay"), 
				"Playlist Good vibes does not contain song.\n");
	}
	
	@Test
	void testFavorite() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.favorite("A Rush of Blood to the Head", "Coldplay");
		lib.favorite("A Rush of Blood to the Head", "Coldplay");
		lib.favorite("In My Place", "Coldplay");
		assertEquals(lib.allFavorites(), ("\tIn My Place by Coldplay\n\tA Rush of Blood to the Head by Coldplay\n"));
	}
	
	@Test
	void testFavoriteSongDoesntExist() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		assertEquals(lib.favorite("Chill", "Coldplay"), "Song Chill by Coldplay could not be located.\n");
	}
	
	@Test
	void testRateBadInt() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		assertEquals(lib.rate("In My Place", "Coldplay", 6), "Please enter an integer from 1 to 5.\n");
		assertEquals(lib.rate("In My Place", "Coldplay", -1), "Please enter an integer from 1 to 5.\n");
	}
	
	@Test
	void testRateMissingSong() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		assertEquals(lib.rate("Chill", "Coldplay", 4), "Song Chill by Coldplay could not be located.\n");
	}
	
	@Test
	void testRate() {
		lib.addAlbum("A Rush of Blood to the Head", "Coldplay");
		lib.rate("A Rush of Blood to the Head", "Coldplay", 3);
		lib.rate("A Rush of Blood to the Head", "Coldplay", 5);
		lib.rate("In My Place", "Coldplay", 5);
		assertEquals(lib.allFavorites(), ("\tIn My Place by Coldplay\n\tA Rush of Blood to the Head by Coldplay\n"));
	}
	
	@Test
	void testAddSongTwice() {
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		assertEquals(lib.allSongTitles(), "\tIn My Place\n");
	}
	
	@Test
	void testAddSongToExistingAlbum() {
		System.out.print(lib.addSong("In My Place", "Coldplay"));
		System.out.print(lib.addSong("Amsterdam", "Coldplay"));
		assertEquals(lib.allSongTitles(), "\tIn My Place\n\tAmsterdam\n");
	}
	
	
	////////////////////////////
	// TEST UserManager BELOW //
	////////////////////////////
	
	@Test
	void testAddUser() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		assertTrue(manager.addUser("Steve"));
	}
	
	@Test
	void testAddUserAlreadyExists() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		manager.addUser("Steve");
		assertFalse(manager.addUser("Steve"));
	}
	
	@Test
	void testAddPassword() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		manager.addUser("Steve");
		assertTrue(manager.addPassword("Steve", "imsteve"));
	}
	
	@Test
	void testAddPasswordNoUser() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		assertTrue(manager.addPassword("Steve", "imsteve"));
	}
	
	@Test
	void testLogin() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		manager.addUser("Steve");
		manager.addPassword("Steve", "imsteve");
		assertTrue(manager.login("Steve", "imsteve", false));
	}
	
	@Test
	void testLoginWrongPassword() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		manager.addUser("Steve");
		manager.addPassword("Steve", "imsteve");
		assertFalse(manager.login("Steve", "imSTEVE", false));
	}
	
	@Test
	void testLoginWrongName() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		manager.addUser("Steve");
		manager.addPassword("Steve", "imsteve");
		assertFalse(manager.login("Jack", "imSTEVE", false));
	}
	
	@Test
	void testLoginNoUser() throws NoSuchAlgorithmException, IOException {
		manager = new UserManager();
		assertFalse(manager.login("Jack", "imSTEVE", false));
	}
	
}
