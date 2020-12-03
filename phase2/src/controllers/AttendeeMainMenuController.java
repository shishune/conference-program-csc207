package controllers;
import entities.User;
import presenters.EventPresenter;
import presenters.MessagePresenter;
import useCases.RoomActions;
import useCases.SpeakerActions;

import java.util.List;
import java.util.Scanner;

/**
 * A controller class for attendee that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class AttendeeMainMenuController extends MainMenuController {
    private controllers.AttendeeController controller;
    private String userID;
    private EventPresenter displayEvent;
    private MessagePresenter displayMessage;
    private RoomActions room;
    private SpeakerActions speaker;
    private ConferenceActions conference;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates this class referring to super class
     * @param userID             the user ID
     * @param attendeeController the controller responsible for user
     */
    public AttendeeMainMenuController(String userID, AttendeeController attendeeController, RoomActions room, SpeakerActions speaker) {
        super(userID, attendeeController, room, speaker);
        this.userID = userID;
        this.controller = attendeeController;
        this.displayEvent = new EventPresenter();
        this.displayMessage = new MessagePresenter();
        this.speaker = speaker;
        this.conference = conference;
    }

    /**
     * Responds to menu option 6- sign up for event
     */
    public void option6(){
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewAvailableSchedule(username);
        if (eventsList.size() == 0){
            displayMessage.noEvents();
        } else {
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                e.set(3, speaker.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
            displayEvent.promptSelectEvent();
            String event = scan.nextLine();
            boolean check = controller.checkEvent(event);
            if (!check) {
                displayEvent.failedNoSuchEvent();
            }
            else {
                displayEvent.promptAddOrSaveEvent();
                String option = scan.nextLine();
                if (option.equalsIgnoreCase("A")){
                    List<Boolean> checks = controller.signupEvent(event, user.getUsername());
                    if(checks.size()==1){
                        if (checks.get(0)){
                            displayEvent.successAddEvent();
                        }
                    }
                    else{
                        if (!checks.get(1)){
                            displayEvent.failedRoomFull();
                        }
                        else if (checks.get(2)){
                            displayEvent.failedAttendeeTimeConflict();
                        }
                        else {
                            displayEvent.failed();
                        }
                    }
                }
                else if(option.equalsIgnoreCase("S")){
                    boolean check1 = controller.saveEvent(event, user.getUsername());
                    if (check1){
                        displayEvent.successSaveEvent();
                    }
                    else {
                        displayEvent.failedSaveEvent();
                    }
                }
                else {
                    displayEvent.failed();
                }
            }
        }
    }

    /**
     * view saved events
     */
    public void option10(){
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewSavedEvents(username);
        if (eventsList.size() == 0){
            displayMessage.noSavedEvents();
        } else {
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                e.set(3, speaker.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
        }
    }

    public void option11(){
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewSavedEvents(username);

        if (eventsList.size() == 0){
            displayMessage.noSavedEvents();
        } else {
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                e.set(3, speaker.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
        }

}}