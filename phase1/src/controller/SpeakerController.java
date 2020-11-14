package controller;

import entities.Speaker;
import useCase.*;

import java.util.List;

public class SpeakerController extends UserController {
    private String SpeakerID;
    //todo should these be private?
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
    // changed return type to boolean
    public boolean sendmessage(String eventID, String message) {
        if (!this.eventActions.eventExists(eventID)){
            return false;
        }
        List<String> attendees = this.eventActions.getEventAttendees(eventID);
        for (String attendeeID : attendees) {
            this.messageActions.createMessage(this.SpeakerID, attendeeID, message);
        }
        return true;
    }
    //helper for presenter
    public boolean sendMessages(List<String> events, String message){
        for (String event:events){
            if (!sendmessage(event, message)){
                return false;
            }
        }
        return true;
    }
    public List<String> viewAttendees(String eventID) {
        return this.eventActions.getEventAttendees(eventID);
        }


}