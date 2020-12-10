//package Factories;
//import controllers.*;
//
//public class UserControllerFactory {
//    public UserController getUserController(String type) {
//
//        if(type == null){
//            return null;
//        }
//
//        if(type.equalsIgnoreCase("A")){
//            return new AttendeeController();
//        }
//
//        else if(type.equalsIgnoreCase("S")){
//            return new SpeakerController();
//        }
//
//        else if(type.equalsIgnoreCase("O")){
//            return new OrganizerController();
//        }
//
//        else
//        {
//            return null;
//        }
//    }
//}
