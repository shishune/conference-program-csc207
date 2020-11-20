package controller;
import presenter.OrganizerMessagePresenter;

import java.util.Scanner;

/**
 * A controller class for organizer that decides what to do based on user input when choosing from
 * the menu of messaging methods.
 * @author Cynthia
 * @version 1
 * */
public class OrganizerMessageMenuController{
    private OrganizerController controller;
    private OrganizerMessagePresenter displayMessage;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the object
     * @param controller the controller responsible for organizer
     */
    public OrganizerMessageMenuController(OrganizerController controller){
        this.controller = controller;
        this.displayMessage = new OrganizerMessagePresenter();
    }
    /**
     * Responds to menu option 1
     */
    public void option1(){
        displayMessage.promptMessage(); // enter your message
        String messageContent = scan.nextLine();
        if(controller.sendSpeakersMessage(messageContent)) {
            displayMessage.successMessage(); // message has been sent successfully
        } else {
            displayMessage.failedMessage();
        }
    }
    /**
     * Responds to menu option 2
     */
    public void option2(){
        displayMessage.promptMessage(); // enter your message
        String message = scan.nextLine();
        displayMessage.promptEvent(); // enter name of event you want to send message to
        String event = scan.nextLine();
        if(!(controller == null) && controller.checkEvent(event)){
            if(controller.sendAttendeesMessage(event, message)) {
                displayMessage.successMessage();
            }
        }
        else{
            displayMessage.eventNotCreated();
        }

    }


}
