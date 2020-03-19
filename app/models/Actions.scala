package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Actions(
                  name: String,
                  link: String
                  )

object Actions {
  implicit val actionReads: Reads[Actions] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "link").read[String]
  )(Actions.apply _)
}
