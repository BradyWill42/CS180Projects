//import statements go here
import java.util.Scanner;

//Javadoc goes here

/**
 * HW-05 -- MyMathHelper
 *
 * This program takes inputs of 1,2,3 and outputs options of 
 * computing GCF of 2 numbers, finding prime factorization of a number,
 * and exiting the program
 *
 * @author Brady Williams, L23
 *
 * @version Feb 12, 2024
 *
 */
public class MyMathHelper {

    public static final String WELCOME_MESSAGE = "Welcome to My Math Helper!";
    public static final String MAIN_MENU_ONE = "Please Select an Operation";
    public static final String MAIN_MENU_TWO = "1 - Calculate Greatest Common Denominator";
    public static final String MAIN_MENU_THREE = "2 - Perform Prime Factorization";
    public static final String MAIN_MENU_FOUR = "3 - Exit";
    public static final String GCD_NOTIFICATION = "Ready to Calculate Greatest Common Denominator";
    public static final String PF_NOTIFICATION = "Ready to Perform Prime Factorization";
    public static final String INPUT_ONE = "Please Enter an Integer";
    public static final String INPUT_TWO = "Please Enter a Second Integer";
    public static final String GCD_OUTPUT = "The Greatest Common Denominator is ";
    public static final String PF_OUTPUT = "The Prime Factorization is ";
    public static final String EXIT_MESSAGE = "Thank you for using My Math Helper!";
    public static final String INVALID_MENU_SELECTION  = "Invalid selection!";
    public static final String INVALID_INPUT = "Invalid Input - Returning to Main Menu";

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.println(WELCOME_MESSAGE);

        String inputOne;
        String inputTwo;
        boolean cont = true;

        while (true) {

            System.out.println(MAIN_MENU_ONE);
            System.out.println(MAIN_MENU_TWO);
            System.out.println(MAIN_MENU_THREE);
            System.out.println(MAIN_MENU_FOUR);

            String selection = input.nextLine();

            if (isInt(selection) && Integer.parseInt(selection) == 1) {
                System.out.println(GCD_NOTIFICATION);

                int gcd = 1;

                System.out.println(INPUT_ONE);
                inputOne = input.nextLine();
                if (!isInt(inputOne) || Integer.parseInt(inputOne) <= 0) {
                    System.out.println(INVALID_INPUT);
                } else {
                    System.out.println(INPUT_TWO);
                    inputTwo = input.nextLine();
                    if (!isInt(inputTwo) || Integer.parseInt(inputTwo) <= 0) {
                        System.out.println(INVALID_INPUT);
                    } else {
                        int num1 = Integer.parseInt(inputOne);
                        int num2 = Integer.parseInt(inputTwo);
                        int num3;


                        if (num2 < num1) {
                            while (num1 > num2) {
                                num1 = num1 - num2;
                                while (num2 > num1) {
                                    num2 = num2 - num1;
                                }
                            }
                            num3 = num1;
                        } else if (num1 < num2) {
                            while (num2 > num1) {
                                num2 = num2 - num1;
                                while (num1 > num2) {
                                    num1 = num1 - num2;
                                }
                            }
                            num3 = num2;

                        } else {
                            num3 = num1;
                        }

                        gcd = num3;
                        System.out.println(GCD_OUTPUT + "" + gcd);
                    }
                }
            } else if (isInt(selection) && Integer.parseInt(selection) == 2) {
                System.out.println(PF_NOTIFICATION);
                System.out.println(INPUT_ONE);
                inputOne = input.nextLine();

                if (isInt(inputOne) && Integer.parseInt(inputOne) > 2) {
                    
                    System.out.print(PF_OUTPUT);
                    primeFactorization(Integer.parseInt(inputOne));
                    System.out.println();

                } else {
                    System.out.println(INVALID_INPUT);
                }
            } else if (isInt(selection) && Integer.parseInt(selection) == 3) { 
                System.out.println(EXIT_MESSAGE);
                break;
            } else { 
                System.out.println(INVALID_MENU_SELECTION);
            }
        }

    } // End main

    public static void primeFactorization(int num) {
        boolean firstInput = true; 

        while (num % 2 == 0 && firstInput) {
            System.out.print("2");
            num = num / 2;
            firstInput = false;
        }

        while (num % 2 == 0 && !firstInput) {
            System.out.print(" x 2");
            num = num / 2;
        }

        for (int i = 3; i <= num; i += 2) {
            while (num % i == 0 && firstInput) {
                System.out.print(i);
                num = num / i;
                firstInput = false;
            }

            while (num % i == 0 && !firstInput) {
                System.out.print(" x " + i);
                num = num / i;
            }

        }

        if (num > 2 && !firstInput) {
            System.out.print(" x " + num);
        } else if (num > 2 && firstInput) {
            System.out.print(num);
        } else {

        }
    }

    public static boolean isInt(String string) {
        if (string == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

} // End class
