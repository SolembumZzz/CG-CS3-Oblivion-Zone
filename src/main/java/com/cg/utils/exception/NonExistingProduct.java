package com.cg.utils.exception;

public class NonExistingProduct extends Exception {
    public NonExistingProduct(String errorMessage) {
        super(errorMessage);
    }
}
