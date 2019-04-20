package com.bdt.blog;

import com.blog.BdtApplication;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@ContextConfiguration(classes = {BdtApplication.class})
@SpringBootTest(webEnvironment= DEFINED_PORT)
@ActiveProfiles("test")
public class BdtSteps {
    @LocalServerPort
    private int randomServerPort;

    private static int staticRandomServerPort;

    @Before
    public void setup(){
        staticRandomServerPort = this.randomServerPort;
    }

    @Given("^BDT Application is up$")
    public void bdt_Application_is_up() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^when we browse to the application we are greeted with a welcome message$")
    public void when_we_browse_to_the_application_we_are_greeted_with_a_welcome_message() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

}
