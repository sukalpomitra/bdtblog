package com.blog.blog;

import java.io.Serializable;

public class GreetingMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    public GreetingMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
