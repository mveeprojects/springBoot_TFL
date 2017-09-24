package com.mark.tfl.acceptance;

import cucumber.api.java.en.Given;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SmokeStepDefs {

    private static Logger LOGGER = LoggerFactory.getLogger(SmokeStepDefs.class);

    @Given("^I call the '(.+)' endpoint locally I should receive status code '(\\d+)'$")
    public void iCallAnEndpointLocally(String endpoint, int expectedStatus) throws Throwable {
        LOGGER.info("Calling the {} endpoint locally", "issues");

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "http://localhost:8080" + endpoint;

        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertThat(actualStatus ,is(expectedStatus));
    }
}
