package slick.sample.querys.infrastucture.persistence

import slick.sample.querys.infrastucture.persistence.GameTypesDAO.GameTypeRow
import slick.sample.querys.infrastucture.persistence.GamesDAO.GameRow
import slick.sample.querys.model.Game


object Converter {

  implicit class toModelConverter(source: Seq[(GameRow, Option[GameTypeRow])]) {
    def toModel: Seq[Game] = source.map(gameMatch)
  }

  private [Converter] def gameMatch(payload: (GameRow, Option[GameTypeRow])) = payload match {
    case (game: GameRow, Some(gameType: GameTypeRow)) => Game(game.name, gameType.name)
    case (game: GameRow, _) => Game(game.name, "undefined")
    case _ => throw new Exception("converter exception")
  }
}
