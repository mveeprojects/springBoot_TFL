package com.mark.tfl.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object GetAllLineStatuses {

  val getAllLineStatuses: HttpRequestBuilder = http("get all line statuses")
    .get("/")
    .check(status is 200)

  val getLineStatuses: ScenarioBuilder = scenario("Get all line statuses")
    .exec(getAllLineStatuses)
}