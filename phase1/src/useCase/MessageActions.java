package useCase;

import entities.Event;
import gateway.LoadUp;
import entities.Message;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

// TODO: Add appropriate return types for methods
// TODO: Add all required methods

public class MessageActions {
    private List<String> conversations = null;
    private List<String> userMessages = null;

    public HashMap<String, Message> messages;

    // hash of messages with sender's ID as key
    // and a list of all messages sent or received by the sender as value
    public HashMap<String, List<Message>> senderMessages;

    private LoadUp loader = new LoadUp(); // this is okay because IGateway

    /** gets list of messages from the IGateway **/
    private void getAllConversations() {
        conversations = loader.getMessagesList();
    }

    /** gets list of all messages between two users **/
    private void getMessages(String senderId, String receiverId) {
        // might not be necessary
    }

    private String generateSentTime() {
        // TODO: Move to Use Case Class
        final String DATE_FORMAT = "dd-M-yyyy k:mm:ss.n"; // format of date and time

        // get local time in toronto time zone
        ZoneId torontoZoneId = ZoneId.of("America/Toronto");
        LocalDateTime zonedDateTime = LocalDateTime.now(torontoZoneId);

        // format the date according to <DATE_FORMAT>
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String dateTime = format.format(zonedDateTime);
        dateTime = dateTime.substring(0, dateTime.indexOf(".") + 4);
        return dateTime;
    }

    /** Gets a ___ of users for the speaker, organizer and attendee messaging actions
     **/
    private void getUsers() {

    }
    public void createMessage() {
        // TODO: Set return type to Message
    }


    //=======================================

    /**
     * Send message to a specific user
     **/
    public void sendMessage(String senderId, String receiverId) {

    }

    /**
     * Send messages to multiple users
     **/
    public void sendMessage(String senderId, List<String> receiverIds) {
        // check senderId for permission to send to multiple users
    }

    public void sendMessageToEvent() {

    }

    /**
     * Returns nested array of all conversations for one user
     **/
    public void printMessages(String senderId) {
        // presenter should call this method and turn array into output
    }

    /**
     * Returns an array of conversation between one user and another user
     **/
    public void printMessages(String senderId, String receiverId) {
        // presenter should call this method and turn array into output
    }


}
