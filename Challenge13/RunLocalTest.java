
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunLocalTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {

        private LinkedCharList list;

        private void populateList(char[] data) {
            for (char c : data) {
                list.addChar(c);
            }
        }


        @Test
        public void testAddChar() {
            list = new LinkedCharList();
            list.addChar('x');
            assertEquals('x', list.getCharAt(0));
        }

        @Test
        public void testInsertCharAt() {
            list = new LinkedCharList();
            list.addChar('a');
            list.addChar('c');
            list.insertCharAt('b', 1);
            assertEquals('b', list.getCharAt(1));
        }

        @Test
        public void testGetCharAt() {
            list = new LinkedCharList();
            list.addChar('a');
            assertEquals('a', list.getCharAt(0));
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetCharAtOutOfBounds() {
            list = new LinkedCharList();
            char[] chars = {'a', 'b', 'a', 'c', 'b', 'a', 'b'};
            list.constructCharList(chars);
            list.getCharAt(7);
        }

        @Test
        public void testSplitWithRegexB_ABCBD() {
            list = new LinkedCharList();
            populateList(new char[] {'A', 'B', 'C', 'B', 'D'});
            LinkedCharList[] result = list.splitLinkedCharList('B');
            assertEquals("Expected three split lists", 3, result.length);
            assertEquals('A', result[0].getCharAt(0));
            assertEquals('C', result[1].getCharAt(0));
            assertEquals('D', result[2].getCharAt(0));
        }

        @Test
        public void testSplitWithRegexB_BBBBB() {
            list = new LinkedCharList();
            populateList(new char[] {'B', 'B', 'B', 'B', 'B'});
            LinkedCharList[] result = list.splitLinkedCharList('B');
            assertNull("Expected no lists to be initialized", result);
        }

        @Test
        public void testSplitWithRegexB_BAAAA() {
            list = new LinkedCharList();
            populateList(new char[] {'B', 'A', 'A', 'A', 'A'});
            LinkedCharList[] result = list.splitLinkedCharList('B');
            assertEquals("Expected two lists, first empty", 2, result.length);
            assertEquals('A', result[1].getCharAt(0));
            assertEquals('A', result[1].getCharAt(3));
        }

        @Test
        public void testSplitWithRegexC_ABCCCE() {
            list = new LinkedCharList();
            populateList(new char[] {'A', 'B', 'C', 'C', 'E'});
            LinkedCharList[] result = list.splitLinkedCharList('C');
            assertEquals("Expected three split lists", 3, result.length);
            assertEquals('A', result[0].getCharAt(0));
            assertEquals('B', result[0].getCharAt(1));
            assertEquals('E', result[2].getCharAt(0));
        }
    }
}