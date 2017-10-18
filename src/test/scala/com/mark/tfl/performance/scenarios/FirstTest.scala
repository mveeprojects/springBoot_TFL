package com.mark.tfl.performance.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object FirstTest {
  val getUsersHttp: HttpRequestBuilder = http("get all users")
    .get("/")
    .check(status is 200)

  val getUsers: ScenarioBuilder = scenario("Get All users")
    .exec(getUsersHttp)

}
