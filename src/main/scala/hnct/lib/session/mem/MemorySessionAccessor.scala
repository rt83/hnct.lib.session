package hnct.lib.session.mem

import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionValue
import hnct.lib.session.api.SessionAccessorSpecification

class MemorySessionAccessor(val accessorSpec : SessionAccessorSpecification) extends SessionAccessor {

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