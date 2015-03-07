package hnct.lib.session.api

import com.fasterxml.jackson.annotation._
import com.fasterxml.jackson.annotation.JsonTypeInfo._

/**
 * Represent a configuration of a Session.
 * @field timeout represent the overall timeout configure for the session. When a field is written to the session and there
 * is no timeout configure, this value will be use
 * @field autoRenewOnAccess whether or not a field is renew when it is read
 */
@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")	// to include the config type in the json
class SessionConfig(var timeout : Int, var autoRenewOnAccess : Boolean, var sessionClass : String) {
	
	def this(sessionClass : String) = this(-1, false, sessionClass)
	
}