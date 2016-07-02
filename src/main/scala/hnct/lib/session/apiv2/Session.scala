package hnct.lib.session.apiv2

/**
 * Session to be used in HNCT projects.
 */
abstract class Session extends TTL {
  
  /**
   * The key-value map that contains session data
   */
  protected val data = scala.collection.mutable.HashMap[String, SessionValue[_]]()
  
  /**
   * Get a session value based on its key.
   * This operation must renew TTL of the session
   */
  def readValue[A](key: String): Either[SessionCode, A]
  
  /**
   * Write a value into session. Value is overriden if exists.
   * Renew the session ater writting.
   */
  def writeValue[A](key: String, value: A, ttl: Long = -1): Unit
  
}

/**
 * TTL manipulation
 */
trait TTL {
  
  /**
   * Time to live (in seconds) of this obbject. 
   */
  protected var _ttl: Long = -1
  
  /**
   * Last time (in ms) this object was read.
   */
  protected var lastRead = System.currentTimeMillis()
  
  /**
   * Check if this object has been expired
   */
  def isExpired = if (_ttl < 0) false else System.currentTimeMillis() > lastRead + _ttl*1000
  
  /**
   * Renew time to live of this object
   */
  def renew = lastRead = System.currentTimeMillis()
  
  /**
   * Get exact remaining time to live (in seconds) of this object
   */
  def ttl: Double = if (_ttl < 0) 0.0 else (lastRead + _ttl*1000 - System.currentTimeMillis()) / 1000
  
}