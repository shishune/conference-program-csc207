package useCase;

import entities.Attendee;
import entities.User;
import useCase.GenerateID;

import java.util.List;

public class AttendeeActions extends UserAccountActions{


    public User createAttendee(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {

        return new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
    }

    public User createAttendee(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID();
        String userId = "A" + generateId;
        return new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
    }
}
