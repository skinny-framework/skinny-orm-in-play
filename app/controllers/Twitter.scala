package controllers

import models._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.i18n.Messages
import play.api.libs.json._

object Twitter extends Controller with JsonReadWrites {

  private[this] val JsonContentType = "application/json"

  def index = Action {
    Ok(views.html.index())
  }

  def showTimeline = Action {
    Ok(Json.toJson(Tweet.findRecent())).as(JsonContentType)
  }

  def showUserTimeline(userId: Long) = Action {
    Ok(Json.toJson(Tweet.findRecentByUserId(userId))).as(JsonContentType)
  }

  private[this] lazy val tweetForm = Form(
    mapping(
      "userName" -> nonEmptyText(maxLength = 20),
      "text" -> nonEmptyText
    )(TweetForm.apply)(TweetForm.unapply)
  )

  def postTweet = Action { implicit req =>
    tweetForm.bindFromRequest.fold(
      formWithErrors => {
        val errors = formWithErrors.errors.map { e =>
          e.key -> Messages(e.message, e.args: _*)
        }.toMap
        BadRequest(Json.toJson(errors)).as(JsonContentType)
      },
      form => {
        val userId = User.findByName(form.userName).map(_.id)
          .getOrElse { User.create(form.userName) }
        Tweet.create(userId, form.text)
        Ok
      }
    )
  }

}
