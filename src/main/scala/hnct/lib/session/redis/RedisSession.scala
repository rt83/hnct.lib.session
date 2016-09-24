//package hnct.lib.session.redis
//
//import hnct.lib.session.api._
//import com.redis._
//import com.google.inject.Inject
//import com.google.inject.assistedinject.Assisted
//
//class RedisSession @Inject() (@Assisted() val config : RedisSessionConfig) extends Session {
//
//	override def destroy(): Unit = {
//	}
//
//	override def initialize(): Unit = {
//		
//		if (config.serverSet.isEmpty) throw new RuntimeException("Server set for redis session is empty. Cannot connect.")
//		
//	}
//
//	def accessor(spec: AccessorDescriptor) = RedisSessionAccessor(spec)
//
//}