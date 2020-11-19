package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import useCase.*;
import entities.*;

/***
 * An organizer controller. Includes all the abilities only organizers can complete.
 * @author Eryka Shi-Shun
 *
 */

public class OrganizerController extends UserController{
    private MessageActions messageActions;
    private EventActions eventActions;
    private RoomActions roomActions = new RoomActions();
    private SpeakerActions speakerActions;
    private OrganizerActions organizerActions;
    private AttendeeActions attendeeActions;
    private String organizerID;


    public OrganizerController(String organizerID, MessageActions messageActions, EventActions eventActions, RoomActions roomActions,
                               AttendeeActions attendeeActions, OrganizerActions organizerActions, SpeakerActions speakerActions ){

        super(eventActions, roomActions, messageActions, attendeeActions, organizerActions, speakerActions);
        this.organizerID = organizerID;
        this.speakerActions = speakerActions;
        this.eventActions = eventActions;
        this.roomActions = roomActions;
        this.messageActions = messageActions;
        this.attendeeActions = attendeeActions;
        this.organizerActions= organizerActions;

    }
    public OrganizerController(){}
    /***
     * create a new event
     * @param title of event
     * @param speakerId of event
     * @param dateTime of event
     * @param roomID of event
     * @return if the event was created- this will return false if the event already exists
     */
    public List<Boolean> createEvent(String title, String speakerId, String dateTime, String roomID){
        List<String> attendees = new ArrayList<String>();
        return this.eventActions.createEvent(title, speakerId, dateTime, attendees, roomID);
    }

    /***
     * cancel an event
     * @param eventID
     * @return true if the event was successfully canceled (ie if it exists, then it will be cancelled)
     */

    @Override
    public boolean cancelEvent(String eventID){
        if (this.eventActions.eventExists(eventID)){
            List<String> eventAttendees = this.eventActions.cancelEvent(eventID);
            for (String attendeeID: eventAttendees){
                this.organizerActions.removeEventFromUser(eventID, attendeeID);
            }
            return true;
        }
        return false;
    }

    // TODO would there be reasons to not be able to create a speaker?

    /***
     * create a speaker and add them to the speaker schedule
     * @param username
     * @param password
     */
    public boolean createSpeaker(String username, String password){
        // what if speaker is already created?
        boolean speaker = true;
        if(!(speakerActions == null)) {
            if (speakerActions.findUserFromUsername(username) != null) {
                speaker = false;
            }

            String speakerID = this.speakerActions.createSpeaker(username, password,
                    new ArrayList<>(), new ArrayList<>(), false).getId();

            this.eventActions.addSpeakerToSchedule(speakerID);
        }
        return speaker;
    }

    /***
     * Create a room and add it to the room schedule
     */
    public boolean createRoom(String roomName){
        if(roomActions != null && eventActions != null){
            String roomID = this.roomActions.createRoom(roomName);
            return this.eventActions.addRoomToSchedule(roomID);
        }
        return false;
    }

    // TODO Schedule the speakers to each speak in one or more rooms at different times
    /**
     * Schedule speaker to speak at an event
     * @param eventID
     * @param speakerID
     * @return if the speaker is successfully scheduled ie if the speaker is free to speak at the event
     */
    public boolean scheduleSpeaker(String eventID, String speakerID){
        String eventDateTime = eventActions.getEvent(eventID).getDateTime();
        if (eventActions.isSpeakerFree(speakerID, eventDateTime)){
            eventActions.setSpeaker(eventID, speakerID);
            // TODO do speakers have a list of events they are speaking at?
            return true;
        }
        return false;
    }

    // TODO edit event details... is this necessary?

    /***
     * reschedule an event with a new date and time
     * @param eventID
     * @param newDateTime
     * @return if the event was successfully rescheduled
     */
    public boolean rescheduleEvent(String eventID, String newDateTime){
        return this.eventActions.changeEventTime(eventID, newDateTime);
    }

    /***
     * Send all the attendees of an event a message
     * @param eventID
     * @param message
     */
    public void sendAttendeesMessage(String eventID, String message){
        List<String> attendees = this.eventActions.getEventAttendees(eventID);

        for (String attendeeID: attendees){
            this.messageActions.createMessage(this.organizerID, attendeeID, message);
        }

    }



    /***
     * Send all the attendees of conference a message
     * @param message
     */
    public void sendAllAttendeesMessage( String message){
        List<String> eventIDs = new ArrayList();
        for(Map.Entry<String, Event> event : eventActions.getEvents().entrySet()) {
            eventIDs.add(event.getValue().getId());
        }

        List<String> attendees = new ArrayList();
        for(String eventID: eventIDs){
            attendees.addAll(this.eventActions.getEventAttendees(eventID));
        }

        for (String attendeeID: attendees){
            this.messageActions.createMessage(this.organizerID, attendeeID, message);
        }

    }

    /***
     * Check if event exists
     * @param event
     */
    public boolean checkEvent(String event){
        return (eventActions.getEvents().containsKey(event));
    }


}

