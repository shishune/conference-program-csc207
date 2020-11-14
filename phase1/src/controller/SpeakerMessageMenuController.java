package controller;
import presenter.SpeakerMessagePresenter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SpeakerMessageMenuController{
    private SpeakerController controller;
    private SpeakerMessagePresenter displayMessage;
    private Scanner scan = new Scanner(System.in);
    public SpeakerMessageMenuController(SpeakerController controller){
        this.controller = controller;
        this.displayMessage = new SpeakerMessagePresenter();
    }
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
