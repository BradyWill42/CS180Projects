import java.util.Scanner;



/**
 * HW-06 -- CreditCardCalculator
 *
 * This program prompts the user to create a card
 * and generates mock credit card payment plans for the user
 *
 * @author Brady Williams, L23
 *
 * @version Feb 19, 2024
 *
 */
public class CreditCardCalculator {
    public static final String WELCOME_MESSAGE = "Welcome to the Credit Card Calculator!";
    public static final String MENU = "Menu";
    public static final String QUIT_OPTION = "0. Quit";
    public static final String ADD_OPTION = "1. Add a card";
    public static final String RATE_PROMPT = "Enter the annual interest rate:";
    public static final String BALANCE_PROMPT = "Enter the balance:";
    public static final String PAYMENT_PROMPT = "Enter the monthly payment:";
    public static final String MODIFY_OPTION = "1. Modify card";
    public static final String PAYOFF_OPTION = "2. Calculate Payoff";
    public static final String CALCULATION_OPTION = "Would you like to print the payoff calculations?";
    public static final String CALCULATION_DECISION = "1. Yes\n2. No";
    public static final String CALCULATION_RESULT = "Months until payoff: %d";
    public static final String ERROR_MESSAGE = "Error! Invalid input.";
    public static final String GOODBYE_MESSAGE = "Thank you!";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choiceString;
        int choiceInt = -1;
        boolean cardCreated = false;
        double rate;
        double balance;
        double monthlyPayment;
        int doubleCalculations;
        int months;

        Card card = null;

        System.out.println(WELCOME_MESSAGE);

        while (!cardCreated) {
         
            System.out.println(MENU);
            System.out.println(QUIT_OPTION);
            System.out.println(ADD_OPTION);
            choiceString = input.nextLine();

            try {
                choiceInt = Integer.parseInt(choiceString);
            } catch (Exception e) {
                System.out.println(ERROR_MESSAGE);
            }

            if (choiceInt == 0) {
                break;
            }

            if (choiceInt == 1) {
                System.out.println(RATE_PROMPT);
                rate = input.nextDouble();
                input.nextLine();
                System.out.println(BALANCE_PROMPT);
                balance = input.nextDouble();
                input.nextLine();
                System.out.println(PAYMENT_PROMPT);
                monthlyPayment = input.nextDouble();
                input.nextLine();

                card = new Card(rate, balance, monthlyPayment);
                System.out.println(card);

                cardCreated = true;

            } 

        }

        while (choiceInt != 0) {
            System.out.println(MENU);
            System.out.println(QUIT_OPTION);
            System.out.println(MODIFY_OPTION);
            System.out.println(PAYOFF_OPTION);
            choiceString = input.nextLine();

            try {
                choiceInt = Integer.parseInt(choiceString);
            } catch (Exception e) {
                System.out.println(ERROR_MESSAGE);
            }

            if (choiceInt == 0) {
                break;
            }
            if (choiceInt == 1) {
                System.out.println(RATE_PROMPT);
                rate = input.nextDouble();
                input.nextLine();
                System.out.println(BALANCE_PROMPT);
                balance = input.nextDouble();
                input.nextLine();
                System.out.println(PAYMENT_PROMPT);
                monthlyPayment = input.nextDouble();
                input.nextLine();

                card = new Card(rate, balance, monthlyPayment);
                System.out.println(card);
            }
            if (choiceInt == 2) {
                System.out.println(CALCULATION_OPTION);
                System.out.println(CALCULATION_DECISION);
                doubleCalculations = input.nextInt();
                input.nextLine();

                if (doubleCalculations == 1) {
                    months = card.calculatePayoff(true);
                    if (months != -1) {
                        System.out.printf(CALCULATION_RESULT + "\n", months);
                    }
                } else if (doubleCalculations == 2) {
                    months = card.calculatePayoff(false);
                    if (months != -1) {
                        System.out.printf(CALCULATION_RESULT + "\n", months);
                    }            
                }
            }
        }

        System.out.println(GOODBYE_MESSAGE);
    }

}