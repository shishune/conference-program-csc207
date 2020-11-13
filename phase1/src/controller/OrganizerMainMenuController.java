package controller;
import entities.User;
import java.util.List;
import presenter.EventPresenter;
import presenter.MessagePresenter;
import presenter.OrganizerMessagePresenter;
import presenter.OrganizerEventPresenter;

import java.util.Scanner;

public class OrganizerMainMenuController extends MainMenuController{
    private UserController controller;
    private OrganizerController oController;
    private User user;
    private OrganizerMessagePresenter displayMessage;
    private OrganizerEventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    public OrganizerMainMenuController(User user, UserController controller, OrganizerController organizerController){
        super(user, controller);
        this.oController = organizerController;
        this.displayMessage = new OrganizerMessagePresenter();
        this.displayEvent = new OrganizerEventPresenter();
    }
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
            menuController.option3();
        }
        if (option.equals("4")){
            menuController.option4();
        }
    }
    public void option6(){
        displayEvent.promptTitle();
        String title = scan.nextLine();
        displayEvent.promptSpeaker();
        String speakerId = scan.nextLine();
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
    private String getDateTimeInput(){
        displayEvent.promptDate();
        String date = scan.nextLine();
        displayEvent.promptTime();
        String time = scan.nextLine();
        String dateTime = date+"/"+time;
        return dateTime;
    }
    private void rescheduleEvent(String event, String dateTime){
        if(oController.rescheduleEvent(event, dateTime)){
            displayEvent.successRescheduleEvent();
        }
        else{
            displayEvent.failedRescheduleEvent();
        }
    }
    private void cancelEvent(String event){

        if(oController.cancelEvent(event)){
            displayEvent.successCancelEvent();
        }
        else{
            displayEvent.failedNoSuchEvent();
        }
    }
    public void option9(){
        displayEvent.promptAddRoom();
        String room = scan.nextLine();
        oController.createRoom();
        displayEvent.successAddRoom();
    }
}
