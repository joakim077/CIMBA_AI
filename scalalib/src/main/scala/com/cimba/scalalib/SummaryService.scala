package com.cimba.scalalib

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.circe._
import io.circe.generic.auto._
import org.jsoup.Jsoup
import scala.jdk.CollectionConverters._
import org.http4s.client.blaze._
import org.http4s.client._
import org.http4s.client.dsl.io._

case class UrlRequest(url: String)
case class SummaryResponse(summary: String)
case class HistoryEntry(url: String, summary: String)

class SummaryService {

  implicit val decoder = jsonOf[IO, UrlRequest]
  val clientResource = BlazeClientBuilder[IO](scala.concurrent.ExecutionContext.global).resource
  val summarizerUrl = sys.env.getOrElse("SUMMARIZER_URL", "http://summarizer:8000/summarize")

  def routes = HttpRoutes.of[IO] {

    case req @ POST -> Root / "summarize" =>
      for {
        urlReq <- req.as[UrlRequest]
        html = Jsoup.connect(urlReq.url).get().body().text()
        summary <- clientResource.use { client =>
          val payload = UrlForm("text" -> html)
          client.expect[SummaryResponse](
            Request[IO](method = Method.POST, uri = Uri.unsafeFromString(summarizerUrl))
              .withEntity(payload)
              .withHeaders(Header.Raw(ci"Content-Type", "application/x-www-form-urlencoded"))
          )(jsonOf[IO, SummaryResponse])
        }
        _ <- DB.save(urlReq.url, summary.summary)
        res <- Ok(SummaryResponse(summary.summary))
      } yield res

    case GET -> Root / "history" =>
      for {
        history <- DB.getAll()
        res <- Ok(history)
      } yield res
  }
}
