import java.util.ArrayList;

public class MusicStore extends Object {
	
	private ArrayList<Album> albums;
	
	public MusicStore() {
		albums = new ArrayList<Album>();
		albums.add(new Album("A Rush of Blood to the Head", "Coldplay"));
		albums.get(0).addSong("Politik", albums.get(0).getArtist());
		albums.get(0).addSong("In My Place", albums.get(0).getArtist());
		albums.get(0).addSong("God Put a Smile Upon Your Face", albums.get(0).getArtist());
		albums.get(0).addSong("The Scientist", albums.get(0).getArtist());
		albums.get(0).addSong("Clocks", albums.get(0).getArtist());
		albums.get(0).addSong("Daylight", albums.get(0).getArtist());
		albums.get(0).addSong("Green Eyes", albums.get(0).getArtist());
		albums.get(0).addSong("Warning Sign", albums.get(0).getArtist());
		albums.get(0).addSong("A Whisper", albums.get(0).getArtist());
		albums.get(0).addSong("A Rush of Blood to the Head", albums.get(0).getArtist());
		albums.get(0).addSong("Amsterdam", albums.get(0).getArtist());

		albums.add(new Album("Waking Up", "OneRepublic"));
		albums.get(1).addSong("Made for You", albums.get(1).getArtist());
		albums.get(1).addSong("All the Right Moves", albums.get(1).getArtist());
		albums.get(1).addSong("Secrets", albums.get(1).getArtist());
		albums.get(1).addSong("Everybody Loves Me", albums.get(1).getArtist());
		albums.get(1).addSong("Missing Persons 1 & 2", albums.get(1).getArtist());
		albums.get(1).addSong("Good Life", albums.get(1).getArtist());
		albums.get(1).addSong("All This Time", albums.get(1).getArtist());
		albums.get(1).addSong("Fear", albums.get(1).getArtist());
		albums.get(1).addSong("Waking Up", albums.get(1).getArtist());
		albums.get(1).addSong("Marchin On", albums.get(1).getArtist());
		albums.get(1).addSong("Lullaby", albums.get(1).getArtist());
	}

	public String searchSongTitle(String title) {
		// TODO Auto-generated method stub
		return "";
	}

	public String searchSongArtist(String artist) {
		// TODO Auto-generated method stub
		return "";
	}

	public String searchAlbumTitle(String title) {
		// TODO Auto-generated method stub
		return "";
	}

	public String searchAlbumArtist(String artist) {
		// TODO Auto-generated method stub
		return "";
	}

	public String[] searchSongTitleAndArtist(String title, String artist) {
		// Return String[title, artist, album]
		// Return null if not found.
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).containsSong(title) && albums.get(i).getArtist().equals(artist)) {
				String[] info = new String[3];
				info[0] = title;
				info[1] = artist;
				info[2] = albums.get(i).getName();
				return info;
			}
		}
		return null;
	}

	public Album getAlbum(String title, String artist) {
		// TODO Auto-generated method stub
		// return null if album not found
		// Otherwise return a copy of the album object
		for (int i = 0; i < albums.size(); i ++) {
			if (albums.get(i).getName().equals(title) && albums.get(i).getArtist().equals(artist)) {
				return albums.get(i).copy();
			}
		}
		return null;
	}

}
