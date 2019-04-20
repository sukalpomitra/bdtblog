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
        return restTemplate.getForEntity(thirdPartyService + "/third/party/message",
                GreetingMessage.class).getBody();
    }
}
