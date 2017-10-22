package com.mark.tfl.simulation

import com.mark.tfl.scenarios.{GetAllLineIssues, GetAllLineStatuses}
import com.mark.tfl.util.{Environment, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class EndpointLoadSimulations extends Simulation {

  val httpConf: HttpProtocolBuilder = http.baseURL(Environment.baseURL)
    .headers(Headers.commonHeader)

  val basicEndpointCheckScenarios = List(

    GetAllLineStatuses.getLineStatuses.inject(
      atOnceUsers(1),
      rampUsersPerSec(1) to 10 during (20 seconds)
    ),

    GetAllLineIssues.getLineIssues.inject(
      atOnceUsers(1),
      rampUsersPerSec(1) to 10 during (20 seconds)
    )
  )

  setUp(basicEndpointCheckScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environment.maxResponseTime.toInt)
    )
}