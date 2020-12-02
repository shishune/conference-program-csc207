package useCases;

import entities.Conference;
import entities.Event;
import gateways.LoadUpIGateway;

import java.util.*;

public class ConferenceActions {
    public HashMap<String, Conference> conferences = new HashMap<String, Conference>();
    private HashMap<String, Conference> conferenceNames = new HashMap<String, Conference>();
    //private HashMap<String, List<String>> events = new HashMap<String, List<String>>(); // ConferenceID: event
    private LoadUpIGateway loader;
    private List<String> conferencesList;

    public ConferenceActions(LoadUpIGateway loader){
        loadAllConferences(loader);
        addLoadedToHashMap();
        this.loader = loader;
    }

    private void loadAllConferences(LoadUpIGateway loader) {
        conferencesList = loader.getEvents();
    }

    private void addLoadedToHashMap() {
        if (conferencesList != null && !conferencesList.isEmpty()) {
            for (String conference : conferencesList){
                String[] conferenceItems = conference.split(",");
                List<String> conferenceEvents = new ArrayList<>(Arrays.asList(conferenceItems[2].split("%%")));
                loadConference(conferenceItems[0], conferenceItems[1], conferenceEvents, conferenceItems[4], conferenceItems[5]);
            }
        }
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

    private Conference loadConference(String conferenceId, String title, List<String> events, String startDateTime, String endDateTime) {
        /*if (attendees.size() == 1 && attendees.get(0).equals("")) { // not certain second one is necessary
            attendees = new ArrayList<>();
        }*/
        Conference newConference = new Conference(conferenceId, title, events, startDateTime, endDateTime);
        conferences.put(conferenceId, newConference);
        conferenceNames.put(title, newConference);
        //this.events.put(conferenceId, events);
        // TODO: put events?
        return newConference;
    }

    public List<String> storeConferences(){
        List<String> storedConferences = new ArrayList<String>();
        for(Map.Entry<String, Conference> conference : conferences.entrySet()) {
            storedConferences.add(conference.getValue().getStringRep()+"\n");
        }
        return storedConferences;
    }
}
