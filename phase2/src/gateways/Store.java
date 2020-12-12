package gateways;

import java.io.IOException;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import interfaces.Storable;
import useCases.MessageActions;
import useCases.OrganizerActions;
import useCases.RoomActions;


/**
 * Stores information of the event regarding attendees, events, messages, organizers, rooms, speakers and user.
 */
public class Store{
    /**
     * Stores information of the event regarding attendees, events, messages, organizers, rooms, speakers and user.
     */

    /**
     * takes in information to in csv
     * @param path path to the csv
     * @param entityList List of entity information
     * */
    private void store(List<String> entityList, String path) {
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String entity : entityList){
                writer.write(entity);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the method in roomActions for obtaining all room ids as a list then stores in csv
     * @param roomActions the use case class responsible for rooms
     * */
    public void storeRooms(Storable roomActions) {
        ArrayList<String> roomsList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/rooms.csv";
        roomsList = roomActions.store();
        store(roomsList, path);
    }


    /**
     * Uses the method in eventActions for obtaining all events as a list then stores in csv
     * @param eventActions the use case class responsible for events
     */
    public void storeEvents(Storable eventActions) {
        // ArrayList<String> eventsList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/events.csv";
        List<String> eventsList = eventActions.store();
        store(eventsList, path);

    }

    /**
     * Uses the method in messageActions for obtaining all messages as a list then stores in csv
     * @param messageActions the use case class responsible for messages
     */
    public void storeMessages(Storable messageActions) {
        ArrayList<String> messagesList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/messages.csv";
        messagesList = messageActions.store();
        store(messagesList, path);
    }

    /**
     * Uses the method in organizerActions for obtaining all organizers as a list then stores in csv
     * @param organizerActions the use case class responsible for organizers
     */
    public <ICsvListWriter> void storeOrganizers(Storable organizerActions) {
        ArrayList<String> organizerList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/organizers.csv";
        organizerList = organizerActions.store();
        store(organizerList, path);
    }

    /**
     * Uses the method in attendeeActions for obtaining all attendees as a list then stores in csv
     * @param attendeeActions the use case class responsible for attendee
     */
    public void storeAttendees(Storable attendeeActions) {
        // attendeeList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/attendees.csv";
        ArrayList<String> attendeeList = attendeeActions.store();
        store(attendeeList, path);
    }

    /**
     * Uses the method in speakerActions for obtaining all speakers as a list then stores in csv
     * @param speakerActions the use case class for speaker
     */
    public void storeSpeakers(Storable speakerActions) {
        // speakerList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/speakers.csv";
        ArrayList<String> speakerList = speakerActions.store();
        store(speakerList, path);
    }

    /**
     * Uses the method in conferenceActions for obtaining all conferences as a list then stores in csv
     * @param conferenceActions the use case class for conference
     */
    public void storeConferences(Storable conferenceActions) {
        // conferenceList = new ArrayList<String>();
        String path = "./phase2/src/assets/dataFiles/conferences.csv";
        ArrayList<String>  conferenceList = conferenceActions.store();
        store(conferenceList, path);

    }


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
            for (String id: conference){
                csvWriter.append(id);
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
