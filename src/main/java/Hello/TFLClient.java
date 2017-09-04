package Hello;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TFLClient {

    private static final String template = "Hello %s";
    private final AtomicLong counter = new AtomicLong();

    private int lastCall = 0;
    private String cachedResponse = "";

    @RequestMapping("/greeting")
    public TFLResponsePOJO greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new TFLResponsePOJO(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/callTFL")
    public String callTFLAPI() {

        if (this.lastCall > 0) {
            return cachedResponse;
        }

        try {
            URL endpoint = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
            HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String responseJsonString = br.readLine();
            br.close();
            this.lastCall = 1;
            this.cachedResponse = responseJsonString;
            return responseJsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}