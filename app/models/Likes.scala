package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Likes(
                  totalCount: Int,
                  canLike: Boolean,
                  hasLiked: Boolean
               )

object Likes {
  implicit val likesRead: Reads[Likes] = (
    (JsPath \ "summary" \ "total_count").read[Int] and
      (JsPath \ "summary" \ "can_like").read[Boolean] and
      (JsPath \ "summary" \ "has_liked").read[Boolean]
  )(Likes.apply _)
}
