package hnct.lib.session.apiv2

/**
 * Result code when getting a value from a session
 */
class SessionCode {
	final case object NOT_FOUND extends SessionCode
	final case object SESSION_EXPIRED extends SessionCode
	final case object VALUE_EXPIRED extends SessionCode
	
	override def toString() = {
		(getClass.getName.stripSuffix("$").split('.')).last.split('$').last
	}
}

object SessionCode extends SessionCode