package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Comments(
                     order: String,
                     totalCount: Int,
                     canComment: Boolean
                   )

object Comments {
  implicit val commentsRead: Reads[Comments] = (
    (JsPath \ "summary" \ "order").read[String] and
      (JsPath \ "summary" \ "total_count").read[Int] and
      (JsPath \ "summary" \ "can_comment").read[Boolean]
  )(Comments.apply _)

}
