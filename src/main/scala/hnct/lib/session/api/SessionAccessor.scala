package hnct.lib.session.api

/**
 * SessionAccessor provide access method to a particular session
 */
trait SessionAccessor {
	
	/**
	 * Return the accessor config of this accessor
	 */
	def config : SessionAccessorConfig
	
	/**
	 * Read the value corresponding to the key
	 * @return the SessionValue object, containing the value of the specified key and the time until this
	 * key will expire
	 */
	def read[A](key : String) : Option[SessionValue[A]]
	
	/**
	 * Get the amount of time left till a key expire
	 */
	def expiration(key : String) : Long
	
	/**
	 * Write a value with unlimited expiration to the session
	 * 
	 * When using the write method, the session value object can be created
	 * using implicit conversion defined in the SessionValue object
	 * 
	 * If we use write(key, value) the value object will be converted to SessionValue with unlimited time to live
	 * 
	 * If we use write (key, (value, 1234)) the tuple (value, 1234) will be converted to SessionValue with ttl = 1234
	 * 
	 * @return this SessionAccessor
	 */
	def write[A](key : String, value : SessionValue[A]) : SessionAccessor
	
	/**
	 * Renew the ttl of a key. The ttl value previously set for this key will be used
	 * 
	 * E.g a key was written with ttl = 10s and it is now 7s since the writing when we call
	 * renew for this key, the key will expire in another 10s from the moment of calling renew
	 * @return true if the renewal is successfully set
	 */
	def renew(key : String) : Boolean
	
	/**
	 * Set the new ttl value for a key, and renew it
	 * @return true if successful, otherwise false
	 */
	def newExpireTime(key : String, ttl : Long) : Boolean
	
	/**
	 * delete a particular key
	 * @return true if successful otherwise false
	 */
	def delete(key : String) : Boolean
	
}