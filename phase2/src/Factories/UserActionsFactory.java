//package Factories;
//import gateways.LoadUpIGateway;
//import useCases.UserAccountActions;
//import useCases.AttendeeActions;
//import useCases.SpeakerActions;
//import useCases.OrganizerActions;
//
//public class UserActionsFactory {
//    public UserAccountActions getUserActions(String type, LoadUpIGateway loader) {
//
//        if(type == null){
//            return null;
//        }
//
//        if(type.equalsIgnoreCase("A")){
//            return new AttendeeActions(loader);
//        }
//
//        else if(type.equalsIgnoreCase("S")){
//            return new SpeakerActions(loader);
//        }
//
//        else if(type.equalsIgnoreCase("O")){
//            return new OrganizerActions(loader);
//        }
//
//        else
//        {
//            return null;
//        }
//    }
//}
