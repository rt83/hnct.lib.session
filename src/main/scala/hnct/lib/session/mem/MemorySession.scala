package hnct.lib.session.mem

import hnct.lib.session.api.Session
import hnct.lib.session.api.SessionAccessorConfig
import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionConfig
import hnct.lib.session.api.SessionValue

import scala.collection._

class MemorySession extends Session {
	
	type ConfigType = MemorySessionConfig
	
	var valueMap = mutable.HashMap[String, SessionValue[_]]()
	
	override var _config = new MemorySessionConfig()	// initialize the _config
	
	def accessor(spec: SessionAccessorConfig): SessionAccessor = new MemorySessionAccessor(spec, valueMap)

	override def configure(config: SessionConfig): Unit = {
		
		config match {
			case c : MemorySessionConfig => super.configure(c)
			case _ => throw new RuntimeException("Cannot configure memory session config with object of type "+config.getClass.getName)
		}

	}

	override def destroy(): Unit = {
		valueMap.clear()
	}
	
	// TODO: a regular thread to remove expired session key

}