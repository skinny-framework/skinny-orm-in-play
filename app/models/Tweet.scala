package models

import scalikejdbc._
import skinny.orm._
import org.joda.time._

case class Tweet(
  id: Long,
  userId: Long,
  text: String,
  createdAt: DateTime,
  user: Option[User] = None)

object Tweet extends SkinnyCRUDMapper[Tweet] {
  override lazy val defaultAlias = createAlias("tw")
  private[this] lazy val t = defaultAlias

  override def extract(rs: WrappedResultSet, rn: ResultName[Tweet]) = autoConstruct(rs, rn, "user")

  lazy val userRef = belongsTo[User](User, (t, user) => t.copy(user = user))

  def create(userId: Long, text: String) = {
    createWithNamedValues(
      column.userId -> userId,
      column.text -> text
    )
  }

  def findRecent(): Seq[Tweet] = {
    joins(Tweet.userRef)
      .limit(100)
      .orderBy(t.createdAt.desc)
      .apply()
  }

  def findRecentByUserId(userId: Long): Seq[Tweet] = {
    where(sqls.eq(t.userId, userId))
      .limit(100)
      .orderBy(t.createdAt.desc)
      .apply()
  }

}

