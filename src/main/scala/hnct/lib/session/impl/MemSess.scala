package hnct.lib.session.impl

import hnct.lib.session.apiv2.Session
import hnct.lib.session.apiv2.SessionValue
import hnct.lib.session.apiv2.SessionCode

class MemSess extends Session {
  
  def readValue[A](key: String): Either[SessionCode, A] = {
    data.get(key) match {
      case None => Left(SessionCode.NOT_FOUND)
      case Some(sv) =>
        if (isExpired) Left(SessionCode.SESSION_EXPIRED)
        else {
          renew
          sv.asInstanceOf[SessionValue[A]].readValue match {
            case None =>
              Left(SessionCode.VALUE_EXPIRED)
            case Some(value) =>
              Right(value)
          }
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