package hnct.lib.session.mem

import hnct.lib.session.api.SessionConfig
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo._

@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")
class MemorySessionConfig extends SessionConfig {

}