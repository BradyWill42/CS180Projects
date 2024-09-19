import java.util.Scanner;

/**
 * HW-07 -- YahtzeeGame
 *
 * This program simulates a game of Yahtzee
 * and uses the YahtzeePlayer class to compute
 * the winner of the game
 *
 * @author Brady Williams, L23
 *
 * @version Feb 26, 2024
 *
 */
public class YahtzeeGame {
    public static void main(String[] args) {
        //Initialize Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Yahtzee!");
        //Collect number of rolls for each player and create arrays for results accordingly
        System.out.println("Enter the number of rolls for each player in the game");
        int numRolls = scanner.nextInt();
        scanner.nextLine();
        int[][] playerOneRollResults = new int[numRolls][5];
        int[][] playerTwoRollResults = new int[numRolls][5];
        //Iterate based on the number of rolls and collect the results. Parse the results and store them in the arrays
        for (int i = 1; i <= numRolls; i++) {
            System.out.println("Enter the roll results separated by commas for roll " + i + " for player 1.");
            String playerOneCurr = scanner.nextLine();
            for (int j = 0; j < playerOneCurr.length(); j += 2) {
                playerOneRollResults[i - 1][j / 2] = Integer.parseInt(playerOneCurr.substring(j, j + 1));
            }
            System.out.println("Enter the roll results separated by commas for roll " + i + " for player 2.");
            String playerTwoCurr = scanner.nextLine();
            for (int j = 0; j < playerTwoCurr.length(); j += 2) {
                playerTwoRollResults[i - 1][j / 2] = Integer.parseInt(playerTwoCurr.substring(j, j + 1));
            }
        }
        //Create YahtzeePlayer objects, call method to check for each dice combination, calculate total
        YahtzeePlayer playerOne = new YahtzeePlayer(playerOneRollResults);
        YahtzeePlayer playerTwo = new YahtzeePlayer(playerTwoRollResults);
        
        playerOne.checkFourOfAKind();
        playerTwo.checkFourOfAKind();
        playerOne.checkFullHouse();
        playerTwo.checkFullHouse();
        playerOne.checkLargeStraight();
        playerTwo.checkLargeStraight();
        playerOne.checkYahtzee();
        playerTwo.checkYahtzee();
        playerOne.calculateTotal();
        playerTwo.calculateTotal();
        int playerOneTotal = playerOne.getTotal();
        int playerTwoTotal = playerTwo.getTotal();
        //Create Strings for dice combinations for each player, call getter methods, and add combinations to String
        String playerOneCombinations = "";
        String playerTwoCombinations = "";
        if (playerOne.getFourOfAKindTotal() > 0) {
            playerOneCombinations += "Four of a Kind, ";
        }
        if (playerOne.hasFullHouse()) {
            playerOneCombinations += "Full House, ";
        }
        if (playerOne.hasLargeStraight()) {
            playerOneCombinations += "Large Straight, ";
        }
        if (playerOne.hasYahtzee()) {
            playerOneCombinations += "Yahtzee";
        }
        //If there is a comma and space at the end of the String, remove it. Same will be done later for player two
        if (playerOneCombinations.length() > 0 && 
            playerOneCombinations.substring(playerOneCombinations.length() - 2).equals(", ")) {
            playerOneCombinations = playerOneCombinations.substring(0, playerOneCombinations.length() - 2);
        }
        if (playerTwo.getFourOfAKindTotal() > 0) {
            playerTwoCombinations += "Four of a Kind, ";
        }
        if (playerTwo.hasFullHouse()) {
            playerTwoCombinations += "Full House, ";
        }
        if (playerTwo.hasLargeStraight()) {
            playerTwoCombinations += "Large Straight, ";
        }
        if (playerTwo.hasYahtzee()) {
            playerTwoCombinations += "Yahtzee";
        }
        if (playerTwoCombinations.length() > 0 && 
            playerTwoCombinations.substring(playerTwoCombinations.length() - 2).equals(", ")) {
            playerTwoCombinations = playerTwoCombinations.substring(0, playerTwoCombinations.length() - 2);
        }
        //Print combinations rolled by each player. If player did not roll any points, 
        //print message indicating player did not score any points
        if (playerOneCombinations.length() > 0) {
            System.out.println("Player 1 scored the following: " + playerOneCombinations);
        } else {
            System.out.println("Player 1 did not score any points");
        }
        if (playerTwoCombinations.length() > 0) {
            System.out.println("Player 2 scored the following: " + playerTwoCombinations);
        } else {
            System.out.println("Player 2 did not score any points");
        }
        //Compare total points for each player and print winner and score
        if (playerOneTotal > playerTwoTotal) {
            System.out.println("Player 1 is the winner by a score of " + 
                playerOne.getTotal() + "-" + playerTwo.getTotal() + "!");
        } else {
            System.out.println("Player 2 is the winner by a score of " + 
                playerTwo.getTotal() + "-" + playerOne.getTotal() + "!");
        }
    }
}
