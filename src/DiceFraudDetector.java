import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;


public class DiceFraudDetector {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.print("Input file name (format: 'name.txt'): ");
		Scanner scnr = new Scanner(System.in); //used to ask user for input
		String fileName = scnr.next(); //user input for desired file will be stored here
		
		File setFile = new File(fileName);
		Scanner fileScanner = new Scanner(setFile); //scanner for fileName file
		
		int i; //counter
		int i2; //2nd count
		int face;
		int count;
		int lineNumber;
		int total = 0; //this is for the total number of rolls
		int percent;
		
		int[] diceSides = new int[6];
		double[] dicePercent = new double[6];
		
		// following code will count how many of each face is rolled and store it in the diceSides array
		for (i = 0; i < diceSides.length; ++i) { //loops the length of slots in the array
			count = 0;
			while (fileScanner.hasNextInt()) { //while theres a next line within the txt file
				lineNumber = Integer.parseInt(fileScanner.nextLine());
				for (i = 0; i < diceSides.length; ++i) {
					face = i + 1; //face is equal to the string of i + 1. so, on the first iteration, the face is 1
					if (lineNumber == face) { //if the next line is equal to the face, respective element in the array will increment
						diceSides[i] = diceSides[i] + 1;
					}
				}
				++total;
			}
		}
		
		System.out.println("File: " + fileName);
		System.out.println("Total = " + total);
		System.out.println("Individual number counts: " + Arrays.toString(diceSides));
		
		//the following code prints out the percentages
		System.out.print("Percentages: [");
		for (i = 0; i < dicePercent.length; ++i) {
			dicePercent[i] = ((double) diceSides[i] / total) * 100;
			if (i < (dicePercent.length - 1)) { //if the current element is NOT the last element
				System.out.printf("%.2f, ", dicePercent[i]); // prints out a, b, c, etc..
			}
			else {
				System.out.printf("%.2f]\n\n", dicePercent[dicePercent.length - 1]); //prints out ...z]
			}
		}
		
		System.out.println("Graph: ");
		
		//the following makes a bar graph representing the frequency of each number
		for (i = 0; i < dicePercent.length; ++i) {
			percent = (int) dicePercent[i];
			System.out.print((i + 1) + ": ");
			for (i2 = 0; i2 < percent; ++i2) {
				System.out.print("[] ");
			}
			System.out.printf("\t%d\n", percent);
		}
		
		//the following calculates chi-squared
		int expectedRoles = total / 6;
		double chiSquared = 0.00;
		
		for (i = 0; i < diceSides.length; ++i) {
			chiSquared = chiSquared + (Math.pow((1.0 * diceSides[i]) - expectedRoles, 2) / expectedRoles);
		}
		
		System.out.printf("\n\nChi-squared = %.2f\n", chiSquared); //prints chi-squared
		
		//the following determines if the die is fair
		if (chiSquared < 11.07) {
			System.out.println("The die is fair");
		}
		else {
			System.out.println("The die is fraudulent");
		}
		
		//closes the fair
		fileScanner.close();
	}

}
