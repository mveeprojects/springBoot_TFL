package com.mark.tfl.performance.simulation

import com.mark.tfl.performance.scenarios.FirstTest
import com.mark.tfl.performance.util.{Environment, Headers}
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf: HttpProtocolBuilder = http.baseURL(Environment.baseURL)
    .headers(Headers.commonHeader)

  val userMicroserviceScenarios = List(
    //PostAndGetUser.postAndGetUser.inject(atOnceUsers(1)),

    FirstTest.getUsers.inject(
      atOnceUsers(1),
      //rampUsers(100) over(1 seconds),
      //constantUsersPerSec(1000) during(15 seconds)
      rampUsersPerSec(1) to 100 during(30 seconds) // 6
      //rampUsersPerSec(10) to 20 during(10 minutes) randomized, // 7
      //splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy(10 seconds), // 8
      //splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy atOnceUsers(30), // 9
      //heavisideUsers(1000) over(20 seconds) // 10
    )
  )

  setUp(userMicroserviceScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environment.maxResponseTime.toInt)
    )

}