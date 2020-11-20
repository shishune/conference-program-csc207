package useCase;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import entities.*;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import gateway.Store;
import useCase.GenerateID;

/**
 * A use case class that stores a list of rooms and can add/remove rooms.
 * @author multiple
 * @version 1
 * */
public class RoomActions {
    private HashMap<String, Room> roomsList = new HashMap<String, Room>();
    private HashMap<String, Room> roomUsername = new HashMap<String, Room>();
    private LoadUpIGateway loader;
    private ArrayList<String> loadUpRooms = new ArrayList<String>();

    public RoomActions(LoadUpIGateway loader) {
        this.loader = loader;
        getAllRooms(loader);
        addLoadedToHashMap();
    }


    public String createRoom(String roomName){
        GenerateID generator = new GenerateID(loader);
        String roomID = "R" + generator.generateId();
        Room room = new Room(roomID, roomName);
        roomUsername.put(roomName, room);
        roomsList.put(roomID, room);
        addRoom(room);
        return roomID;
    }

    /**
     * Instantiates a new RoomActions object. Allows transfer of data via gateway from csv file to hashmap
     * */
    public RoomActions(){
        loadRooms(); // transfer all rooms from rooms.csv to the roomsList Hashmap
    }

    /**
     * Transfers data via gateway class from csv file to hashmap
     * (does not violate SOLID principles because this is done via interface)
     * */
    public void loadRooms(){
        LoadUp loader = new LoadUp(); // initialize gateway class for loading
        List<String> rooms = loader.getRoomsList(); // gateway method to transfer from rooms.csv to a list of room ids
        String roomName = "T-T"; //TODO : NEED TO FIX THIS RN ALL LOADED NAMES ARE GONNA BE CALLED T-T

        for (String id:rooms) {
            Room room = new Room(id, roomName);
            roomsList.put(id, room);
        }
//
//        for (String name:roomNames) {
//            roomUsername.put(name)
//        }
    }

    /**
     * Returns and array of all rooms for storage
     * @Return array of all rooms for storage
     * */
    public ArrayList<String> storeRooms(){
        ArrayList<String> rooms = new ArrayList<String>();
        for (String id:rooms){
            rooms.add(id);
        }
        return rooms;
    }
    /**
     * Adds a room to existing repository of rooms
     * @param room the room to be added
     * @return false if the room id already exists, true if the room has been added successfully
     * */
    public boolean addRoom(Room room){

        if (roomsList.containsKey(room.getRoomId())){
            return false;
        }
        roomsList.put(room.getRoomId(), room);
        roomUsername.put(room.getRoomName(), room);
        return true;
    }

    /**
     * Removes a room from existing repository of rooms
     * @param room the room to be removed
     * @return false if the room id does not exist, true if the room has been removed successfully
     * */
    public boolean removeRoom(Room room){
        if (roomsList.containsKey(room.getRoomId())){
            roomsList.remove(room.getRoomId(), room);
            return true;
        }
        return false;
    }

    /**
     * Getter method for the hashmap of id to rooms
     * @return the hashmap of id to rooms
     * */
    public HashMap<String, Room> returnHashMap(){
        return this.roomsList;
    }

    /** gets list of messages from the IGateway **/
    private void getAllRooms(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        loadUpRooms = loader.getRoomsList();
    }

    /** Adds messages loaded from the csv to <messages> **/
    private void addLoadedToHashMap() {
        //System.out.println(conversations);
        if (loadUpRooms != null && !loadUpRooms.isEmpty()) {
            for (String roomString : loadUpRooms) {
                String[] roomInfo = roomString.split(",");
                Room loadedRoom = new Room(roomInfo[0], roomInfo[1]);
                roomsList.put(roomInfo[0], loadedRoom);
                roomUsername.put(roomInfo[1], loadedRoom);
            }
        }
    }

    public ArrayList<String> getRoomsIds() {
        ArrayList<String> storedR = new ArrayList<String>();
        if (roomsList != null && !roomsList.isEmpty()) {
            for (Map.Entry<String, Room> o : roomsList.entrySet()) {
                storedR.add(o.getValue().getRoomId() + "\n");
            }
        }
        return storedR;
    }


    }
