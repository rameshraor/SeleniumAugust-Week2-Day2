package week2.Day2Assignment4;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String text = "We learn java basics as part of java sessions in java week1";

		System.out.println("Original String  --> " + text);

		/*
		 * Parse the input string into words array - the delimiter is space
		 */

		String[] arrTextWords = text.split("\\s+");

		// convert String array to LinkedHashSet to remove duplicates --> LinkedHashSet
		// does NOT allow duplicates, and will re,move them, if any
		LinkedHashSet<String> lhSetWords = new LinkedHashSet<String>(Arrays.asList(arrTextWords));

		// Now, that the duplicates are removed, re-join the words along with space
		// delimiters
		StringBuilder sbTemp = new StringBuilder();

		int index = 0;

		for (String s : lhSetWords) {

			if (index > 0)
				sbTemp.append(" ");

			sbTemp.append(s);
			index++;
		}

		String textNoDupWords = sbTemp.toString();

		System.out.println("String after removing duplicate words  --> " + textNoDupWords);

	}

}
