package hnct.lib.session.mem

import hnct.lib.session.api.SessionAccessor

import hnct.lib.session.api.SessionValue
import hnct.lib.session.api.SessionAccessorSpecification

import scala.collection._

/**
 * Provide access to an in memory session
 * By declaring val accessorSpec, the compiler automatically
 * create a getter of name accessorSpec hence override the 
 * accessorSpec method from SessionAccessor, because of this
 * we don't have to declare def accessorSpec explicitly
 */
class MemorySessionAccessor(val accessorSpec : SessionAccessorSpecification, valueMap : mutable.HashMap[String, SessionValue[_]]) extends SessionAccessor {

	val _k = "%s:%s:%s"	// key format
	
	
	/**
	 * Get the time till expiration of this key, return -1 if key not found
	 * Return negative if the key is found and already expired
	 */
	def expiration(key: String): Long = { valueMap.
		get(_rK(key)).
		filterNot(_.isExpired).
		fold(-1.toLong)(value => value.ttl - System.currentTimeMillis() + value.created)
	}
	
	/**
	 * change the time to live of the key
	 * and at the same time, renew it
	 */
	def expire(key: String, ttl: Long): Boolean = { valueMap.
		get(_rK(key)).
		filterNot(_.isExpired).
		fold(false)({ value =>
			value.renew
			value.ttl = ttl
			true
		})
	}

	def read[A](key: String): Option[SessionValue[A]] = { 
		valueMap.
		get(_rK(key)).					// get the option out of the map
		filterNot(_.isExpired).		// check if the session value is expired or not
		map(
			_.asInstanceOf[A]
		)		// if not expire, convert it to type A and return
	}

	/**
	 * Renew the key, change the created time to current time
	 */
	def renew(key: String): Boolean = { valueMap.
		get(_rK(key)).					// get the option out of the map
		filterNot(_.isExpired).
		fold(false)({ value =>
			value.renew
			true
		})
	}
	
	/**
	 * Transform the short key to the formatted key
	 */
	private[this] def _rK(key : String) : String = _k.format(accessorSpec.namespace, accessorSpec.sessionName, key)

	def write[A](key: String, value: SessionValue[A]): SessionAccessor = {
		valueMap += (_rK(key) -> value)

		this	// return this so we can chain the call and write multiple values in one line
	}
}