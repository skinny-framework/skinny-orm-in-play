package models

import scalikejdbc._
import skinny.orm._
import org.joda.time._

case class User(
  id: Long,
  name: String,
  description: Option[String],
  createdAt: DateTime,
  tweets: Seq[Tweet] = Nil
)

object User extends SkinnyCRUDMapper[User] {
  override lazy val defaultAlias = createAlias("u")
  private[this] lazy val u = defaultAlias

  override def extract(rs: WrappedResultSet, rn: ResultName[User]) = autoConstruct(rs, rn, "tweets")

  lazy val tweetsRef = hasMany[Tweet](
    many = Tweet -> Tweet.defaultAlias,
    on = (u, t) => sqls.eq(u.id, t.userId),
    merge = (u, ts) => u.copy(tweets = ts)
  )

  def create(name: String): Long = createWithNamedValues(column.name -> name)

  def findByName(name: String): Option[User] = where(sqls.eq(u.name, name)).apply().headOption

}
