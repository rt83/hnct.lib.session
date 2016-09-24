package hnct.lib.session.api

/**
 * Represent a value stored inside session
 * A session value will also contain ttl which tell how much more time till the 
 * key expire
 */
class SessionValue[A](val value : A, var ttl : Long, var created : Long) {
	
	def this(value : A, ttl : Long) = this(value, ttl, System.currentTimeMillis())
	
	/**
	 * Auxiliary constructor for unlimited 
	 */
	def this(value : A) {
		this(value, SessionValue.UNLIMITED)
	}
	
	def isExpired() = ttl > 0 && System.currentTimeMillis() - created > ttl
	
	def renew() = created = System.currentTimeMillis()
	
	override def toString() = value+" - "+ttl
	
}

object SessionValue {
	
	/**
	 * TTL value for unlimited timeout
	 */
	val UNLIMITED = -1
	
	def apply[A](value : A, ttl : Long) = new SessionValue(value, ttl)
	
	def apply[A](value : A) = new SessionValue(value, -1)
	
	
	// implicitly convert from a tuple to a SessionValue so that we can write
	// sessionAccessor.write(key, (X, 1000))
	implicit def fromTuple[A](x : Tuple2[A, Long]) = apply(x._1, x._2)
	
	// implicitly convert from a value of type A to a SessionValue so that we can write
	// sessionAccessor.write(key, value)
	implicit def fromValue[A](x : A) = apply(x)
	
}