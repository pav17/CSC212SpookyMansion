package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit {

	
	/**
	 * Is the secret exit still hidden?
	 */
	private boolean hidden = true;
	
	
	/**
	 * create a new secret exit
	 * @param target
	 * @param description
	 */
	
	public SecretExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/**
	 * is this exit still secret?
	 * @return if the exit has been found or not.
	 */
	public boolean isSecret() {
		return hidden;
	}
	
	@Override
	/**
	 * On SecretExit reveals the exit to the player.
	 */
	public void search() {
		if (hidden) {
			hidden = false;
		}
	}
}
