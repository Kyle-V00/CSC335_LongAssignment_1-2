import java.util.ArrayList;
import java.util.LinkedList:

public class tracking {
	private LinkedList<Song> recent;
	private LinkedList<Song> frequents;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	
	public tracking(ArrayList<Album> albums, ArrayList<Playlist> playlists) {
		this.albums = albums;
		this.playlists = playlists;
		this.recent = new LinkedList<Song>();
		this.frequents = new LinkedList<Song>();
	}
	
	// Setter
	// if there is an uptate in either album or playlist
	private void updateAlbum(ArrayList<Album> album) {
		this.albums = album;
	}
	private void updatePlaylist(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public void playing(Song song) {
		// TODO: need to test if this functions properly
		Song albumSong = null, playlistSong = null;
		int albumFlag = 0, playlistFlag = 0;
		for (int i = 0; i < this.albums.size(); i++) {
			if (this.albums[i].getArtist() == song.getArtist()){
				ArrayList<Song> songListA = this.albums[i].getSongs();
				for (int j = 0; j < songListA.size(); j++) {
					if (songListA[i].getTitle() == song.getTitle()) {
						albumSong = songListA[i];
						albumFlag = 1;
						break;
					}
				}
			}
		}
		for (int k = 0; k < this.playlists.size(); k++) {
			if (this.playlists[k].containSong(song.getName(),song.getArtist())){
				ArrayList<Song> songListP = this.albums[i].getSongs();
				for (int j = 0; j < songListP.size(); j++) {
					if (songListP[j].getTitle() == song.getTitle()) {
						playlistSong = songListP[j];
						playlistFlag = 1;
						break;
					}
				}
			}
		}
		if ((albumFlag == 1) || (playlistFlag == 1)) {
			if (recent.contains(song)) {
				recent.remove(recent.indexOf(song));
				recent.addFirst(song);					
			}
			else if (recent.size() < 10) {
				recent.addFirst(song);				
			}
			else {
				recent.addFirst(song);
				recent.removeLast();
			}
			System.out.print("Playing: " + song);
			if (albumFlag == 1) {
				albumSong.replayCountInc();
			}
			if (playlistFlag == 1) {
				playlistSong.replayCountInc();
			}
			updateFrequents();
			return;
		}
		System.out.print(song + " does not exist in album or playlist");
		return;
	}
	
	public void updateFrequents() {
//		for (int i = 0; i < this.albums.size(); i++) {
//			if (this.albums[i].getArtist() == song.getArtist()){
//				ArrayList<Song> songListA = this.albums[i].getSongs();
//				for (int j = 0; j < songListA.size(); j++) {
//					if (songListA[i].getTitle() == song.getTitle()) {
//						albumSong = songListA[i];
//						albumFlag = 1;
//						break;
//					}
//				}
//			}
//		}
//		for (int k = 0; k < this.playlists.size(); k++) {
//			if (this.playlists[k].containSong(song.getName(),song.getArtist())){
//				ArrayList<Song> songListP = this.albums[i].getSongs();
//				for (int j = 0; j < songListP.size(); j++) {
//					if (songListP[j].getTitle() == song.getTitle()) {
//						playlistSong = songListP[j];
//						playlistFlag = 1;
//						break;
//					}
//				}
//			}
//		}
	}
	
	
	public void getFrequents() {
		
	}
	
	
	
}
