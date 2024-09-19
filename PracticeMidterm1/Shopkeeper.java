import java.util.Scanner;

public class Shopkeeper {

    public static final String NAME_QUESTION = "What is the customer's name?";
    public static final String ITEM_QUESTION = "How many items did the customer purchase?";
    public static final String ITEM_NAME = "Enter the %d%s item name.\n";
    public static final String ITEM_COST = "How much did this item cost?";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        String recieptCode = "";

        System.out.println(NAME_QUESTION);
        String name = input.nextLine();
        String currentSubStr;

        for (int i = 0; i < name.length(); i++) {
            currentSubStr = name.substring(i, i + 1);
            if (i == 0) {
                recieptCode = recieptCode + currentSubStr;
            }
            if (currentSubStr.equals(" ")) {
                recieptCode = recieptCode + name.substring(i + 1, i + 2);
                break;
            }
        }        

        System.out.println(ITEM_QUESTION);
        int numItems = input.nextInt();
        input.nextLine();

        recieptCode = recieptCode + numItems;
        
        String wholeNumber;
        String lastDigit;
        int checkDigit;
        boolean firstOrder = true;
        double totalPrice = 0;
        

        for (int i = 1; i <= numItems; i++) {
            wholeNumber = "" + i;
            lastDigit = wholeNumber.substring(wholeNumber.length() - 1, wholeNumber.length());
            checkDigit = Integer.parseInt(lastDigit);

            if (i > 10 && i < 20) {
                System.out.printf(ITEM_NAME, i, "th");
            } else if (checkDigit == 0) {
                System.out.printf(ITEM_NAME, i, "th");
            } else if (checkDigit == 1) {
                System.out.printf(ITEM_NAME, i, "st");
            } else if (checkDigit == 2) {
                System.out.printf(ITEM_NAME, i, "nd");
            } else if (checkDigit == 3) {
                System.out.printf(ITEM_NAME, i, "rd");
            } else if (checkDigit >= 4) {
                System.out.printf(ITEM_NAME, i, "th");
            }

            String itemName = input.nextLine();

            if (firstOrder) {
                recieptCode = recieptCode + itemName;
                firstOrder = false;
            } else {
                recieptCode = recieptCode + "," + itemName;
            }

            System.out.println(ITEM_COST);
            double cost = input.nextDouble();
            input.nextLine();

            totalPrice = totalPrice + cost;
        }

        recieptCode = recieptCode + ":" + totalPrice;

        System.out.println("Your customer string is " + recieptCode);


        DiningCourt dining = new DiningCourt(new Entree("Penne Pasta", 440, true, true), new Entree("Mac and Cheese", 350, true, false), new Entree("Tilapia in Jalepeno Cream", 590, false, false));

        System.out.println(dining);

        System.out.println(dining.getHeighestCalorieEntree());
        System.out.println(dining.getLowestCalorieEntree());

        dining.printVegetarianEntrees();
        dining.printVeganEntrees();

    }
}