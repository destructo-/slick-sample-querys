package slick.sample.querys.infrastucture.persistence.DAO

import slick.lifted.Rep
import org.joda.time.DateTime

trait CreationTime {
  def creationTime: Rep[DateTime]
}
