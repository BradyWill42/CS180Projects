//Javadoc
/**
 * Project 2 -- Face
 *
 * Create Face of geometric figure from 
 * Two connected triangles
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 1, 2024
 *
 */
public class Face {
    private Triangle[] mesh;
    private UnitVector surfaceNormal;

    public Face(Triangle one, Triangle two) {
        int counter = 0;
        Point[] firstTriangle = {one.getVertexA(), one.getVertexB(), one.getVertexC()};
        Point[] secondTriangle = {two.getVertexA(), two.getVertexB(), two.getVertexC()};
        for (int i = 0; i < firstTriangle.length; i++) {
            for (int j = 0; j < secondTriangle.length; j++) {
                if (firstTriangle[i].compareTo(secondTriangle[j])) {
                    counter++;
                }
            }
        }

        if (counter == 2 && 
            one.getSurfaceNormal()
            .compareTo(two.getSurfaceNormal())) {
            mesh = new Triangle[] {one, two};
            surfaceNormal = one.getSurfaceNormal();
        } else {
            mesh = new Triangle[] {
                new Triangle(), 
                new Triangle()
            };
            surfaceNormal = new UnitVector();
        }       
    }

    public Face() {
        mesh = new Triangle[] {
            new Triangle(),
            new Triangle()
        };
        surfaceNormal = new UnitVector();
    }

    public Triangle[] getMesh() {
        return mesh;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public void flipSurfaceNormal() {
        mesh[0].flipSurfaceNormal();
        mesh[1].flipSurfaceNormal();

        surfaceNormal.flipVector();
    }

    public boolean compareTo(Face face) {
        int counter = 0;
        for (int i = 0; i < this.mesh.length; i++) {
            if (mesh[i].getVertexA().compareTo(face.getMesh()[i].getVertexA()) &&
                mesh[i].getVertexB().compareTo(face.getMesh()[i].getVertexB()) &&
                mesh[i].getVertexC().compareTo(face.getMesh()[i].getVertexC()) &&
                this.getSurfaceNormal().compareTo(face.getSurfaceNormal())) {
                counter++;
            }
        }

        return (counter == 2);
        
    }

    public boolean isSimilar(Face face) {
        return ( ( (this.getMesh()[0].compareTo(face.getMesh()[0]) && 
            this.getMesh()[1].compareTo(face.getMesh()[1])) ||
            (this.getMesh()[1].compareTo(face.getMesh()[0]) &&
            this.getMesh()[0].compareTo(face.getMesh()[1])) ) ||
            this.getSurfaceNormal().compareTo(face.getSurfaceNormal()));
    }

    public String toString() {
        if (mesh[0].toString().equals("[InvalidTriangle]") ||
            mesh[1].toString().equals("[InvalidTriangle]")) {
            return "{InvalidFace}";
        } else {
            return String.format(
                "{F[A%s; B%s; C%s] [A%s; B%s; C%s] N%s}", 
                this.mesh[0].getVertexA(),
                this.mesh[0].getVertexB(),
                this.mesh[0].getVertexC(),
                this.mesh[1].getVertexA(),
                this.mesh[1].getVertexB(),
                this.mesh[1].getVertexC(),
                this.getSurfaceNormal()
            );
        }
    }




}