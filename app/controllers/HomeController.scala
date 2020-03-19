package controllers

import javax.inject._
import models.{Data, OneFile}
import play.api._
import play.api.libs.json.{JsArray, JsError, Json}
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def readPartialJson = Action(parse.json) { request =>
    val dataResult = request.body.validate[Data]
    dataResult.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      data => {
        println(data)
        Ok("lmao")
      }
    )
  }
  def readJsonWithoutData = Action(parse.json) { request =>
    val dataResult = (request.body \ "data").as[JsArray].value.map(_.as[OneFile])
    println(dataResult)
    Ok("Hello")
  }
}
