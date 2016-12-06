package slick.sample.querys.infrastucture.persistence


import slick.lifted.{ProvenShape, Tag}
import slick.driver.PostgresDriver.api._

object GameTypesDAO {
  val query = TableQuery[Scheme]

  case class GameTypeRow(id: Int, name: String)

  class Scheme(tag: Tag) extends Table[GameTypeRow](tag, "game_types") {
    def id = column[Int]("id")
    def name = column[String]("type_name")

    def * = (id, name) <> (GameTypeRow.tupled, GameTypeRow.unapply)
  }
}
