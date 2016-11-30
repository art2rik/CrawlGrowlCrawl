package crawlgrowlcow.master.server

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._

/**
  * Created by krikun on 30.11.16.
  */
object MasterServer {
  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("master")

  // create and start our service actor
  val service = system.actorOf(Props[TaskServerActor], "task-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 9000)

}