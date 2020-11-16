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
    private UserController controller;
    private OrganizerController oController;
    private User user;
    private OrganizerMessagePresenter displayMessage;
    private OrganizerEventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     * @param user the user
     * @param controller the controller responsible for user
     * @param organizerController the controller responsible for organizer
     */
    public OrganizerMainMenuController(User user, UserController controller, OrganizerController organizerController){
        super(user, controller); // THIS DOESNT DO ANYTHING?
        this.user = user;
        this.controller = controller;
        this.oController = organizerController;
        this.displayMessage = new OrganizerMessagePresenter();
        this.displayEvent = new OrganizerEventPresenter();
    }

    /**
     * Responds to menu option 2
     */
    public void option2(){
        displayMessage.printMenu();
        String option = scan.nextLine();
        OrganizerMessageMenuController menuController = new OrganizerMessageMenuController(this.oController);
        if (option.equals("1")){
            menuController.option1();
        }
        if (option.equals("2")){
            menuController.option2();
        }
        if (option.equals("3")){
            super.option2();
        }
    }

    /**
     * Responds to menu option 6
     */
    public void option6(){
        displayEvent.promptTitle();
        String title = scan.nextLine();
        displayEvent.promptSpeaker();
        String speakerUserName = scan.nextLine();
        boolean speakerExists = false;
        String speakerId = "";
        System.out.println("Inside option 6");
        System.out.println("Controller: " + controller);
        while(!speakerExists) {
            if (speakerUserName.equals("NEW")) {
                // TODO create speaker methods
                speakerExists = true;
            } else {
                // System.out.println(controller.returnUsernameHashMap());
                System.out.println(controller.returnUsernameHashMap());
                if (!controller.returnUsernameHashMap().isEmpty()){

                    if (controller.returnUsernameHashMap().containsKey(speakerUserName)) {
                        speakerId = controller.returnUsernameHashMap().get(speakerUserName).getId();
                        speakerExists = true;
                    } else {
                        System.out.println("this speaker does not exist");
                    }
                }
            }
        }
        String dateTime = getDateTimeInput();
        displayEvent.promptRoom();
        String roomID = scan.nextLine();
        List<Boolean> checks = oController.createEvent(title, speakerId, dateTime, roomID);
        if(checks.size()==1){
            displayEvent.successAddEvent();
        }
        else{
            if (checks.get(1)){
                displayEvent.failedDoubleBookRoom();
            }
            else if(!checks.get(1)){
                displayEvent.failedDoubleBookSpeaker();
            }
            displayEvent.failed();
        }
    }

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
    private void rescheduleEvent(String event, String dateTime){
        if(oController.rescheduleEvent(event, dateTime)){
            displayEvent.successRescheduleEvent();
        }
        else{
            displayEvent.failedRescheduleEvent();
        }
    }

    /**
     * helper function to cancel event
     * @param event the event ID
     */
    private void cancelEvent(String event){

        if(oController.cancelEvent(event)){
            displayEvent.successCancelEvent();
        }
        else{
            displayEvent.failedNoSuchEvent();
        }
    }

    /**
     * Responds to menu option 9
     */
    public void option9(){
        displayEvent.promptAddRoom();
        String room = scan.nextLine();
        oController.createRoom();
        displayEvent.successAddRoom();
    }
}
