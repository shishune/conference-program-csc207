package controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import entities.Attendee;
import useCase.*;

public class AttendeeController extends UserController{
//    private MessageActions message;
//    private EventActions e;
    private AttendeeActions attendee;
//    private RoomActions room;
//    private OrganizerActions organizer;
//    private SpeakerActions speaker;

    public AttendeeController(EventActions events, RoomActions rooms, MessageActions message,
                              AttendeeActions attendee) {
        super(events, rooms, message, attendee);
        this.attendee = attendee;
    }

    @Override
    public boolean leaveEvent(String event, String userId){
        String username = this.returnUserIDHashMap().get(userId).getUsername();
        // String username = this.attendee.returnUsersHashMap().get(userId).getUsername(); <-- original
            // i just took away the attendee in this.attendee but i wasnt sure if the 'this' was important
        // i chnged it because i made a master hashmap and the method is now in usercontroller not attendee
        return this.attendee.removeEventFromUser(event, username);
    }
}
