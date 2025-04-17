@RestController
public class SummaryController {

    @Autowired
    private ScalaService scalaService;

    @PostMapping("/api/summarize")
    public ResponseEntity<?> summarize(@RequestBody UrlRequest request) {
        String summary = scalaService.fetchSummary(request.getUrl());
        return ResponseEntity.ok(Map.of("summary", summary));
    }

    @GetMapping("/api/history")
    public ResponseEntity<?> history() {
        return ResponseEntity.ok(scalaService.getHistory());
    }
}
