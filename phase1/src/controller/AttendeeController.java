package controller;

import entities.Attendee;
import useCase.*;

public class AttendeeController extends UserController{

    public AttendeeController(UserAccountActions user, EventActions events, RoomActions rooms, MessageActions message,
                              AttendeeActions attendee) {
        super(user, events, rooms, message, attendee);
    }

    @Override
    public boolean leaveEvent(String event, String userId){
        String username = this.attendee.usersHashMap.get(userId).getUsername();
        return this.attendee.removeEventFromUser(event, username);
    }
}
