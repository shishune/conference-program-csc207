package controller;
import entities.Room;
import entities.Speaker;
import entities.User;
import presenter.EventPresenter;
import presenter.MessagePresenter;
import presenter.OrganizerEventPresenter;
import presenter.EventPresenter;
import useCase.RoomActions;
import useCase.SpeakerActions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * A controller class for attendee that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class AttendeeMainMenuController extends MainMenuController {
    private AttendeeController controller;
    private User user;
    private EventPresenter displayEvent;
    private RoomActions room;
    private SpeakerActions speaker;

    /**
     * instantiates this class referring to super class
     *
     * @param user               the user object
     * @param attendeeController the controller responsible for user
     */
    public AttendeeMainMenuController(User user, AttendeeController attendeeController, RoomActions room, SpeakerActions speaker) {
        super(user, attendeeController, room, speaker);
        this.user = user;
        this.controller = attendeeController;
        this.displayEvent = new EventPresenter();
        this.speaker = speaker;
    }
}