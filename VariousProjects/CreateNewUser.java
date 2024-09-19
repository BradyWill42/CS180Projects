import java.util.Locale;
import java.util.Scanner;

/**
 * HW-03 -- CreateNewUser
 *
 * This program takes in input from System.in
 * performs string manipulation and returns a user String
 *
 * @author Brady Williams, L23
 *
 * @version Jan 9, 2024
 *
 */

public class CreateNewUser {

    //List of public static final strings provided to prevent typos

    public static final String PROMPT_ONE = "Enter Customer First Name:";
    public static final String PROMPT_TWO = "Enter Customer Last Name:";
    public static final String PROMPT_THREE = "Enter Customer Age:";
    public static final String PROMPT_FOUR = "Enter Customer Street Address:";
    public static final String PROMPT_FIVE = "Enter Customer City:";
    public static final String PROMPT_SIX = "Enter Customer State:";
    public static final String PROMPT_SEVEN = "Enter Customer Zip Code:";
    public static final String PROMPT_EIGHT = "Enter Customer Phone Number:";
    public static final String OUTPUT = "The Assigned User String is ";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println(PROMPT_ONE);
        
        String firstName = scan.nextLine();
        
        String firstNameString = firstName.substring(0, 1).toLowerCase() 
            + firstName.substring(firstName.length() - 1, firstName.length()).toLowerCase();

        System.out.println(PROMPT_TWO);

        String lastName = scan.nextLine();

        String lastNameString = lastName.substring(0, 1).toUpperCase() 
            + lastName.substring(1, 2).toUpperCase();

        String name = lastNameString + firstNameString;

        System.out.println(PROMPT_THREE);

        int ageInt = scan.nextInt();
        scan.nextLine();

        String ageString = Integer.toString(ageInt);

        String firstAgeDigit = ageString.substring(0, 1);
        String secondAgeDigit = ageString.substring(1, 2);

        int secondDigit = Integer.parseInt(secondAgeDigit);

        String secondAgeBinary = Integer.toBinaryString(secondDigit);
        
        int secondAgeInt = Integer.parseInt(secondAgeBinary);

        String secondAgeString = String.format("%04d", secondAgeInt);

        String nameAndAge = firstAgeDigit + name + secondAgeString;

        System.out.println(PROMPT_FOUR);
        
        String address = scan.nextLine();

        String addressString = address.replace(" ", "").toUpperCase();

        String nameAgeAddress = nameAndAge + addressString;

        System.out.println(PROMPT_FIVE);

        String city = scan.nextLine();

        String cityString = " ".concat(city.substring(0, 2).toUpperCase());

        String nameAgeAddressCity = nameAgeAddress + cityString;
        
        System.out.println(PROMPT_SIX);

        String state = scan.nextLine();

        char stateChar0 = state.toUpperCase().charAt(0);

        char stateChar1 = state.toUpperCase().charAt(1);

        int stringNumber = ((int) stateChar0) + ((int) stateChar1);

        String nameAgeAddressCityState = nameAgeAddressCity + stringNumber;
        
        System.out.println(PROMPT_SEVEN);

        String zipCode = scan.nextLine();

        int zipCodeString = Integer.parseInt(zipCode.substring(0, 1)) 
            + Integer.parseInt(zipCode.substring(2, 3));

        String nameAgeAddressCityStateZip = nameAgeAddressCityState + zipCodeString;
        
        System.out.println(PROMPT_EIGHT);
        
        String phoneNumber = scan.nextLine();

        String phoneNumberString = phoneNumber;
        phoneNumberString = phoneNumberString.substring(phoneNumberString.length() - 4, phoneNumberString.length());

        String nameAgeAddressCityStateZipPhone = nameAgeAddressCityStateZip + phoneNumberString;

        int totLength = firstName.length() + lastName.length() + ageString.length() + address.length() 
            + city.length() + state.length() + zipCode.length() + phoneNumber.length();

        // int totLength = nameAgeAddressCityStateZipPhone.length();

        String finalString = totLength + nameAgeAddressCityStateZipPhone.replace("O", "").replace("I", "");

        System.out.println("The Assigned User String is " + finalString);


    } //End Main method


} //End CreateNewUser.class
