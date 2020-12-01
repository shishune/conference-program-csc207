package gateways;

import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import useCases.MessageActions;
import useCases.OrganizerActions;
import useCases.RoomActions;

/**
 * Stores information of the event regarding attendees, events, messages, organizers, rooms, speakers and user.
 */
public class Store{

    /**
     * Uses the method in roomActions for obtaining all room ids as a list then stores in csv
     * @param roomActions the use case class responsible for rooms
     * */
    public void storeRooms(RoomActions roomActions) {
        ArrayList<String> roomsList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/rooms.csv";
        //List<String> roomsList = roomActions.storingRooms();
        roomsList = roomActions.storingRooms();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String room : roomsList){
                writer.write(room);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Uses the method in eventActions for obtaining all events as a list then stores in csv
     * @param eventActions the use case class responsible for events
     */
    public void storeEvents(useCases.EventActions eventActions) {
        // ArrayList<String> eventsList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/events.csv";
        List<String> eventsList = eventActions.storeEvents();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String event : eventsList){
                writer.write(event);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the method in messageActions for obtaining all messages as a list then stores in csv
     * @param messageActions the use case class responsible for messages
     */
    public void storeMessages(MessageActions messageActions) {
        ArrayList<String> messagesList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/messages.csv";
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

    /**
     * Uses the method in organizerActions for obtaining all organizers as a list then stores in csv
     * @param organizerActions the use case class responsible for organizers
     */
    public void storeOrganizers(OrganizerActions organizerActions) {
        ArrayList<String> organizerList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/organizers.csv";
        organizerList = organizerActions.storingOrganizers();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String organizer : organizerList){
                writer.write(organizer);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the method in attendeeActions for obtaining all attendees as a list then stores in csv
     * @param attendeeActions the use case class responsible for attendee
     */
    public void storeAttendees(useCases.AttendeeActions attendeeActions) {
        ArrayList<String> attendeeList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/attendees.csv";
        attendeeList = attendeeActions.storingAttendees();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String attendee : attendeeList){
                writer.write(attendee);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the method in speakerActions for obtaining all speakers as a list then stores in csv
     * @param speakerActions the use case class for speaker
     */
    public void storeSpeakers(useCases.SpeakerActions speakerActions) {
        ArrayList<String> speakerList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/speakers.csv";
        speakerList = speakerActions.storeSpeakers();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String speaker : speakerList){
                writer.write(speaker);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the method in conferenceActions for obtaining all conferences as a list then stores in csv
     * @param conferenceActions the use case class for conference
     */
//    public void storeConferences(useCases.ConferenceActions conferenceActions) {
//        ArrayList<String> conferenceList = new ArrayList<String>();
//        String path = "./phase2/src/assets/dataFiles/conferences.csv";
//        conferenceList = conferenceActions.storeConferences();
//        try {
//            FileWriter writer;
//            writer = new FileWriter(path, false);
//            for (String conference : conferenceList){
//                writer.write(conference);
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Stores entities of Attendee, Event, Message, Organizer, Room, Event, Speaker
     * @param attendee from Attendee entity
     * @param organizer from Organizer entity
     * @param message from Message entity
     * @param room from Room entity
     * @param event from Event entity
     * @param speaker from Speaker entity
     * @param conference from Conference entity
     */
    public void storeEntities(ArrayList<String> attendee, ArrayList<String> organizer, ArrayList<String> message, ArrayList<String> room, ArrayList<String> event, ArrayList<String> speaker, ArrayList<String> conference) {

        try {
            FileWriter csvWriter = new FileWriter("./phase2/src/assets/dataFiles/entities.csv");

            for (String id : attendee) {
                csvWriter.append(id);
            }
            for (String id : organizer) {
                csvWriter.append(id);
            }
            for (String id : message) {
                csvWriter.append(id);
            }
            for (String id : room) {
                csvWriter.append(id);
            }
            for (String id : event) {
                csvWriter.append(id);
            }
            for (String id : speaker) {
                csvWriter.append(id);
            }
            for (String id : conference) {
                csvWriter.append(id);
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
