package edu.smith.cs.csc212.spooky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This represents a place in our text adventure.
 * @author jfoley
 *
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place.
	 */
	private List<Exit> exits;
	/**
	 * This is the identifier of the place.
	 */
	private String id;
	/**
	 * What to tell the user about this place in the day.
	 */
	private String description;
	/**
	 * What to tell the user about this place at night.
	 */
	private String nightDescription;
	/**
	 * Has this place been visited
	 */
	public boolean visited;
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
	
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal - whether this place ends the game.
	 */
	protected Place(String id, String description, String nightDescription, boolean terminal) {
		this.id = id;
		this.description = description;
		this.nightDescription = nightDescription;
		this.exits = new ArrayList<>();
		this.terminal = terminal;
	}
	
	/**
	 * Create an exit for the user to navigate to another Place.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		this.exits.add(exit);
	}
	
	/**
	 * For gameplay, whether this place ends the game.
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}
	
	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * The narrative description of this place.
	 * @param timer - The instance of GameTimer to use to check if it is night.
	 * @return what we show to a player about this place.
	 */
	public String getDescription(GameTime timer) {
		if (timer.isNightTime()) {
			return this.nightDescription;
		} else {
			return this.description;
		}
	}
	
	/**
	 * Change visited when this place has been visited.
	 * If it has, inform the player.
	 */
	public void visit() {
		if (this.visited == false) {
			this.visited = true;
		} else {
			System.out.println("It feels like you've been here before...");
		}
	}

	/**
	 * Get a view of the exits from this Place, for navigation.
	 * Excludes secret exits which are still hidden.
	 * @return all the visible exits from this place.
	 */
	public List<Exit> getVisibleExits() {
		List<Exit> visible = new ArrayList<>();
		for (Exit e : this.exits) {
			if (e instanceof SecretExit && e.isSecret()) {
				continue;
			} else {
				visible.add(e);
			}
		}
		return visible;
	}
	
	/**
	 * Get a view of all the exits from this Place, for revealing secret exits.
	 * @return all the available exits from this place.
	 */
	public List<Exit> getAllExits() {
		List<Exit> exitList = new ArrayList<>();
		for (Exit e : this.exits) {
			exitList.add(e);
		}
		return exitList;
	}
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @param nightDescription - this is the description of the place at night.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description, String nightDescription) {
		return new Place(id, description, nightDescription,true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @param nightDescription - this is the description of the place at night.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description, String nightDescription) {
		return new Place(id, description, nightDescription,false);
	}
	
	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place("+this.id+" with "+this.exits.size()+" exits.)";
	}
	
	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}
	
}
