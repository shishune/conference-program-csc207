package useCase;

import entities.*;

import gateway.LoadUpIGateway;
import useCase.EventActions;
import gateway.LoadUp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrganizerActions extends UserAccountActions {

    private HashMap<String, Attendee> organizerHashMap;
    private HashMap<String, Attendee> organizerUsernameHashMap;
    private ArrayList<String> organizers = new ArrayList<String>();

    public HashMap<String, Attendee> returnOrganizersHashMap(){
        return organizerHashMap;
    }

    public HashMap<String, Attendee> returnOrganizersUsernameHashMap(){
        return organizerUsernameHashMap;
    }

    private LoadUp loader = new LoadUp();

    public OrganizerActions(LoadUpIGateway loader) {
        getAllOrganizer(loader); // gets all messages from message.csv
        addOrganizerToHashMap();; // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value
    }

    public User createOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userOrganizer);
        addUsernameToHashMap(userOrganizer);
        return userOrganizer;
    }

    public User createOrganizer(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        GenerateID generateId = new GenerateID();
        String userId = "O" + generateId;
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userOrganizer);
        addUsernameToHashMap(userOrganizer);
        return userOrganizer;
    }

    public boolean addNewEvent(Event event) {
        EventActions eventactions = new EventActions();
        eventactions.loadEvent(event.getId(), event.getTitle(), event.getSpeaker(), event.getDateTime(),
                event.getAttendees(), event.getRoomID());

        List<String> eventList = loader.getEventsList();
        return eventList.contains(event.getId());
    }

    public boolean changeEventTime(String eventId, String newDateTime) {
        EventActions eventactions = new EventActions();
        return eventactions.changeEventTime(eventId, newDateTime);
    }

    public List<String> cancelEvent(String eventId) {
        EventActions eventactions = new EventActions();
        return eventactions.cancelEvent(eventId);
    }

    private void getAllOrganizer(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        organizers = loader.getAttendees();
    }

    private void addOrganizerToHashMap() {
        for(String organizersString : organizers) {
            String[] organizerInfo = organizersString.split(",");
            ArrayList<String> eventList = new ArrayList<String>();
            ArrayList<String> contactList = new ArrayList<String>();
            String[] events = organizerInfo[4].split("%%");
            String[] contacts = organizerInfo[3].split("%%");
            for (String e: events) {
                eventList.add(e);
            }
            for (String c: contacts) {
                contactList.add(c);
            }
            Attendee loadedAttendee = new Attendee(organizerInfo[0], organizerInfo[1], organizerInfo[2], contactList, eventList, Boolean.parseBoolean(organizerInfo[5]), Boolean.parseBoolean(organizerInfo[6]));
            organizerHashMap.put(organizerInfo[0], loadedAttendee);
            organizerUsernameHashMap.put(organizerInfo[1], loadedAttendee);
        }
    }
}
