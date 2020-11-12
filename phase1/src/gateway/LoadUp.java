package gateway;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class LoadUp implements LoadUpIGateway {

    private ArrayList<String> messages = null;
    private ArrayList<String> events = new ArrayList<>();
    // ArrayList<String> organizers = null;
    // etc.etc. one for every .csv
    private ArrayList<String> rooms;
    private ArrayList<String> attendees = null;
    private ArrayList<String> organizers = null;
    protected int objectId;

    public LoadUp() {
        // these might be moved elsewhere, idk yet
        getMessages();
        getEvents();
        // getOrganizers()
        // etc. etc., one for every .csv
        getRooms();
    }
    public ArrayList<String> getMessages() {
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
    public List<String> getEvents() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("../assets/dataFiles/events.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                events.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }
    public List<String> getEvents(String filePath) {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                events.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }


    // Overloaded with custom file path
    public List<String> getMessages(String filePath) {
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

    public ArrayList<String> getAllAttendees() {
        // gets list of attendees from attendees.csv and sets it to <attendeesHM>
        try (BufferedReader br = new BufferedReader(new FileReader("../assets/dataFiles/attendees.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                attendees.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attendees;
    }

    public ArrayList<String> getAllOrganizers() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("../assets/dataFiles/organizers.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                organizers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return organizers;
    }


    public int getNumOfIds() {
        String filename = "entities.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = null;

            while ((line = br.readLine()) != null) {
                objectId++;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            // TODO: tell user error was generated
            e.printStackTrace();
        }
        return objectId;
    }



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

    @Override
    public ArrayList<String> getRoomsList() { return rooms; }

    @Override
    public List<String> getEventsList() {
        return events;
    }

    /** etc. etc. one for every thingy
     * **/
}
