package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import Posts.postsReads

case class Data(
                posts: Seq[Posts]
               )

object Data {
  implicit val readSeqPosts: Reads[Seq[Posts]] = Reads.seq(postsReads)
  implicit val dataRead: Reads[Data] = (
    (JsPath \ "data").read[Seq[Posts]].map(posts => Data(posts)))
}
