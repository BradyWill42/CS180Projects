package Phase1;

import java.io.*;
import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PropertyPermission;

/**
 * SocialMediaProject - User interface
 * <p>
 * This interface contains information
 * on the methods used in the database class
 *
 * @author Claire Noggle, L23
 * @version Mar 28, 2024
 */

public interface User {
    public void deleteProfile(Profile profile);
    public void updateProfile(String username, String email);
    public void sendMessage(Profile sender, Profile receiver, String message);
    public String viewFriends(Profile profile);
    public String viewBlocked(Profile profile);
    public void sendPhotoMessage(Profile sender, Profile receiver, String photoFilename);
    public void assignMessages();
    public void assignPhotoMessages();
    public void readPhotoMessages() throws FileNotFoundException, IOException;
    public void readProfiles() throws FileNotFoundException, IOException;
    public void readMessages() throws FileNotFoundException, IOException;
    public void writePhotoMessages() throws FileNotFoundException;
    public void writeProfiles() throws FileNotFoundException;
    public void writeMessages() throws FileNotFoundException;
}