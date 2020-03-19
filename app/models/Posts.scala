package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import Actions.actionReads

case class Posts(
                  message: Option[String],
                  createdTime: String,
                  fullPicture: Option[String],
                  actions: Seq[Actions],
                  like: Likes,
                  share: Option[Int],
                  comment: Comments,
                  id: String
               )

object Posts {
  implicit val readSeqActions: Reads[Seq[Actions]] = Reads.seq(actionReads)
  implicit val postsReads: Reads[Posts] = (
    (JsPath \ "message").readNullable[String] and
      (JsPath \ "created_time").read[String] and
      (JsPath \ "full_picture").readNullable[String] and
      (JsPath \ "actions").read[Seq[Actions]] and
      (JsPath \ "likes").read[Likes] and
      (JsPath \ "shares" \ "count").readNullable[Int] and
      (JsPath \ "comments").read[Comments] and
      (JsPath \ "id").read[String]
    )(Posts.apply _)

}

