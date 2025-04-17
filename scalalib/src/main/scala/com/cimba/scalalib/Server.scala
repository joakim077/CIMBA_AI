package com.cimba.scalalib

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.circe._
import io.circe.generic.auto._
import scala.concurrent.ExecutionContext.global

object Server extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val service = new SummaryService
    val httpApp = service.routes.orNotFound

    BlazeServerBuilder[IO](global)
      .bindHttp(9000, "0.0.0.0")
      .withHttpApp(httpApp)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
