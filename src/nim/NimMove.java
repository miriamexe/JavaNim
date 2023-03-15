package nim;

/**
 * Record representing a move made in nim.
 */
public record NimMove(int heapIndex, int removeCount) {
	/**
	 * Instantiate a Nim move
	 * 
	 * @param heapIndex Which index to remove objects from. Cannot be negative
	 * @param removeCount How many objects to remove. Has to be positive
	 * @throws IllegalArgumentException If heapIndex or removeCount do not meet the requirements
	 */
	public NimMove(int heapIndex, int removeCount) {
		if (heapIndex < 0 || removeCount <= 0) {
			throw new IllegalArgumentException();
		}
		this.heapIndex = heapIndex;
		this.removeCount = removeCount;
	}
}
