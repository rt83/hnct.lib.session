package hnct.lib.session.mem

import com.google.inject.AbstractModule
import com.google.inject.multibindings.MapBinder
import hnct.lib.session.api.Session
import hnct.lib.session.api.SessionFactory
import com.google.inject.TypeLiteral
import com.google.inject.assistedinject.FactoryModuleBuilder
import hnct.lib.session.api.ImplementationModule

class MemSessionModule extends ImplementationModule {

	def initialize(mb : MapBinder[Class[_ <: Session], SessionFactory]) = {
		
		// install a module that provides an implementation of BasicAccessProcessorFactory
		install(
			new FactoryModuleBuilder().
					implement(classOf[Session], classOf[MemorySession]).
					build(classOf[MemSessionFactory])
		)
		
		// add the binding the the map binder. This will make the mapping Class[MemorySession] -> MemSessionFactory available
		// in the factories map injected into the SessionContainer
		mb.addBinding(classOf[MemorySession]).to(classOf[MemSessionFactory]);
	}
	
}