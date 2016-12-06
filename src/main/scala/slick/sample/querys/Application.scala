package slick.sample.querys

import slick.driver.PostgresDriver.api.Database
import slick.sample.querys.infrastucture.persistence.GameRepository
import scala.concurrent.ExecutionContext.Implicits.global


object Application extends App {

  println("START APPLICATION")

  val dataBase = Database.forConfig("db")
  val repository = new GameRepository(dataBase)

  println("TRY GET DATA")
  repository.getGame("Doom").map(println) // Vector(Game(Doom,ACTION))
  Thread sleep 5000
}
