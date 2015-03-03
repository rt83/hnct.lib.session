package hnct.lib.session

import hnct.lib.config._

import hnct.lib.session.redis._
import hnct.lib.session.api._

object TestConfig {

	def main(args : Array[String]) {
		
		val factoryConfig = SessionFactoryConfig("defaultName", List(
				SessionUnit("c1", RedisSessionConfig(List(RedisServer("localhost", 1983)))),
				SessionUnit("c2", RedisSessionConfig(List(RedisServer("yahoo", 1984), RedisServer("yahoo", 1982)))),
				SessionUnit("c3", RedisSessionConfig(List(RedisServer("google", 1984))))
			)
		)
		
		Configuration.write("example/session.conf", factoryConfig)
		/*val conf1 = Configuration.read("example/session.conf", classOf[SessionFactoryConfig], ConfigurationFormat.JSON)
		
		val unit = conf1.get.unit("c1")
		println(unit.get.config.asInstanceOf[RedisSessionConfig].autoRenewOnAccess)*/
	}
	
}