package slick.sample.querys.infrastucture.persistence.DAO

import org.joda.time.DateTime
import slick.driver.PostgresDriver.api._
import slick.lifted.Tag

import scala.language.higherKinds


object GamesDAO {
  val query = TableQuery[Scheme]

  case class GameRow(id: Int, typeId: Int, name: String, creationTime: DateTime)

  class Scheme(tag: Tag) extends Table[GameRow](tag, "games") with CreationTime {
    def id = column[Int]("id")
    def typeId = column[Int]("type_id")
    def name = column[String]("name")
    override def creationTime = column[DateTime]("ctime")

    def gameType = foreignKey("game_types", typeId, GameTypesDAO.query)(_.id)

    def * = (id, typeId, name, creationTime) <> (GameRow.tupled, GameRow.unapply)
  }

  implicit class OptionsExt[C[_]](q: Query[Scheme, GameRow, C]) {

    def getByName(name: String) = q.filter(_.name === name)

    def withType = q.joinLeft(GameTypesDAO.query).on(_.typeId === _.id)
  }

}
