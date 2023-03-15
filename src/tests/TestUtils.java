package tests;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Based on denkspuren's testing framework:
 * https://gist.github.com/denkspuren/c379cd6d4512144e595d1dab98bba5ff
 */

public class TestUtils {
	public static boolean beTrue(Object... values) {
		return true;
	}

	/**
	 * Helper method that runs given methods and returns true. Useful for void methods
	 * 
	 * @param methods Methods to run
	 * @return
	 */
	public static boolean beTrue(Runnable... methods) {
		for (Runnable method : methods)
			method.run();
		return true;
	}

	/**
	 * Test that given method throws one of given exceptions
	 * 
	 * @param method Method to run
	 * @param exceptionClasses Exceptions to catch
	 * @return
	 */
	public static boolean isCaught(Runnable method, Class... exceptionClasses) {
		try {
			beTrue(method);
			return false;
		} catch (Throwable e) {
			for (Class exceptionClass : exceptionClasses)
				if (exceptionClass.isInstance(e))
					return true;
			return false;
		}
	}

	/**
	 * Test that given objects are all represented by given string
	 * 
	 * @param first Desired string representation
	 * @param rest Objects to check representation
	 * @return
	 */
	public static boolean sameRepr(String first, Object... rest) {
		String firstString = String.valueOf(first);
		return Arrays.stream(rest).allMatch(o -> firstString.equals(String.valueOf(o)));
	}

	/**
	 * Test that given objects' string representation matches given regex
	 * 
	 * @param regex Regex to match against
	 * @param rest Objects whose representation should be matched
	 * @return
	 */
	public static boolean matchRegex(String regex, Object... rest) {
		return Arrays.stream(rest).allMatch(o -> Pattern.matches(regex, String.valueOf(o)));
	}

	/**
	 * Assert that all given boolean values are equal to criteria
	 * 
	 * @throws AssertionError if one of the given values is not equal to criteria
	 * @param criteria Whether values should be true or false
	 * @param intent String to use in AssertionError
	 * @param values Values to check
	 */
	public static void assertBool(Boolean criteria, String intent, Object... values) {
		for (Object value : values)
			if (value instanceof Boolean && !value.equals(criteria))
				throw new AssertionError(intent);
	}

	/**
	 * Assert that all given boolean values are true
	 * 
	 * @throws AssertionError if one of given values is false
	 * @param intent String to use in AssertionError
	 * @param values Values to check
	 */
	public static void assertTrue(String intent, Object... values) {
		assertBool(Boolean.TRUE, intent, values);
	}

	/**
	 * Assert that given boolean values are true
	 * 
	 * @throws AssertionError with default message if one of given values is false
	 * @param values Values to check
	 */
	public static void assertTrue(Object... values) {
		assertTrue("thrown by assertTrue", values);
	}

	/**
	 * Assert that all given boolean values are false
	 * 
	 * @throws AssertionError if one of values is true
	 * @param intent String to use in AssertionError
	 * @param values Values to check
	 */
	public static void assertFalse(String intent, Object... values) {
		assertBool(Boolean.FALSE, intent, values);
	}

	/**
	 * Assert that given boolean values are false
	 * 
	 * @throws AssertionError with default message if one of given values is true
	 * @param values Values to check
	 */
	public static void assertFalse(Object... values) {
		assertFalse("thrown by assertFalse", values);
	}
}
