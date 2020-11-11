package useCase;

import entities.Attendee;
import entities.Event;
import entities.Room;
import entities.User;
import useCase.GenerateID;

import java.util.HashMap;
import java.util.List;

public class AttendeeActions extends UserAccountActions{

    private HashMap<String, Attendee> AttendeesHashMap;

    public HashMap<String, Attendee> returnAttendeesHashMap(){
        return AttendeesHashMap;
    }

    public User createAttendee(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        //addUserToHashMap(userAttendee);
        return userAttendee;
    }

    public User createAttendee(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID();
        String userId = "A" + generateId;
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        //addUserToHashMap(userAttendee);
        return userAttendee;    }
}
