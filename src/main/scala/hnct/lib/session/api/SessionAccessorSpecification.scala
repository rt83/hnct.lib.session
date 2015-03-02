package hnct.lib.session.api

/**
 * A specification tells an accessor the necessary information of how to
 * to access the session. Basic specification includes namespace of the session
 * and the session name. Namespace is used to scope multiple sessions of the same
 * purpose. For example, all user session might go under "user" namespace
 * whereas sessions for services might go under "service"
 * 
 * An example for redis based accessor specification is:
 *  - namespace = userData
 *  - sessionname = userA
 * This accessor specification tell the accessor to access data with keys prefix by userData:userA. An example
 * key will be userData:userA:lastLogin
 **/
 class SessionAccessorSpecification(val namespace : String, val sessionName : String) {
	
}

/**
 * Allow convenient creation of SessionAccessorSpecification without new operator
 */
object SessionAccessorSpecification {
	
	def apply(namespace : String, sessionName : String) = new SessionAccessorSpecification(namespace, sessionName)
	
}