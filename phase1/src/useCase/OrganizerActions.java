package useCase;

import entities.*;

import gateway.LoadUpIGateway;
import useCase.EventActions;
import gateway.LoadUp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: change return type to organizer instead of user (do so for attendee, and speaker as well)

public class OrganizerActions extends UserAccountActions {

    private HashMap<String, Organizer> organizerHashMap = new HashMap<String, Organizer>();
    private HashMap<String, Organizer> organizerUsernameHashMap = new HashMap<String, Organizer>();
    private ArrayList<String> organizers = new ArrayList<String>();
    public ArrayList<String> storedOrganizer;
    private LoadUpIGateway loader;


    public HashMap<String, Organizer> returnOrganizersHashMap(){
        return organizerHashMap;
    }

    public HashMap<String, Organizer> returnOrganizersUsernameHashMap(){
        return organizerUsernameHashMap;
    }

    // private LoadUp loader = new LoadUp();

    public OrganizerActions(LoadUpIGateway loader) {
        // this.loader = loader;
        getAllOrganizer(loader); // gets all messages from message.csv
        addOrganizerToHashMap(); // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value

    }

    public User createOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userOrganizer);
        addUsernameToHashMap(userOrganizer);
//        super.addUserIdToHashMap(userOrganizer);
//        super.addUsernameToHashMap(userOrganizer);
        return userOrganizer;
    }

    public Organizer loadOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, true);
        addUserIdToHashMap(userOrganizer);
        addUsernameToHashMap(userOrganizer);
        organizerHashMap.put(userId, userOrganizer);
        organizerUsernameHashMap.put(username, userOrganizer);
        return userOrganizer;
    }

    public Organizer addToOrganizerHashMap() {

        return null;
    }


    public User createOrganizer(String username, String password, boolean isLogin){
        GenerateID generateId = new GenerateID(loader);
        String userId = "O" + generateId;
        return loadOrganizer(userId, username, password, new ArrayList<String>(), new ArrayList<String>(), false);
    }

//    public boolean addNewEvent(Event event) {
//        EventActions eventactions = new EventActions();
//        eventactions.loadEvent(event.getId(), event.getTitle(), event.getSpeaker(), event.getDateTime(),
//                event.getAttendees(), event.getRoomID());
//
//        List<String> eventList = loader.getEventsList();
//        return eventList.contains(event.getId());
//    }
//
//    public boolean changeEventTime(String eventId, String newDateTime) {
//        EventActions eventactions = new EventActions();
//        return eventactions.changeEventTime(eventId, newDateTime);
//    }

//    public List<String> cancelEvent(String eventId) {
//        EventActions eventactions = new EventActions();
//        return eventactions.cancelEvent(eventId);
//    }

    private void getAllOrganizer(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        organizers = loader.getAllOrganizers();
    }

    private void addOrganizerToHashMap() {
        if (organizers != null) {
            for (String organizersString : organizers) {
                String[] organizerInfo = organizersString.split(",");
                ArrayList<String> eventList = new ArrayList<String>();
                ArrayList<String> contactList = new ArrayList<String>();
                String[] events = organizerInfo[4].split("%%");
                String[] contacts = organizerInfo[3].split("%%");

                for (String e : events) {
                    eventList.add(e);
                }

                for (String c : contacts) {
                    contactList.add(c);
                }

//                Organizer loadedOrganizer = new Organizer(organizerInfo[0], organizerInfo[1], organizerInfo[2], contactList,
//                        eventList, Boolean.parseBoolean(organizerInfo[5]), Boolean.parseBoolean(organizerInfo[6]));
                loadOrganizer(organizerInfo[0], organizerInfo[1], organizerInfo[2], contactList,
                        eventList, Boolean.parseBoolean(organizerInfo[5]));
//                organizerHashMap.put(organizerInfo[0], loadedOrganizer);
//                organizerUsernameHashMap.put(organizerInfo[1], loadedOrganizer);
            }
        }
    }

    public ArrayList<String> storingOrganizers(){
        for(Map.Entry<String, Organizer> o : organizerHashMap.entrySet()) {
            storedOrganizer.add(o.getValue().stringRepresentation());
        }
        return storedOrganizer;

    }
}
