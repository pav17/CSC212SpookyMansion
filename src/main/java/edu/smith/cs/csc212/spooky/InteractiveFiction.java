package edu.smith.cs.csc212.spooky;

import java.util.List;

/**
 * This is our main class for SpookyMansion.
 * It interacts with a GameWorld and handles user-input.
 * It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * @param input - a helper object to ask the user questions.
	 * @param game - the places and exits that make up the game we're playing.
	 * @param timer - the instance of GameTime to use for this game.
	 * @return where - the place the player finished.
	 */
	static String runGame(TextInput input, GameWorld game, GameTime timer) {
		// This is the current location of the player (initialize as start).
		// Maybe we'll expand this to a Player object.
		String place = game.getStart();

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are and the time.
			Place here = game.getPlace(place);
			System.out.println();
			System.out.println("... --- ...");
			System.out.println(here.getDescription(timer));
			System.out.println(timer.GetClockTime());
			here.visit();

			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}
			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" "+i+". " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();

			if (action.equals("quit") || action.equals("escape") || action.equals("q")) {
				if (input.confirm("Are you sure you want to quit?")) {
					return place;
				} else {
					continue;
				}
			} else if (action.equals("help")) { //give the player some help
				System.out.println("To select an action to try, type its number and hit enter.\n" + "To quit, type q, quit, or escape.");
				continue;
			} else if (action.equals("search")) { //reveal secret exits
				for (Exit e : here.getAllExits()) {
					e.search();
				}
				continue;
			} else if (action.equals("rest")) { //stay in place for 2 hours
				timer.increaseHour();
				timer.increaseHour();
				continue;
			}

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			place = destination.getTarget();
			timer.increaseHour();
		}

		return place;
	}

	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();
		
		GameTime timer = new GameTime(0);
		
		// Actually play the game.
		runGame(input, game, timer);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<\n");
		System.out.println("You spent " + timer.getTotalTime() + " hours in the spooky mansion.");
	}

}
