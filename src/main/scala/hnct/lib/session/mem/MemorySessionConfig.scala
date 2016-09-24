package hnct.lib.session.mem

import hnct.lib.session.api.SessionConfig
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo._
import hnct.lib.session.api.Session

@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")
class MemorySessionConfig(sessionClass : Class[_ <: Session]) extends SessionConfig(sessionClass) {
	
	def this() = this(classOf[MemorySession])
	
}