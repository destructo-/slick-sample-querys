package slick.sample.querys.infrastucture.persistence

import slick.dbio.{DBIOAction, NoStream}
import slick.driver.PostgresDriver.api._

import scala.concurrent.Future
import com.typesafe.scalalogging.StrictLogging
import slick.sample.querys.infrastucture.persistence.DAO.GameTypesDAO.GameTypeRow
import slick.sample.querys.infrastucture.persistence.DAO.GamesDAO.GameRow
import slick.sample.querys.infrastucture.persistence.Converter.toModelConverter
import slick.sample.querys.infrastucture.persistence.DAO.GamesDAO
import slick.sample.querys.model.Game

import scala.concurrent.ExecutionContext.Implicits.global


class GameRepository(private val db: Database) extends StrictLogging {

  def getGame(name: String): Future[Seq[Game]] = getByName(name).map(_.toModel)

  private def getByName(name: String): Future[Seq[(GameRow, Option[GameTypeRow])]] =
    execQuery(GamesDAO.query.getByName(name).withType.result)

  private def execQuery[R](action: DBIOAction[R, NoStream, Nothing]): Future[R] = {
    val result = db.run(action)
    result onFailure {
      case t: Throwable =>
        logger.error("An error occurred while accessing the database", t)
    }
    result
  }
}
