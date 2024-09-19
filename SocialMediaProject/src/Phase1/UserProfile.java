package Phase1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * SocialMediaProject - Profile interface
 * <p>
 * This interface contains information
 * on the methods used in the
 * profile class
 *
 * @author Claire Noggle, L23
 * @version Mar 28, 2024
 */

public interface UserProfile {
    public String getUsername();

    public void setFriendsOnly(boolean friendsOnly);

    public boolean isFriendsOnly();

    public void setUsername(String username);

    public String getEmail();

    public void setEmail(String email);

    public String getProfilePicture();

    public void setProfilePicture(String profilePicture);

    public ArrayList<Message> getMessages();

    public void setMessages(ArrayList<Message> messages);

    public ArrayList<String> getFriends();

    public void setFriends(ArrayList<String> friends);

    public void addFriend(String username);

    public ArrayList<String> getBlocked();

    public void setBlocked(ArrayList<String> blocked);

    public void blockUser(String username);

    public void addMessage(Message message);

    public void sortMessages();

    public boolean equals(Profile profile);
}
