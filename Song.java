/*
 * Class Song
 * Author: Liam Mohajeri Norris
 * Purpose: represent a song with an artist
 * and song name, as well as a rating feature
 * to rate songs from 1 to 5, and a "like" field.
 */

public class Song {
	private String artist;
	private String name;
	private boolean like;
	private Rating rating;
	
	/*
	 *  @pre: artist_name != null, song_name != null
	 */
	public Song(String song_name, String artist_name) {
		/*
		 * Constructor. Builds the class with input of artist name and song name.
		 */
		this.artist = artist_name;
		this.name = song_name;
		this.like = false;
		this.rating = Rating.NONE;
	}
	
	// Getters:
	public String getArtist() {
		return this.artist;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getFavorite() {
		return this.like;
	}
	
	public String getRating() {
		return this.rating.toString();
	}
	
	// Setters:
	public void favorite() {
		this.like = true;
	}
	
	/*
	 * @pre: 1 <= rating <= 5
	 */
	public void rate(int rating) {
		if (rating == 1) {
			this.rating = Rating.ONE;
		}
		else if (rating == 2) {
			this.rating = Rating.TWO;
		}
		else if (rating == 3) {
			this.rating = Rating.THREE;
		}
		else if (rating == 4) {
			this.rating = Rating.FOUR;
		}
		else if (rating == 5) {
			this.rating = Rating.FIVE;
			this.like = true; // Automatically favorite song if rating == 5
		}
	}
	
	@Override
	public String toString() {
		return this.name + " by " + this.artist;
	}
}
