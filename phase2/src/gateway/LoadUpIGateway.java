package gateway;

import java.util.*;

public interface LoadUpIGateway {
    /** getEventsList()
     * // gets list of events from the gateway
     **/

    /** getOrganizersList()
     * // gets list of organizers from the gateway
     **/

    // TODO: Finish Java Doc

    public ArrayList<String> getMessagesList();
    public ArrayList<String> getAllAttendees();
    public ArrayList<String> getSpeakersList();
    public ArrayList<String> getAllOrganizers();
    public int getObjectId();
    public void addId(String id);
    public ArrayList<String> getRooms();
    public ArrayList<String> getEvents();

}
