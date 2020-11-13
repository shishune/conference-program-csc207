package useCase;

import gateway.LoadUp;
import gateway.LoadUpIGateway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A use case class that creates a unique id when called
 * @author Mizna & Jiessie
 * @version 1
 */

public class GenerateID {

    protected int objectId; // objectId should be made from an user class/gateway
    private LoadUpIGateway loader;

    /**
     *
     * */
    public GenerateID(LoadUpIGateway loader) {
        this.loader = loader;
    }

    /**
     * Generates 16 digit unique id number when called
     * @return String of unique 16 digits
     * */
    public String generateId() {

        if (loader.getObjectId() <= objectId) {
            objectId += 1;
        }

        else {
            objectId = loader.getObjectId();
        }

        return String.format("%16d", objectId);
    }
}

