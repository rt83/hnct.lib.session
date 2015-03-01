package hnct.lib.session.api

/**
 * A specification tells an accessor the necessary information
 * to access the session. Basic specification includes namespace of the session
 * and the session name. Namespace is used to scope multiple sessions of the same
 * purpose. For example, all user session might go under "user" namespace
 * whereas sessions for services might go under "service"
 */
class SessionAccessorSpecification(val namespace : String, val sessionName : String) {
	
}