package useCase;

import entities.Organizer;
import entities.Speaker;
import entities.User;

import java.util.List;

public class OrganizerActions extends UserAccountActions {

    public User createOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        addUserToHashMap(userOrganizer);
        return userOrganizer;
    }

    public User createOrganizer(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        GenerateID generateId = new GenerateID();
        String userId = "O" + generateId;
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        addUserToHashMap(userOrganizer);
        return userOrganizer;

    }

    // TODO: Event Actions stuff
}
