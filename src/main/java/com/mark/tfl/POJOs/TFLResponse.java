package com.mark.tfl.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TFLResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("lineStatuses")
    private List<Map<String, Object>> lineStatuses;

    public String getName() {
        return name;
    }

    public String getStatus(){
        Map<String, Object> map = lineStatuses.get(0);
        return String.valueOf(map.get("statusSeverityDescription"));
    }
}
