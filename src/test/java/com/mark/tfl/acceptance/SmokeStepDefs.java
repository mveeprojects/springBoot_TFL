package com.mark.tfl.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SmokeStepDefs {

    private static Logger LOGGER = LoggerFactory.getLogger(SmokeStepDefs.class);
    private int statusCode = 0;
    CloseableHttpResponse response;

    @Given("^I call the '(.+)' endpoint locally$")
    public void iCallAnEndpointLocally(String endpoint) throws Throwable {
        LOGGER.info("Calling the {} endpoint locally", "issues");

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "http://localhost:8080" + endpoint;

        HttpGet request = new HttpGet(url);
        response = httpClient.execute(request);
        String s = response.toString();
        assertThat(s, not(""));
    }

    @Then("^I should receive a status code of '(\\d+)'$")
    public void iShouldReceiveAStatusCodeOf(int expectedStatus) throws Throwable {
        statusCode = response.getStatusLine().getStatusCode();
        assertThat(statusCode, is(expectedStatus));
    }
}
