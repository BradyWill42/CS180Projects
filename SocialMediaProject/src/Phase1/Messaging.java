package Phase1;

import java.sql.Time;
import java.time.LocalDateTime;

/**
 * SocialMediaProject - Message interface
 * <p>
 * This interface contains information
 * on the methods used in the
 * message class
 *
 * @author Claire Noggle, L23
 * @version Mar 28, 2024
 */

public interface Messaging {
    public String getMessage();

    public void setMessage(String message);

    public LocalDateTime getTimeSent();

    public void setTimeSent(LocalDateTime timeSent);

    public String getSenderUsername();

    public void setSenderUsername(String sender);

    public String getReceiverUsername();

    public void setReceiverUsername(String receiverUsername);

    public boolean equals(Message message);
}