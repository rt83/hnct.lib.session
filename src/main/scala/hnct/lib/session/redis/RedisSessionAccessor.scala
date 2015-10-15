package hnct.lib.session.redis

import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionAccessorConfig
import hnct.lib.session.api.SessionValue

class RedisSessionAccessor(val configuration : SessionAccessorConfig) extends SessionAccessor {

	def read[A](key: String): Option[SessionValue[A]] = {
		???
	}

	def renew(key: String): Boolean = {
		???
	}

	def write[A](key: String, value: SessionValue[A]): SessionAccessor = {
		???
	}

	def delete(key: String): Boolean = {
		???
	}

  def config: SessionAccessorConfig = {
	  ???
	}

  def expiration(key: String): Long = {
	  ???
	}

  def newExpireTime(key: String, ttl: Long): Boolean = {
	  ???
	}
  
}

object RedisSessionAccessor {
	
	def apply(spec : SessionAccessorConfig) = new RedisSessionAccessor(spec)
	
}