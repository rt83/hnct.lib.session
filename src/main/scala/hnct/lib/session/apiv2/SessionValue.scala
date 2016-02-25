package hnct.lib.session.apiv2

/**
 * This represents a value stored in an HNCT session.
 * TTL = -1 means live forever.
 */
class SessionValue[A](private val value: A) extends TTL {
  
  /**
   * Read and renew TTL of this session value
   */
  def readValue: Option[A] = {
    if (isExpired) None
    else {
      renew
      Some(value)
    }
  }
  
}

object SessionValue {
  def apply[A](value: A, ttl: Long = -1) = {
    val sv = new SessionValue(value)
    sv._ttl = ttl
    sv
  }
}