package controllers;
import entities.Event;
import entities.User;
import presenters.OrganizerEventPresenter;
import presenters.OrganizerMessagePresenter;
import useCases.RoomActions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A controller class for organizer that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia, Mizna, Jiessie
 * @version 1
 * */
public class OrganizerMainMenuController extends MainMenuController {
    private controllers.OrganizerController controller; // = new OrganizerController();
    private useCases.RoomActions room; // = super.getRooms();
    private useCases.SpeakerActions speaker;
    private useCases.EventActions event;
    private useCases.OrganizerActions organizer;
    private User user;
    private OrganizerMessagePresenter displayMessage;
    private OrganizerEventPresenter displayEvent;
    private useCases.AttendeeActions attendee;
    private Scanner scan = new Scanner(System.in);
    private HashMap<String, User> usernameHashmap = new HashMap<String, User>();


    /**
     * Instantiates the main menu responder object
     *
     * @param user                the user
     * @param organizerController the controller responsible for organizer
     */
    public OrganizerMainMenuController(User user, OrganizerController organizerController, RoomActions room, useCases.SpeakerActions speaker, useCases.EventActions event, useCases.OrganizerActions organizerActions, useCases.AttendeeActions attendee) {
        super(user, organizerController, room, speaker);
        this.user = user;
        this.displayMessage = new OrganizerMessagePresenter();
        this.displayEvent = new OrganizerEventPresenter();
        this.room = room;
        this.controller = organizerController;
        this.speaker = speaker;
        this.event = event;
        this.organizer = organizerActions;
        this.attendee = attendee;
    }

    /**
     * Responds to menu option 2- send message to....
     */
    public void option2() {
        displayMessage.printMessageMenu();
        String option = scan.nextLine();
        controllers.OrganizerMessageMenuController menuController = new OrganizerMessageMenuController(this.controller);
        if (option.equals("1")) {
            menuController.option1(); // send message to all speakers
        }
        if (option.equals("2")) {
            menuController.option2(); // send message to all attendees of an event
        }
        if (option.equals("3")) {
            super.option2(); // send message to one person
        }
    }

    /**
     * Responds to menu option 5
     */
    public void option5() {
        displayEvent.promptViewContacts();
        String option = scan.nextLine();

        boolean catcher = true;

        while (catcher) {

            if (option.equals("x") || option.equals("X")) {
                displayEvent.promptEvent();
                String eventName = scan.nextLine();
                if (!event.getEventNames().containsKey(eventName)) {
                    displayEvent.noEvent();
                } else {
                    Event eventObject = event.getEventNames().get(eventName);
                    String eventID = event.getEventNames().get(eventName).getId();
                    if (organizer.getOrganizersEvents(user.getUsername()).contains(eventID)) {
                        displayEvent.allYourContactsEvent(eventObject.getAttendees());
                        catcher = false;
                    } else {
                        displayEvent.notYourEvent();
                    }
                }
            } else {
                List<String> newList = new ArrayList<>();
                for (String contact : user.getContactsList()) {
                    if (controller != null) {
                        newList.add(controller.returnUserIDHashMap().get(contact).getUsername());
                    }
                }
                displayEvent.allYourContacts(newList);
            }

        }
    }

    /**
     * Responds to menu option 6 - create an event
     */
    public void option6() {
        displayEvent.promptTitle();
        String title = "";
        boolean isVip = false;

        while (true) {
            //displayEvent.promptTitle();
            title = scan.nextLine();
            if (!validInput(title)){
               displayMessage.invalidInput();
            } else if (event.eventNameExists(title)) {
                displayEvent.eventExists();
            } else {
                displayEvent.promptVIP();
                String vip = scan.nextLine();
                if (vip.equals("Y") || vip.equals("y")){
                    isVip = true;
                    break;
                }
                if (vip.equals("N") || vip.equals("n")){
                    break;
                }
                else{
                    displayEvent.bad();
                }
            }
        }

        boolean catcher = true;
        List<String> dateTimes = getDateTimeInputs();
        String roomID = "";

        while (catcher) {
            displayEvent.promptRoom();
            String roomName = scan.nextLine();
            // fix TODO does skips the first input??
            if (room != null) {
                if (room.returnRoomUsernameHashMap() != null) {
                    if (room.returnRoomUsernameHashMap().containsKey(roomName)) {
                        roomID = room.returnRoomUsernameHashMap().get(roomName).getRoomId();
                        catcher = false;
                    } else {
                        displayMessage.badRoom();

                        String reply = scan.nextLine();
                        if (reply.equalsIgnoreCase("ADD")) {
                            option9();
                        }
                    }
                }
            }
        }

        List<String> speakerUsernames = new ArrayList<>();
        displayMessage.zeroSpeakers();//
        String zeroSpeaker = scan.nextLine();
        String speakerId = "";

        if (!zeroSpeaker.equalsIgnoreCase("y")) {
            displayMessage.numberOfSpeaker();
            String numberOfSpeakers = scan.nextLine();
            int i = 0;
            while (i < Integer.parseInt(numberOfSpeakers)) {
                boolean catcherUserName = true;
                while (catcherUserName && i < Integer.parseInt(numberOfSpeakers)) {
                    displayMessage.newOrNoSpeaker();
                    String speakerUserName = scan.nextLine();
                    i++;

                    if (speakerUserName.equalsIgnoreCase("NEW")) {
                        String speakerUsername = createSpeaker();
                        if (speakerUserName != null) {
                            speakerId = speaker.getIDFromName(speakerUsername);
                            speakerUsernames.add(speakerUsername);
                            displayMessage.speakerAdded();
                        }

                    } else {
                        boolean catcher1 = true;
                        while (catcher1) {
                            displayEvent.promptSpeaker();
                            String speakerUser = scan.nextLine();

                            if (controller != null && speaker != null) {
                                if (speaker.speakerExists(speakerUser)) {
                                    speakerId = speaker.returnUsernameHashMap().get(speakerUser).getId();

                                    if (speaker.returnUsernameHashMap().get(speakerUser).getEventList() != null) {
                                        if (controller.checkTimeConflict(speakerUser, dateTimes.get(0), dateTimes.get(1))) {
                                            displayEvent.failedDoubleBookSpeaker();
                                            catcher1 = false;

                                        } else {
                                            speakerUsernames.add(speakerId);
                                            displayMessage.speakerAdded();

                                        }
                                    }
                                    speakerUserName = speakerUser;
                                    catcher1 = false;
                                    catcherUserName = false;
                                } else {
                                    displayMessage.speakerDoesNotExist();
                                }
                                catcher1 = false;
                            }
                        }
                    }
                }
            }
        }

        int capacity = -1;
        boolean isInt = false;
        while(!isInt){
            displayMessage.eventCapacity();
            String capStr = scan.nextLine();
            try {
                if(Integer.parseInt(capStr) > 0){
                    capacity = Integer.parseInt(capStr);
                    isInt = true;
                } else {
                    displayMessage.invalidInput();
                }
            } catch (NumberFormatException e){
                displayMessage.invalidCapaity();
            }
        }

        if (controller != null && event != null) {
            List<Boolean> checks = controller.createEvent(title, speakerId, dateTimes.get(0), dateTimes.get(1), roomID, capacity, isVip);


            if (checks.get(0) && checks.size() == 1) {
                String eventToAdd = event.getEventNames().get(title).getId();
                organizer.addEventToUser(eventToAdd, user.getUsername());
                displayEvent.successAddEvent();
            } else {
                if (!checks.get(0)) {
                    int roomCap = room.findRoomFromId(roomID).getCapacity(); // necessary?
                    displayEvent.roomCapacityLow(roomCap);
                }
                if (!checks.get(1)) {
                    displayEvent.failedDoubleBookRoom();
                }
                if (!checks.get(2)) {
                    displayEvent.failedDoubleBookSpeaker();
                }
                displayEvent.notCreated();
            }
        }

    }

    /**
     * Responds to menu option 7
     */
    public void option7() {
        displayEvent.promptCancelMethod();
        String option = scan.nextLine();

        if (option.equalsIgnoreCase("x")) {

            boolean catcher = true;
            while (catcher) {
                displayEvent.promptCancelEvent();
                String eventCancel = scan.nextLine();

                if (eventCancel.equals("!")) {
                    displayEvent.warning();
                    String answer = scan.nextLine();
                    if (answer.equalsIgnoreCase("x")) {
                        break;
                    }
                }
                if (event.getEventNames().containsKey(eventCancel)) {
                    cancelEvent(eventCancel);
                    displayEvent.successCancelEvent();
                    catcher = false;

                } else {
                    displayEvent.noEvent();
                    displayEvent.breakPlease();
                }
            }
        } else {
            displayEvent.promptRescheduleMethod();
            String eventName = "";
            boolean catcher = true;

            while (catcher) {
                eventName = scan.nextLine();
                if (event.getEventNames().containsKey(eventName)) {
                    List<String> dateTimes = getDateTimeInputs();
                    rescheduleEvent(eventName, dateTimes.get(0), dateTimes.get(1));
                    catcher = false;
                } else {
                    displayEvent.noEvent();
                    displayEvent.breakPlease();
                }
                if (eventName.equals("!")) {
                    displayEvent.warning();
                    String answer = scan.nextLine();
                    if (answer.equalsIgnoreCase("x")) {
                        break;
                    }
                }

            }
        }
    }

    /**
     * Responds to menu option 8- view all events user has created
     */
    public void option8() {
        List<List<String>> e = new ArrayList<>();
        for (String event1 : user.getEventList()) {
            List<String> individualEvents = new ArrayList<>();
            if (event.getEvent(event1) != null) {
                individualEvents.add(event.getEvent(event1).getTitle());
                individualEvents.add(event.getEvent(event1).getDateTime());
                String roomName = room.findRoomFromId(event.getEvent(event1).getRoomID()).getRoomName();
                individualEvents.add(roomName);
                String speakerName = speaker.findUserFromId(event.getEvent(event1).getSpeaker()).getUsername();
                individualEvents.add(speakerName);
                e.add(individualEvents);
            }
        }
        displayEvent.displayEvents(e);
    }


    /**
     * helper function to take a start and end dateTime string object from separate date and time inputs
     *
     * @return the list of strings combining date and the start and end time based on separate user inputs for date and time
     */
    private List<String> getDateTimeInputs() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);
        String date = "";
        String startTime = "";
        String endTime = "";
        boolean catcher = true;

        while (catcher) {
            displayEvent.promptDate();
            String d1 = scan.nextLine();

            try {
                Date d = dateFormat.parse(d1);
                date = d.toString();
                catcher = false;
            } catch (ParseException ex) {
                displayEvent.badTime();
            }
        }

        catcher = true;
        while(catcher){
            displayEvent.promptStartTime();
            while (!scan.hasNextInt()) {
                    displayEvent.badTime();
                    scan.next();
                }
            int t1 = scan.nextInt();
            if (t1 < 17 && t1 >= 9){
                startTime = String.valueOf(t1);
                catcher = false;
            }
            else{
                displayEvent.badTime();
            }
        }


        catcher = true;
        while(catcher){
            displayEvent.promptEndTime();
            while (!scan.hasNextInt()) {
                displayEvent.badTime();
                scan.next();
            }
            int t1 = scan.nextInt();
            if (t1 < 17 && t1 >= 9){
                endTime = String.valueOf(t1);
                catcher = false;
            }
            else{
                displayEvent.badTime();
            }
        }

        List<String> dateTimes = new ArrayList<>();
        dateTimes.add(date + "-" + startTime);
        dateTimes.add(date + "-" + endTime);
        return dateTimes;
    }

    /**
     * helper function to reschedule event
     *
     * @param eventTitle the event title
     * @param newStartDateTime the new start date and time for the event to be changed to
     * @param newEndDateTime the new end date and time for the event to be changed to
     */
    private void rescheduleEvent(String eventTitle, String newStartDateTime, String newEndDateTime) {
        if (controller != null) {
            String e = event.getEventNames().get(eventTitle).getId();
            if (controller.rescheduleEvent(e, newStartDateTime, newEndDateTime)) {
                displayEvent.successRescheduleEvent();
            } else {
                displayEvent.failedRescheduleEvent();
            }
        }
    }

    /**
     * helper function to cancel event
     *
     * @param event the event ID
     */
    private boolean cancelEvent(String event) {
        if (controller != null) {
            if (controller.cancelEvent(event)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Responds to menu option 9- create a new room
     */
    public void option9() {

        boolean catcher = true;

        while (catcher) {
            displayEvent.promptAddRoom();
            String roomName = scan.nextLine();
            if (room.returnRoomUsernameHashMap().containsKey(roomName)) {
                displayMessage.alreadyAddedRoom();
                catcher = false;
            } else if (controller != null) {
                if (controller.createRoom(roomName)) {
                    displayMessage.addedRoom();
                    catcher = false;
                }
            }

        }
    }

    /***
     * Responds to menu option 10- create a new user
     */
    public void option10() {
        boolean loop = true;
        displayMessage.printUserMenu();
        while(loop) {
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("x")) {
                loop = false;
            } else if (option.equals("1")) {
                createSpeaker();
                loop = false;
            } else if (option.equals("2")) {
                createAttendee();
                loop = false;
            } else if (option.equals("3")) {
                loop = false;
            } else {
                displayMessage.notValidChoice();
            }
        }

    }

    /***
     * create a new speaker
     */
    private String createSpeaker() {
        displayMessage.createSpeakerMessage();
        while (true){
            displayMessage.userUsernamePrompt();
            String speakerUsername = scan.nextLine();

            if (speakerUsername.equalsIgnoreCase("x")) {
                displayMessage.exit();
                break;
            } else if (controller.usernameExists(speakerUsername)) {
                displayMessage.userExists();
            } else if (controller != null && validInput(speakerUsername)) {
                displayMessage.userPasswordPrompt();
                String newSpeakerPassword = scan.nextLine();
                if (validInput(newSpeakerPassword)) {
                    controller.createSpeaker(speakerUsername, newSpeakerPassword);
                    displayMessage.userCreated();
                    return speakerUsername;
                } else {
                    displayMessage.invalidInput();
                }
            } else {
                displayMessage.invalidInput();
            }
        }
        return null;
    }

    /***
     * create a new user
     */
    private String createAttendee() {
        displayMessage.createAttendeeMessage();
        while (true) {
            displayMessage.userUsernamePrompt();
            String newAttendeeUsername = scan.nextLine();

            if (newAttendeeUsername.equals("x") || newAttendeeUsername.equals("X")) {
                break;
            } else if (controller.usernameExists(newAttendeeUsername)) {
                displayMessage.userExists();
            } else if (controller != null && validInput(newAttendeeUsername)) {
                displayMessage.userPasswordPrompt();
                String newAttendeePassword = scan.nextLine();
                displayEvent.VIPStatusPrompt();
                boolean VIPStatus = false;
                String responseInput = scan.nextLine();
                if (responseInput.equals("VIP")) {
                    VIPStatus = true;
                }
                if (validInput(newAttendeePassword)) {
                    controller.createAttendee(newAttendeeUsername, newAttendeePassword, VIPStatus);
                    displayMessage.userCreated();
                    return newAttendeeUsername;
                } else {
                    displayMessage.invalidInput();
                }
            } else {
                displayMessage.invalidInput();
            }
        }
        return null;
    }

    /***
     * Responds to menu option 11- view all created events
     */
    public void option11() {
        super.option8();
    }


}