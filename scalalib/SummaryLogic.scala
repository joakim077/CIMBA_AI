object SummaryLogic {
  def fetchAndLog(url: String): String = {
    val content = WebCrawler.fetch(url)
    val summary = Http.post("http://summarizer:8000/summarize", Map("text" -> content))
    DB.save(url, summary)
    summary
  }

  def fetchHistory(): List[RequestLog] = {
    DB.fetchAll()
  }
}
