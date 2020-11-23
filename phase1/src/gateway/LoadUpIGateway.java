package gateway;

import java.util.*;

public interface LoadUpIGateway {


    public ArrayList<String> getMessagesList();

    public ArrayList<String> getAllAttendees();

    public ArrayList<String> getSpeakersList();

    /** getOrganizersList()
     * // gets list of organizers from the gateway
     **/
    public ArrayList<String> getAllOrganizers();

    public int getObjectId();

    public void addId(String id);

    public ArrayList<String> getRooms();

    /** getEventsList()
     * // gets list of events from the gateway
     **/
    public ArrayList<String> getEvents();

}
