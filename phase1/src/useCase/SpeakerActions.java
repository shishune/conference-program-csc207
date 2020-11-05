package useCase;

import entities.Speaker;
import entities.User;

import java.util.List;

public class SpeakerActions extends UserAccountActions {

    public User createSpeaker(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        return new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
    }

    public User createSpeaker(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID();
        String userId = "S" + generateId;
        return new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
    }

}
