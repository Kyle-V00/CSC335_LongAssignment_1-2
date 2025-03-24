/*
 * Enum Rating
 * Author: Liam Mohajeri Norris
 * Purpose: represent a rating for
 * a song, from 1 to 5, with 0 being
 * "no rating."
 */

public enum Rating {
	NONE, ONE, TWO, THREE, FOUR, FIVE;
	
	public String toString() {
		if (this.equals(NONE)) {
			return "No Rating";
		}
		else if (this.equals(ONE)) {
			return "1 star";
		}
		else if (this.equals(TWO)) {
			return "2 stars";
		}
		else if (this.equals(THREE)) {
			return "3 stars";
		}
		else if (this.equals(FOUR)) {
			return "4 stars";
		}
		else {
			return "5 stars";
		}
	}
}
