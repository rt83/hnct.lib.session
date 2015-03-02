package hnct.lib.session.redis

import com.fasterxml.jackson.annotation._
import com.fasterxml.jackson.annotation.JsonTypeInfo._

import hnct.lib.session.api.SessionConfig

@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")
case class RedisSessionConfig(host : String, port : Int) extends SessionConfig