package hnct.lib.session.api

import hnct.lib.config.Configuration
import hnct.lib.config.ConfigurationFormat
import scala.collection._
import com.google.inject.Inject
import java.util.Map
import scala.collection.JavaConverters._

class SessionContainer @Inject() (
		val config : SessionContainerConfig,
		val factories : Map[Class[_ <: Session], SessionFactory]	// this is provided by the set binder created in each implementation module
	) {
	
	private var sMap = mutable.HashMap[String, Session]()
	
	{	// declare a new block so that _f is a local variable and not a member
		val _f = factories.asScala
		// build the session from the configuration
		config.units.foreach { unit =>
			sMap += (unit.name -> _f.get(unit.config.sessionClass) 
										.map(_.create(unit.config))
										.getOrElse(throw new RuntimeException(s"Unable to create Session for session unit $unit.name"))
			)
		}
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