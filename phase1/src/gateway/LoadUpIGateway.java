package gateway;

import java.util.*;

public interface LoadUpIGateway {
    /** getEventsList()
     * // gets list of events from the gateway
     **/

    /** getOrganizersList()
     * // gets list of organizers from the gateway
     **/

    /** etc. etc. etc., one for every entity **/

    public List<String> getMessagesList();
    public List<String> getRoomsList();

    public List<String> getEventsList();

}
