package controllers

import play.api.libs.json._
import models._

trait JsonReadWrites {

  implicit val userWrites = new Writes[User] {
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "name" -> user.name,
      "createdAt" -> user.createdAt
    )
  }

  implicit val tweetWrites = new Writes[Tweet] {
    def writes(tweet: Tweet) = Json.obj(
      "id" -> tweet.id,
      "text" -> tweet.text,
      "user" -> tweet.user,
      "createdAt" -> tweet.createdAt
    )
  }

}
