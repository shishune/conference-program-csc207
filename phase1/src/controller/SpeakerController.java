package controller;

import entities.Speaker;
import useCase.*;

import java.util.List;

public class SpeakerController extends UserManager {
    private String SpeakerID;
    public MessageActions messageActions;
    public EventActions eventActions;
    public UserAccountActions userAccountActions;
    public RoomActions roomActions;
    public SpeakerActions speakerActions;
    public OrganizerActions organizerActions;


    public SpeakerController(String SpeakerID, MessageActions messageActions, EventActions eventActions,
                             UserAccountActions userAccountActions, RoomActions roomActions,
                             SpeakerActions speakerActions, OrganizerActions organizerActions) {
        this.messageActions = messageActions;
        this.eventActions = eventActions;
        this.userAccountActions = userAccountActions;
        this.roomActions = roomActions;
        this.speakerActions = speakerActions;
        this.organizerActions = organizerActions;
        this.SpeakerID = SpeakerID;

    }

    public void sendmessage(String eventID, String message) {
        List<String> attendees = this.eventActions.attendees.get(eventID);
        for (String attendeeID : attendees) {
            this.messageActions.createMessage(this.SpeakerID, attendeeID, message);
        }
    }
    
    public List<String> viewAttendees(String eventID) {
        return this.eventActions.attendees.get(eventID);
        }

    }