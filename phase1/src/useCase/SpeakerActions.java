package useCase;

import entities.Speaker;
import entities.User;

import java.util.List;

public class SpeakerActions extends UserAccountActions {

    public User createAttendee(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        return new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
    }

    public User createAttendee(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        String userId = "S" + GenerateID.generateId();
        return new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
    }

}
