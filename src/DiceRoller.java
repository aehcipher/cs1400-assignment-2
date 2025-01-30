import java.io.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class DiceRoller {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scnr = new Scanner(System.in);
		Random rand = new Random();
		String fileName;
		int numRolls;
		PrintWriter fileWrite;
		int[] fair = {10,20,30,40,50};
		int[] loaded = {1,3,6,15,30,60};
		String diceType;
		int[] chances;
		int roll;
		int i;
		
		
		
		System.out.print("Type out the name of your file: ");
		fileName = scnr.next();
		fileWrite = new PrintWriter(fileName + ".txt");
		
		System.out.print("\n How many rolls do you want to do? ");
		numRolls = scnr.nextInt();
		
		while (numRolls < 0 || numRolls > 20000) {
			System.out.print("\n Invalid input. Value must be between 0 and 20,000: ");
			numRolls = scnr.nextInt();
		}
		
		System.out.println("\n Would you like the dice to be fair or loaded? Type 'f' or 'l': ");
		diceType = scnr.next();
		
		while (!diceType.equalsIgnoreCase("f") && !diceType.equalsIgnoreCase("l")) {
			System.out.print("\n Invalid input. Please type 'f' or 'l': ");
			diceType = scnr.next();
		}
		
		System.out.println("");
		
		if (diceType.equals("f")) {
			chances = fair;
		}
		else {
			chances = loaded;
		}
		
		for (i = 0; i < numRolls; ++i) {
			roll = rand.nextInt(60) + 1;
			if (roll <= chances[0]) {
				fileWrite.println(1);
			}
			else if (roll < chances[1]) {
				fileWrite.println(2);
			}
			else if (roll < chances[2]) {
				fileWrite.println(3);
			}
			else if (roll < chances[3]) {
				fileWrite.println(4);
			}
			else if (roll < chances[4]) {
				fileWrite.println(5);
			}
			else {
				fileWrite.println(6);
			}
		}
		System.out.println("Finished! Check local directory.");
		fileWrite.close();
	}

}
