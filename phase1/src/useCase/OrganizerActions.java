package useCase;

import entities.Event;
import entities.Organizer;
import entities.Speaker;
import entities.User;

import useCase.EventActions;
import gateway.LoadUp;


import java.util.List;

public class OrganizerActions extends UserAccountActions {

    private LoadUp loader = new LoadUp();

    public User createOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userOrganizer);
        return userOrganizer;
    }

    public User createOrganizer(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        GenerateID generateId = new GenerateID();
        String userId = "O" + generateId;
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userOrganizer);
        return userOrganizer;

    }

    public User createSpeaker(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Speaker userSpeaker = new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userSpeaker);
        return userSpeaker;
    }

    public User createSpeaker(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID();
        String userId = "S" + generateId;
        Speaker userSpeaker = new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userSpeaker);
        return userSpeaker;
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
}
