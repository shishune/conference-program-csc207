package conference;

import loadUp.LoadUpIGateway;
import store.Storable;

import java.util.*;

public class ConferenceActions implements Storable {
    public HashMap<String, Conference> conferences = new HashMap<String, Conference>();
    private HashMap<String, Conference> conferenceTitlesHash = new HashMap<String, Conference>();
    //private HashMap<String, List<String>> speakerSchedule = new HashMap<String, List<String>>(); // SpeakerID: date
    //private HashMap<String, List<String>> attendees = new HashMap<String, List<String>>(); // EventID: attendees
    //private HashMap<String, List<String>> events = new HashMap<String, List<String>>(); // ConferenceID: event
    private LoadUpIGateway loader;
    private List<String> conferencesList;

    public ConferenceActions(LoadUpIGateway loader) {
        loadAllConferences(loader);
        addLoadedToHashMap();
        this.loader = loader;
    }

    public HashMap<String, Conference> returnIDHashMap() {
        return conferences;
    }

    public HashMap<String, Conference> returnTitleHashMap() {
        return conferenceTitlesHash;
    }

    public boolean addEvent(String conferenceTitle, String eventId) {
        if (conferenceTitlesHash != null) {
            conferenceTitlesHash.get(conferenceTitle).addEvent(eventId);
            return true;
        }
        return false;
    }

    /**
     * add attendee to a conference
     * @param conferenceTitle the title of conference
     * @param attendee the username of attendee
     * @return true if and only if attendee was added to conference
     */
    public boolean addAttendee(String conferenceTitle, String attendee){
        if (conferenceTitlesHash != null || !conferenceTitlesHash.get(conferenceTitle).getAttendees().contains(attendee)){
            conferenceTitlesHash.get(conferenceTitle).addAttendee(attendee);
            return true;
        }
        return false;
    }
    /**
     *
     * @param conferenceTitle the title of conference
     * @param attendeeUsername the username of attendee
     * @return true if and only if attendee was added to conference
     */
    public boolean isAttendee(String conferenceTitle, String attendeeUsername){
        return conferenceTitlesHash.get(conferenceTitle).getAttendees().contains(attendeeUsername);
    }

    /***
     * set speaker of an event
     * @param conferenceID if of event
     * @param speakerID id of new speaker
     */
    public void setSpeaker(String conferenceID, List<String> speakerID) {
        this.conferences.get(conferenceID).setSpeaker(speakerID);
    }

    /**
     * returns the list of all conferences (title and events)
     * @return returns the list of all conferences (title and events)
     */
    public ArrayList<List<String>> returnConferences() {
        ArrayList<List<String>> stringRepConferences = new ArrayList<>();
        for (Map.Entry<String, Conference> entry : conferenceTitlesHash.entrySet()) {
            List<String> stringRepConference = new ArrayList<String>();
            Conference conference = entry.getValue();
            stringRepConference.add(conference.getTitle());
//            stringRepConference.add(conference.getStartDateTime());
//            stringRepConference.add(conference.getEndDateTime());
            String events = "";
            for (String eventID : conference.getEvents()) {
                events += eventID + ",";
            }
            stringRepConference.add(events);
            stringRepConferences.add(stringRepConference);
        }
        return stringRepConferences;
    }

    /**
     * returns the list of all conferences (title and events) that attendee is participating in
     * @param attendeeID the id of the attendee in question
     * @return returns the list of all conferences (title and events) that attendee is participating in
     */
    public ArrayList<List<String>> returnAttendedConferences(String attendeeID){
        ArrayList<List<String>> stringRepConferences = new ArrayList<>();
        for (Map.Entry<String, Conference> entry : conferenceTitlesHash.entrySet()) {
            List<String> stringRepConference = new ArrayList<String>();
            Conference conference = entry.getValue();
            if (conference.getAttendees().contains(attendeeID)){
                stringRepConference.add(conference.getTitle());
                //            stringRepConference.add(conference.getStartDateTime());
                //            stringRepConference.add(conference.getEndDateTime());
                String events = "";
                for (String eventID : conference.getEvents()) {
                    events += eventID + ",";
                }
                stringRepConference.add(events);
                stringRepConferences.add(stringRepConference);
            }

        }
        return stringRepConferences;
    }

    /**
     * returns the list of all conferences (title and events) that attendee is NOT participating in
     * @param attendee the attendee name in question
     * @return list of all conferences (title and events) that attendee is NOT participating in
     */
    public ArrayList<List<String>> returnAvailableConferences(String attendee){
        ArrayList<List<String>> stringRepConferences = new ArrayList<>();
        for (Map.Entry<String, Conference> entry : conferenceTitlesHash.entrySet()) {
            List<String> stringRepConference = new ArrayList<String>();
            Conference conference = entry.getValue();
            if (!conference.getAttendees().contains(attendee)){
                stringRepConference.add(conference.getTitle());
                String events = "";
                for (String eventID : conference.getEvents()) {
                    events += eventID + ",";
                }
                stringRepConference.add(events);
                stringRepConferences.add(stringRepConference);
            }

        }
        return stringRepConferences;
    }

    private void loadAllConferences(LoadUpIGateway loader) {
        conferencesList = loader.getConferencesList();
    }

    private void addLoadedToHashMap() {
        if (conferencesList != null && !conferencesList.isEmpty()) {
            for (String conference : conferencesList) {
                String[] conferenceItems = conference.split(",");
                List<String> conferenceEvents = new ArrayList<>(Arrays.asList(conferenceItems[2].split("%%")));
                List<String> conferenceAttendees = new ArrayList<>(Arrays.asList(conferenceItems[3].split("%%")));
                // List<String> conferenceSpeakers = new ArrayList<>(Arrays.asList(conferenceItems[2].split("%%")));
                ArrayList<String> eventList = new ArrayList<String>();
                ArrayList<String> attendeeList = new ArrayList<String>();

                for (String e : conferenceEvents) {
                    if (!e.equals("")) {
                        eventList.add(e);
                    }
                }
                for (String a : conferenceAttendees) {
                    if (!a.equals("")) {
                        attendeeList.add(a);
                    }
                }
                loadConference(conferenceItems[0], conferenceItems[1], eventList, attendeeList/*, conferenceSpeakers*/);
            }
        }
    }

    public boolean conferenceExists(String conferenceTitle) {
        return conferenceTitlesHash.containsKey(conferenceTitle);
    }

    /**
     * determines whether a given attendee is not participating in a given conference, and whether the conference exists
     * @param conferenceTitle title of conference
     * @param attendee username of attendee
     * @return true if and only if given attendee is not participating in the given conference and the conference exists
     */
    public boolean conferenceAvailable(String conferenceTitle, String attendee){
        if (conferenceTitlesHash.containsKey(conferenceTitle) && !conferenceTitlesHash.get(conferenceTitle).getAttendees().contains(attendee)){
            return true;
        }
        return false;
    }

    /**
     * determines whether conference exists and a given attendee is participating in a given conference
     * @param conferenceTitle title of conference
     * @param attendee username of attendee
     * @return true if and only if conference exists and attendee is participating in the conference
     */
    public boolean conferenceAttended(String conferenceTitle, String attendee){
        return conferenceTitlesHash.containsKey(conferenceTitle) && conferenceTitlesHash.get(conferenceTitle).getAttendees().contains(attendee);
    }

    public void createConference(String title, List<String> events/*, List<String> attendees, List<String> speakers*/) {
        String conferenceId = "C" + String.valueOf(conferences.size());
        loadConference(conferenceId, title, events, new ArrayList<>()/*, speakers*/);
    }


    /***
     * return list of dates in string format of the time beginning with and including startDateTime,
     *      and ending with and excluding  endDateTime
     * @param startDateTime the new start date and time for the conference to be changed to
     * @param endDateTime the new end date and time for the conference to be changed to
     * @return list of dates in string format of the time beginning with and including startDateTime,
     *      and ending with and excluding  endDateTime
     */
    private List<String> timeInBetween(String startDateTime, String endDateTime) {
        int startTime = Integer.parseInt(startDateTime.substring(29, 31));
        int endTime = Integer.parseInt(endDateTime.substring(29, 31));
        String date = startDateTime.substring(0, 10);
        List<String> times = new ArrayList<>();
        times.add(startDateTime);
        while (startTime < endTime) {
            times.add(date + startTime);
            startTime += 1;
        }
        return times;
    }

    private Conference loadConference(String conferenceId, String title, List<String> events, List<String> attendees/*, List<String> speakers*/) {
        /*if (attendees.size() == 1 && attendees.get(0).equals("")) { // not certain second one is necessary
            attendees = new ArrayList<>();
        }*/
        Conference newConference = new Conference(conferenceId, title, events, attendees/*, speakers*/);
        conferences.put(conferenceId, newConference);
        conferenceTitlesHash.put(title, newConference);
        //this.events.put(conferenceId, events);
        // TODO: put events?
        return newConference;
    }

    public ArrayList<String> store() {
        ArrayList<String> storedConferences = new ArrayList<String>();
        for (Map.Entry<String, Conference> conference : conferences.entrySet()) {
            storedConferences.add(conference.getValue().getStringRep() + "\n");
        }
        return storedConferences;
    }

    /***
     * remove event from a conference
     * @param conferenceTitle conference title
     * @param eventID ID of event
     * @return if this has been successful
     */
    public boolean removeEvent(String conferenceTitle, String eventID){
        Conference conference = conferenceTitlesHash.get(conferenceTitle);
        if (conference!= null) {
            return conference.removeEvent(eventID);
        }
        return false;
    }

    /**
     * It will be get the conference IDs
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getConferenceIds() {
        ArrayList<String> storedConference = new ArrayList<String>();
        if (conferences != null && !conferences.isEmpty()) {
            for (Map.Entry<String, Conference> o : conferences.entrySet()) {
                storedConference.add(o.getValue().getId() + "\n");
            }
        }
        return storedConference;
    }


}