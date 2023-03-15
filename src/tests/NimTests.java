package tests;

import java.util.Arrays;

public class NimTests {
	public static void main(String[] args) {
		Runnable[] tests =
				new Runnable[] {new TestsNimMove(), new TestsNimGame(), new TestsMinMaxPlayer()};
		Arrays.stream(tests).forEach(Runnable::run);

		System.out.println("All tests passed");
	}



}
