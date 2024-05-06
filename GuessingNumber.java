import java.util.*;
public class GuessingNumber{
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfAttempts = 1, playerGuess = 0;
        int guessNumber = (int) (Math.random() * 50 + 1);  // Generate random number from 1-50.
        System.out.println("\nThe Guessing Number Game!"); // Introduction message & Instruction.
        System.out.println("Guess the number from 1-50");

        do {
            System.out.printf("Enter a number (Attempt #%d): ", numberOfAttempts);
            playerGuess = sc.nextInt();
while (playerGuess > 50 || playerGuess < 1 ){ //Checks if player entered invalid number.
System.out.printf("Invalid number. Please Try again.\nEnter a number (Attempt #%d): ", numberOfAttempts);
playerGuess = sc.nextInt();
}
            if (playerGuess > guessNumber) {
                System.out.printf("The guess number is lower than %d%n", playerGuess);
            } else if (playerGuess < guessNumber) {
                System.out.printf("The guess number is higher than %d%n", playerGuess);
            } else {
                System.out.printf("Congratulations, you got it right after %d attempts%n", numberOfAttempts);
            }
            numberOfAttempts++;
        } while (playerGuess != guessNumber);
    }
}