//imports
import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import static org.junit.Assert.*;

//Javadoc
/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version June 13, 2022
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.printf("Excellent - all local tests ran successfully.\n");
        } else {
            System.out.printf("Tests failed: %d.\n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString() + failure.getTrace());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Spring 2024</p>
     * 
     * @author Brady Williams, L23
     * 
     * @version Mar 1, 2024
     * 
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        // Each of the correct outputs
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

        @Test(timeout = 1000)
        public void testExpectedOne() {

            // Set the input
            String input = "6" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = WELCOME + System.lineSeparator() +
                    MAIN_MENU + System.lineSeparator() +
                    EXIT + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            GeoFactory.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Make sure you follow the flowchart and use the given strings for the result!",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedTwo() {

            // Set the input
            String input = "5" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

//

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

//

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

//

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

//

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

//

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +

                            "6" + System.lineSeparator();

//

            // Pair the input with the expected result
            String expected = WELCOME + System.lineSeparator() +
            MAIN_MENU + System.lineSeparator() +
            CUBE + System.lineSeparator() +
            FACE + System.lineSeparator() + 
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z1.000); B(x1.000, y-1.000, z1.000); C(x1.000, y0.000, z1.000); N<0.000i, 0.000j, 1.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z1.000); B(x0.000, y-1.000, z1.000); C(x1.000, y-1.000, z1.000); N<0.000i, 0.000j, 1.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x0.000, y0.000, z1.000); B(x1.000, y-1.000, z1.000); C(x1.000, y0.000, z1.000)] [A(x0.000, y0.000, z1.000); B(x0.000, y-1.000, z1.000); C(x1.000, y-1.000, z1.000)] N<0.000i, 0.000j, 1.000k>}") + System.lineSeparator() +
            
            FACE + System.lineSeparator() + 
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y-1.000, z1.000); B(x0.000, y0.000, z1.000); C(x0.000, y0.000, z0.000); N<-1.000i, 0.000j, 0.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y-1.000, z1.000); B(x0.000, y0.000, z0.000); C(x0.000, y-1.000, z0.000); N<-1.000i, 0.000j, 0.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x0.000, y-1.000, z1.000); B(x0.000, y0.000, z1.000); C(x0.000, y0.000, z0.000)] [A(x0.000, y-1.000, z1.000); B(x0.000, y0.000, z0.000); C(x0.000, y-1.000, z0.000)] N<-1.000i, 0.000j, 0.000k>}") + System.lineSeparator() +

            FACE + System.lineSeparator() + 
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x1.000, y-1.000, z1.000); B(x0.000, y-1.000, z1.000); C(x0.000, y-1.000, z0.000); N<0.000i, -1.000j, 0.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x1.000, y-1.000, z1.000); B(x0.000, y-1.000, z0.000); C(x1.000, y-1.000, z0.000); N<0.000i, -1.000j, 0.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x1.000, y-1.000, z1.000); B(x0.000, y-1.000, z1.000); C(x0.000, y-1.000, z0.000)] [A(x1.000, y-1.000, z1.000); B(x0.000, y-1.000, z0.000); C(x1.000, y-1.000, z0.000)] N<0.000i, -1.000j, 0.000k>}") + System.lineSeparator() +

            FACE + System.lineSeparator() + 
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x1.000, y-1.000, z0.000); B(x0.000, y0.000, z0.000); C(x1.000, y0.000, z0.000); N<0.000i, 0.000j, -1.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x1.000, y-1.000, z0.000); B(x0.000, y-1.000, z0.000); C(x0.000, y0.000, z0.000); N<0.000i, 0.000j, -1.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x1.000, y-1.000, z0.000); B(x0.000, y0.000, z0.000); C(x1.000, y0.000, z0.000)] [A(x1.000, y-1.000, z0.000); B(x0.000, y-1.000, z0.000); C(x0.000, y0.000, z0.000)] N<0.000i, 0.000j, -1.000k>}") + System.lineSeparator() +

            FACE + System.lineSeparator() + 
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x1.000, y0.000, z0.000); B(x1.000, y0.000, z1.000); C(x1.000, y-1.000, z1.000); N<1.000i, 0.000j, 0.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x1.000, y0.000, z0.000); B(x1.000, y-1.000, z1.000); C(x1.000, y-1.000, z0.000); N<1.000i, 0.000j, 0.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x1.000, y0.000, z0.000); B(x1.000, y0.000, z1.000); C(x1.000, y-1.000, z1.000)] [A(x1.000, y0.000, z0.000); B(x1.000, y-1.000, z1.000); C(x1.000, y-1.000, z0.000)] N<1.000i, 0.000j, 0.000k>}") + System.lineSeparator() +

            FACE + System.lineSeparator() + 
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z0.000); B(x0.000, y0.000, z1.000); C(x1.000, y0.000, z1.000); N<0.000i, 1.000j, 0.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z0.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z0.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z0.000); B(x1.000, y0.000, z1.000); C(x1.000, y0.000, z0.000); N<0.000i, 1.000j, 0.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x0.000, y0.000, z0.000); B(x0.000, y0.000, z1.000); C(x1.000, y0.000, z1.000)] [A(x0.000, y0.000, z0.000); B(x1.000, y0.000, z1.000); C(x1.000, y0.000, z0.000)] N<0.000i, 1.000j, 0.000k>}") + System.lineSeparator() +
            String.format(CUBE_END, "|C{F[A(x0.000, y0.000, z1.000); B(x1.000, y-1.000, z1.000); C(x1.000, y0.000, z1.000)] [A(x0.000, y0.000, z1.000); B(x0.000, y-1.000, z1.000); C(x1.000, y-1.000, z1.000)] N<0.000i, 0.000j, 1.000k>}{F[A(x0.000, y-1.000, z1.000); B(x0.000, y0.000, z1.000); C(x0.000, y0.000, z0.000)] [A(x0.000, y-1.000, z1.000); B(x0.000, y0.000, z0.000); C(x0.000, y-1.000, z0.000)] N<-1.000i, 0.000j, 0.000k>}{F[A(x1.000, y-1.000, z1.000); B(x0.000, y-1.000, z1.000); C(x0.000, y-1.000, z0.000)] [A(x1.000, y-1.000, z1.000); B(x0.000, y-1.000, z0.000); C(x1.000, y-1.000, z0.000)] N<0.000i, -1.000j, 0.000k>}{F[A(x1.000, y-1.000, z0.000); B(x0.000, y0.000, z0.000); C(x1.000, y0.000, z0.000)] [A(x1.000, y-1.000, z0.000); B(x0.000, y-1.000, z0.000); C(x0.000, y0.000, z0.000)] N<0.000i, 0.000j, -1.000k>}{F[A(x1.000, y0.000, z0.000); B(x1.000, y0.000, z1.000); C(x1.000, y-1.000, z1.000)] [A(x1.000, y0.000, z0.000); B(x1.000, y-1.000, z1.000); C(x1.000, y-1.000, z0.000)] N<1.000i, 0.000j, 0.000k>}{F[A(x0.000, y0.000, z0.000); B(x0.000, y0.000, z1.000); C(x1.000, y0.000, z1.000)] [A(x0.000, y0.000, z0.000); B(x1.000, y0.000, z1.000); C(x1.000, y0.000, z0.000)] N<0.000i, 1.000j, 0.000k>}|") + System.lineSeparator() +
            MAIN_MENU + System.lineSeparator() +
            EXIT + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            GeoFactory.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Make sure you follow the flowchart and use the given strings for the result!",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedThree() {

            // Set the input
            String input = "3" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "6" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = WELCOME + System.lineSeparator() +
            MAIN_MENU + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z1.000); B(x1.000, y-1.000, z1.000); C(x1.000, y0.000, z1.000); N<0.000i, 0.000j, 1.000k>]") + System.lineSeparator() +
            MAIN_MENU + System.lineSeparator() +
            EXIT + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            GeoFactory.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Make sure you follow the flowchart and use the given strings for the result!",
                    expected.trim(), output.trim());
        }


        @Test(timeout = 1000)
        public void testExpectedFour() {

            // Set the input
            String input = "4" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +



                            "0" + System.lineSeparator() +
                            "0" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "0" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "1" + System.lineSeparator() +
                            "-1" + System.lineSeparator() +
                            "1" + System.lineSeparator() +

                            "6" + System.lineSeparator();

            // Pair the input with the expected result0.0......AQW123WQASZaSQW23WSAZ
            String expected = WELCOME + System.lineSeparator() +
            MAIN_MENU + System.lineSeparator() +
            FACE + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y0.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z1.000); B(x1.000, y-1.000, z1.000); C(x1.000, y0.000, z1.000); N<0.000i, 0.000j, 1.000k>]") + System.lineSeparator() +
            TRIANGLE + System.lineSeparator() + 
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y0.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x0.000, y-1.000, z1.000)") + System.lineSeparator() +
            POINT + System.lineSeparator() + 
            String.format(POINT_END, "(x1.000, y-1.000, z1.000)") + System.lineSeparator() +
            String.format(TRIANGLE_END, "[A(x0.000, y0.000, z1.000); B(x0.000, y-1.000, z1.000); C(x1.000, y-1.000, z1.000); N<0.000i, 0.000j, 1.000k>]") + System.lineSeparator() +
            String.format(FACE_END, "{F[A(x0.000, y0.000, z1.000); B(x1.000, y-1.000, z1.000); C(x1.000, y0.000, z1.000)] [A(x0.000, y0.000, z1.000); B(x0.000, y-1.000, z1.000); C(x1.000, y-1.000, z1.000)] N<0.000i, 0.000j, 1.000k>}") + System.lineSeparator() +
            MAIN_MENU + System.lineSeparator() +
            EXIT + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            GeoFactory.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Make sure you follow the flowchart and use the given strings for the result!",
                    expected.trim(), output.trim());
        }


    }
}