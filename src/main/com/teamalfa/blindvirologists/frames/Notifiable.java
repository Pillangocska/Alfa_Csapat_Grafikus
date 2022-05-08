package main.com.teamalfa.blindvirologists.frames;
/**
 * When something changes in the model the player and
 * the frames get notified by this interface.
 */
public interface Notifiable {
    /**
     * A method for transmitting the message.
     */
    void creativeNotify(String msg);
}
