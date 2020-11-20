package controller;
import entities.Message;
import entities.Speaker;
import useCase.*;

import java.util.List;

public class SpeakerController extends UserController {
    private String SpeakerID;
    public MessageActions messageActions; // = super.getMessages();
    public EventActions eventActions; // = super.getEvents();
    public UserAccountActions userAccountActions; // = super.getUserAccountActions();
    public RoomActions roomActions; // = super.getRooms();
    public SpeakerActions speakerActions; // = super.getSpeakers();
    public OrganizerActions organizerActions; // = super.getOrganizers();
    public AttendeeActions attendeeActions; // = super.getAttendees();

    public SpeakerController(String SpeakerID, MessageActions messageActions, EventActions eventActions,
                             RoomActions roomActions,
                             SpeakerActions speakerActions, OrganizerActions organizerActions, AttendeeActions attendeeActions) {
        super(eventActions, roomActions, messageActions, speakerActions);

        this.SpeakerID = SpeakerID;

    }
    /**
     *
     * Allows Speakers to send a message to those attendees attending their event
     * */
    public boolean sendMessages(String eventID, String message) {
        List<String> attendees = this.eventActions.getEventAttendees(eventID);
        for (String attendeeID : attendees) {
            if(this.messageActions.createMessage(this.SpeakerID, attendeeID, message) == null){
                return false;
            }
        }
        return true;
    }
    /**
     *
     * Allows Speakers to view attendees attending their event
     * */

    public List<String> viewAttendees(String eventID) {
        return this.eventActions.getEventAttendees(eventID);
    }

}