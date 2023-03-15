package tests;

import java.util.stream.IntStream;
import nim.NimGame;
import nim.NimMove;

/**
 * Tests methods of class NimGame, including those that require a NimMove. It is assumed that
 * NimMove works as intended
 */
public class TestsNimGame implements Runnable {
	@Override
	public void run() {
		testCreation();
		testGetters();
		testIsWon();
		testToString();
		testMoveIsValid();
		testMakeMove();
	}

	private static void testCreation() {
		TestUtils.assertTrue("NimGame creation errors", TestUtils.isCaught(() -> {
			// Negative size heaps
			new NimGame(new int[] {-1});
		}, IllegalArgumentException.class),

				TestUtils.isCaught(() -> {
					// Too large heap
					new NimGame(new int[] {Integer.MAX_VALUE});
				}, IllegalArgumentException.class),

				TestUtils.isCaught(() -> {
					// Too many heaps
					int heapCount = NimGame.MAX_HEAP_COUNT + 10;
					int[] heaps = IntStream.iterate(1, x -> 1).limit(heapCount).toArray();
					new NimGame(heaps);
				}, IllegalArgumentException.class),

				TestUtils.beTrue(() -> {
					// valid nim game
					new NimGame(new int[] {1, 3, 5, 7});
				}));
	}

	private static void testGetters() {
		final int[] initHeaps = new int[] {20, 40, 20};
		int[] useHeaps = initHeaps.clone();
		NimGame game = new NimGame(useHeaps);
		useHeaps[1] = -1;
		TestUtils.assertTrue("NimGame getHeaps, edited original array", IntStream
				.range(0, initHeaps.length).allMatch(x -> game.getHeaps()[x] == initHeaps[x]));

		TestUtils.assertTrue("NimGame getHeapSize, edited original array", IntStream
				.range(0, initHeaps.length).allMatch(x -> game.getHeapSize(x) == initHeaps[x]));

		TestUtils.assertTrue("NimGame getHeapCount", game.getHeapCount() == initHeaps.length);

		TestUtils.assertTrue("NimGame getHeapSize, negative index", TestUtils.isCaught(() -> {
			game.getHeapSize(-1);
		}, IndexOutOfBoundsException.class));

		TestUtils.assertTrue("NimGame getHeapSize, too large index", TestUtils.isCaught(() -> {
			game.getHeapSize(initHeaps.length);
		}, IndexOutOfBoundsException.class));
	}

	private static void testIsWon() {
		NimGame game = new NimGame(new int[] {0, 0, 0});
		TestUtils.assertTrue(game.isWon());

		game = new NimGame(new int[] {3, 1, 0});
		TestUtils.assertFalse(game.isWon());
	}

	private static void testToString() {
		NimGame game = new NimGame(new int[] {1, 2, 3});
		String repr = "Heaps:\n1\n2\n3";
		TestUtils.assertTrue("NimGame toString with heaps", TestUtils.sameRepr(repr, game));

		game = new NimGame(new int[] {});
		repr = "Heaps:\n";
		TestUtils.assertTrue("NimGame toString without heaps", TestUtils.sameRepr(repr, game));
	}

	private static void testMoveIsValid() {
		NimGame game = new NimGame(new int[] {3, 5, 1, 3});
		NimMove move = new NimMove(5, 1);
		TestUtils.assertFalse("moveIsValid with too big heapIndex", game.moveIsValid(move));

		move = new NimMove(0, 5);
		TestUtils.assertFalse("moveIsValid with too big removeCount", game.moveIsValid(move));

		move = new NimMove(1, 5);
		TestUtils.assertTrue("moveIsValid with valid move", game.moveIsValid(move));
	}

	private static void testMakeMove() {
		final int[] heaps = new int[] {4, 7, 3, 47};
		NimGame game = new NimGame(heaps);

		TestUtils.assertTrue("makeMove with too large heap index", TestUtils.isCaught(() -> {
			NimMove move = new NimMove(40, 1);
			game.makeMove(move);
		}, IndexOutOfBoundsException.class));

		TestUtils.assertTrue("makeMove with too large removeCount", TestUtils.isCaught(() -> {
			NimMove move = new NimMove(1, 30);
			game.makeMove(move);
		}, IllegalArgumentException.class));

		TestUtils.assertTrue("NimGame immutability", TestUtils.beTrue(() -> {
			NimMove move = new NimMove(3, 20);
			game.makeMove(move);
		}), IntStream.range(0, heaps.length).allMatch(x -> game.getHeapSize(x) == heaps[x]));

		NimGame moveGame = new NimGame(heaps);
		NimMove move = new NimMove(3, 20);
		final int[] expectedHeaps = new int[] {4, 7, 3, 27};
		moveGame = moveGame.makeMove(move);
		int[] actualHeaps = moveGame.getHeaps();
		TestUtils.assertTrue("NimGame with valid move", IntStream.range(0, actualHeaps.length)
				.allMatch(x -> actualHeaps[x] == expectedHeaps[x]));
	}
}
