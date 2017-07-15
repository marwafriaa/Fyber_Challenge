import Utils.{buildResult, mapLine, mapLineOutput}
import org.scalatest.{FlatSpec,  Matchers}

import scala.io.Source

/**
  * Created by m.friaa on 14/07/2017.
  */
class OneDataMatchesTheCriteriaHigherThan60s  extends FlatSpec  with  Matchers {

  "Last outuput row " should "  decrease to N=2" in {
       val inputData = Source.fromFile("src/test/resources/OneDataMatchesTheCriteriaHigherThan60s/data_scala.input").getLines.map(mapLine)

    val outputCorrectData = Source.fromFile("src/test/resources/OneDataMatchesTheCriteriaHigherThan60s/data_scala.output").getLines.map(mapLineOutput).toList

    val outputResult = inputData.map(row => buildResult(row)).toList

    outputResult.toSeq should contain allElementsOf outputCorrectData
  }


}
