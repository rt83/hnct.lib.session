package hnct.lib.session.impl

import hnct.lib.session.apiv2.Session
import hnct.lib.session.apiv2.SessionValue
import hnct.lib.session.apiv2.SessionValueCode

class MemSess extends Session {
  
  def readValue[A](key: String): Tuple2[SessionValueCode, Option[A]] = {
    data.get(key) match {
      case None => (SessionValueCode.NOT_FOUND, None)
      case Some(sv) =>
        if (isExpired) (SessionValueCode.EXPIRED, None)
        else {
          renew
          (SessionValueCode.OK, sv.asInstanceOf[SessionValue[A]].readValue)
        }
    }
  }
  
  def writeValue[A](key: String, value: A, ttl: Long = -1) = {
    val sv = SessionValue[A](value, ttl)
    data.put(key, sv)
    renew
  }
  
}

object MemSess {
  def apply(ttl: Long = -1) = {
    val ms = new MemSess()
    ms._ttl = ttl
    ms
  }
}