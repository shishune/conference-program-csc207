package entities;

import java.util.List;

public class Conference {
    private String conferenceID;
    private String title;
    private List<String> events;
    private String startDateTime;
    private String endDateTime;

    public Conference(String conferenceID, String title, List<String> events, String startDateTime, String endDateTime){
        this.conferenceID = conferenceID;
        this.title = title;
        this.events = events;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getStringRep(){
        String eventsString = events.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "%%");
        return conferenceID + "," + title + "," + eventsString + "," + startDateTime + "," + endDateTime;
    }

    //TODO: possible methods - getAllAttendees, getNumAttendees, getAllSpeakers, getNumSpeakers,
    // getAllRooms, getVIPEvents, getVIPAttendees, getVIPSpeakers, getVIPs, helper methods

    //TODO: add conference actions, controller?
    //TODO: edit presenter

    public String getId(){
        return this.conferenceID;
    }

    public String getTitle(){
        return this.title;
    }

    public List<String> getEvents(){
        return this.events;
    }

    public boolean addEvent(String eventID){
        return this.events.add(eventID);
    }

    public boolean removeEvent(String eventID){
        if (!this.events.contains(eventID)){
            this.events.add(eventID);
            return true;
        } else{
            return false;
        }
    }

    public String getDateTime(){
        return startDateTime + "to" + endDateTime;
    }

    public String getStartDateTime(){
        return this.startDateTime;
    }

    public void setStartDateTime(String newStartDateTime){
        this.startDateTime = newStartDateTime;
    }

    public String getEndDateTime(){
        return this.endDateTime;
    }

    public void setEndDateTime(String newEndDateTime){
        this.endDateTime = newEndDateTime;
    }
}
