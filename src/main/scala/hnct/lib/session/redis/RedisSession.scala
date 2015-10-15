package hnct.lib.session.redis

import hnct.lib.session.api._
import com.redis._

class RedisSession extends Session {
	
	type ConfigType = RedisSessionConfig
	
	override var _config = new RedisSessionConfig()	// initialize the config
	var clientPools : RedisClientPool = null
	
	override def configure(c: SessionConfig): Unit = {
		c match {
			case x : RedisSessionConfig => super.configure(_config)
			case _ => throw new RuntimeException("Configuration type not supported! Only configuration of type RedisSessionConfig is accepted!")
		}
	}

	override def destroy(): Unit = {
		clientPools.close
	}

	override def initialize(): Unit = {
		
		if (_config.serverSet.isEmpty) return
		
		val firstServer = _config.serverSet(0)
		
		clientPools = new RedisClientPool(firstServer.host, firstServer.port)
		
	}

	def accessor(spec: SessionAccessorConfig) = RedisSessionAccessor(spec)

}