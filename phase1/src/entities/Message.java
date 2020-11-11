package entities;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Message {
    //private static String lastMessageIdNums = "";
    private String messageId;
    private String senderId;
    private String receiverId;
    private String message;
    private String timeSent;

    public Message(String messageId, String senderId, String receiverId, String message, String timeSent) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timeSent = timeSent;
    }

    public Message(String messageId, String senderId, String receiverId, String message) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timeSent = generateSentTime();
    }

    public String getStringRep() {
        return messageId + "," + senderId + "," + receiverId + "," + message + "," + timeSent;
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

    /**
     * Getter for the ID of message.
     * @return ID of the message
     * */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Getter for the ID of sender.
     * @return ID of the sender
     * */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Getter for the ID of the receiver
     * @return ID of teh receiver
     * */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * Getter for the content of the message.
     * @return content of the message
     * */
    public String getMessage() {
        return message;
    }

    /**
     * Getter for the time sent of message.
     * @return time of message send
     * */
    public String getTimeSent() {
        return timeSent;
    }
}
