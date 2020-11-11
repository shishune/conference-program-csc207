import entities.Event;
import useCase.EventActions;
import org.junit.*;


import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class EventTests {

    // the Event constructor
    @Test(timeout = 50)
    public void testEventmaker() {
        EventActions eventActions = new EventActions();
        eventActions.events = new HashMap<String,Event>();
        eventActions.roomSchedule = new HashMap<String, List<String>>();
        eventActions.roomSchedule.put("Toronto", new ArrayList<String>());
        eventActions.speakerSchedule = new HashMap<String, List<String>>();
        eventActions.speakerSchedule.put("Mike", new ArrayList<String>());
        boolean lions = eventActions.createEvent("Lions", "Mike","2020-10-13 2", new ArrayList<>(), "Toronto");
        assertTrue("Event not created", lions);


    }


}



