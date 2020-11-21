package controller;
import entities.Event;
import entities.User;
import presenter.SpeakerMessagePresenter;
import useCase.*;

import java.util.HashMap;
import java.util.List;

/***
 * A speaker controller. Includes all the abilities only speakers can complete.
 */

public class SpeakerController extends UserController {
    private String SpeakerID;
    public MessageActions messageActions; // = super.getMessages();
    public EventActions eventActions; // = super.getEvents();
    public UserAccountActions userAccountActions; // = super.getUserAccountActions();
    public RoomActions roomActions; // = super.getRooms();
    public SpeakerActions speakerActions; // = super.getSpeakers();
    public OrganizerActions organizerActions; // = super.getOrganizers();
    public AttendeeActions attendeeActions; // = super.getAttendees();
    private SpeakerMessagePresenter displayMessage;

    public SpeakerController(String SpeakerID, MessageActions messageActions, EventActions eventActions, RoomActions roomActions,
                             AttendeeActions attendeeActions, OrganizerActions organizerActions, SpeakerActions speakerActions) {
        super(eventActions, roomActions, messageActions, 's', attendeeActions, organizerActions, speakerActions);

        this.SpeakerID = SpeakerID;
        this.messageActions = messageActions;
        this.eventActions = eventActions;
        this.roomActions = roomActions;
        this.attendeeActions = attendeeActions;
        this.organizerActions = organizerActions;
        this.speakerActions = speakerActions;
        this.displayMessage = new SpeakerMessagePresenter();
    }
    /**
     *
     * Allows Speakers to send a message to those attendees attending their event
     * */
    public boolean sendMessages(String event, String message) {
        if(eventActions != null) {
            HashMap<String, Event> eventHash = eventActions.getEventNames();
            if(eventHash.get(event) != null) {
                HashMap<String, User> speakerIdHash = returnUserIDHashMap();
                HashMap<String, User> attendeeIdHash = returnUserIDHashMap();
                HashMap<String, User> userHash = returnUserUsernameHashMap();
                String eventID = eventHash.get(event).getId();
                List<String> attendees = eventActions.getEventAttendees(eventID);
                for (String attendeeID : attendees) {
                    speakerActions.addUserContactList(speakerIdHash.get(SpeakerID).getUsername(),
                            attendeeIdHash.get(attendeeID).getUsername(), userHash);
                    attendeeActions.addUserContactList(attendeeIdHash.get(attendeeID).getUsername(),
                            speakerIdHash.get(SpeakerID).getUsername(), userHash);
                    if(messageActions.createMessage(SpeakerID, attendeeID, message) == null){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Allows Speakers to view attendees attending their event
     * @param eventID the id of the event
     * */
    public List<String> viewAttendees(String eventID) {
        return this.eventActions.getEventAttendees(eventID);
    }

    /**
     * Allows Speakers to view their events
     * @param speakerID the id of the speaker
     * */
    public List<String> viewEvents(String speakerID){return speakerActions.returnIDHashMap().get(speakerID).getEventList();}

}