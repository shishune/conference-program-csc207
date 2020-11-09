package gateway;

import java.util.ArrayList;
import java.util.List;

public class LoadUp implements LoadUpIGateway {
    ArrayList<String> messages = null;
    // ArrayList<String> events = null;
    // ArrayList<String> organizers = null;
    // etc.etc. one for every .csv

    public LoadUp() {
        getMessages();
        // getEvents()
        // getOrganizers()
        // etc. etc., one for every .csv
    }
    public List<String> getMessages() {

        // gets list of messages from messages.csv
        // and sets it to <messages>
        return messages;
    }

    /** public List<String> getEvents() {

     // gets list of events from events.csv
     // and sets it to <events>
     // return events
     }
     * **/

    /** public List<String> getOrganizers() {

     // gets list of organizers from organizers.csv
     // and sets it to <organizers>
     // return organizerss
     }
     * **/

    /** etc. etc. one for every thingy
     * **/

    // Methods from IGateway
    // To be called inside Use Case Classes
    // this makes it okay to call a gateway inside use case class for some reason
    @Override
    public List<String> getMessagesList() {
        return messages;
    }

    /** etc. etc. one for every thingy
     * **/
}
