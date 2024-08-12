import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        // Create a Random object to generate a random number
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        boolean guessedCorrectly = false;

        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("I have selected a number between 1 and 100. Try to guess it!");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        while (!guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess;
            
            // Check if the input is a valid integer
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();

                // Ensure the guess is within the valid range
                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }

                // Compare the user's guess with the generated number
                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + numberToGuess + " correctly.");
                    guessedCorrectly = true;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number between 1 and 100.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Close the scanner
        scanner.close();
    }
}
