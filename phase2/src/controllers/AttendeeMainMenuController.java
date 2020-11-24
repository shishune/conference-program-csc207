package controllers;
import entities.User;
import presenters.EventPresenter;
import presenters.MessagePresenter;
import useCases.RoomActions;
import useCases.SpeakerActions;

import java.util.List;

/**
 * A controller class for attendee that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class AttendeeMainMenuController extends MainMenuController {
    private controllers.AttendeeController controller;
    private User user;
    private EventPresenter displayEvent;
    private MessagePresenter displayMessage;
    private RoomActions room;
    private SpeakerActions speaker;

    /**
     * Instantiates this class referring to super class
     * @param user               the user object
     * @param attendeeController the controller responsible for user
     */
    public AttendeeMainMenuController(User user, AttendeeController attendeeController, RoomActions room, SpeakerActions speaker) {
        super(user, attendeeController, room, speaker);
        this.user = user;
        this.controller = attendeeController;
        this.displayEvent = new EventPresenter();
        this.displayMessage = new MessagePresenter();
        this.speaker = speaker;
    }

    public void option10(){
        List<List<String>> eventsList = controller.viewSavedEvents(user.getUsername());
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
}