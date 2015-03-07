package hnct.lib.session.redis

import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionAccessorSpecification
import hnct.lib.session.api.SessionValue

class RedisSessionAccessor(val accessorSpec : SessionAccessorSpecification) extends SessionAccessor {

	def expiration(key: String): Long = {
		???
	}

	def expire(key: String, ttl: Long): Boolean = {
		???
	}

	def read[A](key: String): Option[SessionValue[A]] = {
		???
	}

	def renew(key: String): Boolean = {
		???
	}

	def write[A](key: String, value: SessionValue[A]): SessionAccessor = {
		???
	}
  
}

object RedisSessionAccessor {
	
	def apply(spec : SessionAccessorSpecification) = new RedisSessionAccessor(spec)
	
}