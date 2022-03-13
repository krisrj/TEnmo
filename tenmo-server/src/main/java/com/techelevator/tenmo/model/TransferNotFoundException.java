package com.techelevator.tenmo.model;

public class TransferNotFoundException extends Exception {

    public TransferNotFoundException() {
        super("Sorry, that transfer ID didn't match an existing transfer in our system.");
    }
}
