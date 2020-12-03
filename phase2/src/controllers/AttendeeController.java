package controllers;

// import com.sun.org.apache.xpath.internal.operations.Or;

import entities.Attendee;
import entities.Event;
import useCases.RoomActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendeeController extends UserController {
    private useCases.MessageActions message;
    private useCases.EventActions e;
    private useCases.AttendeeActions attendee;

    /**
     *
     * @param events
     * @param rooms
     * @param message
     * @param attendee
     * @param organizer
     * @param speaker
     */
    public AttendeeController(useCases.EventActions events, RoomActions rooms, useCases.MessageActions message,
                              useCases.AttendeeActions attendee, useCases.OrganizerActions organizer, useCases.SpeakerActions speaker, useCases.ConferenceActions conference) {
        super(events, rooms, message, 'a', attendee, organizer, speaker, conference);
        this.attendee = attendee;
        this.e = events;

    }

    @Override
    public boolean leaveEvent(String eventName, String userId){
        String eventID = e.getEventFromName(eventName).getId();
        return this.attendee.removeEventFromUser(eventID, userId);
    }

    /**
     * Saves an event to list of saved events for an user.
     * @param eventName the name of the event to be save
     * @param userName the username of the user who's saved event list is updated
     * @return true if the event is successfully saved return false if not
     */
    public boolean saveEvent(String eventName, String userName){
        return this.attendee.addEventToSavedEvent(eventName, userName);
    }

    /**
     * Removes an event from list of saved events for an user
     * @param eventName the name of the event to be removed
     * @param userID the id of the user who's saved event list is updated
     * @return true if the event is successfully removed return false if not
     */
    public boolean unSaveEvent(String eventName, String userID){
        String eventID = e.getEventFromName(eventName).getId();
        return this.attendee.removeEventFromSavedEvent(eventID, userID);
    }

    /**
     * Shows the events a given user saved
     *
     * @param user the user who wants to see their saved events
     * @return list of the events that a user saved in the form of a list
     * with the string representation of each aspect (title, dateTime, etc)
     */

    public List<List<String>> viewSavedEvents(String user) {
        Attendee a1 = attendee.returnUsernameHashMap().get(user);
        List<String> savedEventList = a1.getSavedEventList();
        List<List<String>> saveEventList = new ArrayList<List<String>>();
        if (e != null) {
            for (String event : savedEventList) {
                String title = e.getEvent(event).getTitle();
                String dateTime = e.getEvent(event).getDateTime();
                String roomId = e.getEvent(event).getRoomID();
                List<String> speakers = e.getEvent(event).getSpeakers();
                List<String> info = new ArrayList<String>();
                info.add(title);
                info.add(dateTime);
                info.add(roomId);
                info.addAll(speakers);
                saveEventList.add(info);
            }
        }
        return saveEventList;
    }

    /**
     * Shows the vip events to a vip attendee
     */

    public List<List<String>> viewVIPEvents(String user){
        List<List<String>> vipEventList = new ArrayList<List<String>>();
        List<String> vEventList = new ArrayList<String>();

        for (String event : e.getEventIds()){
            if (e.getEvent(event).getIsVip()){
                String title = e.getEvent(event).getTitle();
                String dateTime = e.getEvent(event).getDateTime();
                String roomId = e.getEvent(event).getRoomID();
                List<String> speaker = e.getEvent(event).getSpeakers();
                List<String> info = new ArrayList<String>();
                info.add(title);
                info.add(dateTime);
                info.add(roomId);
                info.addAll(speaker);
                vipEventList.add(info);
            }
        }
        return vipEventList;
    }

}