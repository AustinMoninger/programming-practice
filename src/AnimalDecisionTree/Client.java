import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Client class that runs the guessing game.
 * Includes methods for interacting with the
 * console.
 */
public class Client {
	// For interacting with the console
	private static Scanner console = new Scanner(System.in);
	// File to save and load decision trees to/from
	private static String FILE = "animals.txt";
	
	/**
	 * Plays the guessing game as many times as the user wants,
	 * learning after each try. The animals.txt file is loaded
	 * if it exists, but a default starting point is provided
	 * otherwise. Once the user decides to quit, the updates
	 * decision tree is saved to file.
	 * 
	 * @param args Ignored
	 */
	public static void main(String[] args) {
		GuessingGame game; 
		try {
			game = new GuessingGame(FILE);
		} catch (FileNotFoundException e) {
			System.out.println("Starting new game database: " + FILE);
			game = new GuessingGame("Is the animal a mammal?", "Squid", "Cow");
		}
		while(yesNoQuery("Do you want to play the animal guessing game? ")){
			game.play();
		}
		
		game.save(FILE);
	}

	/**
	 * Print a question to the console and wait for a yes/no response from the user.
	 * @param prompt Question to ask user
	 * @return True if user types y or yes, false if n or no was typed.
	 */
	public static boolean yesNoQuery(String prompt) {
		System.out.println(prompt);
		return isUserResponseYes();
	}
	
	/**
	 * Print a question to the console and take whatever is entered as the user response.
	 * @param prompt Question to ask user.
	 * @return Line of text entered by user at console.
	 */
	public static String stringQuery(String prompt) {
		System.out.println(prompt + " ");
		return console.nextLine();
	}
	
	/**
	 * Keep looping until the user responds with a (y)es or (n)o.
	 * @return True on (y)es and false on (n)o
	 */
	public static boolean isUserResponseYes() {
		while(true) {
			String response = console.nextLine().trim().toLowerCase();
			if(response.equals("yes") || response.equals("y")) {
				return true;
			} else if(response.equals("no") || response.equals("n")) {
				return false;
			} else {
				System.out.print("Must respond yes or no. Try again: ");
			}
		}
	}
}
