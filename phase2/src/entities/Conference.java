package entities;

import java.util.List;

public class Conference {
    private String conferenceID;
    private String title;
    private List<String> events;
    private String startDateTime;
    private String endDateTime;

    /**
     * Instantiates a new conference object
     * @param conferenceID the string unique id of this conference
     * @param title the string name of this conference
     * @param events the list of strings of the ids of each event of this conference
     * @param startDateTime the string of the date of this conference which is yyyy-mm-dd hh an d is 24 hour
     * @param endDateTime the string of the date of this conference which is yyyy-mm-dd hh an d is 24 hour
     * */
    public Conference(String conferenceID, String title, List<String> events, String startDateTime, String endDateTime){
        this.conferenceID = conferenceID;
        this.title = title;
        this.events = events;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * @return strign representation of this Conference entity
     */
    public String getStringRep(){
        String eventsString = events.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "%%");
        return conferenceID + "," + title + "," + eventsString + "," + startDateTime + "," + endDateTime;
    }

    //TODO: possible methods - getAllAttendees, getNumAttendees, getAllSpeakers, getNumSpeakers,
    // getAllRooms, getVIPEvents, getVIPAttendees, getVIPSpeakers, getVIPs, helper methods

    //TODO: add conference actions, controller?
    //TODO: edit presenter

    /**
     * Return conferenceID
     * @return ID of conference
     * */
    public String getId(){
        return this.conferenceID;
    }

    /**
     * Return title
     * @return title of this conference
     * */
    public String getTitle(){
        return this.title;
    }

    /**
     * Return list of IDs of the events in this conference
     * @return list IDs of event
     * */
    public List<String> getEvents(){
        return this.events;
    }

    /**
     * Adds the new event ID to the list of event IDs
     * @return true if the event was successfully added, and false if it couldn't be
     * */
    public boolean addEvent(String eventID){
        return this.events.add(eventID);
    }

    /**
     * Removes the event ID from the list of event IDs
     * @return true if the event could be removes, false if it couldn't be
     * */
    public boolean removeEvent(String eventID){
        if (!this.events.contains(eventID)){
            this.events.add(eventID);
            return true;
        } else{
            return false;
        }
    }

    /**
     * Return startDateTime to endDateTime
     * @return String representing the range of start to end date time
     * */
    public String getDateTime(){
        return startDateTime + "to" + endDateTime;
    }

    /**
     * Return startDateTime
     * @return String representing the start date and time of the conference
     * */
    public String getStartDateTime(){
        return this.startDateTime;
    }

    /**
     * Sets the start date time of this Conference
     * */
    public void setStartDateTime(String newStartDateTime){
        this.startDateTime = newStartDateTime;
    }

    /**
     * Return endDateTime
     * @return String representing the end date time of the program
     * */
    public String getEndDateTime(){
        return this.endDateTime;
    }

    /**
     * Sets the end date and time of this Conference entity
     * */
    public void setEndDateTime(String newEndDateTime){
        this.endDateTime = newEndDateTime;
    }
}
