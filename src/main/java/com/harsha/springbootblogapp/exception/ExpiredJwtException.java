package com.harsha.springbootblogapp.exception;

public class ExpiredJwtException extends Exception {
    public ExpiredJwtException(String message) {
        super(message);
    }
}
