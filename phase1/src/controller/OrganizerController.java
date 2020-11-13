package controller;

import java.util.ArrayList;
import java.util.List;
import useCase.*;
import entities.*;

public class OrganizerController extends UserController{
    public MessageActions messageActions;
    public EventActions eventActions;
    public UserAccountActions userAccountActions;
    public RoomActions roomActions;
    public SpeakerActions speakerActions;
    public OrganizerActions organizerActions;
    public AttendeeActions attendeeActions;
    private String organizerID;


    public OrganizerController(String organizerID, MessageActions messageActions, EventActions eventActions,
                                UserAccountActions userAccountActions, RoomActions roomActions,
                                SpeakerActions speakerActions, OrganizerActions organizerActions, AttendeeActions attendeeActions){

        super(userAccountActions, eventActions, roomActions, messageActions, attendeeActions);

        this.organizerID = organizerID;
    }

    /***
     * create a new event
     * @param title of event
     * @param speakerId of event
     * @param dateTime of event
     * @param attendees of event
     * @param roomID of event
     * @return if the event was created- this will return false if the event already exists
     */
    public boolean createEvent(String title, String speakerId, String dateTime,
                               List<String> attendees, String roomID){
        return this.eventActions.createEvent(title, speakerId, dateTime, attendees, roomID);
    }

    /***
     * cancel an event
     * @param eventID
     * @return true if the event was successfully canceled (ie if it exists, then it will be cancelled)
     */
    public boolean cancelEvent(String eventID){
        if (this.eventActions.eventExists(eventID)){
            List<String> eventAttendees = this.eventActions.cancelEvent(eventID);
            for (String attendeeID: eventAttendees){
                this.userAccountActions.removeEventFromUser(eventID, attendeeID);
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
    public void createSpeaker(String username, String password){
        // what if speaker is already created?
        String speakerID = this.speakerActions.createSpeaker(username, password,
                new ArrayList<>(), new ArrayList<>(), false).getId();
        this.eventActions.addSpeakerToSchedule(speakerID);
    }

    /***
     * Create a room and add it to the room schedule
     */
    public void createRoom(){
        String roomID = this.roomActions.createRoom();
        this.eventActions.addRoomToSchedule(roomID);
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

}

