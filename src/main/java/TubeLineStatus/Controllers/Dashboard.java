package TubeLineStatus.Controllers;

import TubeLineStatus.POJOs.TFLResponsePOJOs.TFLResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class Dashboard {

    private ObjectMapper objectMapper;

    public Dashboard() {
        objectMapper = new ObjectMapper();
    }

    @RequestMapping("/")
    public List<TFLResponse> appStatus() throws IOException {
        return callTFLAPI();
    }

    private List<TFLResponse> callTFLAPI() {
        try {
            URL url = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
            List<TFLResponse> tflResponse = objectMapper.readValue(url, new TypeReference<List<TFLResponse>>() {
            });

            for (TFLResponse t : tflResponse) {
                System.out.println(t.getName());
                System.out.println(t.getStatus()+"\n");
            }
            return tflResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
