package com.kenzie.supportingmaterials.exceptions;

class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(String errorMessage) {
        super(errorMessage);
    }
}
