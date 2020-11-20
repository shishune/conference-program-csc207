package controller;
import entities.Room;
import entities.Speaker;
import entities.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import presenter.EventPresenter;
import presenter.MessagePresenter;
import presenter.OrganizerMessagePresenter;
import presenter.OrganizerEventPresenter;
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
    public OrganizerMainMenuController(User user, OrganizerController organizerController, RoomActions room, SpeakerActions speaker) {
        super(user, organizerController); // THIS DOESNT DO ANYTHING?
        this.user = user;
        this.displayMessage = new OrganizerMessagePresenter();
        this.displayEvent = new OrganizerEventPresenter();
        this.room = room;
        this.controller = organizerController;
        this.speaker = speaker;
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
        String title = scan.nextLine();

        boolean catcher = true;
        String dateTime = getDateTimeInput();
        String roomID = "";

        while (catcher){
            displayEvent.promptRoom();
            String roomName = scan.nextLine();

            if (!(room == null)) {
                if (room.returnRoomUsernameHashMap().containsKey(roomName)) {
                    roomID = roomName;
                    catcher = false;
                }
                else {
                    displayMessage.badRoom();
                    String reply = scan.nextLine();
                    if (reply.equals("ADD") || reply.equals("add") || reply.equals("Add")) {
                        option9();
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
            displayEvent.promptCancelEvent();
            String event = scan.nextLine();
            cancelEvent(event);
        } else {
            displayEvent.promptCancelEvent();
            String event = scan.nextLine();
            String dateTime = getDateTimeInput();
            rescheduleEvent(event, dateTime);
        }

    }

    public void option8() {
        displayEvent.viewall();
        String option = scan.nextLine();
        if (option.equals("x") || option.equals("X")) {
            displayEvent.promptCancelEvent();
            String event = scan.nextLine();
            cancelEvent(event);
        } else {
            displayEvent.promptCancelEvent();
            String event = scan.nextLine();
            String dateTime = getDateTimeInput();
            rescheduleEvent(event, dateTime);
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
                if (Integer.parseInt(t) < 17 && Integer.parseInt(t) > 9) {
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
     * @param event    the event ID
     * @param dateTime the string representing date and time
     */
    private void rescheduleEvent(String event, String dateTime) {
        if (controller != null) {
            if (controller.rescheduleEvent(event, dateTime)) {
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
    private void cancelEvent(String event) {
        if (controller != null) {
            if (controller.cancelEvent(event)) {
                displayEvent.successCancelEvent();
            } else {
                displayEvent.failedNoSuchEvent();
            }
        }
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