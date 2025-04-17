package com.cimba.scalalib

import cats.effect._
import doobie._
import doobie.implicits._
import io.circe.generic.auto._

object DB {
  val dbUrl = sys.env.getOrElse("DB_URL", "jdbc:postgresql://postgres:5432/summarydb")
  val dbUser = sys.env.getOrElse("DB_USER", "postgres")
  val dbPass = sys.env.getOrElse("DB_PASS", "password")

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", dbUrl, dbUser, dbPass
  )

  def save(url: String, summary: String): IO[Int] =
    sql"INSERT INTO history (url, summary) VALUES ($url, $summary)".update.run.transact(xa)

  def getAll(): IO[List[HistoryEntry]] =
    sql"SELECT url, summary FROM history ORDER BY id DESC".query[HistoryEntry].to[List].transact(xa)
}
