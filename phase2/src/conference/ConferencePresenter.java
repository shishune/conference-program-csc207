package conference;

import event.EventPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A presenter class. This class is responsible for anything related to displaying conferencess to the user.
 * @author multiple
 * @version 1
 * */
public class ConferencePresenter {

    private EventPresenter eventPresenter;


    /**
     * Presenting conferences
     */
    public ConferencePresenter(){
        this.eventPresenter = new EventPresenter();
    }


    /**
     * prompt for viewing conference events
     */
    public void promptConference(){
        System.out.println("Please enter the conference (title) you would like to see the events of");
    }


    /**
     * prompt for conference sign-up
     */
    public void promptSignUp(){
        System.out.println("Please enter the conference (title) you would like to sign up for.");
    }


    /**
     * conference does not exist or attendee not in it
     */
    public void invalidTitle(){
        System.out.println("This conference does not exist. " +
                "Please enter another title or 'x' to return to menu to sign up for one.");
    }


    /**
     *  attendee not in conference
     */
    public void notAttendingConfernce(){
        System.out.println("You are not participating in this conference. " +
                "Please enter another title or 'x' to return to menu to sign up for one.");
    }


    /**
     * failed sign up for conference.
     */
    public void failedSignUp(){
        System.out.println("This conference does not exist.");
    }


    /**
     * successful sign up for conference
     */
    public void successSignUp(){
        System.out.println("You have successfully signed up for this conference.");
    }


    /**
     * displays all conferences for any users.
     * @param conferencesList the list of conferences to present.
     */
    public void displayConferences(ArrayList<List<String>> conferencesList) {
        if(conferencesList != null){
            if(conferencesList.isEmpty()){
                System.out.println("There are no conferences in existence.");
            } else {
                System.out.println("Conferences:");
                for (List<String> conference : conferencesList) {
                    System.out.println(conference.get(0));
                }
            }
        }
    }


    /**
     * displays conferences attended by attendees.
     * @param conferencesList the list of conferences to present.
     * @return true if and only if conferenceList is non-empty
     */
    public boolean displayAttendedConferences(ArrayList<List<String>> conferencesList){
        if(conferencesList != null){
            if(conferencesList.isEmpty()){
                System.out.println("There are no conferences in existence, or you have not signed up for any conferences. You may sign up for one by returning to the menu.");
                return false;
            } else {
                System.out.println("Conferences:");
                for (List<String> conference : conferencesList) {
                    System.out.println(conference.get(0));
                }
                return true;
            }
        }
        return false;
    }


    /**
     * displays conferences not attended by attendees
     * @param conferencesList the list of conferences to present
     * @return true if and only if conferenceList is non-empty
     */
    public boolean displayAvailableConferences(ArrayList<List<String>> conferencesList){
        if(conferencesList != null){
            if(conferencesList.isEmpty()){
                System.out.println("There are no conferences in existence, or you have already signed up for all conferences.");
                return false;
            } else {
                System.out.println("Conferences:");
                for (List<String> conference : conferencesList) {
                    System.out.println(conference.get(0));
                }
                return true;
            }
        }
        return false;
    }
}



