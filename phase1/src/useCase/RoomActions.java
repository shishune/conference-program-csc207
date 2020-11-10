package useCase;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import entities.*;
import gateway.LoadUp;
import gateway.Store;
import useCase.GenerateID;

/**
 * A use case class that stores a list of rooms and can add/remove rooms.
 * @author Cynthia
 * @version 1
 * */
public class RoomActions {
    private HashMap<String, Room> roomsList;
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
        for (String id:rooms){
            Room room = new Room(id);
            roomsList.put(id, room);
        }
    }

    /**
     * Transfers data via gateway class from hashmap to csv file
     * (does not violate SOLID principles because this is done via interface)
     * */
    public void storeRooms(){
        Store store = new Store(); //initialize gateway class for storing
        ArrayList<String> rooms = new ArrayList<String>();
        for (String id:rooms){
            rooms.add(id);
        }
        store.storeRooms(rooms);
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

    public HashMap<String, Room> returnHashMap(){
        return roomsList;
    }





}
