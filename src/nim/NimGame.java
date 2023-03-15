package nim;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class representing a single game of Nim. Objects of this type are immutable
 */
public class NimGame {
	// limitations are needed to enable unique hashing to a long
	public static final int MAX_HEAP_COUNT = 8;
	public static final int MAX_HEAP_SIZE = 255;

	// todo: with MAX_HEAP_SIZE equal to 2^8, this could technically be a byte[]
	private int[] heaps;

	/**
	 * Insitiate a new game of Nim
	 * 
	 * @throws IllegalArgumentException if there are more than MAX_HEAP_COUNT heaps or any heap is
	 *         bigger than MAX_HEAP_SIZE
	 * @param heaps
	 */
	public NimGame(int[] heaps) {
		if (heaps.length > MAX_HEAP_COUNT
				|| Arrays.stream(heaps).anyMatch(x -> x < 0 || x > MAX_HEAP_SIZE)) {
			throw new IllegalArgumentException();
		}

		this.heaps = heaps.clone();
	}

	/**
	 * Returns a clone of the array containing the remaining number of objects on each heap
	 * 
	 * @return
	 */
	public int[] getHeaps() {
		return heaps.clone();
	}

	/**
	 * Returns the size of the given heap
	 * 
	 * @param heapIndex The size of this heap is returned
	 * @throws IndexOutOfBoundsException If the given index is less than 0 or bigger/equal to the
	 *         count of heaps
	 * @return
	 */
	public int getHeapSize(int heapIndex) {
		if (heapIndex < 0 || heapIndex > heaps.length) {
			throw new IndexOutOfBoundsException();
		}
		return heaps[heapIndex];
	}

	/**
	 * Get the total count of available heaps
	 * 
	 * @return
	 */
	public int getHeapCount() {
		return heaps.length;
	}

	/**
	 * Make given move. Removes given count of objects from the given heap.
	 * 
	 * @param move The move to make
	 * @throws IndexOutOfBoundsException if the given move has a heapIndex bigger than the count of
	 *         heaps
	 * @throws IllegalArgumentException if the removeCount is bigger than the count of remaining
	 *         objects on the heap
	 */
	public NimGame makeMove(NimMove move) {
		if (move.heapIndex() > heaps.length) {
			throw new IndexOutOfBoundsException();
		}
		if (heaps[move.heapIndex()] < move.removeCount()) {
			throw new IllegalArgumentException();
		}

		int[] newHeaps = getHeaps();
		newHeaps[move.heapIndex()] -= move.removeCount();
		return new NimGame(newHeaps);
	}

	/**
	 * Checks whether all heaps are empty and thus the game is won
	 * 
	 * @return
	 */
	public boolean isWon() {
		return Arrays.stream(heaps).allMatch(x -> x == 0);
	}

	/**
	 * Returns a string representation of the current game state
	 */
	public String toString() {
		return "Heaps:\n" + Arrays.stream(heaps).mapToObj(Integer::toString)
				.collect(Collectors.joining("\n"));
	}

	/**
	 * Check whether the given move is valid in this game of Nim
	 * 
	 * @param move The move to check
	 * @return
	 */
	public boolean moveIsValid(NimMove move) {
		return move.heapIndex() < heaps.length && move.removeCount() <= heaps[move.heapIndex()];
	}
}
