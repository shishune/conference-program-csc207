package controller;
import controller.SpeakerController;
import presenter.SpeakerMessagePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A controller class for organizer that decides what to do based on user input when choosing from
 * the menu of messaging methods.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerMessageMenuController{
    private controller.SpeakerController controller;
    private SpeakerMessagePresenter displayMessage;
    private Scanner scan = new Scanner(System.in);

    /**
     * instantiates object
     * @param controller the controller responsible for speaker
     */
    public SpeakerMessageMenuController(SpeakerController controller){
        this.controller = controller;
        this.displayMessage = new SpeakerMessagePresenter();
    }

    /**
     * Responds to menu option 1
     */
    public void option1(){
        displayMessage.promptMessage(); // enter your message
        String content = scan.nextLine();
        displayMessage.promptListEvents(); //
        String eventEntry = scan.nextLine();
        String[] events = eventEntry.split(",");
        if(controller != null) {
            boolean success = false;
            for (String event : events) {
                if (controller.sendMessages(event, content)) {
                    //displayMessage.noAttendees();
                    success = true;

                } else {
                    displayMessage.failedMessage();
                    success = false;
                    break;
                }
            }
            if(success) {
                displayMessage.successMessage();
            }
        }
    }

    /**
     * Responds to menu option 5
     */
    public void option5(){
        displayMessage.promptMessage();
        String content = scan.nextLine();
        displayMessage.promptListEvents();
        List<String> events = new ArrayList<String>();
        while (true){
            String event = scan.nextLine();
            if (event.equals("x")||event.equals("X")){
                break;
            }

            else if (!(controller == null) && controller.eventActions.getEvents().containsKey(event)){
                events.add(event);
            }

            else{
                displayMessage.failedEvent();
            }

        }
        for (String event : events) {
            if (!controller.sendMessages(event, content)){
                displayMessage.noAttendees();
                break;
            }
            controller.sendMessages(event, content);
        }

    }
}
