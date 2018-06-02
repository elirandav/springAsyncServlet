package default

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RuleTheWorldSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .userAgentHeader("PostmanRuntime/7.1.1")

  val headers_0 = Map(
    "Postman-Token" -> "93057483-5946-4a29-81af-d395d284b093",
    "accept-encoding" -> "gzip, deflate",
    "cache-control" -> "no-cache")

  val uri1 = "http://localhost:8080/ruleTheWorld"

  val scn = scenario("TweetsBlockingSimulation")
    .exec(http("request_0")
      .get("/ruleTheWorld")
      .headers(headers_0))

  setUp(scn.inject(constantUsersPerSec(20) during (30 seconds))).protocols(httpProtocol)
}