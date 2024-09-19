//Imports
import java.util.Scanner;

//Javadoc
/**
 * Project 2 -- GeoFactory
 *
 * Interactive UI to create various Geometric
 * shapes using points and vectors
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 1, 2024
 *
 */
public class GeoFactory {
    public static final String WELCOME = "Hello! Welcome to the Geo Factory!";
    public static final String MAIN_MENU = "What type of geometry would you like to build?\n" +
            "1 - A 3D point\n2 - A 3D Unit Vector\n3 - A 3D Triangle\n4 - A 3D Face\n5 - A 3D Cube\n" +
            "6 - Exit";
    public static final String EXIT = "Thank you for using the Geo Factory!";
    public static final String INVALID = "Please make a valid menu selection";
    public static final String POINT = "To create a Point please enter 3 doubles X Y and Z" +
            " each on separate lines";
    public static final String POINT_END = "The Point is %s";
    public static final String VECTOR1 = "How would you like to create your Unit Vector?\n" +
            "1 - I know the IJK values\n2 - From two Points";
    public static final String VECTOR2 = "To create a vector please enter 3 doubles I J and K" +
            " each on separate lines";
    public static final String VECTOR3 = "To create a vector please follow the prompts to make 2 Points";
    public static final String VECTOR_END = "The Vector is %s";
    public static final String TRIANGLE = "To create a Triangle please follow the prompts to make 3 Points";
    public static final String TRIANGLE_END = "The Triangle is %s";
    public static final String FACE = "To create a Face please follow the prompts to make 2 Triangles";
    public static final String FACE_END = "The Face is %s";
    public static final String CUBE = "To create a Cube please follow the prompts to make 6 Faces";
    public static final String CUBE_END = "The Cube is %s";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(WELCOME);
        while (true) {
            System.out.println(MAIN_MENU);
            int selection = -1;
            if (s.hasNextInt()) {
                selection = s.nextInt();
                s.nextLine();
            } else {
                s.nextLine();
                System.out.println(INVALID);
                continue;
            }
            switch (selection) {
                case (1): {
                    pointMenu(s);
                    break;
                }
                case (2): {
                    vectorMenu(s);
                    break;
                }
                case (3): {
                    triangleMenu(s);
                    break;
                }
                case (4): {
                    faceMenu(s);
                    break;
                }
                case (5): {
                    cubeMenu(s);
                    break;
                }
                case (6): {
                    System.out.println(EXIT);
                    return;
                }
                default: {
                    System.out.println(INVALID);
                    break;
                }
            }
        }
    }

    private static Point pointMenu(Scanner s) {
        System.out.println(POINT);
        double x = s.nextDouble();
        s.nextLine();
        double y = s.nextDouble();
        s.nextLine();
        double z = s.nextDouble();
        s.nextLine();
        Point p = new Point(x, y, z);
        System.out.println(String.format(POINT_END, p));
        return p;
    }

    private static UnitVector vectorMenu(Scanner s) {
        while (true) {
            System.out.println(VECTOR1);
            int selection;
            if (s.hasNextInt()) {
                selection = s.nextInt();
                s.nextLine();
            } else {
                System.out.println(INVALID);
                s.nextLine();
                continue;
            }
            switch (selection) {
                case (1): {
                    System.out.println(VECTOR2);
                    double i = s.nextDouble();
                    s.nextLine();
                    double j = s.nextDouble();
                    s.nextLine();
                    double k = s.nextDouble();
                    s.nextLine();
                    UnitVector vector = new UnitVector(i, j, k);
                    System.out.println(String.format(VECTOR_END, vector));
                    return vector;
                }
                case (2): {
                    System.out.println(VECTOR3);
                    Point one = pointMenu(s);
                    Point two = pointMenu(s);
                    UnitVector vector = new UnitVector(one, two);
                    System.out.println(String.format(VECTOR_END, vector));
                    return vector;
                }
                default: {
                    System.out.println(INVALID);
                }
            }
        }
    }

    private static Triangle triangleMenu(Scanner s) {
        System.out.println(TRIANGLE);
        Point a = pointMenu(s);
        Point b = pointMenu(s);
        Point c = pointMenu(s);
        Triangle t = new Triangle(a, b, c);
        System.out.println(String.format(TRIANGLE_END, t));
        return t;
    }

    private static Face faceMenu(Scanner s) {
        System.out.println(FACE);
        Triangle one = triangleMenu(s);
        Triangle two = triangleMenu(s);
        Face f = new Face(one, two);
        System.out.println(String.format(FACE_END, f));
        return f;
    }
    
    private static Cube cubeMenu(Scanner s) {
        System.out.println(CUBE);
        Face one = faceMenu(s);
        Face two = faceMenu(s);
        Face three = faceMenu(s);
        Face four = faceMenu(s);
        Face five = faceMenu(s);
        Face six = faceMenu(s);
        Cube c = new Cube(one, two, three, four, five, six);
        System.out.println(String.format(CUBE_END, c));
        return c;
    }
}
