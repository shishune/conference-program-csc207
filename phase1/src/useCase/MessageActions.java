package useCase;

import entities.Event;
import gateway.LoadUp;
import entities.Message;
import gateway.LoadUpIGateway;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Add appropriate return types for methods
// TODO: Add all required methods

public class MessageActions {
    private ArrayList<String> conversations = null;
    //private List<String> userMessages = null;

    public HashMap<String, Message> messages; // hashmap containing all loaded and new messages

    // hash of messages with sender's ID as key
    // and a list of all messages sent or received by the sender as value
    public HashMap<String, List<Message>> senderMessages;

    public MessageActions(LoadUpIGateway loader) {
        getAllMessages(loader); // gets all messages from message.csv
        addLoadedToHashMap(); // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value
    }

    /** Create a message with unique ID as a parameter **/
    public Message createMessage(String messageId, String senderId, String receiverId, String message, String sentTime) {
        // TODO: Set return type to Message
        Message newMessage = new Message(messageId, senderId, receiverId, message, sentTime);
        loadMessage(messageId, newMessage);
        return newMessage;
    }

    /** Create a message with unique ID as a parameter **/
    public Message createMessage(String messageId, String senderId, String receiverId, String message) {
        // TODO: Set return type to Message
        Message newMessage = new Message(messageId, senderId, receiverId, message, generateSentTime());
        loadMessage(messageId, newMessage);
        return newMessage;
    }

    /** Create a message and generate unique ID for message **/
    public Message createMessage(String senderId, String receiverId, String message) {
        // TODO: Set return type to Message
        GenerateID generateID = new GenerateID();
        String messageId = "M" + generateID;
        Message newMessage = new Message(messageId, senderId, receiverId, message, generateSentTime());
        loadMessage(messageId, newMessage);
        return newMessage;
    }

    /** Load new messages into HashMap of new messages **/
    public void loadMessage(String messageId, Message newMessage){
        // This needs to update every collection we use for messages
        // i.e. if we add a new hashmap/array/etc. for some method, we need to add
        // otherHash.put(messageId, newMessage)
        // messageArray.add(newMessage)
        // etc. etc.
        messages.put(messageId, newMessage);
    }

    /**
     * Generates the time of Message construction.
     * Method gets local time (time according the the timezone on the sender's computer)
     * of the sender, and converts it to Toronto time.
     * @return time of Message construction (converted to Toronto time)
     * */
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

    /** gets list of messages from the IGateway **/
    private void getAllMessages(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        conversations = loader.getMessagesList();
    }

    /** Adds messages loaded from the csv to <messages> **/
    private void addLoadedToHashMap() {
        for(String messageString : conversations) {
            String[] messageInfo = messageString.split(",");
            Message loadedMessage = new Message(
                    messageInfo[0], messageInfo[1], messageInfo[2],
                    messageInfo[3], messageInfo[4]);
            messages.put(messageInfo[0], loadedMessage);
        }
    }

    /**
     * Send message to a specific user
     **/
    public void sendMessage(Message message) {
        loadMessage(message.getMessageId(), message);
    }

    /**
     * Returns all messages sent by user with senderId
     **/
    public List<Message> printMessages(String senderId) {
        // presenter should call this method and turn array into output
        ArrayList<Message> userMessages = null;
        for(Map.Entry<String, Message> message : messages.entrySet()) {
            if(message.getKey() == senderId) {
                userMessages.add(message.getValue());
            }
        }
        return userMessages;
    }

    /**
     * Returns all messages sent by user with senderId and received by user with receiverId
     **/
    public List<Message> printMessages(String senderId, String receiverId) {
        // presenter should call this method and turn array into output
        ArrayList<Message> userMessages = null;
        for(Map.Entry<String, Message> message : messages.entrySet()) {
            if(message.getKey() == senderId && message.getValue().getReceiverId() == receiverId) {
                userMessages.add(message.getValue());
            }
        }
        return userMessages;
    }

    /**
     * Returns an array of all messages (new and old) for storage
     **/
    public List<String> storeMessages() {
        ArrayList<String> allMessages = null;
        // store messages
        for(Map.Entry<String, Message> message : messages.entrySet()) {
            allMessages.add(message.getValue().getStringRep());
        }
        return allMessages;
    }

}
