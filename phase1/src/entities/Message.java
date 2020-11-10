package entities;
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
