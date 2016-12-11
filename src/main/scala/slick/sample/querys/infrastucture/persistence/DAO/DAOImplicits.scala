package slick.sample.querys.infrastucture.persistence.DAO

import org.joda.time.DateTime
import slick.driver.PostgresDriver.api._
import com.github.tototoshi.slick.PostgresJodaSupport._

import scala.language.higherKinds


object DAOImplicits {

  case class TimeConditions(timeStart: DateTime, timeEnd: DateTime)

  case class LimitConditions(offset: Int, limit: Int)

  implicit class TimeConditionsFilter[E <: CreationTime, U, C[_]](q: Query[E, U, C]) {

    def timeFilter(conditions: Option[TimeConditions]): Query[E, U, C] = {
      val action = conditions map { condition =>
        q.filter(_.creationTime.between(condition.timeStart, condition.timeEnd))
      }
      action.getOrElse(q)
    }
  }

  implicit class LimitConditionsFilter[E, U, C[_]](q: Query[E, U, C]) {

    def limitConditionsFiltered(conditions: Option[LimitConditions]): Query[E, U, C] = {
      val action = conditions.map { condition =>
        q.drop(condition.offset).take(condition.limit)
      }
      action.getOrElse(q)
    }
  }

}
