package controller;

import entity.Event;
import entity.Message;
import entity.Room;
import entity.User;
import useCase.RoomActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * A controller class for users. UserController is a parent class to OrganizerController, AccountController and
 * SpeakerController.
 * @author Jiessie and Mizna
 * @version 1
 * */

public class UserController {
    private useCase.UserAccountActions user; // = getUserAccountActions();
    private useCase.MessageActions message; // = super.getMessages();
    private useCase.EventActions e;  //= super.getEvents();
    private useCase.RoomActions room; // = super.getRooms();
    private useCase.AttendeeActions attendee; // = super.getAttendees();
    private useCase.OrganizerActions organizer; // = super.getOrganizers();
    private useCase.SpeakerActions speaker; // = super.getSpeakers();
    private HashMap<String, User> usernameHashmap = new HashMap<String, User>();
    private HashMap<String, User> userIdHashmap = new HashMap<String, User>();

    /**
     * Instantiates a new UserController object. Creates an instance of UserAccountActions, MessageActions, EventActions
     * AttendeeActions, RoomActions.
     */
    public UserController(useCase.EventActions events, RoomActions rooms, useCase.MessageActions message, char userType,
                          useCase.AttendeeActions attendee, useCase.OrganizerActions organizer, useCase.SpeakerActions speaker) {
        this.message = message;
        this.e = events;
        this.room = rooms;
        if (userType == 'o') {
            this.user = organizer;
        } else if (userType == 's') {
            this.user = speaker;
        } else {
            this.user = attendee;
        }

        this.attendee = attendee;
        this.organizer = organizer;
        this.speaker = speaker;
    }
    //alternate constructor to access methods that do not need so many parameters
//    public UserController(){};

    public HashMap<String, User> returnUserUsernameHashMap() {
        if (!(attendee == null) && !attendee.returnUsernameHashMap().isEmpty()) {
            usernameHashmap.putAll(attendee.returnUsernameHashMap());
        }
        if (!(organizer == null) && !organizer.returnUsernameHashMap().isEmpty()) {
            usernameHashmap.putAll(organizer.returnUsernameHashMap());
        }
        if (!(speaker == null) && !speaker.returnUsernameHashMap().isEmpty()) {
            usernameHashmap.putAll(speaker.returnUsernameHashMap());
        }
//        if (!(user == null) && !user.returnUsernameHashMap().isEmpty()){
////            usernameHashmap.putAll(speaker.returnSpeakerUsernameHashMap());
////        }
        return usernameHashmap;

    }


    public HashMap<String, User> returnUserIDHashMap() {
        if (!attendee.returnIDHashMap().isEmpty()) {
            userIdHashmap.putAll(attendee.returnIDHashMap());
        }
        if (!organizer.returnIDHashMap().isEmpty()) {
            userIdHashmap.putAll(organizer.returnIDHashMap());
        }
        if (!speaker.returnIDHashMap().isEmpty()) {
            userIdHashmap.putAll(speaker.returnIDHashMap());
        }
        return userIdHashmap;
    }

    /**
     * Sends a message to a user
     *
     * @param content  the message to be sent
     * @param receiver the user who will be getting the message
     * @param sender   the user who is sending the message
     * @return boolean true if message was successfully sent, false if it was not
     */
    public boolean sendMessage(String sender, String receiver, String content) {
        if (user != null) {
            HashMap<String, User> usernameHash = returnUserUsernameHashMap();
            // add receiver to contact list of sender
            user.addUserContactList(sender, receiver, usernameHash);
            user.addUserContactList(receiver, sender, usernameHash);
            if (usernameHash.get(receiver) == null) {
                return false;
            }
            String receiverId = usernameHash.get(receiver).getId();
            String senderId = usernameHash.get(sender).getId();
            if (user.findUserFromUsername(sender).getContactsList().contains(receiverId)) {
                message.createMessage(senderId, receiverId, content);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Adds a user to the contact list of another user
     *
     * @param toMe  the username who's contact list that is updated
     * @param addMe the username who will be added
     * @return boolean true if contact was successfully added, false if it was not
     */
    public boolean addContact(String addMe, String toMe) { // toMe should be a username
        if (user != null) {
            HashMap<String, User> userUsernameHashMap = returnUserUsernameHashMap();
            User me = user.findUserFromUsername(toMe);
            return user.addUserContactList(toMe, addMe, userUsernameHashMap);
        }
        return false;
    }

    public boolean deleteContact(String removeMe, String toMe) {
        return user.removeUserContactList(toMe, removeMe);
    }

//    public String viewMessageOneSender (String fromMe){
//        MessageActions messageActions = new MessageActions();
//        return messageActions.printMessages(fromMe).toString();
//    };

    //edited to work with presenter, let me know if you disagree

    /**
     * Shows the messages from one user to another
     *
     * @param toMe   the user receiving the messages
     * @param fromMe the user who is sending the messages
     * @return array of a list of messages
     */
    public ArrayList<ArrayList<String>> viewMessages(String fromMe, String toMe) {
        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        HashMap<String, User> usernameHash = returnUserUsernameHashMap();
        List<Message> messageList = message.getMessages(fromMe, toMe);
        for (Message message : messageList) {
            ArrayList<String> info = new ArrayList<String>();
            HashMap<String, User> userIdHash = returnUserIDHashMap();
            String receiverUsername = userIdHash.get(message.getReceiverId()).getUsername();
            String senderUsername = userIdHash.get(message.getSenderId()).getUsername();
            if (senderUsername != null) {
                info.add(senderUsername);
            }
            if (receiverUsername != null) {
                info.add(receiverUsername);
            }
            if (message.getTimeSent() != null) {
                info.add(message.getTimeSent());
            }
            if (message.getMessage() != null) {
                info.add(message.getMessage());
            }
            if (info.get(0) != null && info.get(1) != null && info.get(2) != null && info.get(3) != null) {
                messages.add(info);
            }
        }
        return messages;
    }

    //need this for presenter

    /**
     * Shows the contacts of a user
     *
     * @param userid the user who wants to see their contacts
     * @return array of other usernames who are in their contacts
     */

    public ArrayList<String> viewContacts(String userid) {
        ArrayList<String> contacts = new ArrayList<String>();
        if (user != null) {
            List<String> usersList = user.findUserFromId(userid).getContactsList();
            for (String id : usersList) {
                contacts.add(returnUserIDHashMap().get(id).getUsername());
            }
        }
        return contacts;
    }
    //edited so that presenter can print fail messages based on why user cannot attend event

    /**
     * Adds an event to a user and an attendee to an event
     *
     * @param eventName the event the user wants to attend
     * @param userName  the attendee who wants to attend an event
     * @return list of booleans if users can attend event or not
     */

    public List<Boolean> signupEvent(String eventName, String userName) {
        if (e.getEventNames().containsKey(eventName)) {
            String eventId = e.getEventFromName(eventName).getId();
            List<Boolean> checks = new ArrayList<Boolean>();
            if (!e.eventExists(eventId)) {
                checks.add(false);
                return checks;
            }
            Event e1 = e.getEvent(eventId);
            User a1 = returnUserUsernameHashMap().get(userName);

            if (!checkConflictSpots(eventId) && !checkConflictTime(userName, eventId)) {
                e.addAttendee(e1.getId(), a1.getId());
                a1.getEventList().add(eventId);
                checks.add(true);
                return checks;
            }
            checks.add(false);
            if (!checkConflictSpots(eventId)) {
                checks.add(true);
            } else {
                checks.add(false);
            }
            if (!checkConflictTime(userName, eventId)) {
                checks.add(true);
            } else {
                checks.add(false);
            }
            return checks;
        }
        List<Boolean> checks = new ArrayList<>();
        checks.add(false);
        return checks;
    }

    /**
     * Shows the events a given user is attending
     *
     * @param user the user who wants to see their events
     * @return list of the events that a user is attending in the form of a list
     * with the string representation of each aspect (title, dateTime, etc)
     */

    public List<List<String>> viewOwnSchedule(String user) {
        User a1 = returnUserUsernameHashMap().get(user);
        List<String> eventList = a1.getEventList();
        List<List<String>> scheduleList = new ArrayList<List<String>>();
        if (e != null) {
            for (String event : eventList) {
                String title = e.getEvent(event).getTitle();
                String dateTime = e.getEvent(event).getDateTime();
                String roomId = e.getEvent(event).getRoomID();
                String speaker = e.getEvent(event).getSpeaker();
                List<String> info = new ArrayList<String>();
                info.add(title);
                info.add(dateTime);
                info.add(roomId);
                info.add(speaker);
                scheduleList.add(info);
            }
        }
        return scheduleList;
    }

    /**
     * Shows the events a given user could be attending
     *
     * @param user the user who wants to see possible events to attend
     * @return string of events that an user could attend
     */

    public List<List<String>> viewAvailableSchedule(String user) {
        Set<String> allEvents = e.getEvents().keySet();
        List<String> availableS = new ArrayList<>();
        List<String> targetList = new ArrayList<>(allEvents);

        for (String s : targetList) {
            if (!checkConflictTime(user, s) && !checkConflictSpots(s)) {
                availableS.add(s);
            }
        }
        List<List<String>> scheduleList = new ArrayList<List<String>>();
        for (String event : availableS) {
            String title = e.getEvent(event).getTitle();
            String dateTime = e.getEvent(event).getDateTime();
            String roomId = e.getEvent(event).getRoomID();
            String speaker = e.getEvent(event).getSpeaker();
            List<String> info = new ArrayList<String>();
            info.add(title);
            info.add(dateTime);
            info.add(roomId);
            info.add(speaker);
            scheduleList.add(info);
        }
        return scheduleList;
    }

    /**
     * Shows the spots available in an event
     *
     * @param eventID the event that is given
     * @return int of the number of spots available
     */

    public int spotsAvailable(String eventID) {
        String rooms = e.getEvent(eventID).getRoomID();

        Room r1 = room.returnHashMap().get(rooms);

        return r1.getCapacity() - e.getEvent(eventID).getAttendees().size();

    }

    /**
     * Checks if users can attend an event or not
     *
     * @param username the user who wants to see if they can attend an event
     * @param eventID  the event the user wants to attend
     * @return boolean if user can attend event
     */

    private boolean checkConflictTime(String username, String eventID) {
        //return true if there is a conflict
        String timeEvent = e.getEvent(eventID).getDateTime();
        if (user != null) {

            User u = user.findUserFromUsername(username);

            for (int i = 0; i < u.getEventList().size(); i++) {

                String eventId = u.getEventList().get(i);

                String time = e.getEvent(eventId).getDateTime();

                if (time.equals(timeEvent)) {
                    return true;
                }
            }

            return false;
        }
        return false;
    }

    /**
     * Checks if event is full or not
     *
     * @param eventID the event that is being checked
     * @return boolean if event is full
     */
    private boolean checkConflictSpots(String eventID) {
        return spotsAvailable(eventID) == 0;

    }

    /**
     * To be overridden by OrganizerController
     *
     * @param eventID the event to be cancelled
     * @return boolean if event was cancelled or not
     */
    public boolean cancelEvent(String eventID) {
        return false;
    }

    /**
     * To be overridden by AttendeeController
     *
     * @param eventID  the event to be removed
     * @param username the user who wants to leave the event
     * @return boolean if event was removed or not
     */
    public boolean leaveEvent(String eventID, String username) {
        return false;
    }

    public boolean checkTimeConflict(String username, String dateTime) {
        //return true if there is a conflict
        //String timeEvent = e.getEvent(event).getDateTime();
        if (user != null) {

            User u = user.findUserFromUsername(username);

            if ((u != null) && u.getEventList() != null) {

                for (int i = 0; i < u.getEventList().size(); i++) {

                    String name = u.getEventList().get(i);

                    String time = e.getEvent(name).getDateTime();

                    if (time.equals(dateTime)) {
                        return true;
                    }
                }
            }

            return false;
        }
        return false;
    }
}