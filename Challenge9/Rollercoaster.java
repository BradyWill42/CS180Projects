
/**
 * HW-09 -- Rollercoaster
 *
 * This program acts as the child class
 * to the Ride parent class for any rollercoasters
 * added to the amusement park
 *
 * @author Brady Williams, L23
 *
 * @version Mar 16, 2024
 *
 */
public class Rollercoaster extends Ride {
    private boolean simulated;

    public Rollercoaster(String name, String color, int minHeight, int maxRiders, boolean simulated) {
        super(name, color, minHeight, maxRiders);
        this.simulated = simulated;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    public boolean isSimulated() {
        return simulated;
    }

    public void setSimulated(boolean simulated) {
        this.simulated = simulated;
    }

    public String toString() {
        return String.format("Name: %s\nColor: %s\nMinHeight: %d inches\nMaxRiders: %d\nSimulated: %b", 
            getName(), 
            getColor(), 
            getMinHeight(), 
            getMaxRiders(), 
            simulated);
    }
}
