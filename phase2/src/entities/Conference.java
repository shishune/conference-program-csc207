package entities;

import java.util.List;

public class Conference {
    //TODO: add check start time <= end time, end time >= start time
    private String conferenceID;
    private String title;
    private List<String> events;
    private List<String> attendees;
    private List<String> speakers;
    //private String startDateTime;
    //private String endDateTime;

    /**
     * Instantiates a new conference object
     * @param conferenceID the string unique id of this conference
     * @param title the string name of this conference
     * @param events the list of strings of the ids of each event of this conference
     * */
    public Conference(String conferenceID, String title, List<String> events/*, List<String> attendees, List<String> speakers*/){
        this.conferenceID = conferenceID;
        this.title = title;
        this.events = events;
        //this.attendees = attendees;
        //this.speakers = speakers;
        //this.startDateTime = startDateTime;
        //this.endDateTime = endDateTime;
    }

    /**
     * @return strign representation of this Conference entity
     */
    public String getStringRep(){
        String eventsString = events.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "%%");
        String attendeesString = events.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "%%");
        String speakersString = events.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "%%");
        return conferenceID + "," + title + "," + eventsString + "," + attendeesString + "," + speakersString + ",0";
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

    public List<String> getAttendees(){
        return this.attendees;
    }

    public List<String> getSpeakers(){
        return this.speakers;
    }

    /**
     * Adds the new event ID to the list of event IDs
     * @return true if the event was successfully added, and false if it couldn't be
     * */
    public boolean addEvent(String eventID){
        return this.events.add(eventID);
    }

    /**
     * Adds the new attendee ID to the list of attendee IDs
     * @return true if the attendee was successfully added, and false if it couldn't be
     * */
    public boolean addAttendee(String attendeeID){
        return this.attendees.add(attendeeID);
    }

    /**
     * Adds the new speaker ID to the list of speaker IDs
     * @return true if the speaker was successfully added, and false if it couldn't be
     * */
    public boolean addSpeakers(String speakerID){
        return this.speakers.add(speakerID);
    }

    /**
     * set speaker for event
     * @param speakers new speakers' ids
     * */
    public void setSpeaker(List<String> speakers) {
        this.speakers = speakers;
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
    //public String getDateTime(){
    //    return startDateTime + "to" + endDateTime;
    //}

    /**
     * Return startDateTime
     * @return String representing the start date and time of the conference
     * */
    //public String getStartDateTime(){
        //return this.startDateTime;
    //}

    /**
     * Sets the start date time of this Conference
     * */
    //public void setStartDateTime(String newStartDateTime){
        //this.startDateTime = newStartDateTime;
    //}

    /**
     * Return endDateTime
     * @return String representing the end date time of the program
     * */
    //public String getEndDateTime(){
        //return this.endDateTime;
    //}

    /**
     * Sets the end date and time of this Conference entity
     * */
    //public void setEndDateTime(String newEndDateTime){
        //this.endDateTime = newEndDateTime;
    //}
}
