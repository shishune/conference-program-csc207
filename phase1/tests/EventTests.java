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
        LocalDateTime actualDateTime = LocalDateTime.of(2020, Month.DECEMBER, 25, 4, 0);
        eventActions.events = new HashMap<String,Event>();
        eventActions.timeSchedule = new HashMap<String, List<LocalDateTime>>();
        eventActions.timeSchedule.put("Toronto", new ArrayList<LocalDateTime>());
        eventActions.speakerSchedule = new HashMap<String, List<LocalDateTime>>();
        eventActions.speakerSchedule.put("Mike", new ArrayList<LocalDateTime>());
        boolean lions = eventActions.createEvent("Lions", "Mike",LocalDateTime.of(2018, Month.DECEMBER, 25, 4, 0) , new ArrayList<>(), "Toronto");
        assertTrue("Event not created", lions);

    }

}



