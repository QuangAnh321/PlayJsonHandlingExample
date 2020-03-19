package models
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class OneFile(
                    message: Option[String],
                    fullPicture: Option[String],
                    createdTime: String,
                    link: String,
                    likeCount: Option[Int],
                    shareCount: Option[Int],
                    commentCount: Option[Int],
                    id: String
                  )

object OneFile {
  implicit val pagePostAPIReads: Reads[OneFile] =
    (
      (__ \ "message").readNullable[String] ~
        (__ \ "full_picture").readNullable[String] ~
        (__ \ "created_time").read[String] ~
        (__ \ "actions").read[JsArray].map(_.value.head \ "link").map(_.as[String]) ~
        (__ \ "likes").readNullable[JsValue].map(_.map(_ \ "summary" \ "total_count").map(_.as[Int])) ~
        (__ \ "shares").readNullable[JsValue].map(_.map(_ \ "count").map(_.as[Int])) ~
        (__ \ "comments").readNullable[JsValue].map(_.map(_ \ "summary" \ "total_count").map(_.as[Int])) ~
        (__ \ "id").read[String]
      )(OneFile.apply _)
}
