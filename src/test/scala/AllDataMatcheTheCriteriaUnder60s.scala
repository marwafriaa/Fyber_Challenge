/**
  * Created by m.friaa on 12/07/17.
  */

import Utils._
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class AllDataMatcheTheCriteriaUnder60s extends FlatSpec with  Matchers {

    "N " should "  be incremental " in {
      val inputData = Source.fromFile("src/test/resources/AllDataMatcheTheCriteriaUnder60s/data_scala.input").getLines.map(mapLine)

      val outputCorrectData = Source.fromFile("src/test/resources/AllDataMatcheTheCriteriaUnder60s/data_scala.output").getLines.map(mapLineOutput).toList

      val outputResult = inputData.map(row => buildResult(row)).toList

      outputResult.toSeq should contain allElementsOf outputCorrectData
    }



}

