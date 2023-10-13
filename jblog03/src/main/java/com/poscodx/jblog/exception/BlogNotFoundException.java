package com.poscodx.jblog.exception;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message) {

        super(message);
    }
    public BlogNotFoundException() {

        super("BlogNotFoundException Thrown");
    }
}
