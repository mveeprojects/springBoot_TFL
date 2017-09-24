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
    private static CloseableHttpResponse response;
    private static CloseableHttpClient httpClient;
    private static String baseUrl = "http://localhost:8080";

    @Given("^I call the '(.+)' endpoint locally$")
    public void iCallAnEndpointLocally(String endpoint) throws Throwable {
        LOGGER.info("Calling the {} endpoint locally", "issues");

        httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrl + endpoint);
        response = httpClient.execute(request);
        httpClient.close();

        assertThat(response, not(""));
    }

    @Then("^I should receive a status code of '(\\d+)'$")
    public void iShouldReceiveAStatusCodeOf(int expectedStatus) throws Throwable {
        int statusCode = response.getStatusLine().getStatusCode();
        assertThat(statusCode, is(expectedStatus));
    }
}
