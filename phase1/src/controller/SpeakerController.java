package controller;
import controller.UserController;
import entity.Event;
import entity.User;
import presenter.SpeakerMessagePresenter;
import useCase.RoomActions;

import java.util.HashMap;
import java.util.List;

/***
 * A speaker controller. Includes all the abilities only speakers can complete.
 */

public class SpeakerController extends UserController {
    private String SpeakerID;
    public useCase.MessageActions messageActions; // = super.getMessages();
    public useCase.EventActions eventActions; // = super.getEvents();
    public useCase.UserAccountActions userAccountActions; // = super.getUserAccountActions();
    public useCase.RoomActions roomActions; // = super.getRooms();
    public useCase.SpeakerActions speakerActions; // = super.getSpeakers();
    public useCase.OrganizerActions organizerActions; // = super.getOrganizers();
    public useCase.AttendeeActions attendeeActions; // = super.getAttendees();
    private SpeakerMessagePresenter displayMessage;

    /**
     * Instantiates a new SpeakerController object. Creates an instance of SpeakerID, MessageActions, EventActions
     * AttendeeActions, RoomActions, OrganizerActions and SpeakerActions.
     */

    public SpeakerController(String SpeakerID, useCase.MessageActions messageActions, useCase.EventActions eventActions, RoomActions roomActions,
                             useCase.AttendeeActions attendeeActions, useCase.OrganizerActions organizerActions, useCase.SpeakerActions speakerActions) {
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