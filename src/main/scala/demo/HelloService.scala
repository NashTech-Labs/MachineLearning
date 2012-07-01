package demo

/**
 * Created with IntelliJ IDEA.
 * User: Astar
 * Date: 6/15/12
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */

import cc.spray._

trait HelloService extends Directives {
  val helloService = {
    path("hello") {
      get {
        completeWith {
          <h1>Say hello to Spray</h1>
        }
      }
    }
  }
}

/*object Server extends HelloService
{
  def main(args:Array[String]){
    println("Hello Mayank")
  }
}
            */