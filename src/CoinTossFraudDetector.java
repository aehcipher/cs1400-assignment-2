import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CoinTossFraudDetector {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Input file name (format: 'name.txt'): ");
		Scanner scnr = new Scanner(System.in); //used to ask user for input
		String fileName = scnr.next(); //user input for desired file will be stored here
		
		File setFile = new File(fileName);
		Scanner fileScanner = new Scanner(setFile); //scanner for fileName file
		
		int maxNumHeads = 0; // stores the max number of consecutive heads
		int curNumHeads = 0; //stores the current number of consecutive heads
		int numTosses = 0; // stores the total number of tosses
		int[] flipsHT = new int[2]; // stores the number of heads and tails flipped
		double expectedChain;
		
		while (fileScanner.hasNext()) {
			if (fileScanner.next().equals("h")) { // next line is heads
				++curNumHeads;
				++flipsHT[0];
			}
			else { // fileScanner.next().equals("t"), next line is Tails
				if (curNumHeads > maxNumHeads) {
					maxNumHeads = curNumHeads;
				}
				curNumHeads = 0;
				++flipsHT[1];
			}
			
			++numTosses;
		}
		
		expectedChain = (Math.log10(numTosses / 2.0)) / Math.log10(2.0);
		
		System.out.println("Total = " + numTosses);
		System.out.println("Individual number counts: " + Arrays.toString(flipsHT));
		
		//prints percentages
		System.out.print("Percentages: ["); 
		System.out.printf("%.2f, ", (flipsHT[0] / (double)numTosses) * 100);
		System.out.printf("%.2f]\n", (flipsHT[1] / (double)numTosses) * 100);
		
		System.out.println("Longest chain of heads: " + maxNumHeads);
		
		if (((double)maxNumHeads + 3) < expectedChain) {
			System.out.println("The coin flips are probably fake");
		}
		else {
			System.out.println("The coin flips were probably done with a real coin");
		}
		

	}

}
