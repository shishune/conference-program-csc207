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
    private HashMap<String, Room> roomsID = new HashMap<String, Room>();
    private HashMap<String, Room> roomUsername = new HashMap<String, Room>();
    private LoadUpIGateway loader;
    private ArrayList<String> loadUpRooms = new ArrayList<String>();

    public RoomActions(LoadUpIGateway loader) {
        this.loader = loader;
        getAllRooms(loader);
        addRoomToHashMap();
    }


    public HashMap<String, Room> returnHashMap() {
        return roomsID;
    }

    public HashMap<String, Room> returnRoomUsernameHashMap() {
        return roomUsername;
    }

    public boolean roomExists(String username){
        return roomUsername.containsKey(username);
    }

    public Room createRoom(String username) {
        GenerateID generateId = new GenerateID(loader);
        String userId = "R" + generateId.generateId();
        Room room = new Room(userId, username);
        System.out.println(returnHashMap());
        addRoomIdToHashMap(room);
        addRoomUsernameToHashMap(room);
        System.out.println(returnHashMap());
        addRoom(room);
        return room;
    }

    private void addRoomIdToHashMap(Room addMe) {
        if (!roomsID.containsKey(addMe.getRoomId())) {
            roomsID.put(addMe.getRoomId(), addMe);
        }
    }

    private void addRoomUsernameToHashMap(Room addMe) {

        if (!roomUsername.containsKey(addMe.getRoomName())) {
            roomUsername.put(addMe.getRoomName(), addMe);
        }
    }

    public boolean addRoom(Room room){

        if (roomsID.containsKey(room.getRoomId())){
            return false;
        }
        roomsID.put(room.getRoomId(), room);
        roomUsername.put(room.getRoomName(), room);
        return true;
    }

    public boolean removeRoom(Room room){
        if (roomsID.containsKey(room.getRoomId()) && roomUsername.containsKey(room.getRoomName())){
            roomsID.remove(room.getRoomId(), room);
            roomUsername.remove(room.getRoomName(), room);
            return true;
        }
        return false;
    }

    public Room findRoomFromUsername(String username) {
        return roomUsername.get(username);
    }

    public Room findRoomFromId(String userId) {
        return roomsID.get(userId);
    }

    private void getAllRooms(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        loadUpRooms = loader.getRooms();
    }

    private void addRoomToHashMap() {

        if (loadUpRooms != null) {

            for (String roomString : loadUpRooms) {
                String[] roomInfo = roomString.split(",");

                Room loadedRoom = new Room(roomInfo[0], roomInfo[1]);

                roomsID.put(roomInfo[0], loadedRoom);
                roomUsername.put(roomInfo[1], loadedRoom);
            }

        }
    }

    public ArrayList<String> getRoomsIds() {
        ArrayList<String> storedR = new ArrayList<String>();
        if (roomsID != null && !roomsID.isEmpty()) {
            for (Map.Entry<String, Room> o : roomsID.entrySet()) {
                storedR.add(o.getValue().getRoomId() + "\n");
            }
        }
        return storedR;
    }

    public ArrayList<String> storingRooms() {
        ArrayList<String> storedR = new ArrayList<String>();
        if(roomUsername != null && !roomUsername.isEmpty()) {
            for (Map.Entry<String, Room> o : roomUsername.entrySet()) {
                storedR.add(o.getValue().getRoomName() + "\n");
            }
        }
        return storedR;

    }




    /*
    */
/**
     * Instantiates a new RoomActions object. Allows transfer of data via gateway from csv file to hashmap
     * *//*

    public RoomActions(){
        loadRooms(); // transfer all rooms from rooms.csv to the roomsList Hashmap
    }

    */
/**
     * Transfers data via gateway class from csv file to hashmap
     * (does not violate SOLID principles because this is done via interface)
     * *//*

    public void loadRooms(){
        LoadUp loader = new LoadUp(); // initialize gateway class for loading
        List<String> rooms = loader.getRooms(); // gateway method to transfer from rooms.csv to a list of room ids
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

    */
/**
     * Returns and array of all rooms for storage
     * @Return array of all rooms for storage
     * *//*

    public ArrayList<String> storeRooms(){
        ArrayList<String> rooms = new ArrayList<String>();
        for (String id:rooms){
            rooms.add(id);
        }
        return rooms;
    }
    */
/**
     * Adds a room to existing repository of rooms
     * @param room the room to be added
     * @return false if the room id already exists, true if the room has been added successfully
     * *//*

    public boolean addRoom(Room room){

        if (roomsList.containsKey(room.getRoomId())){
            return false;
        }
        roomsList.put(room.getRoomId(), room);
        roomUsername.put(room.getRoomName(), room);
        return true;
    }

    */
/**
     * Removes a room from existing repository of rooms
     * @param room the room to be removed
     * @return false if the room id does not exist, true if the room has been removed successfully
     * *//*

    public boolean removeRoom(Room room){
        if (roomsList.containsKey(room.getRoomId())){
            roomsList.remove(room.getRoomId(), room);
            return true;
        }
        return false;
    }

    */
/**
     * Getter method for the hashmap of id to rooms
     * @return the hashmap of id to rooms
     * *//*

    public HashMap<String, Room> returnHashMap(){
        return this.roomsList;
    }

    */
/** gets list of messages from the IGateway **//*

    private void getAllRooms(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        loadUpRooms = loader.getRooms();
    }


    */
/** Adds messages loaded from the csv to <messages> **//*

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
        //return loadUpRooms;
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

    public ArrayList<String> storingRooms() {
        ArrayList<String> storedR = new ArrayList<String>();
        if(roomUsername != null && !roomUsername.isEmpty()) {
            for (Map.Entry<String, Room> o : roomUsername.entrySet()) {
                storedR.add(o.getValue().getRoomName() + "\n");
            }
        }
        return storedR;

    }
*/


    }
