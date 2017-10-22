package com.mark.tfl.util

object Environment {
  val baseURL: String = scala.util.Properties.envOrElse("baseURL", "http://localhost:80/")
  val users: String = scala.util.Properties.envOrElse("numberOfUsers", "5")
  val maxResponseTime: String = scala.util.Properties.envOrElse("maxResponseTime", "500") // in milliseconds
}