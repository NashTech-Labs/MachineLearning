package com.example

import cc.spray._
import Sentiment._
import cc.spray.typeconversion.SprayJsonSupport._
import com.example.DataStructures.Text
import com.example.DataStructures.TextJsonProtocol._
import cc.spray.json._
import cc.spray.http._

/*
curl -XPUT http://0.0.0.0:8080/json -d '{"text": "123"}'
ok Text(123)
curl -GET http://0.0.0.0:8080/fromjson --data-urlencode 'text={"text": "123"}'


parameter("text") {
text =>
val j = text.asJson.convertTo[Text]


 */

trait SentimentService extends Directives {

  val sentimentService = {
    path("classify") {
      get {
        parameter("text") {
          text =>
            print("*** text: " + text)
            val classification = classify(text)
            _.complete("Text:\n" + text + "\n\nBest category: " + classification.bestCategory() + "\n\nClassification:\n" + classification)
        }
      }
    } ~
    path("positive") {
      post {
        content(as[MultipartFormData]) { formData => ctx =>
          val t = new String(formData.parts("upload").content.get.buffer)
          ctx.complete("\n#####\n" + t + "\n#####\n")
        }
      }
    } ~
    path("negative") {
      post {
        formFields('upload.as[String], 'status.as[String]) { (formData: String, status: String) =>
          _.complete("\n#####\n" + formData + "\n#####\n")
        }
      }
    }
  }
}


/*
trait SentimentService extends Directives {

  val sentimentService = {
    path("classify") {
      get {
        parameter("text") {
          text =>
            print("*** text: " + text)
            val classification = classify(text)
            _.complete("Text:\n" + text + "\n\nBest category: " + classification.bestCategory() + "\n\nClassification:\n" + classification)
        }
      }
    } ~
    path("positive") {
      post {
        content(as[MultipartFormData]) { formData => ctx =>
          val t = new String(formData.parts("upload").content.get.buffer)
          ctx.complete("\n#####\n" + t + "\n#####\n")
        }
      }
    } ~
    path("negative") {
      post {
        formFields('upload.as[String], 'status.as[String]) { (formData: String, b: String) =>
          val t = formData
          _.complete("\n#####\n" + b + "\n#####\n")
        }
      }
    }
  }
}
 */

