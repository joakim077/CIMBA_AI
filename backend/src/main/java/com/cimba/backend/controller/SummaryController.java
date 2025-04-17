package main.java.com.cimba.backend.controller;

import com.cimba.backend.model.UrlRequest;
import com.cimba.backend.service.ScalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SummaryController {

    @Autowired
    private ScalaService scalaService;

    @PostMapping("/summarize")
    public ResponseEntity<?> summarize(@RequestBody UrlRequest request) {
        String summary = scalaService.fetchSummary(request.getUrl());
        return ResponseEntity.ok(Map.of("summary", summary));
    }

    @GetMapping("/history")
    public ResponseEntity<?> history() {
        return ResponseEntity.ok(scalaService.getHistory());
    }
}
