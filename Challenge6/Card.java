

/**
 * HW-06 -- Card
 *
 * This program computes the necessary calculations
 * for the credit card payment plan
 *
 * @author Brady Williams, L23
 *
 * @version Feb 19, 2024
 *
 */
public class Card {
    private double rate;
    private double balance;
    private double monthlyPayment;

    public Card(double rate, double balance, double monthlyPayment) {
        this.rate = rate;
        this.balance = balance;
        this.monthlyPayment = monthlyPayment;
    }

    public double getRate() {
        return rate;
    }

    public double getBalance() {
        return balance;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int calculatePayoff(boolean output) {
        double monthlyInterestRate;
        double monthlyInterestCharge;
        double monthlyPrincipalPayment;
        int months;
        double totalInterestPaid = 0;
        
        for (months = 0; balance > 0; months++) {

            monthlyInterestRate = rate / 12;
            monthlyInterestCharge = monthlyInterestRate * balance;
            monthlyPrincipalPayment = monthlyPayment - monthlyInterestCharge;

            if (monthlyPayment < monthlyInterestCharge) {
                System.out.println("Error: Impossible to payoff balance under these conditions");
                return -1;
            }

            if (balance - monthlyPrincipalPayment < 0) {
                monthlyPrincipalPayment = balance;
                balance = 0;
            } else {
                balance = balance - monthlyPrincipalPayment;
            }
            totalInterestPaid = totalInterestPaid + monthlyInterestCharge;

            if (output) {
                System.out.printf("Payment: %d - Principal: %.2f - Interest: %.2f - Remaining Balance: %.2f\n", 
                    (months + 1), monthlyPrincipalPayment, monthlyInterestCharge, balance);
            }

        }
        if (output) {
            System.out.printf("Total Interest Paid: %.2f\n", totalInterestPaid);
        }

        return months;

    }

    public String toString() {
        return String.format("Rate: %.2f - Balance: %.2f - Payment: %.2f", rate, balance, monthlyPayment);
    }

}