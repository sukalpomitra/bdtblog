package com.bdt.blog;

import com.blog.BdtApplication;
import com.blog.blog.GreetingMessage;
import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@ContextConfiguration(classes = {BdtApplication.class})
@SpringBootTest(webEnvironment= DEFINED_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:test.properties")
public class BdtSteps {
    @LocalServerPort
    private int randomServerPort;

    private static int staticRandomServerPort;

    private WireMockServer wireMockServer = new WireMockServer();

    @Before
    public void setup(){
        staticRandomServerPort = this.randomServerPort;
        setupWireMock();
    }

    @After
    public void cleanup() {
        wireMockServer.stop();
    }

    private void setupWireMock(){
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/third/party/message"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getJson())));
    }

    private String getJson(){
        String json = "";
        try {
            json = IOUtils.toString(BdtSteps.class.getClassLoader().getResourceAsStream("thirdparty.json"),
                    "UTF-8");
        } catch (IOException e) {
        }
        return json;
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
