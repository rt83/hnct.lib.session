package hnct.lib.session.apiv2

/**
 * Result code when getting a value from a session
 */
class SessionValueCode {
	final case object OK extends SessionValueCode 
	final case object NOT_FOUND extends SessionValueCode
	final case object EXPIRED extends SessionValueCode
	
	override def toString() = {
		(getClass.getName.stripSuffix("$").split('.')).last.split('$').last
	}
}

object SessionValueCode extends SessionValueCode