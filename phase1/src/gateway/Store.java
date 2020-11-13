package gateway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Organizer;
import useCase.MessageActions;
import useCase.RoomActions;
import useCase.*;
import entities.Message;

public class Store{

    /**
     * Uses the method in roomActions for obtaining all room ids as a list then stores in csv
     * @param roomActions the use case class responsible for rooms
     * */
    public void storeRooms(RoomActions roomActions) {
        ArrayList<String> roomsList = new ArrayList<String>();
        String path = "../assets/dataFiles/rooms.csv";
        roomsList = roomActions.storeRooms();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String message : roomsList){
                writer.write(message);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeEvents(EventActions eventActions) {
        // ArrayList<String> eventsList = new ArrayList<String>();
        String path = "../assets/dataFiles/rooms.csv";
        List<String> eventsList = eventActions.storeEvents();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String message : eventsList){
                writer.write(message);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeMessages(MessageActions messageActions) {
        ArrayList<String> messagesList = new ArrayList<String>();
        String path = "../assets/dataFiles/messages.csv";
        messagesList = messageActions.storeMessages();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String message : messagesList){
                writer.write(message);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void storeOrganizers(OrganizerActions organizerActions) {
        ArrayList<String> organizerList = new ArrayList<String>();
        String path = "../assets/dataFiles/rooms.csv";
        organizerList = organizerActions.storingOrganizers();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String message : organizerList){
                writer.write(message);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void storeAttendees(AttendeeActions attendeeActions) {
        ArrayList<String> attendeeList = new ArrayList<String>();
        String path = "../assets/dataFiles/rooms.csv";
        attendeeList = attendeeActions.storingAttendees();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String message : attendeeList){
                writer.write(message);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void storeSpeakers(SpeakerActions speakerActions) {
//        ArrayList<String> speakerList = new ArrayList<String>();
//        String path = "../assets/dataFiles/rooms.csv";
//        speakerList = speakerActions.storeSpeakers();
//        try {
//            FileWriter writer;
//            writer = new FileWriter(path, false);
//            for (String message : speakerList){
//                writer.write(message);
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void storeEntities(ArrayList<String> attendee, ArrayList<String> organizer, ArrayList<String> message, ArrayList<String> room, ArrayList<String> event, ArrayList<String> speaker) {
        try {
            FileWriter csvWriter = new FileWriter("../assets/dataFiles/entities.csv");

            for (String id : attendee) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : organizer) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : message) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : room) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : event) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : speaker) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
