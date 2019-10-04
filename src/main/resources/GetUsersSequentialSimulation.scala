/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package computerdatabase

class GetUsersSequentialSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8888")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .userAgentHeader("PostmanRuntime/7.1.1")

  val headers_0 = Map(
    "accept-encoding" -> "gzip, deflate",
    "cache-control" -> "no-cache",
    "Content-Type" -> "application/json")

  val scn = scenario("GetUsers Sequential Simulation")
    .exec(http("request_0")
      .get("/users/2505")
      .headers(headers_0))

 setUp(scn.inject(constantUsersPerSec(20) during (60 seconds))).protocols(httpProtocol)
}
