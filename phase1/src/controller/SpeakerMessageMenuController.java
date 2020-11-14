package controller;
import presenter.SpeakerMessagePresenter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A controller class for organizer that decides what to do based on user input when choosing from
 * the menu of messaging methods.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerMessageMenuController{
    private SpeakerController controller;
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
        displayMessage.promptMessage();
        String content = scan.nextLine();
        displayMessage.promptListEvents();
        List<String> events = new ArrayList<String>();
        while (true){
            String event = scan.nextLine();
            events.add(event);
            if (event.equals("x")||event.equals("X")){
                break;
            }
        }
        if(controller.sendMessages(events, content)){
            displayMessage.successMessage();
        }
        else{
            displayMessage.failedEvent();
        }
    }
}