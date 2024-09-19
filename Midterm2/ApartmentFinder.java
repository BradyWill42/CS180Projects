
/**
 * Midterm 2 -- ApartmentFinder
 *
 * This class extends the class
 * RealEstateFinder, and can be used
 * to find costs associated with 
 * apartments
 *
 * @author Brady Williams, L23
 *
 * @version Mar 26, 2024
 *
 */
public class ApartmentFinder extends RealEstateFinder {
    private int propertyCount;
    private double commission;

    public ApartmentFinder(String projectName, int employeeCount, 
        boolean international, int propertyCount, double commission) {

        super(projectName, employeeCount, international);

        if (propertyCount < 0) {
            throw new IllegalArgumentException();
        }
        this.propertyCount = propertyCount;
        this.commission = commission;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public double getCommission() {
        return commission;
    }

    public double calculateAverageCommission() {
        return commission / propertyCount;
    }

    public double calculateReturnOnInvestment(double averageSalary, int months) {
        return commission - this.calculateCostEstimate(averageSalary, months);
    }
}
