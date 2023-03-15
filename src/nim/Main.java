package nim;

import java.util.HashMap;
import java.util.Map;
import nim.players.ConsolePlayer;
import nim.players.MinMaxPlayer;
import nim.players.Player;
import nim.utils.ConsoleUtils;

public class Main {
	private static final int[] heaps = new int[] {1, 3, 5, 7};

	public static void main(String[] args) {
		Map<String, Runnable> modes = new HashMap<String, Runnable>();
		modes.put("pvp", Main::playPvP);
		modes.put("pvc", Main::playPvC);

		if (args.length != 1 || !modes.containsKey(args[0])) {
			System.out.println("Usage: java Main.java [pvc|pvp]");
			return;
		}

		String mode = args[0];
		modes.get(mode).run();
	}

	private static void playPvP() {
		System.out.println("\n--- Player 1 ---");
		ConsolePlayer cp1 = ConsolePlayer.create();
		System.out.println("\n--- Player 2 ---");
		ConsolePlayer cp2 = ConsolePlayer.create();

		NimGame game = new NimGame(heaps);
		NimManager nm = new NimManager(cp1, cp2);
		Player winner = nm.playGame(game);

		System.out.println("Player " + winner.getName() + " wins!");
	}

	private static void playPvC() {
		ConsolePlayer cp = ConsolePlayer.create();
		MinMaxPlayer mmp = new MinMaxPlayer();

		NimGame game = new NimGame(heaps);
		NimManager nm = new NimManager(cp, mmp);

		boolean playerBegins = ConsoleUtils.getBool("Should human player begin?");
		if (!playerBegins) {
			nm.switchPlayers();
		}

		Player winner = nm.playGame(game);
		System.out.println("Player " + winner.getName() + " wins!");
	}

}
