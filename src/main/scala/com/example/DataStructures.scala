package com.example

import cc.spray.json.DefaultJsonProtocol


object DataStructures {

  case class Text(text: String)
  object TextJsonProtocol extends DefaultJsonProtocol {
    implicit val textFormat = jsonFormat1(Text)
  }


}
