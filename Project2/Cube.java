//Imports
import java.util.ArrayList;

//Javadoc
/**
 * Project 2 -- Cube
 *
 * Create cube from 6 distict faces
 * containing 8 unique 3-dimentional
 * points.
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 1, 2024
 *
 */
public class Cube {
    private Face[] mesh;

    public Cube(
        Face one, Face two, Face three, 
        Face four, Face five, Face six
    ) {
        this.mesh = new Face[] {
            one,
            two,
            three,
            four,
            five,
            six
        };
        int vectorCounter = 0;
        int borderCounter = 0;
        int faceCheck = 0;

        // System.out.println(mesh.length);

        for (int i = 0; i < mesh.length; i++) {
            ArrayList<Point> checkPoints = new ArrayList<Point>();

            checkPoints.add(mesh[i].getMesh()[0].getVertexA());
            checkPoints.add(mesh[i].getMesh()[0].getVertexB());
            checkPoints.add(mesh[i].getMesh()[0].getVertexC());
            checkPoints.add(mesh[i].getMesh()[1].getVertexA());
            checkPoints.add(mesh[i].getMesh()[1].getVertexB());
            checkPoints.add(mesh[i].getMesh()[1].getVertexC());

            for (int k = 0; k < checkPoints.size(); k++) {
                for (int h = 0; h < checkPoints.size(); h++) {
                    if (k == h) {
                        continue;
                    }
                    if (checkPoints.get(k).compareTo(checkPoints.get(h))) {
                        checkPoints.remove(k);
                    }
                }
            }

            for (int j = 0; j < mesh.length; j++) {

                if (j == i) {
                    continue;
                }

                ArrayList<Point> currentPoints = new ArrayList<Point>();

                currentPoints.add(mesh[j].getMesh()[0].getVertexA());
                currentPoints.add(mesh[j].getMesh()[0].getVertexB());
                currentPoints.add(mesh[j].getMesh()[0].getVertexC());
                currentPoints.add(mesh[j].getMesh()[1].getVertexA());
                currentPoints.add(mesh[j].getMesh()[1].getVertexB());
                currentPoints.add(mesh[j].getMesh()[1].getVertexC());

                for (int k = 0; k < currentPoints.size(); k++) {
                    for (int h = 0; h < currentPoints.size(); h++) {
                        if (k == h) {
                            continue;
                        }
                        if (currentPoints.get(k).compareTo(currentPoints.get(h))) {
                            currentPoints.remove(k);
                        }
                    }
                }

                // System.out.printf("Loop 1: %d, Loop 2: %d, CurrentArray: %s\n", i, j, currentPoints);
                // System.out.printf("Loop 1: %d, Loop 2: %d, CheckArray: %s\n", i, j, checkPoints);

                if (checkPoints.size() == 4 && currentPoints.size() == 4) {
                    for (int m = 0; m < 4; m++) {
                        for (int n = 0; n < 4; n++) {
                            if (n == m) {
                                continue;
                            }
                            for (int p = 0; p < 4; p++) {
                                for (int t = 0; t < 4; t++) {
                                    if (p == t) {
                                        continue;
                                    }
                                    UnitVector check = new UnitVector(checkPoints.get(m), checkPoints.get(n));
                                    UnitVector current = new UnitVector(currentPoints.get(p), currentPoints.get(t));
                                    if (check.compareTo(current)) {
                                        vectorCounter++;
                                        //System.out.println("One Added.");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    break;
                }

                //System.out.println("vector counter: " + vectorCounter);

                if (vectorCounter == 8) {
                    borderCounter++;
                }

                vectorCounter = 0;
            }

            //System.out.println("border counter: " + borderCounter);

            if (borderCounter == 4)  {
                faceCheck++;
            }

            borderCounter = 0;

        }

        //System.out.println("face check: " + faceCheck);

        if (faceCheck == 6) {
            mesh[0] = one;
            mesh[1] = two;
            mesh[2] = three;
            mesh[3] = four;
            mesh[4] = five;
            mesh[5] = six;
        } else {
            mesh[0] = new Face();
            mesh[1] = new Face();
            mesh[2] = new Face();
            mesh[3] = new Face();
            mesh[4] = new Face();
            mesh[5] = new Face();
        }
    }

    public Cube() {
        mesh[0] = new Face();
        mesh[1] = new Face();
        mesh[2] = new Face();
        mesh[3] = new Face();
        mesh[4] = new Face();
        mesh[5] = new Face();
    }

    public Face[] getMesh() {
        return mesh;
    }

    public String toString() {
        for (int i = 0; i < 6; i++) {
            if (mesh[i].toString().equals("{InvalidFace}")) {
                return "|InvalidCube|";
            }
        }

        return String.format("|C%s%s%s%s%s%s|", 
            mesh[0],
            mesh[1],
            mesh[2],
            mesh[3],
            mesh[4],
            mesh[5]
        );
    }
}