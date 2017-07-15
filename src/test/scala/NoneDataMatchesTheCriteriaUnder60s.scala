/**
  * Created by m.friaa on 12/07/17.
  */

import org.scalatest.{FlatSpec,  Matchers}

import scala.io.Source
import Utils._

class NoneDataMatchesTheCriteriaUnder60s extends FlatSpec with  Matchers {

    "All outuput rows " should "  have  N=1" in {
      val inputData = Source.fromFile("src/test/resources/NoneDataMatchesTheCriteriaUnder60s/data_scala.input").getLines.map(mapLine)

      val outputCorrectData = Source.fromFile("src/test/resources/NoneDataMatchesTheCriteriaUnder60s/data_scala.output").getLines.map(mapLineOutput).toList

      val outputResult = inputData.map(row => buildResult(row)).toList

      outputResult.toSeq should contain allElementsOf outputCorrectData
    }



}

