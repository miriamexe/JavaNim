package tests;

import nim.NimMove;

/**
 * Tests using record NimMove
 */
public class TestsNimMove implements Runnable {
	@Override
	public void run() {
		testCreation();
		testGetters();
		testEquals();
	}

	private static void testCreation() {
		TestUtils.assertTrue("Create NimMove with negative heapIndex", TestUtils.isCaught(() -> {
			new NimMove(-1, 10);
		}, IllegalArgumentException.class));

		TestUtils.assertTrue("Create NimMove with negative removeCount", TestUtils.isCaught(() -> {
			new NimMove(1, -50);
		}, IllegalArgumentException.class));

		TestUtils.assertTrue("Create regular NimMove", TestUtils.beTrue(() -> {
			new NimMove(4, 30);
		}));
	}

	private static void testGetters() {
		final int heapIndex = 10;
		final int removeCount = 23;
		NimMove move = new NimMove(heapIndex, removeCount);
		TestUtils.assertTrue("NimMove getters", move.heapIndex() == heapIndex,
				move.removeCount() == removeCount);
	}

	private static void testEquals() {
		final NimMove move1 = new NimMove(3, 10);
		final NimMove move2 = new NimMove(3, 10);
		TestUtils.assertTrue("NimMove equals", move1.equals(move2));
	}
}
