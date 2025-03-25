import java.util.ArrayList;
import java.util.LinkedList;

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
	// if there is an upDate in either album or playlist
	
	// takes an updated version of album 
	void updateAlbum(ArrayList<Album> album) {
		this.albums = album;
	}
	
	// takes an updated version of playlists
	void updatePlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public LinkedList<Song> playing(String title, String artist) {
		// TODO: need to test if this functions properly
		Song albumSong = null, playlistSong = null;
		int albumFlag = 0, playlistFlag = 0;
		int albumPlace = -1, playlistPlace = -1;
		for (int i = 0; i < this.albums.size(); i++) {
//			if (this.albums.get(i).getArtist().equals(artist)){
//				ArrayList<Song> songListA = this.albums.get(i).getSongList();
////				ArrayList<Song> songListA = this.albums.get(i).getSongs();
//				for (int j = 0; j < songListA.size(); j++) {
//					if (songListA[j].getTitle().equals(title)) {
//						albumSong = songListA[j];
//						albumFlag = 1;
//						break;
//					}
//				}
//			}
			if (albums.get(i).containsSong(title)) {
				albumFlag = 1; 
				albumSong = albums.get(i).incSongCount(title);
//				albumPlace = i;
			}
		}
		for (int k = 0; k < this.playlists.size(); k++) {
			if (playlists.get(k).containsSong(title,artist)){
//				ArrayList<Song> songListP = this.playlists[i].getSongs();
//				for (int l = 0; l < songListP.size(); l++) {
//					if (songListP[l].getTitle().equals(title)) {
//						playlistSong = songListP[l];
//						playlistFlag = 1;
//						break;
//					}
//				}
				playlistFlag = 1;
				playlistSong = playlists.get(k).incSongCount(title, artist);
//				playlistPlace = k;
			}			
		}
		if ((albumFlag == 1) || (playlistFlag == 1)) {
			Song song = (albumFlag == 1) ? albumSong : playlistSong;
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
			System.out.print("Playing: " + song + "\n");
//			if (albumFlag == 1) {
//				albumSong.replayCountInc();
//			}
//			if (playlistFlag == 1) {
//				playlistSong.replayCountInc();
//			}
//			updateFrequents();
			return recent;
		}
		System.out.print(title + " by "+ artist + " does not exist in album or playlist\n");
		return null;
	}
	
//	public void updateFrequents() {
//		Song albumSong = null, playlistSong = null;
//		int albumFlag = 0, playlistFlag = 0;
//		int countMax = 0;
//		
//		for (int i = 0; i < this.albums.size(); i++) {
//			Album album = this.albums.get(i);
//			for (int j = 0; j < album.getNumSongs(); j++) {
//				Song song = album[i];
//				if (song.getReplayCount() >= countMax) {
//					if (countMax < song.getReplayCount()) {
////						frequents.remove(frequents.indexOf(song));	
//						countMax = song.getReplayCount();
//					}
//					else if (frequents.size() < 10) {
//						frequents.addFirst(song);				
//					}
//					else {
//						frequents.addFirst(song);
//						frequents.removeLast();
//					}
//				}
//			}
//		}
//		
//		for (int k = 0; k < this.playlists.size(); k++) {
//			ArrayList<Song> songListP = this.playlists[i].getSongs();
//			for (int j = 0; j < songListP.size(); j++) {
//				Song playlistSong = songListP[j];
//				if (playlistSong.getReplayCount() >= countMax) {
//					if (countMax < song.getReplayCount()) {
////						frequents.remove(frequents.indexOf(song));	
//						countMax = song.getReplayCount();
//					}
//					if (!frequents.contains(playlistSong)) {
//						if (frequents.size() < 10) {
//							frequents.addFirst(song);				
//						}
//						else {
//							frequents.addFirst(song);
//							frequents.removeLast();
//						}
//					}
//				}
//			}
//		}
//	}
	
	
	public LinkedList<Song> getFrequents() {
		return this.frequents;
	}
	
	
	
}