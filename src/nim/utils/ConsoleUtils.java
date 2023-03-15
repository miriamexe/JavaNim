package nim.utils;

import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleUtils {
	private static Scanner consoleScanner = new Scanner(System.in);

	/**
	 * Prompt user to enter a valid integer until one is entered
	 * 
	 * @param prompt The prompt to display to the user
	 * @return The integer the user entered
	 */
	public static int getInt(String prompt) {
		System.out.println(prompt);
		while (!consoleScanner.hasNextInt()) {
			consoleScanner.next();
			System.out.println("Please enter a valid integer.");
		}
		return consoleScanner.nextInt();
	}

	/**
	 * Prompt user to enter a valid integer equal to or bigger than given lower bound
	 * 
	 * @param prompt Prompt to display to user
	 * @param lowerBound Inclusive lower bound for accepted numbers
	 * @return
	 */
	public static int getInt(String prompt, int lowerBound) {
		System.out.println(prompt);
		int result;
		while (true) {
			if (!consoleScanner.hasNextInt()) {
				System.out.println("Please enter a valid integer.");
				consoleScanner.next();
				continue;
			}
			result = consoleScanner.nextInt();
			if (result < lowerBound) {
				System.out.println("Please enter an integer equal to or bigger than " + lowerBound);
				continue;
			}
			break;
		}
		return result;
	}

	/**
	 * Prompt user to enter a valid integer within given bounds
	 * 
	 * @param prompt Prompt to display to user
	 * @param lowerBound inclusive lower bound for accepted numbers
	 * @param upperBound exclusive upper bound for accepted numbers
	 * @return
	 */
	public static int getInt(String prompt, int lowerBound, int upperBound) {
		System.out.println(prompt);
		int result;
		while (true) {
			if (!consoleScanner.hasNextInt()) {
				System.out.println("Please enter a valid integer.");
				consoleScanner.next();
				continue;
			}
			result = consoleScanner.nextInt();
			if (result < lowerBound || result >= upperBound) {
				System.out.println("Please enter an integer equal to or bigger than " + lowerBound
						+ " and lower than " + upperBound);
				continue;
			}
			break;
		}
		return result;
	}

	/**
	 * Prompts user to enter an integer that matches given predicate
	 * 
	 * @param prompt Prompt to display to user
	 * @param predicate Predicate to test inputs against
	 * @return
	 */
	public static int getInt(String prompt, Predicate<Integer> predicate) {
		System.out.println(prompt);
		int result;
		while (true) {
			if (!consoleScanner.hasNextInt()) {
				System.out.println("Please enter a valid integer.");
				consoleScanner.next();
				continue;
			}
			result = consoleScanner.nextInt();
			if (!predicate.test(result)) {
				System.out.println("Please enter an integer that matches the criteria");
				continue;
			}
			break;
		}
		return result;
	}

	/**
	 * Prompt user a yes or no question and return a bool
	 * 
	 * @param prompt Prompt to display to user
	 * @return
	 */
	public static boolean getBool(String prompt) {
		return getBool(prompt, "y", "n");
	}

	/**
	 * Prompt user a yes or no question and return bool
	 * 
	 * @param prompt Prompt to display to user
	 * @param yes String to use to choose yes
	 * @param no String to use to choose no
	 * @return
	 */
	public static boolean getBool(String prompt, String yes, String no) {
		if (yes.equals(no)) {
			throw new IllegalArgumentException();
		}

		System.out.println(prompt + "[" + yes + "/" + no + "]");
		while (true) {
			String selection = consoleScanner.nextLine();
			if (selection.equals(yes)) {
				return true;
			}
			if (selection.equals(no)) {
				return false;
			}
			System.out.println("Please enter [" + yes + "] or [" + no + "]");
		}
	}
}
