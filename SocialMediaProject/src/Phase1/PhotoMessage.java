package Phase1;

import java.time.LocalDateTime;

/**
 * SocialMediaProject - Photo Message class
 * <p>
 * This class is used to create
 * a new photo message object.
 *
 * @author Anuraag Kolli, L23
 * @version Mar 28, 2024
 */


public class PhotoMessage extends Message implements Photos {

    private String photoFilename;

    /**
     * This PhotoMessage constructor
     * creates a new photo message object
     *
     * @param photoFilename    - String
     * @param timeSent         - LocalDateTime
     * @param senderUsername   - String
     * @param receiverUsername - String
     */

    public PhotoMessage(String photoFilename, LocalDateTime timeSent,
                        String senderUsername, String receiverUsername) {
        super(timeSent, senderUsername, receiverUsername);
        this.photoFilename = photoFilename;
    }

    public String getPhotoFilename() {
        return photoFilename;
    }

    public void setPhotoFilename(String photoFilename) {
        this.photoFilename = photoFilename;
    }

    public String toString() {
        return photoFilename + "##" + super.getSenderUsername() + "##"
                + super.getReceiverUsername() + "##" + super.getTimeSent();
    }
}