import java.util.*;

public class Lucky9 {

    static Scanner userInput = new Scanner(System.in);
    static TreeMap<Integer, String> leaderBoard = new TreeMap<>(Collections.reverseOrder());

    public static void main(String[] args) {
        System.out.println("\nWelcome to Lucky9");
        while (true) {
            System.out.println("\n[1] Start Game");
            System.out.println("[2] View Leaderboard");
            System.out.println("[3] Exit");
            System.out.print("Select your action: ");
            int action = userInput.nextInt();

            switch (action) {
                case 1 -> {
                    userInput.nextLine();
                    System.out.print("\nEnter your dealer name: ");
                    String dealerName = userInput.nextLine();
                    System.out.print("Enter cash fund: ");
                    int dealerFund = userInput.nextInt();
                    startGame(dealerName, dealerFund);
                }
                case 2 ->
                    viewLeaderBoard();
                case 3 -> {
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void startGame(String dealerName, int dealerFund) {
        ArrayList<Integer> playerBet = new ArrayList<Integer>();
        ArrayList<Integer> winBetList = new ArrayList<Integer>();
        ArrayList<Integer> loseBetList = new ArrayList<Integer>();
        ArrayList<String> winList = new ArrayList<String>();
        ArrayList<String> loseList = new ArrayList<String>();
        int[] cardValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        String[] cardNames = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] cardSymbol = {"♥", "♦", "♣", "♠"};
        int randomCard, dealerInitialFund, randomSymbol, multiplyBet, playerWinTotal = 0, playerLoseTotal = 0, playerTotal = 0, bankerTotal = 0;
        dealerInitialFund = dealerFund;
        System.out.print("Enter number of player(s): ");
        int numberOfPlayer = userInput.nextInt();
        while (numberOfPlayer > 20 || numberOfPlayer < 1) {
            System.out.println("Invalid number of player. Players can be only 1-20.");
            System.out.print("Enter number of player(s): ");
            numberOfPlayer = userInput.nextInt();
        }
        boolean playAgain = true;
        while (playAgain) {
            for (int count = 0; count < numberOfPlayer; count++) {
                int randomBet = (int) (Math.random() * 9 + 2);
                randomBet = randomBet * 10;
                playerBet.add(randomBet);
                System.out.printf("Player %d bet: %3d%n", (count + 1), randomBet);
            }
            playAgainChoices(1);
            int action = userInput.nextInt();
            if (action == 1) {
                bankerTotal = 0;
                randomCard = (int) (Math.random() * cardNames.length);
                randomSymbol = (int) (Math.random() * cardSymbol.length);
                bankerTotal += cardValues[randomCard];
                System.out.printf("Your 1st card: %s %s%n", cardNames[randomCard], cardSymbol[randomSymbol]);
                randomCard = (int) (Math.random() * cardNames.length);
                randomSymbol = (int) (Math.random() * cardSymbol.length);
                bankerTotal += cardValues[randomCard];
                System.out.printf("Your 2nd card: %s %s%n", cardNames[randomCard], cardSymbol[randomSymbol]);
                System.out.print("Draw another card [Y/N]: ");
                char choice = userInput.next().toUpperCase().charAt(0);
                int dealerNumberOfCards = 2;

                if (choice == 'Y') {
                    dealerNumberOfCards++;
                    randomCard = (int) (Math.random() * cardNames.length);
                    randomSymbol = (int) (Math.random() * cardSymbol.length);
                    System.out.printf("Your 3rd card: %s %s%n", cardNames[randomCard], cardSymbol[randomSymbol]);
                    bankerTotal += cardValues[randomCard];
                    bankerTotal = bankerTotal % 10;
                    System.out.printf("Your total value: %d%n", bankerTotal);
                } else if (choice == 'N') {
                    bankerTotal = bankerTotal % 10;
                    System.out.printf("Your total value: %d%n", bankerTotal);
                }

                System.out.println("Press enter to view player's card...");
                userInput.nextLine();
                userInput.nextLine();

                for (int playerCount = 0; playerCount < numberOfPlayer; playerCount++) {
                    int playerNumberOfCards = 2;
                    playerTotal = 0;
                    System.out.printf("%nPlayer %d%n", (playerCount + 1));
                    randomCard = (int) (Math.random() * cardNames.length);
                    randomSymbol = (int) (Math.random() * cardSymbol.length);
                    playerTotal += cardValues[randomCard];
                    System.out.printf("1st card: %s %s%n", cardNames[randomCard], cardSymbol[randomSymbol]);
                    randomCard = (int) (Math.random() * cardNames.length);
                    randomSymbol = (int) (Math.random() * cardSymbol.length);
                    playerTotal += cardValues[randomCard];
                    System.out.printf("2nd card: %s %s%n", cardNames[randomCard], cardSymbol[randomSymbol]);
                    playerTotal = playerTotal % 10;

                    if (playerTotal < 5 && playerTotal != 9) {
                        playerNumberOfCards++;
                        randomCard = (int) (Math.random() * cardNames.length);
                        randomSymbol = (int) (Math.random() * cardSymbol.length);
                        playerTotal += cardValues[randomCard];
                        System.out.printf("3rd card: %s %s%n", cardNames[randomCard], cardSymbol[randomSymbol]);
                    }
                    playerTotal = playerTotal % 10;
                    System.out.printf("Player %d value: %d%n", (playerCount + 1), playerTotal);
                    System.out.println("\n------------------------");

                    if (playerTotal == 9 && bankerTotal == 9) {
                        if (dealerNumberOfCards == 2 && playerNumberOfCards == 3) {
                            loseList.add("Player " + (playerCount + 1));
                            loseBetList.add(playerBet.get(playerCount));
                        } else {
                            multiplyBet = playerBet.get(playerCount) * 3;
                            playerBet.set(playerCount, multiplyBet);
                            multiplyBet = 0;
                        }
                    } else if (playerTotal == 9 && playerTotal != bankerTotal) {
                        multiplyBet = playerBet.get(playerCount) * 3;
                        playerBet.set(playerCount, multiplyBet);
                        multiplyBet = 0;
                    } else if (playerTotal > bankerTotal) {
                        winList.add("Player " + (playerCount + 1) + "(" + playerBet.get(playerCount) + ")");
                        winBetList.add(playerBet.get(playerCount));
                    } else if (playerTotal < bankerTotal) {
                        loseList.add("Player " + (playerCount + 1));
                        loseBetList.add(playerBet.get(playerCount));
                    }

                }

                System.out.println("\nWinners(s)");
                for (int count = 0; count < winList.size(); count++) {
                    System.out.println(winList.get(count));
                }
                winList.clear();
                System.out.println("\nLoser(s)");
                for (int count = 0; count < loseList.size(); count++) {
                    System.out.println(loseList.get(count));
                }
                loseList.clear();
                for (int count = 0; count < winBetList.size(); count++) {
                    playerWinTotal += winBetList.get(count);
                }
                for (int count = 0; count < loseBetList.size(); count++) {
                    playerLoseTotal += loseBetList.get(count);
                }

                dealerFund = dealerFund - playerWinTotal + playerLoseTotal;
                System.out.println("\n------------------------\n");
                System.out.println("Dealer Result:");
                System.out.printf("Win: %,d%n", playerLoseTotal);
                System.out.printf("Loss: %,d%n", playerWinTotal);
                System.out.printf("Dealer total fund: %,d%n", dealerFund);

                playAgainChoices(1);
                action = userInput.nextInt();
                while (action != 1 && action != 2) {
                    System.out.println("Invalid action. Please try again.");
                    playAgainChoices(1);
                    action = userInput.nextInt();
                }
                if (dealerFund > 0) {
                    loseBetList.clear();
                    winBetList.clear();
                    playerBet.clear();
                    switch (action) {
                        case 1 -> {
                            playerWinTotal = 0;
                            playerLoseTotal = 0;
                            playAgain = true;
                        }
                        case 2 -> {
                            int score = dealerFund - dealerInitialFund;
                            leaderBoard.put(score, dealerName); // put score and name into leaderBoard (TreeMap)
                            playAgain = false;
                            main(null);
                        }
                    }
                } else {
                    System.out.println("Negative fund. Game over!");
                    System.out.println("------------------------");
                    int score = dealerFund - dealerInitialFund;
                    leaderBoard.put(score, dealerName); // put score and name into leaderBoard (TreeMap)
                    playAgain = false;
                    main(null);
                }

            } else if (action == 2) {
                main(null);
            }

        }
    }

    public static void viewLeaderBoard() {
        System.out.println("\nLeader Board (TOP 10)");
        System.out.println("------------------------\n");
        int count = 0;
        for (Map.Entry<Integer, String> leaderBoardList : leaderBoard.entrySet()) {
            if (count >= 10) {
                break;
            }
            System.out.printf("%d) %s (%,d)%n", (count + 1), leaderBoardList.getValue(), leaderBoardList.getKey());
            count++;
        }
        // prints empty leaderboard on empty number of dealer.
        for (int i = count + 1; i <= 10; i++) {
            System.out.printf("%d) %n", i);
        }
    }

    public static void playAgainChoices(int action) {
        if (action == 1) {
            System.out.println("[1] Play");
        } else {
            System.out.println("\n[1] Play again");
        }
        System.out.println("[2] Quit");
        System.out.print("Select your action: ");
    }
}
