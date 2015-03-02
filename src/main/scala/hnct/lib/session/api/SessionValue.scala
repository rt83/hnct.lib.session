package hnct.lib.session.api

/**
 * Represent a value stored inside session
 * A session value will also contain ttl which tell how much more time till the 
 * key expire
 */
class SessionValue[A](val value : A, val ttl : Long) {
	
	/**
	 * Auxiliary constructor for unlimited 
	 */
	def this(value : A) {
		this(value, SessionValue.UNLIMITED)
	}
	
}

object SessionValue {
	
	/**
	 * TTL value for unlimited timeout
	 */
	val UNLIMITED = -1
	
}