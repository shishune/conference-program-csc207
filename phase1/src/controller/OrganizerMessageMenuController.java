package controller;
import presenter.OrganizerMessagePresenter;

import java.util.Scanner;

public class OrganizerMessageMenuController{
    private OrganizerController controller;
    private OrganizerMessagePresenter displayMessage;
    private Scanner scan = new Scanner(System.in);
    public OrganizerMessageMenuController(OrganizerController controller){
        this.controller = controller;
        this.displayMessage = new OrganizerMessagePresenter();
    }
    public void option1(){
        displayMessage.promptMessage();
        String message = scan.nextLine();
        controller.sendSpeakersMessage();
        displayMessage.successMessage();
    }
    public void option2(){
        displayMessage.promptMessage();
        String message = scan.nextLine();
        controller.sendAttendeesMessage(); //todo: send to all attendees, not to attendees in a single event
        displayMessage.successMessage();
    }
}
