package controllers

import javax.inject.Inject
import models._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.i18n.MessagesApi
import play.api.libs.json._
import play.api.Play.current

class Twitter @Inject() (messages: MessagesApi) extends Controller with JsonReadWrites {

  private[this] val JsonContentType = "application/json; charset=utf-8"

  def index = Action { Ok(views.html.index()) }

  def showTimeline = Action {
    val tweets = Tweet.findRecent()
    Ok(Json.toJson(tweets)).as(JsonContentType)
  }

  def showUserTimeline(userId: Long) = Action {
    val tweets = Tweet.findRecentByUserId(userId)
    Ok(Json.toJson(tweets)).as(JsonContentType)
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
        val errors = formWithErrors.errors.map { e => e.key -> messages.preferred(req).apply(e.message, e.args: _*) }.toMap
        BadRequest(Json.toJson(errors)).as(JsonContentType)
      },
      form => {
        User.findByName(form.userName) match {
          case Some(user) => Tweet.create(user.id, form.text)
          case _ =>
            val userId = User.create(form.userName)
            Tweet.create(userId, form.text)
        }
        Ok
      }
    )
  }

  def deleteTweet(tweetId: Long) = Action {
    Tweet.deleteById(tweetId)
    Ok
  }

}
