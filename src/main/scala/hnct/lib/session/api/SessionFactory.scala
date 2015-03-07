package hnct.lib.session.api

import hnct.lib.config.Configuration
import hnct.lib.config.ConfigurationFormat
import scala.collection._

object SessionFactory {
	
	private val configFileName = "session.json"
	private val systemPropName = "sessionConfigFile"
	
	var sMap = mutable.HashMap[String, Session]()
	
	// read the configuration
	val config = Configuration.read(
				Some(configFileName), 
				Some(systemPropName), 
				classOf[SessionFactoryConfig], 
				ConfigurationFormat.JSON
	).
	// throw exception when cannot read the configuration file successfully
	getOrElse(throw new RuntimeException("Could not load the session configuration file"))
	
	// build the session from the configuration
	config.units.foreach { unit =>
		val session = Class.
			forName(unit.config.sessionClass, 
					true, 
					Thread.currentThread().getContextClassLoader
			).
			newInstance().
			asInstanceOf[Session]
		
		session.configure(unit.config)
		
		sMap + (unit.name -> session)
	}
	
	// validation to see whether the default session unit exist
	if (sMap.get(config.defaultUnit) == None) {
		// print out some warning
	}
	
	def getSession() = {
		sMap.get(config.defaultUnit)
	}
	
	def getSession(unitName : String) = {
		sMap.get(unitName)
	}
	
}