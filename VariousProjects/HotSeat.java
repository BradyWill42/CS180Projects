import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.io.*;




public class HotSeat {

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot slave = new Robot();
        boolean located;


        do {
            slave.keyPress(KeyEvent.VK_CONTROL);
            slave.keyPress(KeyEvent.VK_R);
            slave.keyRelease(KeyEvent.VK_R);
            slave.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(10000);
        } while (true);

        

    }
}