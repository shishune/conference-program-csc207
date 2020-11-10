import entities.Event;
import useCase.EventActions;
import org.junit.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class EventTesting {

    @Test(timeout = 50)
    public void testString() {
        Event lions = new Event("E123","Lions", "Mike", "2020-10-13 2", new ArrayList<>(), "Toronto");
        assertEquals("E123, Lions, Mike, 2020-10-13 2, [], Toronto", lions.string());

    }

    @Test(timeout = 50)
    public void TestId() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals("E123", lions.getId());

    }

    @Test(timeout = 50)
    public void TestAttendees() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals(new ArrayList<>(), lions.getAttendees());

    }

    @Test(timeout = 50)
    public void TestEventtitle() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals("Lions", lions.getTitle());

    }

    @Test(timeout = 50)
    public void TestDateTime() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals("2020-10-13 2", lions.getDateTime());

    }

    @Test(timeout = 50)
    public void TestRoomID() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals("Toronto", lions.getRoomID());

    }

    @Test(timeout = 50)
    public void TestSpeaker() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals("Mike", lions.getSpeaker());

    }

    @Test(timeout = 50)
    public void TestAddAttendee() {
        Event lions = new Event("E123","Lions", "Mike","2020-10-13 2" , new ArrayList<>(), "Toronto");
        assertEquals(true, lions.addAttendee("A124"));
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A124");
        assertEquals(expected, lions.getAttendees());
    }

    @Test(timeout = 50)
    public void TestRemoveAttendee() {
        ArrayList<String> orginal = new ArrayList<String>();
        orginal.add("A124");
        Event lions = new Event("E123", "Lions", "Mike", "2020-10-13 2", orginal, "Toronto");
        assertEquals(true, lions.removeAttendee("A124"));
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, lions.getAttendees());
    }

}

