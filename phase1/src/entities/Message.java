package entities;
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Message {
    private static String lastMessageIdNums = "";
    private String messageId;
    private String senderId;
    private String receiverId;
    private String message;
    private String sentTime;

    public Message(String senderId, String receiverId, String message) {
        messageId = generateMessageId(); // load last message time from message database (remember for if nothing in file)
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        sentTime = getSentTime(); // replace with ime (converted to est)
        // then add info to message database
        // (??) add info to entityId database
    }

    private String generateMessageId() {
        String id = "M";
        String lastMessageIdNumsSuffix;

        if (lastMessageIdNums == "") {
            id = id + "0000000000000000";
            lastMessageIdNums = "0000000000000000";
        } else if (lastMessageIdNums == "0000000000000000") {
            id = id + "0000000000000001";
            lastMessageIdNums = "0000000000000001";
        } else {
            lastMessageIdNumsSuffix = lastMessageIdNums.replaceAll("^[0]*", "");
            System.out.println(lastMessageIdNumsSuffix);

            int currMessageIdNumsSuffix = Integer.parseInt(lastMessageIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currMessageIdNumsSuffix).length(); i++) {
                id += "0";
            }
           id += currMessageIdNumsSuffix;
           lastMessageIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastMessageIdNums);
        return id;
    }

    private String getSentTime() {
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

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public String getSendTime() {
        return sentTime;
    }
}
