package presenters;

import java.util.ArrayList;
import java.util.List;

/**
 * A presenter class. This class is responsible for anything related to displaying conferencess to the user.
 * @author multiple
 * @version 1
 * */
public class ConferencePresenter {

    /**
     * Displays conferences
     * */
    public void displayConferences(ArrayList<List<String>> conferencesList){
        if(conferencesList != null){
            if(conferencesList.isEmpty()){
                System.out.println("There are no conferences in existance.");
            } else {
                int count = 1;
                for (List<String> info: conferencesList){
                    //TODO: detect if conference is current
                    //TODO: print events
                    if(true /* conference start time same or after current time*/){
                        System.out.println(count);
                        count ++;

                        System.out.println("Conference title: "+info.get(0));
                        // display events?
                        //System.out.println("Events: "+info.get(1));
                        System.out.println("Start Time: "+info.get(1));
                        System.out.println("End Time: "+info.get(2));

                        System.out.println("\n");
                    }
                }
            }
        }
    }
}
