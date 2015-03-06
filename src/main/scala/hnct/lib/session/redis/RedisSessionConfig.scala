package hnct.lib.session.redis

import com.fasterxml.jackson.annotation._
import com.fasterxml.jackson.annotation.JsonTypeInfo._

import hnct.lib.session.api.SessionConfig

case class RedisServer(var host : String, var port : Int = 6379)

@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")
case class RedisSessionConfig(serverSet : List[RedisServer]) extends SessionConfig(-1, false, classOf[RedisSession].getName())

object RedisSessionConfig {
	def apply() = new RedisSessionConfig(List[RedisServer]())	// add in one more constructor method so we can use RedisSessionConfig()
}