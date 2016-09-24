//package hnct.lib.session.redis
//
//import hnct.lib.session.api.SessionAccessor
//import hnct.lib.session.api.AccessorDescriptor
//import hnct.lib.session.api.SessionValue
//
//class RedisSessionAccessor(val configuration : AccessorDescriptor) extends SessionAccessor {
//
//	def read[A](key: String): Option[SessionValue[A]] = {
//		???
//	}
//
//	def renew(key: String): Boolean = {
//		???
//	}
//
//	def write[A](key: String, value: SessionValue[A]): SessionAccessor = {
//		???
//	}
//
//	def delete(key: String): Boolean = {
//		???
//	}
//
//  def config: AccessorDescriptor = {
//	  ???
//	}
//
//  def expiration(key: String): Long = {
//	  ???
//	}
//
//  def newExpireTime(key: String, ttl: Long): Boolean = {
//	  ???
//	}
//  
//}
//
//object RedisSessionAccessor {
//	
//	def apply(spec : AccessorDescriptor) = new RedisSessionAccessor(spec)
//	
//}