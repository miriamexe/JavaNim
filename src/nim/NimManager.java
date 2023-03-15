package nim;

import nim.players.Player;

/**
 * Manager class for nim games. After supplying two players, this manager handles games between
 * these players.
 * 
 * To implement a GUI, one could extend this class and override the playGame and displayGame methods
 */
public class NimManager {
	private Player player1;
	private Player player2;

	public NimManager(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * Handles players taking turns until game is finished and a player wins. Player 1 begins.
	 * 
	 * @throws RuntimeException if a player generates an invalid move
	 * @param game Game to play
	 * @return The player who won the game
	 */
	public Player playGame(NimGame game) {
		boolean player1Turn = true;
		while (!game.isWon()) {
			displayGame(game);

			NimMove move = (player1Turn ? player1 : player2).generateMove(game);
			if (!game.moveIsValid(move)) {
				throw new RuntimeException("The player generated an invalid move");
			}
			game = game.makeMove(move);

			player1Turn = !player1Turn;
		}

		return player1Turn ? player1 : player2;
	}

	/**
	 * Switch which player goes first
	 */
	public void switchPlayers() {
		Player swapReference = player1;
		player1 = player2;
		player2 = swapReference;
	}

	/**
	 * Displays the game by printing a string to the console
	 * 
	 * @param game
	 */
	protected void displayGame(NimGame game) {
		System.out.println(game.toString());
	}
}
