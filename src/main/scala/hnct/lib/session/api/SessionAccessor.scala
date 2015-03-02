package hnct.lib.session.api

/**
 * SessionAccessor provide access method to a particular session
 */
trait SessionAccessor {
	
	/**
	 * Return the accessor specification of this accessor
	 */
	def accessorSpec : SessionAccessorSpecification
	
	/**
	 * Read the value corresponding to the key
	 * @return the SessionValue object, containing the value of the specified key and the time until this
	 * key will expire
	 */
	def read[A](key : String) : SessionValue[A]
	
	/**
	 * Get the amount of time left till a key expire
	 */
	def expiration(key : String) : Long
	
	/**
	 * Write a value with unlimited expiration to the session
	 * @return this SessionAccessor
	 */
	def write[A](key : String, value : SessionValue[A]) : SessionAccessor
	
	/**
	 * Write a value with a particular ttl to the session
	 * @return this SessionAccessor
	 */
	def write[A](key : String, value : SessionValue[A], ttl : Long) : SessionAccessor
	
	/**
	 * Renew the ttl of a key. The ttl value previously set for this key will be used
	 * 
	 * E.g a key was written with ttl = 10s and it is now 7s since the writing when we call
	 * renew for this key, the key will expire in another 10s from the moment of calling renew
	 * @return true if the renewal is successfully set
	 */
	def renew(key : String) : Boolean
	
	/**
	 * Set the new ttl value for a key
	 * @return this SessionAccessor
	 */
	def expire(key : String, ttl : Long) : SessionAccessor
	
}