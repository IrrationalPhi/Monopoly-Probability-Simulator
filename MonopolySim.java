import java.util.*;
import java.io.*;

public class MonopolySim {

	public static void main(String[] args) throws FileNotFoundException {
		// output file
		PrintStream output = new PrintStream(new File("results.txt"));

		// perform the simulations
		int numSims = 100000;
		int numRolls = 100;

		// store rates
		double[] rates = new double[40];

		// simulate
		for (int n = 0; n < numSims; n++) {
			MonopolyGame game = new MonopolyGame();
			for (int i = 0; i < numRolls; i++) {
				game.doRoll(); 
			}

			double[] currentRates = game.getBoard();
			normalize(currentRates);
			addArr(rates, currentRates);
		}

		// normalize rates
		normalize(rates);

		for (int i = 0; i < rates.length; i++) {
			output.printf(rates[i] + ",");
		}
		output.printf("%d\n", 0);
	}

	// add arr2 to arr1
	public static void addArr(double[] arr1, double[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] += arr2[i];
		}
	}

	public static void normalize(double[] arr1) {
		double sum = 0;
		for (int i = 0; i < arr1.length; i++) {
			sum += arr1[i];
		}

		for (int i = 0; i < arr1.length; i++) {
			arr1[i] /= sum;
		}
	}
}