package nim.players;

import java.util.Scanner;
import nim.NimGame;
import nim.NimMove;
import nim.utils.ConsoleUtils;

/**
 * Player object representing a player using the console to enter desired moves
 */
public class ConsolePlayer implements Player {
	private static Scanner consoleScanner = new Scanner(System.in);

	private String name;

	public ConsolePlayer(String name) {
		this.name = name;
	}

	/**
	 * Prompts user in console to enter a name and returns an object with that name
	 * 
	 * @return Console player with the name the user chose
	 */
	public static ConsolePlayer create() {
		System.out.println("Please enter your name: ");
		String name = consoleScanner.nextLine();
		return new ConsolePlayer(name);
	}

	/**
	 * Prompts player to enter the heap to remove objects from and the count of objects to remove
	 * 
	 * @param heaps The number of remaining objects on each heap
	 * @return The move the player wishes to make
	 */
	public NimMove generateMove(NimGame game) {
		NimMove move;
		do {
			move = promptUserForMove(game);
		} while (!game.moveIsValid(move));
		return move;
	}

	public String getName() {
		return name;
	}

	/**
	 * Prompt user to enter move data in console. Returned move is not necessarily valid
	 * 
	 * @param heaps
	 * @return
	 */
	private NimMove promptUserForMove(NimGame game) {
		int heapIndex = ConsoleUtils
				.getInt("Player " + name
						+ ", please enter which non-empty heap to remove objects from [1 - "
						+ game.getHeapCount() + "]",
						x -> x >= 1 && x <= game.getHeapCount() && game.getHeapSize(x - 1) > 0)
				- 1;

		int removeCount =
				ConsoleUtils.getInt("Player " + name + ", please enter how many objects to remove",
						1, game.getHeapSize(heapIndex) + 1);

		return new NimMove(heapIndex, removeCount);
	}
}
