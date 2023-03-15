package tests;

import nim.NimGame;
import nim.NimMove;
import nim.players.MinMaxPlayer;

public class TestsMinMaxPlayer implements Runnable {

	@Override
	public void run() {
		testGenerateMove();
	}

	/**
	 * Tests whether generateMove() generates the best move in very basic scenarios
	 */
	private static void testGenerateMove() {
		NimGame game = new NimGame(new int[] {0, 0, 2, 0});
		MinMaxPlayer player = new MinMaxPlayer();
		final NimMove expectedMove = new NimMove(2, 1);
		NimMove generatedMove = player.generateMove(game);
		TestUtils.assertTrue("MinMaxPlayer generates best move with only one heap",
				generatedMove.equals(expectedMove));
	}

}
