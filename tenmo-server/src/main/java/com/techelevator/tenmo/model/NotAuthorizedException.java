package com.techelevator.tenmo.model;

public class NotAuthorizedException extends Exception {

    public NotAuthorizedException() {
        super("Not authorized to perform this transfer, please contact support.");
    }
}
