package hnct.lib.session.mem

import hnct.lib.session.api.Session
import hnct.lib.session.api.AccessorDescriptor
import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionConfig
import hnct.lib.session.api.SessionValue
import scala.collection._
import com.google.inject.assistedinject.Assisted
import com.google.inject.Inject
import hnct.lib.session.api.SessionFactory

class MemorySession @Inject() (@Assisted() val config : SessionConfig) extends Session {
	
	var valueMap = mutable.HashMap[String, SessionValue[_]]()
	
	override def accessor(spec: AccessorDescriptor): SessionAccessor = new MemorySessionAccessor(config.asInstanceOf[MemorySessionConfig], spec, valueMap)

	override def destroy(): Unit = {
		valueMap.clear()
	}
	
	override def initialize() : Unit = {
		
	}
	
	// TODO: a regular thread to remove expired session key

}

trait MemSessionFactory extends SessionFactory