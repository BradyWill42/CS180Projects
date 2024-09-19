// Import statements here
import java.util.Scanner;

// Your JavaDoc here

/**
 * Project 1 -- ChessElo
 *
 * This program calculates a chess players elo based on 
 * a variety of conditions and games played
 *
 * @author Brady Williams, L23
 *
 * @version Feb 15, 2024
 *
 */
public class ChessElo {

    //Defining static Strings
    public static final String WELCOME_MESSAGE = "Welcome to the Chess Elo Calculator!";
    public static final String MAIN_MENU = "Please Select an Operation\n" + "1 - Single Match\n" +
            "2 - Tournament Results\n" + "3 - Field Prediction\n" + "4 - Exit";

    public static final String SINGLE_MATCH = "Please Enter Player 1's Elo followed by a hyphen '-' " +
            "then the Outcome followed by Player 2's Elo.";
    public static final String TOURNAMENT_RESULTS = "Please Enter the Tournament String to be Calculated.";
    public static final String FIELD_PREDICTION = "Please Enter a String of Elo Ratings.";
    public static final String MATCH_OUTCOME = "The Result of the Single Match is ";
    public static final String TOURNAMENT_OUTCOME = "The Final Tournament Results are ";
    public static final String PREDICTION_OUTCOME = "The Chess Elo Calculator Predicts ";
    public static final String CONFIRMATION = "Are You Sure You Want to Exit?";
    public static final String INVALID_INPUT = "Invalid Input! Try again";
    public static final String THANK_YOU = "Thank You For Using the Chess Elo Calculator!";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // Define scanner variable
        System.out.println(WELCOME_MESSAGE); //print welcome message

        //flux variables
        int playerOneElo = 0; 
        int playerTwoElo = 0;
        int playerOneTemp = 0;
        int playerTwoTemp = 0;
        double kValue = 25.0;
        boolean mainLoop = true;

        while (mainLoop) { 
        //main loop that ensures that the main menu message is sent out after every choice is completed
            System.out.println(MAIN_MENU); //print main menu
            String choiceString = input.nextLine(); //ask for choice
            if (menuConvert(choiceString)) { 
            //using the function menuConvert, ensure the string is a number, otherwise returns false

                int choice = Integer.parseInt(choiceString); 
                //Because the string is a number, we can convert it using Integer.parseInt()
                
                if (choice == 1) { //option for single match
                    System.out.println(SINGLE_MATCH); //print single match prompt
                    String matchInput = input.nextLine(); 
                    //get the match input as a string, we can assume that 
                    //this string was input correctly according to the instructions
                    for (int i = 0; i < matchInput.length(); i++) { //loop through the string
                        if (matchInput.substring(i, i + 1).equals("-")) { 
                            //check if the current index of the string is the 
                            //charact "-", ,if so, do the following
                            playerOneElo = Integer.parseInt(matchInput.substring(0, i)); 
                            //This parses the int from the start of the string up 
                            //to the "-" to get the first players elo
                            playerTwoElo = Integer.parseInt(matchInput.substring(i + 2, matchInput.length())); 
                            //this parses the string after the w/l condition to get the second players elo

                            if (matchInput.substring(i + 1, i + 2).toLowerCase().equals("w")) { 
                                // condition for if player one wins

                                //Set updated elo to temp var to ensure the next calculation runs smoothly
                                playerOneTemp = updateElo(playerOneElo, winProbability(
                                    calculateRValue(playerOneElo), 
                                    calculateRValue(playerTwoElo)), 
                                    kValue, true);
                                playerTwoTemp = updateElo(playerTwoElo, winProbability(
                                    calculateRValue(playerTwoElo), 
                                    calculateRValue(playerOneElo)), 
                                    kValue, false);

                                //set elos to updated temp variables.
                                playerOneElo = playerOneTemp;
                                playerTwoElo = playerTwoTemp;
                                break; //break out 
                            } else if (matchInput.substring(i + 1, i + 2).toLowerCase().equals("l")) { 
                                //condition for if player two wins - same as previous but for losses
                                playerOneTemp = updateElo(playerOneElo, winProbability(
                                    calculateRValue(playerOneElo), 
                                    calculateRValue(playerTwoElo)), 
                                    kValue, false);
                                playerTwoTemp = updateElo(playerTwoElo, winProbability(
                                    calculateRValue(playerTwoElo), 
                                    calculateRValue(playerOneElo)), 
                                    kValue, true);
                                playerOneElo = playerOneTemp;
                                playerTwoElo = playerTwoTemp;
                                break;
                            } else { // if they try to input a tie, prompt with invalid input bc they are silly gooses
                                System.out.println(INVALID_INPUT);
                            }             
                        }
                    }
                    System.out.println(MATCH_OUTCOME + playerOneElo + "-" + playerTwoElo); //print out match outcome
                } else if (choice == 2) { //option for tournament match

                    System.out.println(TOURNAMENT_RESULTS); //print tournament prompt

                    //tournament flux variables
                    String tournamentOutput = ""; //
                    String tournamentInput = input.nextLine();
                    String tournamentString = tournamentInput;
                    String currentStr;
                    String tempStr;
                    String firstElo = "";
                    String secondElo = "";
                    String winCondition = "";

                    //loop through tournament string and collect the first elo
                    for (int i = 0; i < tournamentString.length(); i++) {
                        currentStr = tournamentString.substring(i, i + 1);
                        if (numConvert(currentStr)) {
                            firstElo = firstElo + currentStr;
                        } else {
                            tournamentString = tournamentString.substring(i + 1, tournamentString.length());
                            break;
                        }
                    }
                    //set player elo
                    playerOneElo = Integer.parseInt(firstElo);
                    
                    //begin for loop that parses through each competitor elo
                    for (int i = 0; i < tournamentString.length(); i++) {
                        currentStr = tournamentString.substring(i, i + 1);

                        //if "-" is not detecte and it is not a number in the current string
                        //then convert to a win condtion string, as it is either a w or l
                        if (!numConvert(currentStr) && !currentStr.equals("-")) {
                            winCondition = currentStr.toLowerCase();
                        } 
                        //if its a number, convert
                        if  (numConvert(currentStr)) {
                            secondElo = secondElo + currentStr;
                        }
                       
                        //if win, do this 
                        if (winCondition.equals("w") && (currentStr.equals("-") || 
                            i == tournamentString.length() - 1)) { 
                                
                            //player two elo set to the next competitor in tournament stirng
                            playerTwoElo = Integer.parseInt(secondElo); 

                            //set temp vars
                            playerOneTemp = updateElo(playerOneElo, winProbability(
                                calculateRValue(playerOneElo), 
                                calculateRValue(playerTwoElo)), 
                                kValue, true);
                            playerTwoTemp = updateElo(playerTwoElo, winProbability(
                                calculateRValue(playerTwoElo), 
                                calculateRValue(playerOneElo)), 
                                kValue, false);

                            //update elos
                            playerOneElo = playerOneTemp;
                            playerTwoElo = playerTwoTemp;

                            //add to final tournament string
                            tournamentOutput = tournamentOutput + "-" + playerTwoElo;

                            secondElo = "";
                            continue;

                        //if loss, do this
                        } else if (winCondition.equals("l") && 
                            (currentStr.equals("-") || i == tournamentString.length() - 1)) { 
                            
                            //same behaviour as win condition, except win condition is flipped. 
                            playerTwoElo = Integer.parseInt(secondElo);

                            playerOneTemp = updateElo(playerOneElo, winProbability(
                                calculateRValue(playerOneElo), 
                                calculateRValue(playerTwoElo)), 
                                kValue, false);
                            playerTwoTemp = updateElo(playerTwoElo, winProbability(
                                calculateRValue(playerTwoElo), 
                                calculateRValue(playerOneElo)), 
                                kValue, true);
                                
                            playerOneElo = playerOneTemp;
                            playerTwoElo = playerTwoTemp;

                            tournamentOutput = tournamentOutput + "-" + playerTwoElo;

                            secondElo = "";
                            continue;
                        }
                    }
                    tournamentOutput = playerOneElo + tournamentOutput;
                    System.out.println(TOURNAMENT_OUTCOME + tournamentOutput);

                } else if (choice == 3) {

                    //set prediction flux variables
                    System.out.println(FIELD_PREDICTION);
                    String predictionInput = input.nextLine();
                    int counter = 0;
                    int playerElo;
                    int compareElo;
                    String myElo = "";
                    String checkElo = "";
                    int greatestElo = Integer.MIN_VALUE;
                    int smallestElo = Integer.MAX_VALUE;
                    int secondGElo = Integer.MIN_VALUE;
                    int secondSElo = Integer.MAX_VALUE;
                    int greatestEloGain = 0;
                    int greatestEloLoss = 0;
                    int lowestEloGain = 0;
                    int lowestEloLoss = 0;
                    String currentNum = "";
                    String checkNum = "";
                
                    //loop through prediction string
                    for (int i = 0; i < predictionInput.length(); i++) {
                        currentNum = predictionInput.substring(i, i + 1);

                        //if number, add to my elo
                        if (numConvert(currentNum)) {
                            myElo = myElo + currentNum;
                        } 

                        //if not number, or if theres an end of the string, set player elo
                        if (!numConvert(currentNum) || i == predictionInput.length() - 1) {
                            playerElo = Integer.parseInt(myElo);

                            //check for duplicates using a double for loop
                            for (int k = 0; k < predictionInput.length(); k++) {
                                checkNum = predictionInput.substring(k, k + 1);
                                if (numConvert(checkNum)) {
                                    checkElo = checkElo + checkNum;
                                } 
                                if (!numConvert(checkNum) || k == predictionInput.length() - 1) {
                                    compareElo = Integer.parseInt(checkElo);
                                   
                                    if (compareElo == playerElo) {
                                        counter++;
                                    }
                                    if (compareElo > greatestElo) {
                                        greatestElo = compareElo;
                                    }
                                    if (compareElo < smallestElo) {
                                        smallestElo = compareElo;
                                    }
                                    if (compareElo < greatestElo && compareElo > secondGElo) {
                                        secondGElo = compareElo;
                                    }
                                    if (compareElo > smallestElo && compareElo < secondSElo) {
                                        secondSElo = compareElo;
                                    }
                                    checkElo = "";
                                }

                            }        
                            
                            //check cases for counters
                            if (counter > 1 && greatestElo == playerElo) {
                                secondGElo = greatestElo;
                            }
                            if (counter > 1 && smallestElo == playerElo) {
                                secondSElo = smallestElo;
                            }


                            myElo = "";
                            counter = 0;
                        }

                    }

                    //check cases for 
                    if (secondGElo == -1 && secondSElo == -1) {
                        secondGElo = smallestElo;
                        secondSElo = greatestElo;
                    } else if (secondGElo == -1 && secondSElo >= 0) {
                        secondGElo = secondSElo;
                    } else if (secondGElo >= 0 && secondSElo == -1) {
                        secondSElo = secondGElo;
                    }
                    
                    //update elos by predictions using idea that its win margin 
                    //is greatest at when you are the best team, playing the second 
                    //best team, and vice versa for loss
                    greatestEloGain = updateElo(greatestElo, winProbability(
                        calculateRValue(greatestElo), 
                        calculateRValue(secondGElo)), 
                        kValue, true) - greatestElo;

                    greatestEloLoss = updateElo(greatestElo, winProbability(
                        calculateRValue(greatestElo), 
                        calculateRValue(smallestElo)), 
                        kValue, false) - greatestElo;

                    lowestEloGain = updateElo(smallestElo, winProbability(
                        calculateRValue(smallestElo), 
                        calculateRValue(greatestElo)), 
                        kValue, true) - smallestElo;
                    lowestEloLoss = updateElo(smallestElo, winProbability(
                        calculateRValue(smallestElo), 
                        calculateRValue(secondSElo)), 
                        kValue, false) - smallestElo;    
                        
                    // System.out.printf("%d %d %d %d\n", greatestElo, secondGElo, secondSElo, smallestElo);
                    System.out.printf(PREDICTION_OUTCOME + "%d (%+d/%+d) %d (%+d/%+d)\n", 
                        smallestElo, lowestEloGain, lowestEloLoss, greatestElo, greatestEloGain, greatestEloLoss);

                } else if (choice == 4) {
                    
                    while (true) {
                        System.out.println(CONFIRMATION);
                        String confirmation = input.nextLine();
                        if (confirmation.toLowerCase().equals("yes") || 
                            confirmation.toLowerCase().equals("y")) {
                            System.out.println(THANK_YOU);
                            mainLoop = false;
                            break;
                        } else if (confirmation.toLowerCase().equals("no") || 
                            confirmation.toLowerCase().equals("n")) {
                            break;
                        } else {
                            System.out.println(INVALID_INPUT);
                        }
                    }                      
                } else {
                    System.out.println(INVALID_INPUT);
                }

                
            } else {
                System.out.println(INVALID_INPUT);
            }
            
        }
            
    } // End main

    public static double calculateRValue(int elo) {
        double calculatedElo = Math.pow(10.0, elo / 400.0);
        return calculatedElo;
    }

    public static double winProbability(double rValOne, double rValTwo) {
        double winProbability = (rValOne) / (rValOne + rValTwo);
        return winProbability;
    }

    public static int updateElo(int pOneElo, double pOneProb, double kVal, boolean win) {
        if (win) {
            int updatedElo =  (int) (pOneElo + (kVal * (1.0 - pOneProb)));
            return updatedElo;
        } else {
            int updatedElo = (int) (pOneElo + (kVal * (0.0 - pOneProb)));
            return updatedElo;
        }
    }
   
    public static boolean menuConvert(String input) {
        try {
            int optionChoice = Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }  

    public static boolean numConvert(String num) {
        try {
            int numberInput = Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

} // End class


