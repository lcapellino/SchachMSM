
package view

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import model.ChessBoard
import util.JsonUtil


case class WebServer() {

  object TestException extends Throwable

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatchers.lookup("task-dispatcher")
  println("executionContext: " +executionContext.toString)
  val json = JsonUtil()

  val apiRoutes: Route = path("move") {

          parameters('x.as[Int], 'y.as[Int], 'xNew.as[Int], 'yNew.as[Int]) { (x,y,xNew,yNew) =>
            post {
              entity(as[String]) { param =>
                var board: ChessBoard = json.createBoardFromJSON(param)
                board.move(x,y,xNew,yNew) match {
                  case Some(x: ChessBoard) => {
                    board = x
                  }
                  case None => {
                    println("ungültiger Zug!")
                  }
                }
                val resp: ResponseEntity = HttpEntity(ContentTypes.`application/json`, board.toJson().toString())
                complete(HttpResponse(StatusCodes.OK, entity = resp))
              }
            }
          }
        }

  val bindingFuture = Http().bindAndHandle(apiRoutes, "localhost", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

  /*
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ ⇒ system.terminate()) // and shutdown when done
 */

}