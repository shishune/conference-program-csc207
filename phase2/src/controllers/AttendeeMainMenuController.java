package controllers;
import presenters.EventPresenter;
import presenters.MessagePresenter;
import useCases.ConferenceActions;
import useCases.RoomActions;
import useCases.SpeakerActions;

import java.util.ArrayList;
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
    private SpeakerActions speakerActions;
    private ConferenceActions conferenceActions;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates this class referring to super class
     *
     * @param userID             the user ID
     * @param attendeeController the controller responsible for user
     */
    public AttendeeMainMenuController(String userID, AttendeeController attendeeController, RoomActions room, SpeakerActions speakerActions, ConferenceActions conferenceActions) {
        super(userID, attendeeController, room, speakerActions, conferenceActions);
        this.userID = userID;
        this.controller = attendeeController;
        this.displayEvent = new EventPresenter();
        this.displayMessage = new MessagePresenter();
        this.speakerActions = speakerActions;
        this.room = room;
        this.conferenceActions = conferenceActions;
    }

    /**
     * Responds to menu option 6- sign up for event
     */
    public void option6() {
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        // TODO print list of users conferences
        // TODO have user choose which conference events they want to see
        ArrayList<List<String>> conferences = conferenceActions.returnAttendedConferences(username);
        boolean exists = displayConference.displayAttendedConferences(conferences);
        if (exists) {
            displayConference.promptConference();
            String conferenceTitle = scan.nextLine();
            while (!conferenceActions.conferenceAttended(conferenceTitle, username)) {
                displayConference.invalidTitle();
                conferenceTitle = scan.nextLine();
                if(conferenceTitle.equalsIgnoreCase("x")){ //this cannot be inserted into loop condition
                    break;
                }
            }
            if (!conferenceTitle.equalsIgnoreCase("x")) {
                List<List<String>> eventsList = controller.viewAvailableSchedule(username, conferenceTitle);

                if (controller.isVIP(username)) {
                    List<List<String>> vipEventsList = controller.viewVIPEvents(username, conferenceTitle);
                    signUpVIP(username, vipEventsList);
                }

                if (eventsList.size() == 0) {
                    displayMessage.noEvents();
                } else {
                    for (List<String> e : eventsList) {
                        e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                        if (e.get(3).equals("")) {
                            e.set(3, "There are no speakers at the moment for this event.");
                        } else {
                            e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
                        }

                    }
                    displayEvent.displayEvents(eventsList);
                    displayEvent.promptSelectEvent();
                    String event = scan.nextLine();
                    boolean check = controller.checkEvent(event);
                    if (!check) {
                        displayEvent.failedNoSuchEvent();
                    } else {
                        displayEvent.promptAddOrSaveEvent();
                        String option = scan.nextLine();
                        if (option.equalsIgnoreCase("A")) {
                            List<Boolean> checks = controller.signupEvent(event, username);
                            if (checks.size() == 1) {
                                if (checks.get(0)) {
                                    displayEvent.successAddEvent();
                                }
                            } else {
                                if (!checks.get(1)) {
                                    displayEvent.failedRoomFull();
                                } else if (checks.get(2)) {
                                    displayEvent.failedAttendeeTimeConflict();
                                } else {
                                    displayEvent.failed();
                                }
                            }
                        } else if (option.equalsIgnoreCase("S")) {
                            boolean check1 = controller.saveEvent(event, username);
                            if (check1) {
                                displayEvent.successSaveEvent();
                            } else {
                                displayEvent.failedSaveEvent();
                            }
                        } else {
                            displayEvent.failed();
                        }
                    }
                }
            }
        }
    }
    /**
     * helper to sign up to VIP events
     */
    public void signUpVIP(String username, List<List<String>> vipEventsList){
        if (vipEventsList.size() == 0) {
            displayMessage.noEvents();
        } else {
            for (List<String> e : vipEventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                if (e.get(3).equals("")) {
                    e.set(3, "There are no speakers at the moment for this event.");
                } else {
                    e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
                }

            }
            displayEvent.displayEvents(vipEventsList);
            displayEvent.promptSelectEvent();
            String event = scan.nextLine();
            boolean check = controller.checkEvent(event);
            if (!check) {
                displayEvent.failedNoSuchEvent();
            } else {
                displayEvent.promptAddOrSaveEvent();
                String option = scan.nextLine();
                if (option.equalsIgnoreCase("A")) {
                    List<Boolean> checks = controller.signupEvent(event, username);
                    if (checks.size() == 1) {
                        if (checks.get(0)) {
                            displayEvent.successAddEvent();
                        }
                    } else {
                        if (!checks.get(1)) {
                            displayEvent.failedRoomFull();
                        } else if (checks.get(2)) {
                            displayEvent.failedAttendeeTimeConflict();
                        } else {
                            displayEvent.failed();
                        }
                    }
                } else if (option.equalsIgnoreCase("S")) {
                    boolean check1 = controller.saveEvent(event, username);
                    if (check1) {
                        displayEvent.successSaveEvent();
                    } else {
                        displayEvent.failedSaveEvent();
                    }
                } else {
                    displayEvent.failed();
                }
            }
        }

    }

    /**
     * view saved events
     */
    public void option10() {
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewSavedEvents(username);
        if (eventsList.size() == 0) {
            displayMessage.noSavedEvents();
        } else {
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                if (e.get(3).equals("")) {
                    e.set(3, "There are no speakers at the moment for this event.");
                } else {
                    e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
                }
            }
            displayEvent.displayEvents(eventsList);
        }
    }

    /**
     * view vip events
     */

    public void option11() {
        String conferenceTitle = "";
        ArrayList<List<String>> conferences = conferenceActions.returnConferences();
        displayConference.displayConferences(conferences);
        displayConference.promptConference();
        while(!conferenceActions.conferenceExists(conferenceTitle)){
            conferenceTitle = scan.nextLine();
        }
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewVIPEvents(username, conferenceTitle);

        if (eventsList.size() == 0){
            //displayMessage.noEvents();
            displayEvent.noEventsAvailable();
        } else {
            displayEvent.eventIntro();
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                List<String> speakerList = new ArrayList<String>();
                for (String speaker : e.get(3).split(",")) {
                    if (speaker.equals("")){
                        speakerList.add(displayMessage.noSpeakers());
                    } else {
                        speakerList.add(speakerActions.findUserFromId(speaker).getUsername());
                    }
                }
                e.set(3, String.valueOf(speakerList));
                //e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
        }
    }

    /**
     * sign up for conference
     */
    public void option12(){
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        ArrayList<List<String>> conferences = conferenceActions.returnAvailableConferences(username);
        boolean available = displayConference.displayAvailableConferences(conferences);
        if (available) {
            displayConference.promptSignUp();

            String conferenceTitle = scan.nextLine();
            if (conferenceActions.conferenceAvailable(conferenceTitle, username)) {
                conferenceActions.addAttendee(conferenceTitle, username);
                System.out.println(conferenceActions.returnTitleHashMap().get(conferenceTitle).getAttendees());
                displayConference.successSignUp();
            } else {
                displayConference.failedSignUp();
            }
        }
    }
}