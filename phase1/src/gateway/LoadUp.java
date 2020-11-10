package gateway;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class LoadUp implements LoadUpIGateway {

    private ArrayList<String> messages = null;
    // ArrayList<String> events = null;
    // ArrayList<String> organizers = null;
    // etc.etc. one for every .csv
    private ArrayList<String> rooms;

    public LoadUp() {
        // these might be moved elsewhere, idk yet
        getMessages();
        // getEvents()
        // getOrganizers()
        // etc. etc., one for every .csv
        getRooms();
    }
    public List<String> getMessages() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("../assets/dataFiles/messages.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public List<String> getRooms(){
        rooms = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("../assets/dataFiles/rooms.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                rooms.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Overloaded with custom file path
    public ArrayList<String> getMessages(String filePath) {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while((line = br.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public ArrayList<String> getMessagesList() {
        return messages;
    }

    /** etc. etc. one for every thingy
     * **/
}
