package hnct.lib.session.api

/**
 * A session is a place to store user data in the form of key - value
 * It provide mechanism to control the timeout of a key
 * It allows serialization of object into the session
 *  
 * A session provide method to retrieve SessionAccessor
 * It also do necessary configuration, initialize necessary states,
 * 
 * e.g. initialize the connection pool to redis server, check whether a session file
 * is in the file system, etc...
 * 
 * IMPORTANT: all Session subtypes must provide a default constructor so that it can be
 * created from the class name
 * 
 */
trait Session {
	
	type SpecType <: SessionAccessorSpecification
	
	/**
	 * Configure the Session
	 */
	def configure(config : SessionConfig) : Unit
	
	/**
	 * Allow the user to initialize the session
	 */
	def initialize() : Unit
	
	/**
	 * Allow the user to destroy the session
	 * When destroying, all data should be removed from the storage
	 */
	def destroy() : Unit
	
	/**
	 * Return an accessor to access the session
	 */
	def accessor(spec : SpecType) : SessionAccessor
	
}