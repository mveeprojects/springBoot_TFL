package TubeLineStatus.POJOs.TFLResponsePOJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TFLResponse {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TFLResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
