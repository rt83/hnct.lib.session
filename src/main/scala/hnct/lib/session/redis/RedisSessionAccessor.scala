package hnct.lib.session.redis

import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionAccessorSpecification
import hnct.lib.session.api.SessionValue

class RedisSessionAccessor(val accessor : SessionAccessorSpecification) extends SessionAccessor {

	def accessorSpec: SessionAccessorSpecification = {
		accessor
	}

	def expiration(key: String): Long = {
		???
	}

	def expire(key: String, ttl: Long): SessionAccessor = {
		???
	}

	def read[A](key: String): SessionValue[A] = {
		???
	}

	def renew(key: String): Boolean = {
		???
	}

	def write[A](key: String, value: SessionValue[A], ttl: Long): SessionAccessor = {
		???
	}

	def write[A](key: String, value: SessionValue[A]): SessionAccessor = {
		???
	}
  
}

object RedisSessionAccessor {
	
	def apply(spec : SessionAccessorSpecification) = new RedisSessionAccessor(spec)
	
}