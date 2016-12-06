package slick.sample.querys.infrastucture.persistence

import slick.lifted.Tag
import slick.driver.PostgresDriver.api._
import scala.language.higherKinds


object GamesDAO {
  val query = TableQuery[Scheme]

  case class GameRow(id: Int, typeId: Int, name: String)

  class Scheme(tag: Tag) extends Table[GameRow](tag, "games") {
    def id = column[Int]("id")
    def typeId = column[Int]("type_id")
    def name = column[String]("name")

    def gameType = foreignKey("game_types", typeId, GameTypesDAO.query)(_.id)

    def * = (id, typeId, name) <> (GameRow.tupled, GameRow.unapply)
  }

  implicit class OptionsExt[C[_]](q: Query[Scheme, GameRow, C]) {

    def getByName(name: String) = q.filter(_.name === name)

    def withType = q.joinLeft(GameTypesDAO.query).on(_.typeId === _.id)
  }

}
