package useCase;

import entities.*;
import useCase.GenerateID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendeeActions extends UserAccountActions{

    private HashMap<String, Attendee> AttendeesHashMap;
    private ArrayList<String> attendees = new ArrayList<String>();

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

    private void addAttendeeToHashMap() {
        for(String attendeeString : attendees) {
            String[] attendeeInfo = attendeeString.split(",");
            for()
            ArrayList<String> attendees = new ArrayList<String>();

            Attendee loadedAttendee = new Attendee(attendeeInfo[0], attendeeInfo[1], attendeeInfo[2], attendeeInfo[3], attendeeInfo[4], attendeeInfo[5], attendeeInfo[6]);
            AttendeesHashMap.put(attendeeInfo[0], );
        }
    }
}
