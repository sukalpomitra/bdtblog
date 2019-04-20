package com.blog.blog;

import org.springframework.stereotype.Service;

@Service
public class ServiceBdt {
    public GreetingMessage getGreetingMessage() {
        GreetingMessage greetingMessage =  new GreetingMessage();
        greetingMessage.setMessage("Hello World");
        return greetingMessage;
    }
}
