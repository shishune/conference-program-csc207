package useCase;

import entities.Event;
import entities.Organizer;
import gateway.LoadUp;
import entities.Message;
import gateway.LoadUpIGateway;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

// TODO: Add appropriate return types for methods
// TODO: Add all required methods

public class MessageActions {
    private ArrayList<String> conversations = new ArrayList<String>(); // list containing loaded messages
    private HashMap<String, Message> messages = new HashMap<String, Message>(); // hashmap containing all loaded and new messages
    private LoadUpIGateway loader;

    public MessageActions(LoadUpIGateway loader) {
        // with message ID as key and message object as value
        this.loader = loader;
        getAllMessages(loader); // gets all messages from message.csv
        addLoadedToHashMap(); // adds those messages to a hashmap of all messages from the csv
    }

    /** Create a message with unique ID as a parameter **/
    public Message createMessage(String messageId, String senderId, String receiverId, String message, String sentTime) {
        Message newMessage = new Message(messageId, senderId, receiverId, message, sentTime);
        loadMessage(messageId, newMessage);
        return newMessage;
    }

    /** Create a message with unique ID as a parameter **/
    public Message createMessage(String messageId, String senderId, String receiverId, String message) {
        Message newMessage = new Message(messageId, senderId, receiverId, message, generateSentTime());
        loadMessage(messageId, newMessage);
        return newMessage;
    }

    /** Create a message and generate unique ID for message **/
    public Message createMessage(String senderId, String receiverId, String message) {
        GenerateID generateID = new GenerateID(loader);
        String messageId = "M" + generateID.generateId();
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
        //System.out.println(conversations);
        if (conversations != null) {
            for(String messageString : conversations) {
                String[] messageInfo = messageString.split(",");
                Message loadedMessage = new Message(messageInfo[0], messageInfo[1], messageInfo[2],messageInfo[3], messageInfo[4]);
                //Message loadedMessage = createMessage(messageInfo[0], messageInfo[1], messageInfo[2], messageInfo[3], messageInfo[4]);
                messages.put(messageInfo[0], loadedMessage);
            }
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
        List<Message> userMessages = new ArrayList<Message>();
        for(Map.Entry<String, Message> message : messages.entrySet()) {
            if(message.getKey().equals(senderId)) {
                userMessages.add(message.getValue());
            }
        }
        // sort userMessages by time sent
        userMessages.sort(Comparator.comparing(Message::getTimeSent));

        /**
         //If return ids instead of objects
         ArrayList<String> messageIds = new ArrayList<String>();
         for(Message message : userMessages) {
            messageIds.add(message.getMessageId());
         }
         return messageIds;
         **/
        return userMessages;
    }

    /**
     * Returns all messages sent by user with senderId and received by user with receiverId
     **/
    public List<Message> printMessages(String senderId, String receiverId) {
        // presenter should call this method and turn array into output
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for(Map.Entry<String, Message> message : messages.entrySet()) {
            if(message.getKey().equals(senderId) && message.getValue().getReceiverId().equals(receiverId)) {
                userMessages.add(message.getValue());
            }
        }
        // sort userMessages by time sent
        userMessages.sort(Comparator.comparing(Message::getTimeSent));
        return userMessages;
    }

    /** For if presenter needs to access message using its Id (for printMessages) **/
    public String getMessageFromMap(String messageId) {
        return messages.get(messageId).getStringRep();
    }

    /**
     * Returns an array of all messages (new and old) for storage
     **/
    public ArrayList<String> storeMessages() {
        ArrayList<String> allMessages = new ArrayList<String>();
        // store messages
        if (messages != null && !messages.isEmpty()) {
            for (Map.Entry<String, Message> message : messages.entrySet()) {
                allMessages.add(message.getValue().getStringRep());
            }
        }
        return allMessages;
    }

    public ArrayList<String> getMessengerIds() {
        ArrayList<String> storedMessenger= new ArrayList<String>();
        if (messages != null && !messages.isEmpty()) {
            for (Map.Entry<String, Message> o : messages.entrySet()) {
                storedMessenger.add(o.getValue().getMessageId() + "\n");
            }
        }
        return storedMessenger;
    }

}
