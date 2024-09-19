package Phase2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Phase1.Profile;
import Phase1.UserDatabase;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.lang.reflect.Modifier;

/**
 * A framework to run public test cases for PJ3.
 *
 * <p>Purdue University -- CS18000 -- Spring 2021</p>
 *
 * @author J Morris Purdue CS
 * @version Feb 20, 2024
 */

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

        @Test(timeout = 1000)
        public void testReadingWritingDeletingProfiles() throws FileNotFoundException, IOException {

            UserDatabase db = new UserDatabase("profiles1.txt", "messages1.txt", "photos1.txt");

            db.writeProfiles();
            db.writeMessages();
            db.writePhotoMessages();

            db.readMessages();
            db.readPhotoMessages();
            db.readProfiles();

            Profile brady = new Profile("bradywilliams", "password", "will2828@purdue.edu", null, null);
            Profile john = new Profile("johndoe", "password", "johndoe@purdue.edu", null, null);
            Profile jane = new Profile("janedoe", "password", "janedoe@purdue.edu", null, null);
            Profile phil = new Profile("philcollins", "password", "philcollins@purdue.edu", null, null);

            brady.addFriend("johndoe");
            brady.addFriend("janedoe");
            brady.blockUser("philcollins");

            john.addFriend("bradywilliams");
            john.blockUser("janedoe");
            john.blockUser("philcollins");

            phil.blockUser("bradywilliams");
            phil.blockUser("janedoe");
            phil.blockUser("johndoe");


            db.addProfile(brady);
            db.addProfile(john);
            db.addProfile(jane);
            db.addProfile(phil);

            db.sendMessage(brady, john, "Hello John, Its brady");

            db.writeProfiles();
            db.writeMessages();
            db.writePhotoMessages();

            String expectedOutput = "bradywilliams,will2828@purdue.edu,johndoe##janedoe,philcollins\n" +
                    "johndoe,johndoe@purdue.edu,bradywilliams,janedoe##philcollins\n" +
                    "janedoe,janedoe@purdue.edu,,\n" +
                    "philcollins,philcollins@purdue.edu,,bradywilliams##janedoe##johndoe";


            String actualOutput = "";

            try (BufferedReader reader = new BufferedReader(new FileReader("profiles1.txt"))) {
                String in = "";
                while ((in = reader.readLine()) != null) {
                    actualOutput += in + "\n";
                }
            } catch (IOException a) {
                Assert.assertTrue("An IO exception was encountered while reading profiles1.txt", false);
            } catch (Exception e) {
                Assert.assertTrue("An unknown exception was encountered while reading profiles1.txt", false);
            }

            Assert.assertEquals("Make sure your profiles are writing to the output file correctly",
                    expectedOutput.trim(), actualOutput.trim());

            db.deleteProfile(brady);
            db.deleteProfile(john);
            db.deleteProfile(jane);
            db.deleteProfile(phil);

            db.writeProfiles();

            expectedOutput = "";

            actualOutput = "";

            try (BufferedReader reader = new BufferedReader(new FileReader("profiles1.txt"))) {
                String in = "";
                while ((in = reader.readLine()) != null) {
                    actualOutput += in + "\n";
                }
            } catch (IOException a) {
                Assert.assertTrue("An IO exception was encountered while reading profiles1.txt", false);
            } catch (Exception e) {
                Assert.assertTrue("An unknown exception was encountered while reading profiles1.txt", false);
            }

            Assert.assertEquals("Make sure your profiles are deleting from the output file correctly",
                    expectedOutput.trim(), actualOutput.trim());

        }

        @Test(timeout = 1000)
        public void testUpdatingProfile() throws FileNotFoundException, IOException {

            UserDatabase db = new UserDatabase("profiles2.txt", "messages2.txt", "photos2.txt");

            Profile brady = new Profile("bradywilliams", "password", "will2828@purdue.edu", null, null);
            Profile john = new Profile("johndoe", "password", "johndoe@purdue.edu", null, null);
            Profile jane = new Profile("janedoe", "password", "janedoe@purdue.edu", null, null);
            Profile phil = new Profile("philcollins", "password", "philcollins@purdue.edu", null, null);

            db.addProfile(brady);
            db.addProfile(john);
            db.addProfile(jane);
            db.addProfile(phil);

            db.writeProfiles();
            db.writeMessages();
            db.writePhotoMessages();

            db.updateProfile("bradywilliams", "bradymonster8@gmail.com");

            db.writeProfiles();
            db.writeMessages();
            db.writePhotoMessages();

            db.readMessages();
            db.sendMessage(db.findProfile("johndoe"), db.findProfile("bradywilliams"), "Hello Brady, whats your favorite color");
            db.writeMessages();

            String expectedOutput = "bradywilliams,bradymonster8@gmail.com,,\n" +
                    "johndoe,johndoe@purdue.edu,,\n" +
                    "janedoe,janedoe@purdue.edu,,\n" +
                    "philcollins,philcollins@purdue.edu,,\r\n";

            String actualOutput = "";

            try (BufferedReader reader = new BufferedReader(new FileReader("profiles2.txt"))) {
                String in = "";
                while ((in = reader.readLine()) != null) {
                    actualOutput += in + "\n";
                }
            } catch (IOException a) {
                Assert.assertTrue("An IO exception was encountered while reading profiles2.txt", false);
            } catch (Exception e) {
                Assert.assertTrue("An unknown exception was encountered while reading profiles2.txt", false);
            }

            Assert.assertEquals("Make sure your profiles are writing to the output file correctly",
                    expectedOutput.trim(), actualOutput.trim());

        }

        @Test(timeout = 1000)
        public void testMessaging() throws FileNotFoundException, IOException {

            UserDatabase db = new UserDatabase("profiles3.txt", "messages3.txt", "photos3.txt");

            Profile brady = new Profile("bradywilliams", "password", "will2828@purdue.edu", null, null);
            Profile john = new Profile("johndoe", "password", "johndoe@purdue.edu", null, null);

            db.addProfile(brady);
            db.addProfile(john);

            db.writeProfiles();
            db.writeMessages();
            db.writePhotoMessages();

            db.sendMessage(db.findProfile("bradywilliams"), db.findProfile("johndoe"), "Hello John, whats your favorite color");
            db.sendPhotoMessage(db.findProfile("johndoe"), db.findProfile("bradywilliams"), "pic.png");

            db.writeMessages();
            db.writePhotoMessages();

            db.readMessages();
            db.readPhotoMessages();
            db.readProfiles();

            db.writeProfiles();
            db.writeMessages();
            db.writePhotoMessages();


            String expectedOutput = "pic.png##johndoe##bradywilliams##";

            String actualOutput = "";

            try (BufferedReader reader = new BufferedReader(new FileReader("photos3.txt"))) {
                String in = "";
                while ((in = reader.readLine()) != null) {
                    actualOutput += in + "\n";
                }
            } catch (IOException a) {
                Assert.assertTrue("An IO exception was encountered while reading photos3.txt", false);
            } catch (Exception e) {
                Assert.assertTrue("An unknown exception was encountered while reading photos3.txt", false);
            }

            Assert.assertTrue("Make sure your messages are writing to the output file correctly",
                    actualOutput.contains(expectedOutput));



            expectedOutput = "Hello John, whats your favorite color##bradywilliams##johndoe##";

            actualOutput = "";

            try (BufferedReader reader = new BufferedReader(new FileReader("messages3.txt"))) {
                String in = "";
                while ((in = reader.readLine()) != null) {
                    actualOutput += in + "\n";
                }
            } catch (IOException a) {
                Assert.assertTrue("An IO exception was encountered while reading messages3.txt", false);
            } catch (Exception e) {
                Assert.assertTrue("An unknown exception was encountered while reading messages3.txt", false);
            }

            Assert.assertTrue("Make sure your messages are writing to the output file correctly",
                    actualOutput.contains(expectedOutput));
        }

    }

}