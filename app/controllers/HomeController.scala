package controllers

import com.mohiva.play.silhouette.api.Silhouette
import javax.inject._
import play.api.mvc._
import utils.auth.DefaultEnv

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               silhouette: Silhouette[DefaultEnv]) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def protectedPage = silhouette.SecuredAction { implicit request =>
    Ok(views.html.authorized("You have been authorized", request.identity))
  }

}
