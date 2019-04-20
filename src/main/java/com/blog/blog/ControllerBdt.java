package com.blog.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/bdt/v1", produces = APPLICATION_JSON_VALUE)
public class ControllerBdt {

  @Autowired
  private ServiceBdt bdtService;

  @GetMapping(path = "message")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  GreetingMessage getGreeingMessage() {
      return bdtService.getGreetingMessage();
  }
}
