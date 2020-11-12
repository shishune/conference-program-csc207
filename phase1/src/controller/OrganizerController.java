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
        // what if speaker is already created?
        String speakerID = this.speakerActions.createSpeaker(username, password,
                new ArrayList<>(), new ArrayList<>(), false).getId();    // TODO is this violating clean architecture?
        this.eventActions.speakerSchedule.put(speakerID, new ArrayList<>());
    }


    public void createRoom(){
        String roomID = this.roomActions.createRoom();
        this.eventActions.roomSchedule.put(roomID, new ArrayList<>());
    }

    // TODO Schedule the speakers to each speak in one or more rooms at different times
    /**
     * Schedule speaker to speak at an event
     * TODO: an event or a room??? have both???
     * // does this imply that there is currently no speaker for this event?
     * can events be without a speaker?
     */
    public boolean scheduleSpeaker(String eventID, String speakerID){
        String eventDateTime = eventActions.events.get(eventID).getDateTime();
        if (eventActions.isSpeakerFree(speakerID, eventDateTime)){
            eventActions.setSpeaker(eventID, speakerID);
            // TODO do speakers have a list of events they are speaking at?
            return true;
        }
        return false;
    }

    // TODO edit event details... is this necessary?


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

