@Service
public class ScalaService {
    public String fetchSummary(String url) {
        return SummaryLogic.fetchAndLog(url); // Scala interop call
    }

    public List<RequestLog> getHistory() {
        return SummaryLogic.fetchHistory();
    }
}
