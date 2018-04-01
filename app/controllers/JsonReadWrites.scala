package controllers

import play.api.libs.json._
import models._

// joda-time
import org.joda.time._
import play.api.libs.json.JodaWrites
import play.api.libs.json.JodaReads

trait JsonReadWrites {
  implicit val dateTimeWriter: Writes[DateTime] = JodaWrites.jodaDateWrites("dd/MM/yyyy HH:mm:ss")
  implicit val dateTimeJsReader = JodaReads.jodaDateReads("yyyyMMddHHmmss")

  implicit val userWrites = new Writes[User] {
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "name" -> user.name,
      "createdAt" -> user.createdAt)
  }

  implicit val tweetWrites = new Writes[Tweet] {
    def writes(tweet: Tweet) = Json.obj(
      "id" -> tweet.id,
      "text" -> tweet.text,
      "user" -> tweet.user,
      "createdAt" -> tweet.createdAt)
  }

}
