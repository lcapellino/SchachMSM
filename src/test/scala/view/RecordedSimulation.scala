package de.htwg.se.sudoku.aview


import io.gatling.core.Predef._
import io.gatling.http.Predef._


class RecordedSimulation extends Simulation {
  var board = """{"grid":{"size":8,"player":true,"simulated":false,"check":"None","checkmate":"None","cells":[{"row":0,"col":0,"hasMoved":false,"piece":"♖"},{"row":0,"col":1,"hasMoved":false,"piece":"♘"},{"row":0,"col":2,"hasMoved":false,"piece":"♗"},{"row":0,"col":3,"hasMoved":false,"piece":"♕"},{"row":0,"col":4,"hasMoved":false,"piece":"♔"},{"row":0,"col":5,"hasMoved":false,"piece":"♗"},{"row":0,"col":6,"hasMoved":false,"piece":"♘"},{"row":0,"col":7,"hasMoved":false,"piece":"♖"},{"row":1,"col":0,"hasMoved":false,"piece":"♙"},{"row":1,"col":1,"hasMoved":false,"piece":"♙"},{"row":1,"col":2,"hasMoved":false,"piece":"♙"},{"row":1,"col":3,"hasMoved":false,"piece":"♙"},{"row":1,"col":4,"hasMoved":false,"piece":"♙"},{"row":1,"col":5,"hasMoved":false,"piece":"♙"},{"row":1,"col":6,"hasMoved":false,"piece":"♙"},{"row":1,"col":7,"hasMoved":false,"piece":"♙"},{"row":6,"col":0,"hasMoved":false,"piece":"♟"},{"row":6,"col":1,"hasMoved":false,"piece":"♟"},{"row":6,"col":2,"hasMoved":false,"piece":"♟"},{"row":6,"col":3,"hasMoved":false,"piece":"♟"},{"row":6,"col":4,"hasMoved":false,"piece":"♟"},{"row":6,"col":5,"hasMoved":false,"piece":"♟"},{"row":6,"col":6,"hasMoved":false,"piece":"♟"},{"row":6,"col":7,"hasMoved":false,"piece":"♟"},{"row":7,"col":0,"hasMoved":false,"piece":"♜"},{"row":7,"col":1,"hasMoved":false,"piece":"♞"},{"row":7,"col":2,"hasMoved":false,"piece":"♝"},{"row":7,"col":3,"hasMoved":false,"piece":"♛"},{"row":7,"col":4,"hasMoved":false,"piece":"♚"},{"row":7,"col":5,"hasMoved":false,"piece":"♝"},{"row":7,"col":6,"hasMoved":false,"piece":"♞"},{"row":7,"col":7,"hasMoved":false,"piece":"♜"},{"row":0,"col":0,"hasMoved":false,"piece":"♖"},{"row":0,"col":1,"hasMoved":false,"piece":"♘"},{"row":0,"col":2,"hasMoved":false,"piece":"♗"},{"row":0,"col":3,"hasMoved":false,"piece":"♕"},{"row":0,"col":4,"hasMoved":false,"piece":"♔"},{"row":0,"col":5,"hasMoved":false,"piece":"♗"},{"row":0,"col":6,"hasMoved":false,"piece":"♘"},{"row":0,"col":7,"hasMoved":false,"piece":"♖"},{"row":1,"col":0,"hasMoved":false,"piece":"♙"},{"row":1,"col":1,"hasMoved":false,"piece":"♙"},{"row":1,"col":2,"hasMoved":false,"piece":"♙"},{"row":1,"col":3,"hasMoved":false,"piece":"♙"},{"row":1,"col":4,"hasMoved":false,"piece":"♙"},{"row":1,"col":5,"hasMoved":false,"piece":"♙"},{"row":1,"col":6,"hasMoved":false,"piece":"♙"},{"row":1,"col":7,"hasMoved":false,"piece":"♙"},{"row":6,"col":0,"hasMoved":false,"piece":"♟"},{"row":6,"col":1,"hasMoved":false,"piece":"♟"},{"row":6,"col":2,"hasMoved":false,"piece":"♟"},{"row":6,"col":3,"hasMoved":false,"piece":"♟"},{"row":6,"col":4,"hasMoved":false,"piece":"♟"},{"row":6,"col":5,"hasMoved":false,"piece":"♟"},{"row":6,"col":6,"hasMoved":false,"piece":"♟"},{"row":6,"col":7,"hasMoved":false,"piece":"♟"},{"row":7,"col":0,"hasMoved":false,"piece":"♜"},{"row":7,"col":1,"hasMoved":false,"piece":"♞"},{"row":7,"col":2,"hasMoved":false,"piece":"♝"},{"row":7,"col":3,"hasMoved":false,"piece":"♛"},{"row":7,"col":4,"hasMoved":false,"piece":"♚"},{"row":7,"col":5,"hasMoved":false,"piece":"♝"},{"row":7,"col":6,"hasMoved":false,"piece":"♞"},{"row":7,"col":7,"hasMoved":false,"piece":"♜"}]}}"""
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:36.0) Gecko/20100101 Firefox/36.0")

  val uri1 = "http://localhost:8080"

  val scn = scenario("de.htwg.se.schach.aview.RecordedSimulation")
    .exec(http("request_0")
      .post("/move?x=0&y=1&xNew=0&yNew=3")
      .header("Content-Type", "application/json")
      .body(StringBody(board)).asJson
    )
    .exec(http("request_1")
      .post("/move?x=0&y=1&xNew=0&yNew=3")
      .header("Content-Type", "application/json")
      .body(StringBody(board)).asJson
    )
    .exec(http("request_2")
      .post("/move?x=0&y=1&xNew=0&yNew=3")
      .header("Content-Type", "application/json")
      .body(StringBody(board)).asJson
    )

  setUp(scn.inject(atOnceUsers(400))).protocols(httpProtocol)
}
