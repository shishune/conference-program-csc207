package entities;
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Message {
    //private static String lastMessageIdNums = "";
    private String messageId;
    private String senderId;
    private String receiverId;
    private String message;
    private String timeSent;

    public Message(String messageId, String senderId, String receiverId, String message, String timeSent) {
        // messageId = generateMessageId(); // Transfer Use case class: load last message time from message database (remember for if nothing in file)
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timeSent = timeSent;
        // timeSent = generateSentTime(); // Transfer Use Case Class: replace with ime (converted to est)
        // then add info to message data file
        // (??) add info to entityId data file
    }

    /**
     * Generates a unique ID for this message.
     * @return Unique ID for this message.
     * */
    /**private String generateMessageId() {
        // TODO: Change to implement java.io (DUE NOV 03)
        // TODO: Move to Use Case class, Replace with UUID
        String id = "M";
        String lastMessageIdNumsSuffix;

        if (lastMessageIdNums.equals("")) {
            id = id + "0000000000000000"; //
            lastMessageIdNums = "0000000000000000";
        } else if (lastMessageIdNums.equals("0000000000000000")) {
            id = id + "0000000000000001";
            lastMessageIdNums = "0000000000000001";
        } else {
            lastMessageIdNumsSuffix = lastMessageIdNums.replaceAll("^[0]*", "");
            System.out.println(lastMessageIdNumsSuffix);

            int currMessageIdNumsSuffix = Integer.parseInt(lastMessageIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currMessageIdNumsSuffix).length(); i++) {
                id = id + "0";
            }
           id += currMessageIdNumsSuffix;
           lastMessageIdNums = id.substring(1);
        }
        //System.out.println(id);
        //System.out.println(lastMessageIdNums);
        return id;
    }**/

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
