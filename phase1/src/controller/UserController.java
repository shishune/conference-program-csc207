package controller;

public interface UserController {

    public boolean sendMessage();
    public boolean addContact();
    public boolean deleteContact();
    public String vewMessage();
}
/*    Parent BASIC USER type actions **make into interfaces according to action**
         (user)
        Send messages
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

