package com.interbanking.interbanking.exceptionscustom;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
