/**
  * Created by mFRIAA on 12/07/17.
  */

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source
import Utils._

class TestUseCases extends FlatSpec with  Matchers{

  "All outuput row " should "  have all  N=1" in {
    val inputData = Source.fromFile("src/test/resources/data_scala.input").getLines.map(mapLine)

    val outputCorrectData = Source.fromFile( "src/test/resources/data_scala.output" ).getLines.map(mapLineOutput).toList

    val outputResult = inputData.map(row => buildResult(row)).toList

     outputResult.toSeq should contain allElementsOf (outputCorrectData)
  }

}

