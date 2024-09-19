import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//Javadoc
/**
 * Challenge 10 - StockGame
 *
 * Class that tracks trades between
 * concurrent running threads
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 25, 2024
 *
 */
public class StockGame extends Thread {


    private static double stockPrice = 100;
    private static int availableShares = 100;
    private static int tradeCount = 0;
    private static Object lock = new Object();
    private String name;
    private int numShares;
    private String fileName;


    public StockGame(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        this.numShares = 0;
    }  

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            String[] trades;
            String line;
            while ((line = br.readLine()) != null) {
                trades = splitLine(line);
                synchronized (lock) {
                    if (stockOption(trades[0]) == 1) {
                        buyAndSell(stockOption(trades[0]) * getAmount(trades[1]), 1);
                        printRound(getAmount(trades[1]), stockPrice, name, 1);
                    } else if (stockOption(trades[0]) == -1) {
                        buyAndSell(stockOption(trades[0]) * getAmount(trades[1]), -1);
                        printRound(getAmount(trades[1]), stockPrice, name, -1);
                    } else {
                        System.out.println("Error, invalid input!");
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error, invalid input!");
        }
    }


    /**
    * Challenge 10 - stockOption(String s)
    *
    * This function takes a line from a file
    * and determines the according stock option
    * where a positive one (+1) is a buy, and a 
    * negative one (-1) is a sell. 
    *
    * This program will return a 0 when the buy 
    * or sell line is corrupted
    * 
    * @param line String line input for function 
    */
    public int stockOption(String line) {
        if (line.contains("BUY")) {
            return 1;
        } else if (line.contains("SELL")) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
    * Challenge 10 - splitLine(String s)
    *
    * This function takes a line from a file
    * and splits it by the ","
    *
    * @return String array where index 0 is BUY/SELL
    * and index 1 is the amount
    * @param line String line input for function
    */
    public String[] splitLine(String line) {
        return line.split(",");
    }

    /**
    * Challenge 10 - getAmount(String s)
    *
    * This function takes a line from a file
    * and determines the according amount of 
    * shares to be bought or sold. 
    * 
    * @param line String line input for function 
    */
    public int getAmount(String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;            
        }   
    }


    public synchronized void buyAndSell(int amount, int option) {
        if (option == 1) {
            if (amount <= availableShares) {
                synchronized (lock) {
                    numShares += amount;
                    availableShares -= amount;
                    stockPrice += (1.5 * amount);
                }
            } else {
                System.out.println("Insufficient shares available, cancelling order...");
            }
        } else {
            if (amount <= numShares) {
                synchronized (lock) {
                    numShares += amount;
                    availableShares -= amount;
                    stockPrice += (2 * amount);
                }
            } else {
                System.out.println("Insufficient shares available, cancelling order...");
            }
        }
    }

    public void printRound(int shares, double price, String myName, int option) {
        synchronized (lock) {
            tradeCount++;
        }
        if (option == 1) {
            System.out.printf("---------\nStock Price: %.2f\nAvailable Shares: %d\n" +
                "Trade Number: %d\nName: %s\nPurchasing %d shares at %.2f...\n", 
                stockPrice, 
                availableShares, 
                tradeCount, 
                myName, 
                shares, 
                price);
        } else {
            System.out.printf("---------\nStock Price: %.2f\nAvailable Shares: %d\n" +
                "Trade Number: %d\nName: %s\nSelling %d shares at %.2f...\n", 
                stockPrice, 
                availableShares, 
                tradeCount, 
                myName, 
                shares, 
                price);
        }       
    }
    


    public static void main(String[] args) {
        try {
            StockGame[] stockTraders = {new StockGame("Xander", "TraderOneMoves.txt"),
                new StockGame("Afua", "TraderTwoMoves.txt")};
        
            for (int i = 0; i < stockTraders.length; i++) {
                stockTraders[i].start();
            }
            for (int i = 0; i < stockTraders.length; i++) {
                stockTraders[i].join();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }



    }

}

