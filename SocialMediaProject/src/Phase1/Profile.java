package Phase1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;


//Javadoc

/**
 * SocialMediaProject - Profile
 * <p>
 * This class contains the
 * information regarding a
 * profile
 *
 * @author Anuraag Kolli, L23
 * @version Mar 28, 2024
 */
public class Profile implements UserProfile {
    private String username; //username of profile
    private String password; //password of profile
    private String email; //email of profile
    private String profilePicture; //file location of image
    private ArrayList<Message> messages;
    private ArrayList<String> friends;
    private ArrayList<String> blocked;
    private boolean friendsOnly;

    /**
     * This Profile constructor takes the
     * information to create a new basic profile
     * without messages.
     *
     * @param username - String
     * @param email    - String
     * @param friends  - String[]
     * @param blocked  - String[]
     */

    public Profile(String username, String password, String email, ArrayList<String> friends, ArrayList<String> blocked) {
        this.username = username;
        this.password = password;
        this.email = email;

        if (friends == null) {
            this.friends = new ArrayList<String>();
        } else {
            this.friends = friends;
        }

        if (blocked == null) {
            this.blocked = new ArrayList<String>();
        } else {
            this.blocked = blocked;
        }

        this.messages = new ArrayList<>();

        this.friendsOnly = false;
    }

    /**
     * This Profile constructor takes in no parameters
     * and creates an empty object.
     */
    public Profile() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.friends = null;
        this.blocked = null;
        this.friendsOnly = false;
    }

    /**
     * Retrieves the username of the profile.
     *
     * @return The username of the profile.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the profile.
     *
     * @return The password of the profile.
     */
    public String getPassword() {
        return password;
    }

    public void setFriendsOnly(boolean friendsOnly) {
        this.friendsOnly = friendsOnly;
    }

    public boolean isFriendsOnly() {
        return friendsOnly;
    }

    /**
     * Sets the username of the profile.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the profile.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the email of the profile.
     *
     * @return The email of the profile.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the profile.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the file location of the profile picture.
     *
     * @return The file location of the profile picture.
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the file location of the profile picture.
     *
     * @param profilePicture The file location of the profile picture to set.
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Retrieves the array of messages associated with the profile.
     *
     * @return The array of messages associated with the profile.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the array of messages associated with the profile.
     *
     * @param messages The array of messages to set.
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /**
     * Retrieves the array of profiles that are friends with this profile.
     *
     * @return The array of profiles that are friends with this profile.
     */
    public ArrayList<String> getFriends() {
        return friends;
    }

    /**
     * Sets the array of profiles that are friends with this profile.
     *
     * @param friends The array of profiles to set.
     */
    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    /**
     * Adds a friend to the array of friends
     *
     * @param friendUsername the username of the friend to be added
     */

    public void addFriend(String friendUsername) {
        this.friends.add(friendUsername);
    }

    /**
     * Removes a friend from the array of friends
     *
     * @param friendUsername the username of the friend to be removed
     */

    public void removeFriend(String friendUsername) {
        this.friends.remove(friendUsername);
    }

    /**
     * Retrieves the array of profiles that are blocked by this profile.
     *
     * @return The array of profiles that are blocked by this profile.
     */
    public ArrayList<String> getBlocked() {
        return blocked;
    }

    /**
     * Sets the array of profiles that are blocked by this profile.
     *
     * @param blocked The array of profiles to set.
     */
    public void setBlocked(ArrayList<String> blocked) {
        this.blocked = blocked;
    }

    /**
     * Adds string username of blocked profile
     * to arraylist
     *
     * @param blockedUsername The array of profiles to set.
     */
    public void blockUser(String blockedUsername) {
        blocked.add(blockedUsername);
    }

    /**
     * Removes string username of blocked profile
     * to arraylist
     *
     * @param blockedUsername The username of the profile to be unblocked
     */
    public void unblockUser(String blockedUsername) {
        blocked.remove(blockedUsername);
    }

    /**
     * Add a message that to the profile that
     * the user has sent
     *
     * @param message The message to add
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Delete a message from the profile that
     * the user has sent
     *
     * @param message The message to delete
     */
    public void deleteMessage(Message message) {
        messages.remove(message);
    }


    /**
     * This sorts the messages by time send incase a message
     * was added that's not in the correct order
     */
    public void sortMessages() {
        messages.sort(Comparator.comparing(Message::getTimeSent));
    }

    /**
     * Checks if 2 profiles are equal to eachother
     *
     * @return boolean if the username and email are equal
     */
    @Override
    public boolean equals(Profile profile) {
        return (username.equals(profile.getUsername()) &&
                email.equals(profile.getEmail()));
    }

    public String toString() {
        String returnString = username + "," + password + "," + email + ",";
        String friendString = "";
        for (String friend : friends) {
            friendString += friend + "##";
        }
        String blockedString = "";
        for (String blockedUser : blocked) {
            blockedString += blockedUser + "##";

        }

        returnString = returnString + friendString + "," + blockedString;

        return returnString;
    }
}