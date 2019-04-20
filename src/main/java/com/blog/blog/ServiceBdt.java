package com.blog.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceBdt {
    @Value(value = "${third.party.service}")
    private String thirdPartyService;

    public GreetingMessage getGreetingMessage() {
        RestTemplate restTemplate = new RestTemplate();
        return translateMessage(restTemplate.getForEntity(thirdPartyService + "/third/party/message",
                GreetingMessage.class).getBody());
    }

    private GreetingMessage translateMessage(final GreetingMessage greetingMessage) {
        String translatedMessage;
        if (greetingMessage.getMessage().equals("Hello World")) {
            translatedMessage = "Hello World";
        } else {
            translatedMessage = "Goodbye World";
        }
        GreetingMessage response = new GreetingMessage();
        response.setMessage(translatedMessage);
        return response;
    }
}
