package edu.smith.cs.csc212.spooky;

public class GameTime {
	
	/**
	 * Stores the time.
	 */
	private int hour;
	
	/**
	 * The total time spent in the game
	 */
	private int totalTime;
	
	/**
	 * sets up the GameTime class and sets the initial time.
	 * @param hour
	 */
	public GameTime(int hour) {
		this.hour = hour;
		totalTime = 0;
	}
	
	/**
	 * Translates the hour variable into a readable time.
	 * @return a string describing the time in 12 hour time.
	 */
	public String GetClockTime() {
		if (hour == 0) {
			return("It is 12:00 pm.");
		} else if (hour == 1) {
			return("It is 1:00 am.");
		} else if (hour == 2) {
			return("It is 2:00 am.");
		} else if (hour == 3) {
			return("It is 3:00 am.");
		} else if (hour == 4) {
			return("It is 4:00 am.");
		} else if (hour == 5) {
			return("It is 5:00 am.");
		} else if (hour == 6) {
			return("It is 6:00 am.");
		} else if (hour == 7) {
			return("It is 7:00 am.");
		} else if (hour == 8) {
			return("It is 8:00 am.");
		} else if (hour == 9) {
			return("It is 9:00 am.");
		} else if (hour == 10) {
			return("It is 10:00 am.");
		} else if (hour == 11) {
			return("It is 11:00 am.");
		} else if (hour == 12) {
			return("It is 12:00 am.");
		} else if (hour == 13) {
			return("It is 1:00 pm.");
		} else if (hour == 14) {
			return("It is 2:00 pm.");
		} else if (hour == 15) {
			return("It is 3:00 pm.");
		} else if (hour == 16) {
			return("It is 4:00 pm.");
		} else if (hour == 17) {
			return("It is 5:00 pm.");
		} else if (hour == 18) {
			return("It is 6:00 pm.");
		} else if (hour == 19) {
			return("It is 7:00 pm.");
		} else if (hour == 20) {
			return("It is 8:00 pm.");
		} else if (hour == 21) {
			return("It is 9:00 pm.");
		} else if (hour == 22) {
			return("It is 10:00 pm.");
		} else if (hour == 23) {
			return("It is 11:00 pm.");
		} else {
			return("Your watch is broken.");
		}
	}
	
	/**
	 * Just returns the hour.
	 * @return the current hour.
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * Returns the total time spent in game.
	 * @return the total time.
	 */
	public int getTotalTime() {
		return totalTime;
	}
	
	/**
	 * Increments the hour by one and resets to 0 when hours reaches 23.
	 * Also increments totalTime.
	 */
	public void increaseHour() {
		if (hour < 23) {
			hour++;
		} else {
			hour = 0;
		}
		totalTime++;
	}
	
	/**
	 * Checks if it is night time.
	 * @return true if between 10pm and 6am (inclusive).
	 */
	public boolean isNightTime() {
		if(hour > 21 || hour < 7) {
			return true;
		} else {
			return false;
		}
	}
}
