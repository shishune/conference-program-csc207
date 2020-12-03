package presenters;

public class OrganizerConferencePresenter extends ConferencePresenter{
    public void printEventsText(){
        System.out.println("Available events");
    };

    public void printConferencesText() {
        System.out.println("Available conferences");
    }
    public void printOrganizerConferenceMenu(){
        System.out.println(""
                        + "\n[1] Create a conference"
                        + "\n[2] Add events to an existing conference");
    }

    public void promptConference(){
        System.out.println("Select the conference you would like to add event(s) to.");
    }

    public void promptAddEvent(){
        System.out.println("List out the event/events you would like to add to this conference \nseparated by commas, without spaces");
    }

    public void successAddEvents(String eventName) {
        System.out.println("Your event" + eventName + "has been successfully added to the conference.");
    }

    public void failedAddEvents(String eventName) {
        System.out.println("Something went wrong. Your event" + eventName + "could not be added to the conference");
    }

    public void promptCreateConferenceTitle() {
        System.out.println("Enter the name of your conference");
    }

    public void conferenceExists(){
        System.out.println("A conference with this name already exists");
    }

    public void successCreateConference() {
        System.out.println("Congratulations! You have successfully created a conference.");
    }
}
