package com.cg.utils.exception;

public class NonExistingUser extends Exception {
    public NonExistingUser(String errorMessage) {
        super(errorMessage);
    }
}
