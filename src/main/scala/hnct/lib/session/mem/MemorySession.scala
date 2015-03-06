package hnct.lib.session.mem

import hnct.lib.session.api.Session
import hnct.lib.session.api.SessionAccessorSpecification
import hnct.lib.session.api.SessionAccessor
import hnct.lib.session.api.SessionConfig

class MemorySession extends Session {
	type SpecType = SessionAccessorSpecification

	def accessor(spec: SpecType): SessionAccessor = {
		???
	}

	def configure(config: SessionConfig): Unit = {
		???
	}

	def destroy(): Unit = {
		???
	}

	def initialize(): Unit = {
		???
	}

}