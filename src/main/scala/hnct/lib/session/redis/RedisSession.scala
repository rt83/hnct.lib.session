package hnct.lib.session.redis

import hnct.lib.session.api._
import com.redis._

class RedisSession extends Session {
	
	type SpecType = SessionAccessorSpecification
	
	var config : RedisSessionConfig = RedisSessionConfig()	// initialize the config
	var clientPools : RedisClientPool = null
	
	def configure(c: SessionConfig): Unit = {
		c match {
			case x : RedisSessionConfig => config = x
			case _ => throw new RuntimeException("Configuration type not supported! Only configuration of type RedisSessionConfig is accepted!")
		}
	}

	def destroy(): Unit = {
		
	}

	def initialize(): Unit = {
		
		if (config.serverSet.isEmpty) return
		
		val firstServer = config.serverSet(0)
		
		clientPools = new RedisClientPool(firstServer.host, firstServer.port)
		
	}

	def accessor(spec: SessionAccessorSpecification) = RedisSessionAccessor(spec)

}