package nim.players;

import nim.NimGame;
import nim.NimMove;

/**
 * This interface is for objects that can be used as a Nim player
 */
public interface Player {
	/**
	 * Given the current game state, generate a move saying how many objects should be removed from
	 * which heap
	 * 
	 * The returned move has to be valid; that is, return true when passed to the NimMove::isValid
	 * method
	 * 
	 * @param heaps The current count of remaining objects on each heap
	 * @return The move to make
	 */
	public NimMove generateMove(NimGame game);

	/**
	 * Get the player's name for display purposes
	 * 
	 * @return
	 */
	public String getName();
}
