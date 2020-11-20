package controller;
import entities.Event;
import entities.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import presenter.OrganizerMessagePresenter;
import presenter.OrganizerEventPresenter;
import useCase.EventActions;
import useCase.OrganizerActions;
import useCase.SpeakerActions;
import useCase.RoomActions;

import java.util.Scanner;

/**
 * A controller class for organizer that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class OrganizerMainMenuController extends MainMenuController {
    private OrganizerController controller; // = new OrganizerController();
    private RoomActions room; // = super.getRooms();
    private SpeakerActions speaker;
    private EventActions event;
    private OrganizerActions organizer;
    private User user;
    private OrganizerMessagePresenter displayMessage;
    private OrganizerEventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     *
     * @param user                the user
     * @param organizerController the controller responsible for organizer
     */
    public OrganizerMainMenuController(User user, OrganizerController organizerController, RoomActions room, SpeakerActions speaker, EventActions event, OrganizerActions organizerActions) {
        super(user, organizerController); // THIS DOESNT DO ANYTHING?
        this.user = user;
        this.displayMessage = new OrganizerMessagePresenter();
        this.displayEvent = new OrganizerEventPresenter();
        this.room = room;
        this.controller = organizerController;
        this.speaker = speaker;
        this.event = event;
        this.organizer = organizerActions;
    }

    /**
     * Responds to menu option 2
     */
    public void option2() {
        displayMessage.printMenu();
        String option = scan.nextLine();
        OrganizerMessageMenuController menuController = new OrganizerMessageMenuController(this.controller);
        if (option.equals("1")){
            menuController.option1(); // send message to all speakers
        }
        if (option.equals("2")){
            menuController.option2(); // send message to all attendees of an event
        }
        if (option.equals("3")){
            super.option2(); // send message to one person
        }
    }

    /**
     * Responds to menu option 6
     */
    public void option6() {
        displayEvent.promptTitle();
        String title ="";

        while(true){
            //displayEvent.promptTitle();
            String t = scan.nextLine();
            if(event.getEventNames().containsKey(t)){
                displayEvent.eventExists();
            }
            else{
                title = t;
                break;
            }
        }

        boolean catcher = true;
        String dateTime = getDateTimeInput();
        String roomID = "";

        while (catcher){
            displayEvent.promptRoom();
            String roomName = scan.nextLine();

            if (room != null) {
                if (room.returnRoomUsernameHashMap() != null) {
                    if (room.returnRoomUsernameHashMap().containsKey(roomName)) {
                        roomID = room.returnRoomUsernameHashMap().get(roomName).getRoomId();
                        catcher = false;
                    } else {
                        displayMessage.badRoom();
                        String reply = scan.nextLine();
                        if (reply.equals("ADD") || reply.equals("add") || reply.equals("Add")) {
                            option9();
                        }
                    }
                }
            }
        }

        String speakerId = "";

        boolean catcherUserName = true;

        while (catcherUserName) {

            displayMessage.newOrNoSpeaker();
            String speakerUserName = scan.nextLine();

            if (speakerUserName.equals("NEW") || speakerUserName.equals("new") || speakerUserName.equals("New")) {
                option10();
                catcherUserName = false;

            }

            else {
                boolean catcher1 = true;
                while (catcher1) {
                    displayEvent.promptSpeaker();
                    String speakerUser = scan.nextLine();

                    if (controller != null && speaker != null) {
                        if (speaker.returnUsernameHashMap().containsKey(speakerUser)) {
                            speakerId = speaker.returnUsernameHashMap().get(speakerUser).getId();

                            if (speaker.returnUsernameHashMap().get(speakerUser).getEventList() != null){
                                if (controller.checkTimeConflict(speakerUser, dateTime)){
                                    displayEvent.failedDoubleBookSpeaker();
                                    catcher1 = false;
                                }
                            }
                            speakerUserName = speakerUser;
                            catcher1 = false;
                            catcherUserName = false;
                        }
                        else {
                            displayMessage.speakerNotCreated();
                        }
                        catcher1 = false;
                    }
                }
            }
        }

        if (controller != null) {

            List<Boolean> checks = controller.createEvent(title, speakerId, dateTime, roomID);
            if (checks.size() == 1) {
                displayEvent.successAddEvent();
            } else {
                if (checks.get(1)) {
                    displayEvent.failedDoubleBookRoom();
                } else if (!checks.get(1)) {
                    displayEvent.failedDoubleBookSpeaker();
                }
                displayEvent.failed();
            }
        } else {
            displayEvent.failed();
        }

    }

    /**
     * Responds to menu option 7
     */
    public void option7() {
        displayEvent.promptCancelMethod();
        String option = scan.nextLine();

        if (option.equals("x") || option.equals("X")) {

            boolean catcher = true;
            while(catcher){
                displayEvent.promptCancelEvent();
                String eventCancel = scan.nextLine();
                if(event.getEventNames().containsKey(eventCancel)) {
                    cancelEvent(eventCancel);
                    displayEvent.successCancelEvent();
                    catcher = false;

                }
                else {
                    displayEvent.noEvent();
                }
            }
        }
        else {
            displayEvent.promptRescheduleMethod();
            String e = "";
            boolean catcher = true;

            while(catcher){
                String ev = scan.nextLine();
                if (event.getEventNames().containsKey(ev)){
                    e = ev;
                    String dateTime = getDateTimeInput();
                    rescheduleEvent(ev, dateTime);
                    catcher = false;
                }
                else{
                    displayEvent.noEvent();
                }

            }
        }
    }


    public void option8() {
        displayEvent.viewall();
        displayEvent.displayEvents(controller.viewAvailableSchedule(user.getUsername()));
    }


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
                        System.out.println(eventObject.getAttendees());
                    } else {
                        displayEvent.notYourEvent();
                    }
                }
            } else {
                organizer.
                System.out.println("I didnt code this yet rip");
            }

        }
    }


    /**
     * helper function to take a dateTime string object from separate date  and time inputs
     *
     * @return the string combining date and time based on separate user inputs for date and time
     */
    private String getDateTimeInput() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);
        String date = "";
        String time = "";
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
        while (catcher) {

            displayEvent.promptTime();
            if (scan.hasNextInt()) {
                String t = scan.nextLine();
                if (Integer.parseInt(t) < 17 && Integer.parseInt(t) >= 9) {
                    time = t;
                    catcher = false;
                } else {
                    displayEvent.badTime();
                }
            }
        }

        String dateTime = date + "-" + time;
        return dateTime;
    }

    /**
     * helper function to reschedule event
     *
     * @param events    the event title
     * @param dateTime the string representing date and time
     */
    private void rescheduleEvent(String events, String dateTime) {
        if (controller != null) {
            String e = event.getEventNames().get(events).getId();
            if (controller.rescheduleEvent(e, dateTime)) {
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
     * Responds to menu option 9
     */
    public void option9() {

        boolean catcher = true;
        String roomID;

        while (catcher) {
            displayEvent.promptAddRoom();
            String roomName = scan.nextLine();
            if (room.returnRoomUsernameHashMap().containsKey(roomName)) {
                displayMessage.alreadyAddedRoom();
                catcher = false;
            } else if (controller != null) {
                if (controller.createRoomActions(roomName)) {
                    displayMessage.addedRoom();
                    catcher = false;
                }
            }

        }
    }

    public void option10() {

        boolean catcherUserName = true;

        while (catcherUserName) {
            displayMessage.speakerUsernamePrompt();
            String newSpeakerName = scan.nextLine();


            if (speaker.returnUsernameHashMap().containsKey(newSpeakerName)) {
                displayMessage.alreadySpeaker();
            }

            else if (controller != null) {
                displayMessage.speakerPasswordPrompt();
                String newSpeakerPassword = scan.nextLine();
                controller.createSpeaker(newSpeakerName, newSpeakerPassword);
                displayMessage.speakerCreated();
                catcherUserName = false;
            }
        }

        }
    }

//        displayEvent.promptAddRoom();
//        String room = scan.nextLine();
//        if (controller != null) {
//            controller.createRoom(room);
//        }
//            displayEvent.successAddRoom();
//    }
//while (catcher){
//        displayEvent.promptRoom();
//        String roomName = scan.nextLine();
//        if (!(room == null)) {
//        if (room.returnHashMap().containsKey(roomName)) {
//        roomID = roomName;
//        catcher = false;
//        } else {
//        displayMessage.badRoom();
//        String reply = scan.nextLine();
//        if (reply.equals("ADD") || reply.equals("add") || reply.equals("Add")) {
//        displayMessage.newRoom();
//        String name = scan.nextLine();
//        if (!(controller == null) && controller.createRoomActions(name)) {
//        displayMessage.addedRoom();
//        catcher = false;
//        }
//        }
//        }
//        }
//        }
//            displayMessage.speakerUsernamePrompt();
//            String newSpeakerName = scan.nextLine();
//            displayMessage.speakerPasswordPrompt();
//
//            while (catcherUserName) {
//                String newSpeakerPassword = scan.nextLine();
//                if (controller != null) {
//                    controller.createSpeaker(newSpeakerName, newSpeakerPassword);
//                    displayMessage.speakerCreated();
//                    catcherUserName = false;
//                }
//            }
//                        displayMessage.newRoom();
//                        String name = scan.nextLine();
//                        if (!(controller == null) && controller.createRoomActions(name)) {
//                        displayMessage.addedRoom();
//                        catcher = false;