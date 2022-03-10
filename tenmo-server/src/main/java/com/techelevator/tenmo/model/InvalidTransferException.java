package com.techelevator.tenmo.model;

public class InvalidTransferException extends Exception {

    public InvalidTransferException() {
        super("Invalid Transfer, please contact support.");
    }
}
