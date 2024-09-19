//Javadoc
/**
 * Project 2 -- Triangle
 *
 * Create Triangle from Three Points
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 1, 2024
 *
 */
public class Triangle {

    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private UnitVector surfaceNormal;

    public Triangle(Point vertA, Point vertB, Point vertC) {
        this.vertexA = vertA;
        this.vertexB = vertB;
        this.vertexC = vertC;

        UnitVector ab = new UnitVector(vertexA, vertexB);
        UnitVector ac = new UnitVector(vertexA, vertexC);

        surfaceNormal = ab.crossProduct(ac);
    }

    public Triangle() {
        this.vertexA = new Point();
        this.vertexB = new Point();
        this.vertexC = new Point();

        this.surfaceNormal = new UnitVector();
    }

    public Point getVertexA() {
        return vertexA;
    }

    public Point getVertexB() {
        return vertexB;
    }

    public Point getVertexC() {
        return vertexC;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public Point[] getVertices() {
        Point[] points = {getVertexA(), getVertexB(), getVertexC()};
        return points;
    }

    public void flipSurfaceNormal() {
        surfaceNormal.flipVector();
    }

    public boolean compareTo(Triangle triangle) {
        return (vertexA.compareTo(triangle.getVertexA()) &&
            vertexB.compareTo(triangle.getVertexB()) &&
            vertexC.compareTo(triangle.getVertexC()) &&
            surfaceNormal.compareTo(triangle.getSurfaceNormal()));
    }

    public boolean isSimilar(Triangle triangle) {
        int counter = 0;
        Point[] firstTriangle = {this.getVertexA(), this.getVertexB(), this.getVertexC()};
        Point[] secondTriangle = {triangle.getVertexA(), triangle.getVertexB(), triangle.getVertexC()};
        for (int i = 0; i < firstTriangle.length; i++) {
            for (int j = 0; j < secondTriangle.length; j++) {
                if (firstTriangle[i].compareTo(secondTriangle[0])) {
                    counter++;
                }
            }
        }

        return (counter == 3);
    }

    public String toString() {
        if (this.getVertexA().compareTo(this.getVertexB()) ||
            this.getVertexA().compareTo(this.getVertexC()) ||
            this.getVertexB().compareTo(this.getVertexC()) ||
            this.getSurfaceNormal().compareTo(new UnitVector())) {
            return "[InvalidTriangle]";
        } else {
            return String.format("[A%s; B%s; C%s; N%s]", 
                this.getVertexA(), 
                this.getVertexB(), 
                this.getVertexC(), 
                this.getSurfaceNormal()
            );
        }
    }

}