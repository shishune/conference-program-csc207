package useCase;

import entities.Speaker;
import entities.User;
//import entities.Event; //Todo - delete this if not needed

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

    /* Todo - delete this method if not needed.
    public void sendMessageToEvent (Event event, EventActions action){
        action.sendMessageToEvent(event);
    }
     */

}
