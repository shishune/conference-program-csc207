package controller;

import entities.Event;
import entities.Organizer;
import entities.Speaker;
import entities.User;
import useCase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * An organizer controller. Includes all the abilities only organizers can complete.
 * @author Eryka Shi-Shun
 *
 */

public class OrganizerController extends UserController{
    private MessageActions messageActions;
    private EventActions eventActions;
    private RoomActions roomActions;
    private SpeakerActions speakerActions;
    private OrganizerActions organizerActions;
    private String organizerID;


    public OrganizerController(String organizerID, MessageActions messageActions, EventActions eventActions, RoomActions roomActions, //hello
                               AttendeeActions attendeeActions, OrganizerActions organizerActions, SpeakerActions speakerActions ){ //hello

        super(eventActions, roomActions, messageActions, 'o', attendeeActions, organizerActions, speakerActions); //hello
        this.organizerID = organizerID;
        this.speakerActions = speakerActions;
        this.eventActions = eventActions;
        this.roomActions = roomActions;
        this.messageActions = messageActions;
        // this.attendeeActions = attendeeActions;
        this.organizerActions = organizerActions;
    }
    /***
     * create a new event
     * @param title of event
     * @param speakerId of event
     * @param dateTime of event
     * @param roomID of event
     * @return if the event was created- this will return false if the event already exists
     */
    public List<Boolean> createEvent(String title, String speakerId, String dateTime, String roomID){
        List<String> attendees = new ArrayList<String>();
        Event event = this.eventActions.createEvent(title, speakerId, dateTime, attendees, roomID);
        List<Boolean> checks = new ArrayList<Boolean>();
        if(event != null){
            scheduleSpeaker(event.getId(), speakerId, true);
            speakerActions.isEventAddedToSpeaker(event.getId(), speakerId);
            checks.add(true);
            return checks;
        }
        checks.add(false);
        if (!eventActions.isRoomFree(roomID, dateTime)){
            checks.add(true);
        } else if(!eventActions.isSpeakerFree(speakerId, dateTime)){
            checks.add(false);
        }
        return checks;
    }

    /***
     * cancel an event
     * @param eventName
     * @return true if the event was successfully canceled (ie if it exists, then it will be cancelled)
     */

    //@Override
    public boolean cancelEvent(String eventName){
        if (this.eventActions.getEventNames().containsKey(eventName)) {
            List<String> eventAttendees = this.eventActions.cancelEvent(eventName);
            String s = eventActions.getEventNames().get(eventName).getSpeaker();
            if (speakerActions.isEventRemovedFromSpeaker(this.eventActions.getEventFromName(eventName).getId(), s)) {
                if (eventAttendees != null) {
                    for (String attendeeID : eventAttendees) {
                        this.organizerActions.removeEventFromUser(eventName, attendeeID);
                    }

                    return true;
                }
            }
        }
        return false;
    }

    /***
     * create a speaker and add them to the speaker schedule
     * @param username
     * @param password
     */
    public boolean createSpeaker(String username, String password){
        // what if speaker is already created?
        boolean speaker = true;
        if(speakerActions != null) {
            if (speakerActions.findUserFromUsername(username) != null) {
                return false;
            }

            String speakerID = this.speakerActions.createSpeaker(username, password,
                    new ArrayList<>(), new ArrayList<>(), false).getId();

            this.eventActions.addSpeakerToSchedule(speakerID);
        }
        return speaker;
    }

    /***
     * Create a room and add it to the room schedule
     */
    public boolean createRoomActions(String roomName){
        if(roomActions != null && eventActions != null){
            String roomID = this.roomActions.createRoom(roomName).getRoomId();
            return this.eventActions.addRoomToSchedule(roomID);
        }
        return false;
    }

    /**
     * Schedule speaker to speak at an event. under the assumption that speaker can be added/ is free
     * @param eventID
     * @param speakerID
     *
     */
    public boolean scheduleSpeaker(String eventID, String speakerID, boolean canAdd){
        if (canAdd) {
            eventActions.setSpeaker(eventID, speakerID);
            String speakerUsername = speakerActions.returnIDHashMap().get(speakerID).getUsername();
            speakerActions.addEventToUser(eventID, speakerUsername);
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
     * @param event
     * @param message
     */
    public boolean sendAttendeesMessage(String event, String message){
        HashMap<String, Event> eventsHash = eventActions.getEventNames();
        if(eventsHash.get(event) == null) {
            return false;
        }

        String eventID = eventsHash.get(event).getId();
        List<String> attendees = eventActions.getEventAttendees(eventID);
        for (String attendeeID: attendees){
            messageActions.createMessage(organizerID, attendeeID, message);
        }
        return true;
    }

    public boolean sendSpeakersMessage(String messageContent) {
        HashMap<String, User> userHash = returnUserUsernameHashMap();
        HashMap<String, Speaker> speakersHash = speakerActions.returnUsernameHashMap();
        HashMap<String, Organizer> idHash = organizerActions.returnIDHashMap();
        String userUsername = idHash.get(organizerID).getUsername();
        for(Map.Entry<String, Speaker> entry : speakersHash.entrySet()) {
            organizerActions.addUserContactList(userUsername, entry.getValue().getUsername(), userHash);
            speakerActions.addUserContactList(entry.getValue().getUsername(), userUsername, userHash);
            if(userHash.get(entry.getValue().getUsername()) == null) {
                return false;
            }
            if (organizerActions.findUserFromUsername(userUsername).getContactsList().contains(entry.getValue().getId())) {
                messageActions.createMessage(organizerID, entry.getValue().getId(), messageContent);
            } else {
                return false;
            }
        }
        return true;
    }


    /***
     * Send all the attendees of conference a message
     * @param message
     */
    public void sendAllAttendeesMessage( String message){
        List<String> eventIDs = new ArrayList();
        for(Map.Entry<String, Event> event : eventActions.getEvents().entrySet()) {
            eventIDs.add(event.getValue().getId());
        }

        List<String> attendees = new ArrayList();
        for(String eventID: eventIDs){
            attendees.addAll(this.eventActions.getEventAttendees(eventID));
        }

        for (String attendeeID: attendees){
            this.messageActions.createMessage(this.organizerID, attendeeID, message);
        }

    }

    /***
     * Check if event exists
     * @param event
     */
    public boolean checkEvent(String event){
        return (eventActions.getEventNames().containsKey(event));
    }


}


