package nim.players;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import nim.NimGame;
import nim.NimMove;


public class MinMaxPlayer implements Player {
	/**
	 * Values for given hashes that have already been calculated. If 1 is stored, the player whose
	 * turn it is is winning. If -1 is stored, the other player is winning
	 */
	private Map<Long, Byte> cachedValues;
	/**
	 * Used in hash generation, stored as class variable so it is only calculated once
	 */
	private int shiftIncrement;

	public MinMaxPlayer() {
		cachedValues = new HashMap<Long, Byte>();
		shiftIncrement = (int) Math.ceil(Math.log(NimGame.MAX_HEAP_SIZE) / Math.log(2));
	}

	public String getName() {
		return "MinMax-Player";
	}

	public NimMove generateMove(NimGame game) {
		NimMove firstLegalMove = null;
		int[] heaps = game.getHeaps();
		for (int heapIndex = 0; heapIndex < heaps.length; heapIndex++) {
			for (int removeCount = heaps[heapIndex]; removeCount > 0; removeCount--) {
				if (firstLegalMove == null) {
					firstLegalMove = new NimMove(heapIndex, removeCount);
				}

				heaps[heapIndex] -= removeCount;
				byte value = minMax(heaps, false);
				heaps[heapIndex] += removeCount;
				if (value > 0) {
					return new NimMove(heapIndex, removeCount);
				}
			}
		}
		return firstLegalMove;
	}

	/**
	 * Performs MinMax algorithm to find value of position given game of Nim
	 * 
	 * @param heaps The current gamestate
	 * @param maximizingTurn Whether it's the maximizing player's turn (or, otherwise, the
	 *        minimizing player's)
	 * @return Value of given game state
	 */
	private byte minMax(int[] heaps, boolean maximizingTurn) {
		long hash = getUniqueHashForGameState(heaps);
		if (hash == 0) {
			return (byte) (maximizingTurn ? 1 : -1);
		}
		if (cachedValues.containsKey(hash)) {
			byte cachedValue = cachedValues.get(hash);
			return (byte) (maximizingTurn ? cachedValue : -cachedValue);
		}

		for (int heapIndex = 0; heapIndex < heaps.length; heapIndex++) {
			for (int removeCount = heaps[heapIndex]; removeCount > 0; removeCount--) {
				heaps[heapIndex] -= removeCount;
				byte newValue = minMax(heaps, !maximizingTurn);
				heaps[heapIndex] += removeCount;

				// as this method only returns -1 or 1, we can stop as soon as a winning move is
				// found
				if ((maximizingTurn && newValue > 0) || (!maximizingTurn && newValue < 0)) {
					cachedValues.put(hash, (byte) 1);
					return newValue;
				}
			}
		}
		byte value = (byte) (maximizingTurn ? -1 : 1);
		cachedValues.put(hash, (byte) -1);
		return value;
	}

	/**
	 * Return a unique hash for the given game state
	 * 
	 * @param heaps
	 * @return
	 */
	private long getUniqueHashForGameState(int[] heaps) {
		int[] heapsSorted = heaps.clone();
		Arrays.sort(heapsSorted);

		return IntStream.range(0, heapsSorted.length)
				.mapToLong(i -> (long) (heapsSorted[i] << (i * shiftIncrement)))
				.reduce(Long::sum).orElse(0);
	}
}
