package controllers;

import play.api._
import mvc._

object Application extends Controller {
  def main = Action {
    Ok(views.html.main())
  }
}
