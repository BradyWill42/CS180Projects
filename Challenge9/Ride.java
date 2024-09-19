
/**
 * HW-09 -- Ride
 *
 * This program acts as the parent
 * class for any Rides added to 
 * the park.
 *
 * @author Brady Williams, L23
 *
 * @version Mar 16, 2024
 *
 */
public class Ride {
    private String color;
    private int maxRiders;
    private int minHeight;
    private String name;
    
    public Ride() {
        this.color = "";
        this.maxRiders = 0;
        this.minHeight = 0;
        this.name = "";
    }

    public Ride(String name, String color, int minHeight, int maxRiders) {
        this.name = name;
        this.color = color;
        this.minHeight = minHeight;
        this.maxRiders = maxRiders;
    }

    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    public String getColor() {
        return color;
    }

    public int getMaxRiders() {
        return maxRiders;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxRiders(int maxRiders) {
        this.maxRiders = maxRiders;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Name: %s\nColor: %s\nMinHeight: %d inches\nMaxRiders: %d", 
            name, 
            color, 
            minHeight, 
            maxRiders);
    }


}
