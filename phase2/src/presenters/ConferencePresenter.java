package presenters;

import entities.Event;
import entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A presenter class. This class is responsible for anything related to displaying conferencess to the user.
 * @author multiple
 * @version 1
 * */
public class ConferencePresenter {

    private EventPresenter eventPresenter;

    public ConferencePresenter(){
        this.eventPresenter = new EventPresenter();
    }
    /**
     * Displays conferences
     * */
//    public void displayConferences(ArrayList<List<String>> conferencesList, List<List<String>> eventsList, HashMap<String, User> userIdHash){
//        if(conferencesList != null){
//            if(conferencesList.isEmpty()){
//                System.out.println("There are no conferences in existance.");
//            } else {
//                int count = 1;
//                for (List<String> info: conferencesList){
//                    //TODO: detect if conference is current
//                    //TODO: print events
//                    if(true /* conference start time same or after current time*/){
//                        System.out.println(count);
//                        count ++;
//
//                        System.out.println("Conference title: "+ info.get(0));
//                        // print events
//                        String[] events = info.get(1).split("%%");
//                        System.out.println("Conference Events:");
//                        eventPresenter.displayEvents(eventsList, userIdHash);
//                        //for(String event : events){
//                            //System.out.println(userHash.get(event).getUsername());
//                        //}
//                        // print attendees
//                        //String[] attendees = info.get(2).split("%%");
//                        //System.out.println("Conference Attendees");
//                        //for(String attendee : attendees){
//                        //}
//                        // print speakers
//                        String[] speakers = info.get(3).split("%%");
//                        System.out.println("Conference Speakers:");
//                        //TODO: (optional) put speakers into conferences
//                        // if not implemented, delete the following loop and code
//                        for(String speaker : speakers){
//                            System.out.println(userIdHash.get(speaker).getUsername());
//                        }
//                        //System.out.println("Start Time: "+info.get(1));
//                        //System.out.println("End Time: "+info.get(2));
//
//
//                        System.out.println("\n");
//                    }
//                }
//            }
//        }
//    }
    public void promptConference(){
        System.out.println("Please enter the conference you would like to see the events of");
    }

    public void displayConferences(ArrayList<List<String>> conferencesList) {
        if(conferencesList != null){
            if(conferencesList.isEmpty()){
                System.out.println("There are no conferences in existence.");
            } else {
                System.out.println("Conferences:");
                for (List<String> conference : conferencesList) {
                    System.out.println(conference.get(0) + "\n");
                }
            }
        }
    }
}


//                    //TODO: detect if conference is current
//                    //TODO: print events
//                    if(true /* conference start time same or after current time*/){
//                        System.out.println(count);
//                        count ++;
//
//                        System.out.println("Conference title: "+ info.get(0));
//                        // print events
//                        String[] events = info.get(1).split("%%");
//                        System.out.println("Conference Events:");
//                        //for(String event : events){
//                        //System.out.println(userHash.get(event).getUsername());
//                        //}
//                        // print attendees
//                        //String[] attendees = info.get(2).split("%%");
//                        //System.out.println("Conference Attendees");
//                        //for(String attendee : attendees){
//                        //}
//                        // print speakers
//                        String[] speakers = info.get(3).split("%%");
//                        System.out.println("Conference Speakers:");
//                        //TODO: (optional) put speakers into conferences
//                        // if not implemented, delete the following loop and code
//                        for(String speaker : speakers){
//                            System.out.println(userIdHash.get(speaker).getUsername());
//                        }
//                        //System.out.println("Start Time: "+info.get(1));
//                        //System.out.println("End Time: "+info.get(2));






