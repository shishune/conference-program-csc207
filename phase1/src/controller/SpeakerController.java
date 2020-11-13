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


    public SpeakerController(String speakerID, MessageActions messageActions, EventActions eventActions,
                               UserAccountActions userAccountActions, RoomActions roomActions,
                               SpeakerActions speakerActions, OrganizerActions organizerActions, AttendeeActions attendeeActions){

        super(userAccountActions, eventActions, roomActions, messageActions, attendeeActions);

        this.SpeakerID = speakerID;
    }

    public void sendmessage(String eventID, String message) {
        List<String> attendees = this.eventActions.getAttendees().get(eventID);
        for (String attendeeID : attendees) {
            this.messageActions.createMessage(this.SpeakerID, attendeeID, message);
        }
    }
    
    public List<String> viewAttendees(String eventID) {
        return this.eventActions.getAttendees().get(eventID);
        }

    }