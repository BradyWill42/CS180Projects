
/**
 * HW-09 -- Waterslide
 *
 * This program acts as the child class
 * to the Ride parent class for any water slides
 * added to the waterpark
 *
 * @author Brady Williams, L23
 *
 * @version Mar 16, 2024
 *
 */
public class Waterslide extends Ride {
    private double splashDepth;

    public Waterslide(String name, String color, int minHeight, int maxRiders, double splashDepth) {
        super(name, color, minHeight, maxRiders);
        this.splashDepth = splashDepth;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    public double getSplashDepth() {
        return splashDepth;
    }
    
    public void setSplashDepth(double splashDepth) {
        this.splashDepth = splashDepth;
    }

    public String toString() {
        return String.format("Name: %s\nColor: %s\nMinHeight: %d inches\nMaxRiders: %d\nSplashDepth: %.1f feet", 
            getName(), 
            getColor(), 
            getMinHeight(), 
            getMaxRiders(), 
            splashDepth);
    }
}
