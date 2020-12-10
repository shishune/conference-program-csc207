package Factories;

import controllers.AttendeeController;
import controllers.OrganizerController;
import controllers.SpeakerController;
import gateways.LoadUpIGateway;
import useCases.*;

public class UserActionsFactory {
    public UserAccountActions getUserActions(String type, LoadUpIGateway loader) {

        if(type == null){
            return null;
        }

        if(type.equalsIgnoreCase("A")){
            return new AttendeeActions(loader);
        }

        else if(type.equalsIgnoreCase("S")){
            return new SpeakerActions(loader);
        }

        else if(type.equalsIgnoreCase("O")){
            return new OrganizerActions(loader);
        }

        else
        {
            return null;
        }
    }
}
