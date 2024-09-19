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
 * SocialMediaProject - UserDatabase
 * <p>
 * This class contains the methods
 * regarding the other two classes
 *
 * @author Anuraag Kolli, L23
 * @version Mar 28, 2024
 */


public class UserDatabase implements User {
    private String profileFilename;
    private String messagesFilename;
    private String photosFilename;
    private ArrayList<Profile> users;
    private ArrayList<Message> allMessages;
    private ArrayList<PhotoMessage> photoMessages;

    public ArrayList<Profile> getUsers() {
        return users;
    }

    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public UserDatabase(String profileFilename, String messagesFilename, String photosFilename) {
        this.profileFilename = profileFilename;
        this.messagesFilename = messagesFilename;
        this.photosFilename = photosFilename;
        this.users = new ArrayList<>();
        this.allMessages = new ArrayList<>();
        this.photoMessages = new ArrayList<>();
    }

    /**
     * Deletes profiles from users array.
     */
    public void deleteProfile(Profile profile) {
        users.remove(profile);
    }

    /**
     * Adds profile to users array.
     */
    public void addProfile(Profile profile) {
        users.add(profile);
    }

    /**
     * Updates the profile by changing their email
     * if usernames are equal.
     */
    public void updateProfile(String username, String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setUsername(username);
                users.get(i).setEmail(email);
            }
        }
    }

    /**
     * Creates a new message object and sends it to the receiver.
     */
    public void sendMessage(Profile sender, Profile receiver, String message) {
        Message newMessage = new Message(message, LocalDateTime.now(),
                sender.getUsername(), receiver.getUsername());
        allMessages.add(newMessage);
        sender.addMessage(newMessage);
        receiver.addMessage(newMessage);
    }

    /**
     * View Message method that just prints all the messages.
     */
    public String viewMessages(String profileUsername) {
        Profile profile = new Profile();
        String result = "Messages:";
        for (Profile user : users) {
            if (user.getUsername().equals(profileUsername)) {
                profile = user;
            }
        }
        for (Message message : allMessages) {
            result += "\n" + message.toString();
        }
        return result;
    }

    /**
     * Creates a new message object and sends it to the receiver.
     */
    public void deleteMessage(Profile sender, String receiverUsername, String message) {
        Message messageToDelete = new Message(message, sender.getUsername(), receiverUsername);
        allMessages.remove(messageToDelete);
        sender.deleteMessage(messageToDelete);
    }

    /**
     * goes through all the friends of the profile passed through
     * and returns the friends.
     *f
     * @return String of all friends
     */
    public String viewFriends(Profile profile) {
        String string = null;
        for (String name : profile.getFriends()) {
            string += "name" + "\n";
        }
        if (string == null) {
            return "No friends!";
        } else {
            return string;
        }
    }

    /**
     * goes through all the blocked users of the profile passed through
     * and returns the blocked users.
     *
     * @return String of all blocked users
     */
    public String viewBlocked(Profile profile) {
        String string = null;
        for (String name : profile.getBlocked()) {
            string += "name" + "\n";
        }
        if (string == null) {
            return "No blocked users!";
        } else {
            return string;
        }
    }

    public void blockUser(Profile profile, String usernameToBlock) {
        ArrayList<String> newBlocked = profile.getBlocked();
        newBlocked.add(usernameToBlock);
        for (Profile user : users) {
            if (user.getUsername().equals(profile.getUsername())) {
                user.setBlocked(newBlocked);
            }
        }
    }



    /**
     * Sends a photo message that
     * creates a new photo message object and stores it in
     * the messages array.
     */
    public void sendPhotoMessage(Profile sender, Profile receiver, String photoFilename) {
        PhotoMessage photoMessage = new PhotoMessage(photoFilename, LocalDateTime.now(),
                sender.getUsername(), receiver.getUsername());
        photoMessages.add(photoMessage);
        sender.addMessage(photoMessage);
    }

    /**
     * Assigns the new messages when read in to their appropriate
     * senders and adds to its array.
     */
    public void assignMessages() {
        for (Message message : allMessages) {
            String senderUsername = message.getSenderUsername();

            for (Profile profile : users) {
                if (profile.getUsername().equals(senderUsername)) {
                    profile.addMessage(message);
                }
            }
        }
    }

    /**
     * Assigns the new photo messages when read in to their appropriate
     * senders and adds to its array.
     */
    public void assignPhotoMessages() {
        for (PhotoMessage photoMessage : photoMessages) {
            String senderUsername = photoMessage.getSenderUsername();

            for (Profile profile : users) {
                if (profile.getUsername().equals(senderUsername)) {
                    profile.addMessage(photoMessage);
                }
            }
        }
    }

    /**
     * Reads files that contains all photo Messages data
     * and stores it in the array.
     */
    public void readPhotoMessages() throws FileNotFoundException, IOException {
        photoMessages = new ArrayList<PhotoMessage>();
        File f = new File(photosFilename);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(photosFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    String[] parts = line.split("##");
                    String photoFilename = parts[0];
                    String senderUsername = parts[1];
                    String receiverUsername = parts[2];
                    LocalDateTime timeSent = LocalDateTime.parse(parts[3]);
                    photoMessages.add(new PhotoMessage(photoFilename, timeSent, senderUsername, receiverUsername));
                } else {
                    continue;
                }
                
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads files that contains all user data
     * and stores it in their variables.
     */
    public void readProfiles() throws FileNotFoundException, IOException {
        users = new ArrayList<Profile>();
        File f = new File(profileFilename);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(profileFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    String[] lineList = line.split(",");
    
                    String username = lineList[0];
                    String password = lineList[1];
                    String email = lineList[2];

                    
                    
    
    
                    String[] readFriends, readBlocked;
                    ArrayList<String> friends = new ArrayList<String>(), blocked = new ArrayList<String>();
    
                    if (lineList.length > 3) {
                        if (lineList[3] != null && !lineList[3].equals("") && lineList[3].contains("##")) {
                            readFriends = lineList[3].split("##");
                            friends = new ArrayList<String>(Arrays.asList(readFriends));
                        } else {
                            String friend = lineList[3];
                            friends = new ArrayList<String>();
                            friends.add(friend);
                            
                        }
                    }
                    if (lineList.length > 4) {
                        if (lineList[4] != null && !lineList[4].equals("") && lineList[4].contains("##")) {
                            readBlocked = lineList[4].split("##");
                            blocked = new ArrayList<String>(Arrays.asList(readBlocked));
                        } else {
                            String block = lineList[4];
                            blocked = new ArrayList<String>();
                            blocked.add(block);
                        }
                    }
                    this.users.add(new Profile(username, password, email, friends, blocked));
                } else {
                    continue;
                }
                
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads files that contains all messages data
     * and stores it in their variables.
     */
    public void readMessages() throws FileNotFoundException, IOException {
        allMessages = new ArrayList<Message>();
        File f = new File(messagesFilename);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(messagesFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    String[] parts = line.split("##");
                    String message = parts[0];
                    String senderUsername = parts[1];
                    String receiverUsername = parts[2];
                    LocalDateTime timeSent = LocalDateTime.parse(parts[3]);
                    allMessages.add(new Message(message, timeSent, senderUsername, receiverUsername));
                } else {
                    continue;
                }
                
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Writes files that contains all photo message data
     */
    public void writePhotoMessages() throws FileNotFoundException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(photosFilename, false))) {
            for (PhotoMessage photoMessage : photoMessages) {
                bw.write(photoMessage.toString());
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes files that contains all profile data.
     */
    public void writeProfiles() throws FileNotFoundException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(profileFilename, false))) {
            for (Profile profile : users) {
                bw.write(profile.toString());
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes files that contains all message data.
     */
    public void writeMessages() throws FileNotFoundException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(messagesFilename, false))) {
            for (Message message : allMessages) {
                bw.write(message.toString());
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns profile in users that matches username
     *
     * @return Profile that matches username in users arraylist
     */
    public Profile findProfile(String username) {
        for (Profile name : users) {
            if (name.getUsername().equals(username)) {
                return name;
            }
        }
        return null;
    }


    /**
     * Returns profile in users that matches username
     *
     * @return Profile that matches username in users arraylist
     */
    public Profile loginProfile(String username, String password) {
        for (Profile name : users) {
            if (name.getUsername().equals(username) && name.getPassword().equals(password)) {
                return name;
            }
        }
        return null;
    }


}