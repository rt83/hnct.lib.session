//package hnct.lib.session.redis
//
//import com.fasterxml.jackson.annotation._
//import com.fasterxml.jackson.annotation.JsonTypeInfo._
//
//import hnct.lib.session.api.SessionConfig
//
//case class RedisServer(val uri : String)
//
//@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")
//class RedisSessionConfig(
//		sessionClass : Class[_ <: RedisSession], 
//		var serverSet : List[RedisServer]) extends SessionConfig(sessionClass) {
//		
//	def this(serverSet : List[RedisServer]) = this(classOf[RedisSession], serverSet)
//	
//	def this() = this(List[RedisServer]())
//	
//}