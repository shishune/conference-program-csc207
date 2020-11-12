package controller;

import java.util.ArrayList;
import java.util.List;
import useCase.*;
import entities.*;

public class OrganizerController extends UserManager{
    public MessageActions messageActions;
    public EventActions eventActions;
    public UserAccountActions userAccountActions;
    public RoomActions roomActions;
    public SpeakerActions speakerActions;
    public OrganizerActions organizerActions;
    private String organizerID;


    public OrganizerController( String organizerID, MessageActions messageActions, EventActions eventActions,
                                UserAccountActions userAccountActions, RoomActions roomActions,
                                SpeakerActions speakerActions, OrganizerActions organizerActions){
        this.messageActions = messageActions;
        this.eventActions = eventActions;
        this.userAccountActions = userAccountActions;
        this.roomActions = roomActions;
        this.speakerActions = speakerActions;
        this.organizerActions = organizerActions;
        this.organizerID = organizerID;

    }

    public boolean createEvent(String title, String speakerId, String dateTime,
                               List<String> attendees, String roomID){
        return this.eventActions.createEvent(title, speakerId, dateTime, attendees, roomID);
    }


    public boolean cancelEvent(String eventID){
        if (this.eventActions.events.containsKey(eventID)){
            List<String> eventAttendees = this.eventActions.cancelEvent(eventID);
            for (String attendeeID: eventAttendees){
                this.userAccountActions.removeEventFromUser(eventID, attendeeID);
            }
            return true;
        }
        return false;
    }

    // TODO would there be reasons to not be able to create a speaker?
    public void createSpeaker(String username, String password){
        User speaker = this.speakerActions.createSpeaker(username, password, new ArrayList<>(), new ArrayList<>(), false);
        this.eventActions.speakerSchedule.put(speaker.getId(), new ArrayList<>());
    }


    public void createRoom(){
        String roomID = this.roomActions.createRoom();
        this.eventActions.roomSchedule.put(roomID, new ArrayList<>());
    }

    // TODO Schedule the speakers to each speak in one or more rooms at different times
    /**
     * Schedule speaker to speak at an event
     * TODO: an event or a room???
     */
    public void scheduleSpeaker(String eventID){
        // boolean
    }


    public boolean rescheduleEvent(String eventID, String newDateTime){
        return this.eventActions.changeEventTime(eventID, newDateTime);
    }


    public void sendAttendeesMessage(String eventID, String message){
        List<String> attendees = this.eventActions.attendees.get(eventID);

        for (String attendeeID: attendees){
            this.messageActions.createMessage(this.organizerID, attendeeID, message);
        }

    }

}
