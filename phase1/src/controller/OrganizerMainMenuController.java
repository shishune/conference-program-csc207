package controller;
import entities.User;
import java.util.List;
import presenter.EventPresenter;
import presenter.MessagePresenter;
import presenter.OrganizerMessagePresenter;
import presenter.OrganizerEventPresenter;
import useCase.SpeakerActions;

import java.util.Scanner;

/**
 * A controller class for organizer that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class OrganizerMainMenuController extends MainMenuController{
    private OrganizerController controller;
    private User user;
    private OrganizerMessagePresenter displayMessage;
    private OrganizerEventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     * @param user the user
     * @param organizerController the controller responsible for organizer
     */
    public OrganizerMainMenuController(User user, OrganizerController organizerController){
        super(user, organizerController); // THIS DOESNT DO ANYTHING?
        this.user = user;
        this.displayMessage = new OrganizerMessagePresenter();
        this.displayEvent = new OrganizerEventPresenter();
    }

    /**
     * Responds to menu option 2
     */
    public void option2(){
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
        displayEvent.promptSpeaker();
        String speakerUserName = scan.nextLine();
        String speakerId = "";

        //TODO: new! what if someone is named new??
        if (speakerUserName.equals("NEW") || speakerUserName.equals("new") || speakerUserName.equals("New")) {
            displayMessage.speakerUsernamePrompt();
            String newSpeakerName = scan.nextLine();

            displayMessage.speakerPasswordPrompt();
            String newSpeakerPassword = scan.nextLine();
            if (controller != null) {
                controller.createSpeaker(newSpeakerName, newSpeakerPassword);
        }
        }

        if (controller != null) {
            if (controller.returnUsernameHashMap().containsKey(speakerUserName)) {
                speakerId = controller.returnUsernameHashMap().get(speakerUserName).getId();
            }
            else {
                displayMessage.speakerNotCreated();
            }
        }

        String dateTime = getDateTimeInput();
        displayEvent.promptRoom();
        String roomID = scan.nextLine();
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
        }
    }

//        boolean speakerExists = false;
//        String speakerId = "";
//        System.out.println("Inside option 6");
//        System.out.println("Controller: " + controller);
//        // if (user != null && controller != null) {
//            //while(!speakerExists) {
//            if (speakerUserName.equals("NEW")) {
//                // TODO create speaker methods
//                speakerExists = true;
//            } else {
//                // System.out.println(controller.returnUsernameHashMap());
//                if (!controller.returnUsernameHashMap().isEmpty()) {
//
//                    if (controller.returnUsernameHashMap().containsKey(speakerUserName)) {
//                        speakerId = controller.returnUsernameHashMap().get(speakerUserName).getId();
//                        speakerExists = true;
//                    } else {
//                        System.out.println("this speaker does not exist");
//                    }
//                }
//            }
        //}

    /**
     * Responds to menu option 7
     */
    public void option7(){
        displayEvent.promptCancelMethod();
        String option = scan.nextLine();
        if (option.equals("x")||option.equals("X")){
            displayEvent.promptCancelEvent();
            String event = scan.nextLine();
            cancelEvent(event);
        }
        else{
            displayEvent.promptCancelEvent();
            String event = scan.nextLine();
            String dateTime = getDateTimeInput();
            rescheduleEvent(event, dateTime);
        }

    }

    /**
     * helper function to take a dateTime string object from separate date  and time inputs
     * @return the string combining date and time based on separate user inputs for date and time
     */
    private String getDateTimeInput(){
        displayEvent.promptDate();
        String date = scan.nextLine();
        displayEvent.promptTime();
        String time = scan.nextLine();
        String dateTime = date+"/"+time;
        return dateTime;
    }

    /**
     * helper function to reschedule event
     * @param event the event ID
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
     * @param event the event ID
     */
    private void cancelEvent(String event){
    if (controller != null){
        if(controller.cancelEvent(event)){
            displayEvent.successCancelEvent();
        }
        else{
            displayEvent.failedNoSuchEvent();
        }
    }
    }

    /**
     * Responds to menu option 9
     */
    public void option9() {
        displayEvent.promptAddRoom();
        String room = scan.nextLine();
        if (controller != null) {
            controller.createRoom();
        }
            displayEvent.successAddRoom();
    }
}
