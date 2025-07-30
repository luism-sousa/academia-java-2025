package pt.upacademy.jseproject.utils.scannerUtils;

import java.util.Scanner;
import java.util.Set;

/**
 * Utility class for handling user input from the console using Scanner.
 * Provides methods to read and validate integer and string values.
 *
 */
public class ScannerUtils {
	/**********
	 * Fields
	 *********/

	/**
	 * Scanner for reading input from the console.
	 */
	private Scanner sc = new Scanner(System.in);
	/**
	 * Scanner for parsing individual input lines.
	 */
	private Scanner lineSc;

	/***********
	 * Methods
	 **********/

	/**
	 * Reads a line of input from the console.
	 * 
	 * @return the input string entered by the user
	 */
	public String getValue() {
		return sc.nextLine();
	}
	
	/**
	 * 
	 * Reads a line of input from the console and checks if it's valid
	 *
	 * @param msg the prompt message
	 * @return the input string enter by the user	 *
	 */
	public String getString(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isString(value)) {
				return toString(value);
			}
		} while (true);
	}
	
	/**
	 * Checks if the provided string can be parsed as an integer.
	 * 
	 * @param value the string to check
	 * @return true if the string is text, false if empty or otherwise
	 */
	public boolean isString(String value) {
		if (value == null || value.trim().isEmpty()) {
	        return false;
	    }
	    return value.matches("^[\\p{L}\\s]+$");
	}
	
	/**
	 * Converts the provided string to an integer. Assumes the string is a valid
	 * integer.
	 * 
	 * @param value the string to convert
	 * @return the integer value
	 */
	public String toString(String value) {
		lineSc = new Scanner(value);
		return lineSc.next();
	}
	

	/**
	 * Checks if the provided string can be parsed as an integer.
	 * 
	 * @param value the string to check
	 * @return true if the string is an integer, false otherwise
	 */
	public boolean isInt(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextInt();
	}

	/**
	 * Checks if the provided string can be parsed as an long.
	 * 
	 * @param value the string to check
	 * @return true if the string is an long, false otherwise
	 */
	public boolean isLong(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextLong();
	}

	/**
	 * Checks if the provided string can be parsed as an double.
	 * 
	 * @param value the string to check
	 * @return true if the string is an double, false otherwise
	 */
	public boolean isDouble(String value) {
//		lineSc = new Scanner(value);
//		return lineSc.hasNextDouble();
		try {
			Double.parseDouble(value);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Converts the provided string to an integer. Assumes the string is a valid
	 * integer.
	 * 
	 * @param value the string to convert
	 * @return the integer value
	 */
	public int toInt(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextInt();
	}

	/**
	 * Converts the provided string to an integer. Assumes the string is a valid
	 * long.
	 * 
	 * @param value the string to convert
	 * @return the long value
	 */
	public long toLong(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextLong();
	}

	/**
	 * Converts the provided string to an double. Assumes the string is a valid
	 * double.
	 * 
	 * @param value the string to convert
	 * @return the double value
	 */
	public double toDouble(String value) {
//		lineSc = new Scanner(value);
//		return lineSc.nextDouble();
		return Double.parseDouble(value);
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Keeps prompting until a valid integer is entered.
	 * 
	 * @param msg the prompt message
	 * @return the integer entered by the user
	 */
	public int getInt(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isInt(value)) {
				return toInt(value);
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid long from the console.
	 * Keeps prompting until a valid long is entered.
	 * 
	 * @param msg the prompt message
	 * @return the long entered by the user
	 */
	public long getLong(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isLong(value)) {
				return toLong(value);
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid double from the console.
	 * Keeps prompting until a valid double is entered.
	 * 
	 * @param msg the prompt message
	 * @return the double entered by the user
	 */
	public double getDouble(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isDouble(value)) {
				return toDouble(value);
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Only accepts integers that are present in the provided array.
	 * 
	 * @param msg    the prompt message
	 * @param values the array of valid integer values
	 * @return the valid integer entered by the user
	 */
	public int getValidInt(String msg, int[] values) {
		do {
			StringBuilder validStringBuilder = new StringBuilder(msg + "(");
			for (int i : values) {
				validStringBuilder.append(" ").append(i);
			}
			validStringBuilder.append(" )");
			String validString = validStringBuilder.toString();
			int result = getInt(validString);
			for (int i : values) {
				if (result == i) {
					return result;
				}
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid long from the console. Only
	 * accepts long that are present in the provided set.
	 * 
	 * @param msg    the prompt message
	 * @param values the set of valid long values
	 * @return the valid long entered by the user
	 */
	public int getValidLong(String msg, Set<Long> values) {
		do {
			StringBuilder validStringBuilder = new StringBuilder(msg + "(");
			for (long i : values) {
				validStringBuilder.append(" ").append(i);
			}
			validStringBuilder.append(" )");
			String validString = validStringBuilder.toString();
			int result = getInt(validString);
			for (long i : values) {
				if (result == i) {
					return result;
				}
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Only accepts integers within the specified min and max range (inclusive).
	 * 
	 * @param msg the prompt message
	 * @param min the minimum valid value
	 * @param max the maximum valid value
	 * @return the valid integer entered by the user
	 */
	public int getValidInt(String msg, int min, int max) {
		int result;
		do {
			String myMsg = msg + "(Valores validos entre " + min + " e " + max + ")";
			result = getInt(myMsg);
		} while (result < min || result > max);
		return result;
	}

}
