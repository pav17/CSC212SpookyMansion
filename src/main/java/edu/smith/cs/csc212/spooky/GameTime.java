package edu.smith.cs.csc212.spooky;

public class GameTime {
	
	/**
	 * Stores the time.
	 */
	private int hour;
	
	/**
	 * sets up the gametime class and sets the initial time.
	 * @param hour
	 */
	public GameTime(int hour) {
		this.hour = hour;
	}
	
	/**
	 * Just returns the hour
	 * @return the current hour
	 */
	public int getHour() {
		return hour;
	}
}
