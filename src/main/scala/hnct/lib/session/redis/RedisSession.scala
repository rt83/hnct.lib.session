package hnct.lib.session.redis

import hnct.lib.session.api._

class RedisSession extends Session {
	
	type SpecType = SessionAccessorSpecification
	
	var config : RedisSessionConfig = RedisSessionConfig("", -1)	// initialize the config

	def configure(c: SessionConfig): Unit = {
		c match {
			case x : RedisSessionConfig => config = x
			case _ => throw new RuntimeException("Configuration type not supported! Only configuration of type RedisSessionConfig is accepted!")
		}
	}

	def destroy(): Unit = {
		???
	}

	def initialize(): Unit = {
		???
	}

	def accessor(spec: SessionAccessorSpecification) = RedisSessionAccessor(spec)

}