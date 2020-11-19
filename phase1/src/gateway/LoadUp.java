package gateway;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Loads up information for user including messages, events, rooms, attendees and organizers
 */
public class LoadUp implements LoadUpIGateway {

    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> events = new ArrayList<>();
    // ArrayList<String> organizers = null;
    // etc.etc. one for every .csv
    private ArrayList<String> rooms = new ArrayList<>();
    private ArrayList<String> attendees = new ArrayList<>();
    private ArrayList<String> organizers = new ArrayList<>();
    private ArrayList<String> speakers = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();
    protected int objectId;

    /**
     * Loads up information of messages, events, rooms, attendees and organizers
     */
    public LoadUp() {
        // these might be moved elsewhere, idk yet
        getMessages();
        getEvents();
        // getOrganizers()
        // etc. etc., one for every .csv
        getRooms();
        getSpeakers();
        getIds();
    }

    /**
     * This method returns all object ids in a list from entities.csv
     * @return object ids in a list
     */
    public ArrayList<String> getIds(){
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/entities.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                ids.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }

    /**
     * adds to the list of ids
     * @param id the id of an entity
     */
    public void addId(String id){
        System.out.println(ids);
        this.ids.add(id);
    }
    /**
     * This method returns messages in a list from messages.csv
     * @return messages in a list
     */
    public ArrayList<String> getMessages() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/messages.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    /**
     * This method returns rooms in a list from rooms.csv
     * @return rooms in a list
     */

    public ArrayList<String> getRooms(){
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/rooms.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                rooms.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }


    /**
     * This method returns rooms in a list from events.csv
     * @return rooms
     */
    public List<String> getEvents() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/events.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                events.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    /**
     * Getter for events
     * @param filePath the text file path of event to be found
     * @return events
     */
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

    /**
     * Getter for messages
     * @param filePath the text file path of messages to be found
     * @return messages
     */
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

    /**
     * This method returns attendees in a list from attendees.csv
     * @return attendees in a list
     */
    public ArrayList<String> getAllAttendees() {
        // gets list of attendees from attendees.csv and sets it to <attendeesHM>
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/attendees.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                attendees.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attendees;
    }

    /**
     * This method returns organizers in a list from organizers.csv
     * @return organizers in a list
     */
    public ArrayList<String> getAllOrganizers() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/organizers.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                organizers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return organizers;
    }

    /**
     * This method returns messages in a list from messages.csv
     * @return messages in a list
     */
    public ArrayList<String> getSpeakers() {
        // gets list of messages from messages.csv and sets it to <messages>
        try (BufferedReader br = new BufferedReader(new FileReader("./phase1/src/assets/dataFiles/speakers.csv"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                speakers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return speakers;
    }

    /**
     * Getter for the number of the object IDs
     * @return objectId in a list
     */
    public int getNumOfIds() {
        /*
        String filename = "./phase1/src/assets/dataFiles/entities.csv";
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
        System.out.println(objectId);
        return objectId;
        */

        return ids.size();
    }

    /**
     * Getter for the last object Id made using IGateway
     * */
    public int getObjectId() {
        //LoadUp l = new LoadUp();
        return getNumOfIds();
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

    /*@Override
    public ArrayList<String> getRooms() { return rooms; }*/

    @Override
    public List<String> getEventsList() {
        return events;
    }

    @Override
    public ArrayList<String> getSpeakersList() { return speakers; }

    /** etc. etc. one for every thingy
     * **/
}
