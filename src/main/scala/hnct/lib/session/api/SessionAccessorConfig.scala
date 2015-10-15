package hnct.lib.session.api

/**
 * A config tells an accessor the necessary information of how to
 * to access the session. Basic config includes namespace of the session
 * and the session name. Namespace is used to scope multiple sessions of the same
 * purpose. For example, all user session might go under "user" namespace
 * whereas sessions for services might go under "service"
 * 
 * An example for redis based accessor config is:
 *  - namespace = userData
 *  - sessionname = userA
 * This accessor config tell the accessor to access data with keys prefix by userData:userA. An example
 * key will be userData:userA:lastLogin
 **/
 class SessionAccessorConfig(val namespace : String, val sessionName : String) {
	
}

/**
 * Allow convenient creation of SessionAccessorConfig without new operator
 */
object SessionAccessorConfig {
	
	def apply(namespace : String, sessionName : String) = new SessionAccessorConfig(namespace, sessionName)
	
}