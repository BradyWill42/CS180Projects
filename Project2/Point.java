//Javadoc
/**
 * Project 2 -- Point
 *
 * Create point from x, y, and z
 * values. Ensure comparisons are equal
 * within 0.001 of each value. 
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 1, 2024
 *
 */
public class Point {

    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y)  {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean compareTo(Point point) {
        return (Math.abs(x - point.getX()) <= 0.0001 && 
            Math.abs(y - point.getY()) <= 0.0001 && 
            Math.abs(z - point.getZ()) <= 0.0001);
    }

    public String toString() { 
        return String.format("(x%.3f, y%.3f, z%.3f)", x + 0.0, y + 0.0, z + 0.0);
    }

    


}