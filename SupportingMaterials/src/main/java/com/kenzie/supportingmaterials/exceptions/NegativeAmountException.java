package com.kenzie.supportingmaterials.exceptions;

class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
