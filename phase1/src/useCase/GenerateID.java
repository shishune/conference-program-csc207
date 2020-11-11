package useCase;

import gateway.LoadUp;
import gateway.LoadUpIGateway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerateID {

    protected int objectId; // objectId should be made from an user class/gateway

    public int getObjectId() {
        LoadUp l = new LoadUp();
        return l.getNumOfIds();
    }

    public String generateId() {

        if (getObjectId() <= objectId) {
            objectId += 1;
        }

        else {
            objectId = getObjectId();
        }

        return String.format("%16d", objectId);
    }
}

