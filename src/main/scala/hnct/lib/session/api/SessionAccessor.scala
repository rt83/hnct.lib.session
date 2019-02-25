package hnct.lib.session.api

import scala.concurrent.Future

trait SessionAccessor {
	
	/**
	 * Return the accessor config of this accessor
	 */
	def descriptor : AccessorDescriptor
	
	/**
	 * Read the value corresponding to the key
	 * @return the SessionValue object, containing the value of the specified key and the time until this
	 * key will expire
	 */
	def read[A](key : String) : Future[Option[SessionValue[A]]]
	
	/**
	 * Get the amount of time left till a key expire
	 */
	def expiration(key : String) : Future[Long]
	
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
	 * This is async
	 * 
	 * @return true / false 
	 */
	def write[A](key : String, value : SessionValue[A]) : Future[Boolean]
	
	/**
	 * Renew the ttl of a key. The ttl value previously set for this key will be used
	 * 
	 * E.g a key was written with ttl = 10s and it is now 7s since the writing when we call
	 * renew for this key, the key will expire in another 10s from the moment of calling renew
	 * @return true if the renewal is successfully set
	 */
	def renew(key : String) : Future[Boolean]
	
	/**
	 * Set the new ttl value for a key, and renew it
	 * @return true if successful, otherwise false
	 */
	def newExpireTime(key : String, ttl : Long) : Future[Boolean]
	
	/**
	 * delete a particular key
	 * @return true if successful otherwise false
	 */
	def delete(key : String) : Future[Boolean]
	
}