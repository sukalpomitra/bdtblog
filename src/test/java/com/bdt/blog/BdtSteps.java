package com.bdt.blog;

import com.blog.BdtApplication;
import com.blog.blog.GreetingMessage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
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
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GreetingMessage> response = restTemplate
                    .getForEntity("http://localhost:" + staticRandomServerPort + "/bdt/v1//message",
                            GreetingMessage.class);
            assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
            assertThat(response.getBody().getMessage(), equalTo("Hello World"));
        } catch (HttpClientErrorException ex) {
            fail();
        }
    }

}
