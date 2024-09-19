package Phase1;

import java.sql.Time;
import java.time.LocalDateTime;

//javadoc

/**
 * SocialMediaProject - Message
 * <p>
 * This class contains the
 * information regarding a message
 * that has been created
 *
 * @author Brady Williams, L23
 * @version Mar 28, 2024
 */
public class Message implements Messaging {
    private String message; //message content
    private LocalDateTime timeSent; //time message was sent
    private String senderUsername; //sender of the message
    private String receiverUsername; //receiver of the message
    private String image; //file location of image in message

    /**
     * This Message constructor takes the
     * information needed to create a
     * message and send it, without image
     *
     * @param message          - String
     * @param timeSent         - Time
     * @param senderUsername   - String
     * @param receiverUsername - String
     */
    public Message(String message, LocalDateTime timeSent, String senderUsername, String receiverUsername) {
        this.message = message;
        this.timeSent = timeSent;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    /**
     * This Message constructor will be
     * used in the extended PhotoMessage class
     *
     * @param timeSent         - Time
     * @param sender           - Profile
     * @param receiverUsername - Profile
     */
    public Message(LocalDateTime timeSent, String sender, String receiverUsername) {
        this.timeSent = timeSent;
        this.senderUsername = sender;
        this.receiverUsername = receiverUsername;
    }

    /**
     * This Message constructor takes the
     * information needed to create a
     * message and send it, without image
     * or time
     *
     * @param message          - String
     * @param senderUsername   - String
     * @param receiverUsername - String
     */
    public Message(String message, String senderUsername, String receiverUsername) {
        this.message = message;
        this.timeSent = null;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    /**
     * This Message constructor takes no
     * parameters and sets message to null,
     * timeSent to null, sender to null,
     * receiver to null, and image to null
     */
    public Message() {
        this.message = null;
        this.timeSent = null;
        this.senderUsername = null;
        this.receiverUsername = null;
        this.image = null;
    }

    /**
     * Retrieves the message content.
     *
     * @return The message content.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message content.
     *
     * @param message The message content to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the time the message was sent.
     *
     * @return The time the message was sent.
     */
    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    /**
     * Sets the time the message was sent.
     *
     * @param timeSent The time the message was sent.
     */
    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    /**
     * Returns the sender profile of the message.
     *
     * @return The sender profile of the message.
     */
    public String getSenderUsername() {
        return senderUsername;
    }

    /**
     * Sets the sender profile of the message.
     *
     * @param sender The sender profile of the message.
     */
    public void setSenderUsername(String sender) {
        this.senderUsername = sender;
    }

    /**
     * Retrieves the receiver profile of the message.
     *
     * @return The receiver profile of the message.
     */
    public String getReceiverUsername() {
        return receiverUsername;
    }

    /**
     * Sets the receiver profile of the message.
     *
     * @param receiverUsername The receiver profile of the message.
     */
    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    /**
     * Checks if 2 messages are equal to eachother
     *
     * @param message1 The message to be checked
     * @return boolean if the messages have the
     * same text and the sender and recievers are
     * the same
     */
    @Override
    public boolean equals(Message message1) {
        return (message.equals(message1.getMessage()) &&
                senderUsername.equals(message1.getSenderUsername()) &&
                receiverUsername.equals(message1.getReceiverUsername()));
    }

    public String toString() {
        return message + "##" + senderUsername + "##"
                + receiverUsername + "##" + timeSent;
    }

}