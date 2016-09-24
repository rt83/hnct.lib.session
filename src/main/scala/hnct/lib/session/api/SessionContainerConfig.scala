package hnct.lib.session.api

import com.google.inject.Module

case class SessionUnit(var name : String, var config : SessionConfig)

case class SessionContainerConfig(
		var defaultUnit : String, 
		var units : List[SessionUnit],
		var implementations : List[Class[_ <: Module]]) {	// this is the list of all module represent the implementations Session trait, e.g. the MemorySessionModule
	
	def unit(unitName : String) = units.find { _.name.equals(unitName) }
	
}