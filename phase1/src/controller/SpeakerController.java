package controller;
import entities.Speaker;
import useCase.*;

import java.util.List;

public class SpeakerController extends UserController {
    private String SpeakerID;
    public MessageActions messageActions;
    public EventActions eventActions;
    public UserAccountActions userAccountActions;
    public RoomActions roomActions;
    public SpeakerActions speakerActions;
    public OrganizerActions organizerActions;
    public AttendeeActions attendeeActions;

    public SpeakerController(String SpeakerID, MessageActions messageActions, EventActions eventActions,
                             UserAccountActions userAccountActions, RoomActions roomActions,
                             SpeakerActions speakerActions, OrganizerActions organizerActions, AttendeeActions attendeeActions) {
        super(userAccountActions, eventActions, roomActions, messageActions, attendeeActions, organizerActions, speakerActions);

        this.SpeakerID = SpeakerID;

    }
    /**
     *
     * Allows Speakers to send a message to those attendees attending their event
     * */
    public void sendMessages(String eventID, String message) {
        List<String> attendees = this.eventActions.getEventAttendees(eventID);
        for (String attendeeID : attendees) {
            this.messageActions.createMessage(this.SpeakerID, attendeeID, message);
        }
    }
    /**
     *
     * Allows Speakers to view attendees attending their event
     * */

    public List<String> viewAttendees(String eventID) {
        return this.eventActions.getEventAttendees(eventID);
    }

}