//imports
import java.util.Scanner;

//Javadoc
/**
 * Midterm 1 -- BookingInfo
 *
 * This program will accept input values for
 * various information about flight booking
 *
 * @author Brady Williams, L23
 *
 * @version Feb 20, 2024
 *
 */
public class BookingInfo {
    
    public static final String WELCOME_MESSAGE = "Welcome to the BookingInfo program!";
    public static final String TYPE_PROMPT = "What is the type of booking?";
    public static final String AIRLINE_NAME_PROMPT = "What is the name of the airline?";
    public static final String CLASS_PROMPT = "What is the class of travel?";
    public static final String PAYMENT_MODE_PROMPT = "What is the mode of payment?";
    public static final String DOLLARS_USED_PROMPT = "How many dollars did you use for this booking?";
    public static final String MILES_USED_PROMPT = "How many miles did you use for this booking?";
    public static final String FREQUENT_FLYER_PROMPT = "What is your Frequent Flyer number?";
    public static final String ORIGIN_PROMPT = "What is the origin airport code?";
    public static final String DESTINATION_PROMPT = "What is the destination airport code?";
    public static final String TRAVELERS_PROMPT = "How many adults and children are traveling on this trip?";
    public static final String LAYOVER_COUNT_PROMPT = "How many layovers are there between the source and destination airport?";
    public static final String ITH_CODE_PROMPT = "What is the airport code for layover number ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int milesUsed = -1;
        int dollarsUsed = -1;
        String code = "";
    

        System.out.println(WELCOME_MESSAGE);
        System.out.println(TYPE_PROMPT);
        String bookingType = input.nextLine();

        code = code + bookingType.substring(0, 1).toUpperCase() + "_";

        System.out.println(AIRLINE_NAME_PROMPT);
        String airlineName = input.nextLine();

        String currentSubStr;
        int nameLength = 0;
        for (int i = 0; i < airlineName.length(); i++) {
            currentSubStr = airlineName.substring(i, i + 1);
            if (i == 0) {
                code = code + currentSubStr.toUpperCase();
            }
            if (currentSubStr.equals(" ")) {
                code = code + airlineName.substring(i + 1, i + 2).toUpperCase();
            } else {
                nameLength++;
            }
        }    
        
        code = code + nameLength + "_";

        System.out.println(CLASS_PROMPT);
        String travelClass = input.nextLine();

        if (travelClass.toLowerCase().equals("first")) {
            code = code + 1 + "_";
        } else if (travelClass.toLowerCase().equals("premium economy")) {
            code = code + 2 + "_";
        } else {
            code = code + 3 + "_";
        }

        System.out.println(PAYMENT_MODE_PROMPT);
        String paymentMode = input.nextLine();
        
        if (paymentMode.toLowerCase().equals("miles")) {
            System.out.println(MILES_USED_PROMPT);
            milesUsed = input.nextInt();
            input.nextLine();
            code = code + milesUsed + "M_";
        } else if (paymentMode.toLowerCase().equals("cash")) {
            System.out.println(DOLLARS_USED_PROMPT);
            dollarsUsed = input.nextInt();
            input.nextLine();
            code = code + dollarsUsed + "USD_";
        }

        System.out.println(FREQUENT_FLYER_PROMPT);
        String frequentFlyerNumber = input.nextLine();

        if (frequentFlyerNumber.toLowerCase().equals("n/a")) {
            code = code + "N_";
        } else {
            code = code + frequentFlyerNumber.substring(
                frequentFlyerNumber.length() - 3, frequentFlyerNumber.length()) + "_";
        }



        System.out.println(ORIGIN_PROMPT);
        String origin = input.nextLine();

        code = code + origin + "->";

        System.out.println(DESTINATION_PROMPT);
        String destination = input.nextLine();
        System.out.println(TRAVELERS_PROMPT);
        String travelers = input.nextLine();
        System.out.println(LAYOVER_COUNT_PROMPT);
        int layovers = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= layovers; i++) {
            System.out.println(ITH_CODE_PROMPT + i);
            String airportCode = input.nextLine();
            code = code + airportCode + "->";
        }

        code = code + destination + "_";

        String parseStr;
        String adults = "";
        String children = "";
        boolean spaceDetected = false;
        for (int i = 0; i < travelers.length(); i++) {
            parseStr = travelers.substring(i, i + 1);
            if (checkNum(parseStr) && !spaceDetected) {
                adults = adults + parseStr;
                spaceDetected = true;
                continue;
            }
            if (checkNum(parseStr) && spaceDetected) {
                children = children + parseStr;
            }
        }
        
        code = code + adults + "A" + children + "C;";

        System.out.println(code);
    }

    public static boolean checkNum(String str) {
        try {
            int num = Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}


