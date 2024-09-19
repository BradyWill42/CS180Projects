/**
 * HW-07 -- YahtzeePlayer
 *
 * This program performs calculations on the 
 * roll results supplied to it to find
 * the points earned per player
 *
 * @author Brady Williams, L23
 *
 * @version Feb 26, 2024
 *
 */
public class YahtzeePlayer {

    private int[][] rollResults;
    private int total;
    private int fourOfAKindTotal;
    private boolean fullHouse;
    private boolean largeStraight;
    private boolean yahtzee;

    public YahtzeePlayer(int[][] rollResults) {
        this.rollResults = rollResults;
        this.total = 0;
        this.fourOfAKindTotal = 0;
        this.fullHouse = false;
        this.largeStraight = false;
        this.yahtzee = false;

    }

    public int getFourOfAKindTotal() {
        return fourOfAKindTotal;
    }

    public boolean hasFullHouse() {
        return fullHouse;
    }

    public boolean hasLargeStraight() {
        return largeStraight;
    }

    public boolean hasYahtzee() {
        return yahtzee;
    }

    public int getTotal() {
        return total;
    }

    public void checkFourOfAKind() {
        int counter = 0;
        int points = 0;
        boolean fourOfAKind = false;

        for (int i = 0; (i < rollResults.length && !fourOfAKind); i++) {
            for (int j = 0; (j < rollResults[i].length && !fourOfAKind); j++) {
                for (int k = 0; (k < rollResults[i].length); k++) {
                    if (rollResults[i][j] == rollResults[i][k]) {
                        counter++;
                    }
                    points += rollResults[i][k];
                }   
                if (counter == 4) {
                    fourOfAKind = true;
                } else {
                    points = 0;
                    counter = 0;
                }
            }
        }

        if (fourOfAKind) {
            fourOfAKindTotal = points;
        } else {
            fourOfAKindTotal = 0;
        }


    }

    public void checkFullHouse() { 
        int counter = 0;
        boolean pair = false;
        boolean threeOfAKind = false;

        for (int i = 0; (i < rollResults.length && !fullHouse); i++) {
            for (int j = 0; (j < rollResults[i].length && !fullHouse); j++) {
                for (int k = 0; (k < rollResults[i].length && !fullHouse); k++) {
                    if (rollResults[i][j] == rollResults[i][k]) {
                        counter++;
                    }
                }
                if (counter == 2) {
                    pair = true;
                }
                if (counter == 3) {
                    threeOfAKind = true;
                }
                counter = 0;

            }
            if (pair && threeOfAKind) {
                fullHouse = true;
            }
            pair = false;
            threeOfAKind = false;
        }
    }

    public void checkLargeStraight() {
        int counter = 0;
        boolean testStraight = false;

        for (int i = 0; (i < rollResults.length && !largeStraight); i++) {
            for (int j = 0; (j < rollResults[i].length - 1 && !largeStraight); j++) {
                if (rollResults[i][j] + 1 == rollResults[i][j + 1]) {
                    counter++;
                }
            }
            if (counter == 4) {
                testStraight = true;
            }
            counter = 0;
        }

        largeStraight = testStraight;
    }

    public void checkYahtzee() {
        int counter = 0;

        for (int i = 0; (i < rollResults.length && !yahtzee); i++) {
            for (int j = 0; (j < rollResults[i].length && !yahtzee); j++) {
                if (rollResults[i][0] == rollResults[i][j]) {
                    counter++;
                }
            }
            if (counter == 5) {
                yahtzee = true;
            }
            counter = 0;
        }
    }

    public void calculateTotal() {
        total += fourOfAKindTotal;
        if (fullHouse) {
            total += 25;
        }
        if (largeStraight) {
            total += 40;
        }
        if (yahtzee) {
            total += 50;
        }
    }




}