package interfaces;

import entities.Attendee;

import java.util.ArrayList;
import java.util.Map;

public interface Storable {
    /**
     * Store entity as a list
     * @return ArrayList<String>
     */
    ArrayList<String> store();
}
