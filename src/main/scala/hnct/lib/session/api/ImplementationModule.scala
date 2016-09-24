package hnct.lib.session.api

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.multibindings.MapBinder
import com.google.inject.assistedinject.FactoryModuleBuilder

abstract class ImplementationModule extends AbstractModule {
	
	def configure() = {
		// define the map binder that map from the Class of a session implementation to the factory that create it
		val mb = MapBinder.newMapBinder(binder(), new TypeLiteral[Class[_ <: Session]]() {}, new TypeLiteral[SessionFactory]() {})
		
		initialize(mb);
	}
	
	def initialize(mb : MapBinder[Class[_ <: Session], SessionFactory])
	
}