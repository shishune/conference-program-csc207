package useCase;

import entities.Attendee;
import entities.Speaker;
import entities.User;
//import entities.Event; //Todo - delete this if not needed

import java.util.List;

public class SpeakerActions extends UserAccountActions {
    // why does this return user instead of speaker type?
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

    /* Todo - delete this method if not needed.
    public void sendMessageToEvent (Event event, EventActions action){
        action.sendMessageToEvent(event);
    }
     */

}
