package com.cg.utils.exception;

public class BlockedUserException extends Exception{
    public BlockedUserException(String errorMessage) {
        super(errorMessage);
    }
}
