
public class MusicStore {

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
