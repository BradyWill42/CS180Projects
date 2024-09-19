//Javadoc
/**
 * Project 2 -- UnitVector
 *
 * Create Unit vector from 
 * 2 points, or from three
 * directional components (i, j, k)
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 1, 2024
 *
 */
public class UnitVector {

    private double i;
    private double j;
    private double k;

    public UnitVector(double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;

        double magnitude = Math.sqrt((i * i) + (j * j) + (k * k));

        if ((Math.abs(magnitude - 1) > 0.0001)) {
            this.i = this.i / magnitude;
            this.j = this.j / magnitude;
            this.k = this.k / magnitude;
        }
    }

    public UnitVector(Point start, Point end) {
        this.i = end.getX() - start.getX();
        this.j = end.getY() - start.getY();
        this.k = end.getZ() - start.getZ();

        double magnitude = Math.sqrt((i * i) + (j * j) + (k * k));

        if ((Math.abs(magnitude - 1) > 0.0001)) {
            this.i = this.i / magnitude;
            this.j = this.j / magnitude;
            this.k = this.k / magnitude;
        }
    }

    public UnitVector() {
        this.i = 0;
        this.j = 0;
        this.k = 0;
    }

    public double getI() {
        return i;
    }

    public double getJ() {
        return j;
    }

    public double getK() {
        return k;
    }

    public void flipVector() {
        i = -i;
        j = -j;
        k = -k;
    }

    public boolean compareTo(UnitVector vector) {
        return (Math.abs(i - vector.getI()) <= 0.0001 && 
        Math.abs(j - vector.getJ()) <= 0.0001 && 
        Math.abs(k - vector.getK()) <= 0.0001);
    }

    public UnitVector crossProduct(UnitVector b) {
        UnitVector c = new UnitVector(
            (this.j * b.getK()) - (this.k * b.getJ()), 
            (this.k * b.getI()) - (this.i * b.getK()), 
            (this.i * b.getJ()) - (this.j * b.getI())
        );

        return c;
    }

    public String toString() {
        if (this.compareTo(new UnitVector())) {
            return String.format("<%s>", "InvalidUnitVector");
        } else { 
            return String.format("<%.3fi, %.3fj, %.3fk>", i + 0.0, j + 0.0, k + 0.0);
        }
    }


}