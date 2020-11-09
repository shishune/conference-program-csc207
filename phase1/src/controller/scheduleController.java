package controller;

public interface scheduleController {

    public boolean signupEvent();

    public boolean cancelSpotEvent();

    public String viewOwnSchedule();

    public String viewAvailableSchedule();

    public int spotsAvailable();

    public boolean checkConflict();

    }

/*    Parent BASIC USER type actions **make into interfaces according to action**
         (user)
        Send and receive messages
        Add contacts
        Delete/Block Contact
        View Message

        (schedule)
        Sign-up for events
        Cancel Spot in Event
        View their own schedule (s)
        View available schedule(s)
        Number of spots left
        Check conflict with timing and spots
*/
