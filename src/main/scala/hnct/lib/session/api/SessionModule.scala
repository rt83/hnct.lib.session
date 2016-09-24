package hnct.lib.session.api

import com.google.inject.AbstractModule
import com.google.inject.Singleton
import hnct.lib.config.Configuration
import hnct.lib.config.ConfigurationFormat

class SessionModule(val config : SessionContainerConfig) extends AbstractModule {
	
	def this() = this(
		Configuration.read(Some(SessionModule.configFileName), Some(SessionModule.systemPropName), 
				classOf[SessionContainerConfig], ConfigurationFormat.JSON
		).
		// throw exception when cannot read the configuration file successfully
		getOrElse(throw new RuntimeException("Could not load the session configuration file"))
	)
	
	def configure() = {
		bind(classOf[SessionContainer]).in(classOf[Singleton])
		bind(classOf[SessionContainerConfig]).toInstance(config)
		
		// install all listed implementation module
		config.implementations.foreach( m => install(m.newInstance()) )
	}
	
}

object SessionModule {
	
	val configFileName = "session.json"
	val systemPropName = "sessionConfigFile"
	
}