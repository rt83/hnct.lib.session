package hnct.lib.session

import hnct.lib.session.impl.MemSess
import hnct.lib.session.apiv2.SessionCode

object Test {
  
  case class User(name: String)
  
  case class Client(name: String)
  
	def main(args : Array[String]) {
	  val ms = MemSess(2)
	  ms.writeValue("a", User("a"))
	  ms.writeValue("b", Client("b"))
	  val userName = ms.readValue[User]("a") match {
	    case Left(SessionCode.NOT_FOUND) => "Not found"
	    case Left(SessionCode.SESSION_EXPIRED) => "Expired"
	    case Right(name) => name
	  }
	  println(userName)
	  println(ms.ttl)
	}
}