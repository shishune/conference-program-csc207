package useCase;

import entities.*;
import gateway.LoadUpIGateway;
import gateway.LoadUp;
import useCase.GenerateID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendeeActions extends UserAccountActions{

    private HashMap<String, Attendee> attendeesHashMap;
    private HashMap<String, Attendee> attendeeUsernameHashMap;
    private ArrayList<String> attendees = new ArrayList<String>();
    public ArrayList<String> storedAttendee;
    private LoadUpIGateway loader;

    public HashMap<String, Attendee> returnAttendeesHashMap(){
        return attendeesHashMap;
    }

    public HashMap<String, Attendee> returnAttendeesUsernameHashMap(){
        return attendeeUsernameHashMap;
    }

    public AttendeeActions(LoadUpIGateway loader) {
        getAllAttendees(loader); // gets all messages from message.csv
        addAttendeeToHashMap();
        this.loader = loader;
        // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value
    }

    public User createAttendee(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userAttendee);
        addUsernameToHashMap(userAttendee);
        return userAttendee;
    }

    public User createAttendee(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID(loader);
        String userId = "A" + generateId;
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userAttendee);
        addUsernameToHashMap(userAttendee);
        return userAttendee;    }

    private void getAllAttendees(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        attendees = loader.getAllAttendees();
    }

    private void addAttendeeToHashMap() {
        for(String attendeeString : attendees) {
            String[] attendeeInfo = attendeeString.split(",");
            ArrayList<String> eventList = new ArrayList<String>();
            ArrayList<String> contactList = new ArrayList<String>();
            String[] events = attendeeInfo[4].split("%%");
            String[] contacts = attendeeInfo[3].split("%%");
            for (String e: events) {
                eventList.add(e);
            }
            for (String c: contacts) {
                contactList.add(c);
            }
            Attendee loadedAttendee = new Attendee(attendeeInfo[0], attendeeInfo[1], attendeeInfo[2], contactList,
                    eventList, Boolean.parseBoolean(attendeeInfo[5]), Boolean.parseBoolean(attendeeInfo[6]));
            attendeesHashMap.put(attendeeInfo[0], loadedAttendee);
            attendeeUsernameHashMap.put(attendeeInfo[1], loadedAttendee);
        }
    }

    public ArrayList<String> storingAttendees(){
        for(Map.Entry<String, Attendee> o : attendeesHashMap.entrySet()) {
            storedAttendee.add(o.getValue().stringRepresentation());
        }
        return storedAttendee;

    }
}
