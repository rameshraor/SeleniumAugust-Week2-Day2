package week2.Day2Assignment4;

public class SumOfDigitsFromString {

	/**
	 * Declare the variables
	 */
	static String text = "Tes12Le79af65";
	int sum = 0;

	public static void main(String[] args) {

		// Instantiate the class
		SumOfDigitsFromString myObj = new SumOfDigitsFromString();

		// Perform the sum of digits using method 1
		// Replace the non-digits with null, convert the string into char array, and
		// navigate thru the array
		myObj.doWithMethod1(text);

		// Perform the sum of digits using method 2
		// Parse thru the string characters, check if they are digits, and calculate the
		// sum
		myObj.doWithMethod2(text);

	}

	private void doWithMethod1(String StrForCalc) {

		// variable to store sum of digits
		int sumOfDigits = 0;

		// variable to hold character
		char ch;

		// variable to hold numeric value of character
		int numOfChar;

		// Replace all the non-digits into ""
		String strOnlyNumbers = StrForCalc.replaceAll("[^\\d.]", "");
		System.out.println("Method 1 --> New string after replacing all non-digits by null : " + strOnlyNumbers);

		// Convert it into character array
		char[] arrNbrDigits = strOnlyNumbers.toCharArray();

		for (int i = 0; i < arrNbrDigits.length; i++) {
			if (Character.isDigit(arrNbrDigits[i])) {
				System.out.print("Digits in the String : " + arrNbrDigits[i]);
				System.out.println();
				int a = Integer.parseInt(String.valueOf(arrNbrDigits[i]));
				sumOfDigits += a;
			}
		}
		System.out.println("Method 2 Result  --> The sum of digits in the string " + StrForCalc + " = " + sumOfDigits);

	}

	private void doWithMethod2(String StrForCalc) {

		// variable to store sum of digits
		int sumOfDigits = 0;

		// variable to hold character
		char ch;

		// variable to hold numeric value of character
		int numOfChar;

		// loop to iterate the String
		for (int i = 0; i < StrForCalc.length(); i++) {

			// find character
			ch = StrForCalc.charAt(i);

			// check character is digit?
			if (Character.isDigit(ch)) {

				// find numeric value of character
				numOfChar = Character.getNumericValue(ch);

				// then add it to sum variable
				sumOfDigits += numOfChar;

			}

		}

		// display result
		System.out.println("Method 2 Result  --> The sum of digits in the string " + StrForCalc + " = " + sumOfDigits);
	}

}
