package com.cimba.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class ScalaService {

    @Value("${scala.endpoint}")
    private String scalaEndpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchSummary(String url) {
        Map<String, String> request = Map.of("url", url);
        Map response = restTemplate.postForObject(scalaEndpoint + "/summarize", request, Map.class);
        return (String) response.get("summary");
    }

    public List<Map<String, Object>> getHistory() {
        return restTemplate.getForObject(scalaEndpoint + "/history", List.class);
    }
}
