package hnct.lib.session

import com.google.inject.Guice
import hnct.lib.session.api.SessionModule
import hnct.lib.session.api.SessionContainer
import hnct.lib.session.api.AccessorDescriptor
import hnct.lib.session.api.SessionValue
import scala.concurrent.ExecutionContext.Implicits.global

object ExampleModuleInstallation {
	
	def main(args : Array[String]) {
		val injector = Guice.createInjector(new SessionModule());
		
		val container = injector.getInstance(classOf[SessionContainer]);
		val memSes = container.getSession().getOrElse(throw new RuntimeException("There is no session!!"));
		
		val accessor = memSes.accessor(AccessorDescriptor("USERS", "userA"))
		
		accessor.write("isLoggedIn", SessionValue(true, 3000)). // write a value
			flatMap { success =>	
	
				accessor.expiration("isLoggedIn")	// after finish, find out what is the ttl
	
			} map (println(_))						// print out the ttl
		
		Thread.sleep(1000);
		
		accessor.read[Boolean]("isLoggedIn") map { value =>
			println(value)
		}
		
		Thread.sleep(5000);
	}
	
}