###### **README FILE** - G-0082
## Conference System Program
_This program creates a system allowing users to login in and according to their account type, schedule for events, message other users and present personalized schedules, whilst implementing design rules from Clean Architecture._

#### How this program works.
The user is first asked to either sign up or log in to their account. When signing up they have the choice of 3 different user types; Organizer, Speaker and Attendee. When logging into the account the user is prompted to give their username and password, if they are inputted incorrectly pr if they do not exist. The user will be taken back to the starting menu.

Once they have logged in, depending on their role in the program, they will be presented with different menus, allowing different actions.
All users can;
            
    [1] Log out
    [2] Send a message
    [3] View all messages
    [4] Add a contact
    [5] View all contacts
    [13] View all Conferences
            
#####Option 1 
 
Allows them to log out, saving the information they have created in a corresponding csv file, which will be then loaded. If user does not log out, their information will not be stored.


#####Option 2

Allows them to send a message. The recipients of the messages will differ for each user.
- Organizers can send messages to all speakers and all attendees of an event, as well as individually
- Speakers can send messages to all attendees of one event or multiple events, as well as respond to a specific attendee
- Attendees can send messages to other attendees, as well as view messages that have been sent to them.

#####Option 3 

Allows users to see all the messages that have been sent, and are able to reply to them, categorized by users. 

#####Option 4 

Allows users to add any contact they want.

#####Option 5 

Allows users to view all the contacts they have already added.
 
#####Option 6 

Allow 
            

### Organizer
The organizer-specific menu is extended by the options below;

    [6] Add an event/create new speaker account"
    [7] Remove/reschedule an event
    [8] View all your created events
    [9] Add room
    [10] Add user
    [11] View all conferences
    [12] Add a conference
    [13] Change capacity of an event
    [14] View Statistics
                
#####Option 6
 
Allows organizers to create an event, and in doing so, allows them to create a new room, and/or adding a new speaker.
 - They can also use existing speakers and rooms. 
 - This option checks if speakers are double-booked or if the room is double-booked

#####Option 7 

Allows organizers to remove or reschedule an event. This option will also check if the event exists and if rescheduling will cause errors.Option 8 allows organizers to view all the events that the current user has created

#####Option 8 

Allows organizers to add a room without creating an event

#####Option 9 

Allows organizers to create a speaker without creating an event

#####Option 10

Allows organizers to see all the events.

#####Option 11

Allows smthn

### Speaker
The speaker-specific menu is extended by the option below;
     
    [6] View your schedule of talks
    
Option 6 allows the speaker to view the events they are scheduled to speak at.

###### Note: The total number of speakers must be greater than or equal to the number of events as each event can only have one speaker.

### Attendee
The attendee-specific menu is extended by the options below;
    
    [6] Sign up for an event / Save an event"
    [7] Cancel enrollment for an event"
    [8] View all events"
    [9] View your schedule of events"
    [10] View your saved events"
    [11] View VIP events"
    [12] Sign up for conference
    
    
#####Option 6 

Allows the attendee to sign up to an event after showing the available events to them. It does not present the events that conflict with events that they are already sign up for, full events, nor the events they are already signed up for. 

 +***New Feature!*** It also allows the attendee to save an event. This will then allow them to view these saved events at a later time and 

#####Option 7 

Allows the attendee to cancel their spot for an event they are already signed up for.



#####Option 8 
 
Allows the attendee to view the possible events available to them, with no conflicts.

#####Option 9 

Allows the attendee to view the schedule of events they are already signed up for, showing the title, speaker and room that it will take place in.


##Design

Our code implements the design rules from Clean Architecture. The UML diagram of this design can be found in the UML file.
The choice to store the data in the csv files was made to prepare for the implementation for phase 2, as the way the information is processed, is similar to the way databases are used.






