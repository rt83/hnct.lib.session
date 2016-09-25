package hnct.lib.session.api

/**
 * Factory interface to create the async session
 */
trait SessionFactory {
	
	def create(conf : SessionConfig) : Session
	
}

/**
 * The point to initialize a session store. It then provide method to retrieve the accessor as needed
 * 
 * Note that, configuration will be passed to the AsyncSession through injection.
 */
trait Session {

	def initialize()
	
	def destroy()
	
	def accessor(descriptor : AccessorDescriptor) : SessionAccessor
	
}