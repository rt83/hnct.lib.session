package hnct.lib.session.mem

import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionValue
import hnct.lib.session.api.AccessorDescriptor
import scala.collection._
import scala.concurrent.Future

/**
 * Provide access to an in memory session
 * By declaring val accessorSpec, the compiler automatically
 * create a getter of name accessorSpec hence override the 
 * accessorSpec method from SessionAccessor, because of this
 * we don't have to declare def accessorSpec explicitly
 */
class MemorySessionAccessor(val sessionConfig : MemorySessionConfig, val adesc : AccessorDescriptor, var valueMap : mutable.HashMap[String, SessionValue[_]]) extends SessionAccessor {

	val _k = "%s:%s:%s"	// key format


	/**
	 * Get the time till expiration of this key, return -1 if key not found
	 * Return negative if the key is found and already expired
		*
		* if there is no ttl set for the value, and session config dictates one
		* use it, otherwise ttl = -1
	 */
	def expiration(key: String): Future[Long] = {
		Future.successful({
			valueMap.
				get(_rK(key)).
				filterNot(
					_.isExpired() || sessionConfig.timeout != -1
				).
				fold(-1.toLong)(value => (if (value.ttl == -1) value.ttl else sessionConfig.timeout) - System.currentTimeMillis() + value.created)
		})
	}

	/**
	 * change the time to live of the key
	 * and at the same time, renew it
		*
		* if there is no ttl set for the value and session config
		* dictates a default timeout, use it
	 */
	def newExpireTime(key: String, ttl: Long): Future[Boolean] = { Future.successful(
			valueMap.
				get(_rK(key)).
				filterNot(_.isExpired).
				fold(false)({ value =>
					value.renew
					if (ttl == -1) value.ttl = sessionConfig.timeout else value.ttl = ttl
					true
				})
			)
	}

	def read[A](key: String): Future[Option[SessionValue[A]]] = {
		Future.successful(
			valueMap.
				get(_rK(key)).					// get the option out of the map
				filterNot(_.isExpired).		// check if the session value is expired or not
				map( v => {
					if (sessionConfig.autoRenewOnAccess) renew(key)
					v.asInstanceOf[SessionValue[A]]
				})		// if not expire, convert it to type A and return
		)
	}

	/**
	 * Renew the key, change the created time to current time
	 */
	def renew(key: String): Future[Boolean] = {
		Future.successful(
				valueMap.
					get(_rK(key)).					// get the option out of the map
					filterNot(_.isExpired).
					fold(false)({ value =>
						value.renew
						true
					})
		)
	}

	/**
	 * Transform the short key to the formatted key
	 */
	private[this] def _rK(key : String) : String = _k.format(adesc.namespace, adesc.sessionName, key)

	def write[A](key: String, value: SessionValue[A]): Future[Boolean] = {

		if (value.ttl == -1) value.ttl = sessionConfig.timeout

		valueMap += (_rK(key) -> value)

		Future.successful(true)
	}

	def delete(key: String): Future[Boolean] = {
		valueMap -= _rK(key)

		Future.successful(true)
	}

  def descriptor: AccessorDescriptor = {
	  adesc
	}
	
}