package controllers;

import entities.Conference;
import presenters.EventPresenter;
import presenters.OrganizerConferencePresenter;
import presenters.OrganizerMessagePresenter;
import useCases.ConferenceActions;
import useCases.EventActions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OrganizerConferenceController {
    private controllers.OrganizerController controller;
    private OrganizerConferencePresenter displayConference;
    private EventPresenter displayEvent;
    private ConferenceActions conferenceActions;
    private EventActions eventActions;
    private Scanner scan = new Scanner(System.in);

    public OrganizerConferenceController(OrganizerController controller, ConferenceActions conference, EventActions event) {
        this.controller = controller;
        this.conferenceActions = conference;
        this.eventActions = event;
        this.displayConference = new OrganizerConferencePresenter();
        this.displayEvent = new EventPresenter();
    }

    /**
     * Responds to menu option 1 - create conference
     */
    public void option1() {
        // prompt conference constructor objects
        HashMap<String, Conference> titleHash = conferenceActions.returnTitleHashMap();
        displayConference.promptCreateConferenceTitle();
        String conferenceTitle = scan.nextLine();
        if(!titleHash.containsKey(conferenceTitle)){
            // when prompt for events, print list of events
            addEvents(conferenceTitle, true);
            displayConference.successCreateConference();
        } else {
            displayConference.conferenceExists();
        }
    }

    /**
     * Responds to menu option 2 - add events to existing conference
     */
    public void option2() {
        // print list of conferences
        displayConference.printConferencesText();
        displayConference.displayConferences(conferenceActions.returnConferences());

        displayConference.printConferencesText();
        String conferenceTitle = scan.nextLine();

        addEvents(conferenceTitle, false);
    }

    public void addEvents(String conferenceTitle, boolean createNewConference){
        displayConference.printEventsText();
        //TODO: Display events on screen (get/make the list of lists)
        // displayEvent.displayEvents();

        String events = "";
        if(!createNewConference){
            displayConference.printEventsText();
            events = scan.nextLine();
        }

        String[] eventsList = events.split(",");

        if(!createNewConference){
            // add the event to conference if need be
            for(String eventName : eventsList){
                String eventId = eventActions.getEventFromName(eventName).getId();
                if(conferenceActions.addEvent(conferenceTitle, eventId)){
                    displayConference.successAddEvents(eventName);
                } else {
                    displayConference.failedAddEvents(eventName);
                }
            }

        } else {
            conferenceActions.createConference(conferenceTitle, Arrays.asList(eventsList));
            for(String eventName : eventsList){
                String eventId = eventActions.getEventFromName(eventName).getId();
                if(conferenceActions.addEvent(conferenceTitle, eventId)){
                    displayConference.successAddEvents(eventName);
                } else {
                    displayConference.failedAddEvents(eventName);
                }
            }
        }
    }
}
