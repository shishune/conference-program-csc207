package useCases;

import entities.Conference;
import entities.Event;
import gateways.LoadUpIGateway;

import java.util.*;

public class ConferenceActions {
    public HashMap<String, Conference> conferences = new HashMap<String, Conference>();
    private HashMap<String, Conference> conferenceTitlesHash = new HashMap<String, Conference>();
    //private HashMap<String, List<String>> speakerSchedule = new HashMap<String, List<String>>(); // SpeakerID: date
    //private HashMap<String, List<String>> attendees = new HashMap<String, List<String>>(); // EventID: attendees
    //private HashMap<String, List<String>> events = new HashMap<String, List<String>>(); // ConferenceID: event
    private LoadUpIGateway loader;
    private List<String> conferencesList;

    public ConferenceActions(LoadUpIGateway loader){
        loadAllConferences(loader);
        addLoadedToHashMap();
        this.loader = loader;
    }

    public HashMap<String, Conference> returnIDHashMap(){
        return conferences;
    }

    public HashMap<String, Conference> returnTitleHashMap(){
        return conferenceTitlesHash;
    }

    public boolean addEvent(String conferenceTitle, String eventId){
        if(conferenceTitlesHash != null){
            conferenceTitlesHash.get(conferenceTitle).addEvent(eventId);
            return true;
        }
        return false;
    }

    /***
     * set speaker of an event
     * @param conferenceID if of event
     * @param speakerID id of new speaker
     */
    public void setSpeaker(String conferenceID, List<String> speakerID){
        this.conferences.get(conferenceID).setSpeaker(speakerID);
    }

    public ArrayList<List<String>> returnConferences(){
        ArrayList<List<String>> stringRepConferences = new ArrayList<>();
        for(Map.Entry<String, Conference> entry : conferenceTitlesHash.entrySet()){
            List<String> stringRepConference = new ArrayList<String>();
            Conference conference = entry.getValue();
            stringRepConference.add(conference.getTitle());
            //stringRepConference.add(conference.getStartDateTime());
            //stringRepConference.add(conference.getEndDateTime());

            stringRepConferences.add(stringRepConference);
        }
        return stringRepConferences;
    }

    private void loadAllConferences(LoadUpIGateway loader) {
        conferencesList = loader.getConferencesList();
    }

    private void addLoadedToHashMap() {
        if (conferencesList != null && !conferencesList.isEmpty()) {
            for (String conference : conferencesList){
                String[] conferenceItems = conference.split(",");
                List<String> conferenceEvents = new ArrayList<>(Arrays.asList(conferenceItems[2].split("%%")));
                List<String> conferenceAttendees = new ArrayList<>(Arrays.asList(conferenceItems[2].split("%%")));
                List<String> conferenceSpeakers = new ArrayList<>(Arrays.asList(conferenceItems[2].split("%%")));
                loadConference(conferenceItems[0], conferenceItems[1], conferenceEvents/*, conferenceAttendees, conferenceSpeakers*/);
            }
        }
    }

    public boolean conferenceExists(String conferenceTitle){
        if(conferenceTitlesHash.containsKey(conferenceTitle)){
            return true;
        }
        return false;
    }

    public Conference createConference(String title, List<String> events/*, List<String> attendees, List<String> speakers*/){
        useCases.GenerateID generateId = new GenerateID(loader);
        String conferenceId = "C" + generateId.generateId();
        //TODO: set conference start time to start time of first event & end time = end time of last event ???
        return loadConference(conferenceId, title, events/*, attedees, speakers*/);
    }



    /***
     * return list of dates in string format of the time beginning with and including startDateTime,
     *      and ending with and excluding  endDateTime
     * @param startDateTime the new start date and time for the conference to be changed to
     * @param endDateTime the new end date and time for the conference to be changed to
     * @return list of dates in string format of the time beginning with and including startDateTime,
     *      and ending with and excluding  endDateTime
     */
    private List<String> timeInBetween(String startDateTime, String endDateTime){
        int startTime = Integer.parseInt(startDateTime.substring(29, 31));
        int endTime = Integer.parseInt(endDateTime.substring(29, 31));
        String date = startDateTime.substring(0, 10);
        List<String> times = new ArrayList<>();
        times.add(startDateTime);
        while (startTime < endTime){
            times.add(date + startTime);
            startTime += 1;
        }
        return times;
    }

    private Conference loadConference(String conferenceId, String title, List<String> events/*, List<String> attendees, List<String> speakers*/) {
        /*if (attendees.size() == 1 && attendees.get(0).equals("")) { // not certain second one is necessary
            attendees = new ArrayList<>();
        }*/
        Conference newConference = new Conference(conferenceId, title, events/*, attendees, speakers*/);
        conferences.put(conferenceId, newConference);
        conferenceTitlesHash.put(title, newConference);
        //this.events.put(conferenceId, events);
        // TODO: put events?
        return newConference;
    }

    public ArrayList<String> storeConferences(){
        ArrayList<String> storedConferences = new ArrayList<String>();
        for(Map.Entry<String, Conference> conference : conferences.entrySet()) {
            storedConferences.add(conference.getValue().getStringRep()+"\n");
        }
        return storedConferences;
    }
}