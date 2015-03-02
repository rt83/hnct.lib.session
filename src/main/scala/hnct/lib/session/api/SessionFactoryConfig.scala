package hnct.lib.session.api

case class SessionUnit(var name : String, var config : SessionConfig)

case class SessionFactoryConfig(var defaultUnit : String, var units : List[SessionUnit]) {
	
	def unit(unitName : String) = units.find { _.name.equals(unitName) }
	
}