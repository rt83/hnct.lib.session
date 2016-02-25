package hnct.lib.session

import hnct.lib.session.impl.MemSess
import hnct.lib.session.apiv2.SessionValueCode

object Test {
  
  case class User(name: String)
  
  case class Client(name: String)
  
	def main(args : Array[String]) {
	  val ms = MemSess(2)
	  ms.writeValue("a", User("a"))
	  ms.writeValue("b", Client("b"))
	  val userName = ms.readValue[User]("a") match {
	    case (SessionValueCode.NOT_FOUND, None) => "Not found"
	    case (SessionValueCode.EXPIRED, None) => "Expired"
	    case (_, Some(name)) => name
	  }
	  println(userName)
	  println(ms.ttl)
	}
}