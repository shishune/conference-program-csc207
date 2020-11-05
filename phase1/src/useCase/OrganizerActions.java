package useCase;

import entities.Organizer;
import entities.User;

import java.util.List;

public class OrganizerActions {

    public User createOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        return new Organizer(userId, username, password, contactsList, eventList, isLogin, true);
    }

    public User createOrganizer(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        String userId = "O" + GenerateID.generateId();
        return new Organizer(userId, username, password, contactsList, eventList, isLogin, true);

    }
}
