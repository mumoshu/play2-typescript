package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def greet2 = Action {
    Ok(views.html.greet2())
  }

  def issue14 = Action {
    Ok(views.html.issue14())
  }
  
}
