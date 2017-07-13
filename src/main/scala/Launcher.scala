import java.text.DecimalFormat
import java.util.logging.{Level, Logger}

import scala.io.Source
import Utils._


/**
  * Created by mFRIAA on 12/07/17.
  */
object Launcher extends App {


  val LOGGER = Logger.getLogger(this.getClass.toString)

  val formatter = new DecimalFormat("#.###")
  // Verify Parameters number
  if (args.length < 1) {
    LOGGER.log(Level.SEVERE,
      """
        |Wrong number of parameters
        |
        |Usage: Launcher <input-path>
        |
        |    <input-path> path of the data file
        |
        """.stripMargin)
    System.exit(1)
  }
  val filename = args(0)
  //Extract Lines from File
  val raws = Source.fromFile(filename).getLines.toList
  // Map input Data
  val splittedData = raws.map(line => {
    mapLine(line)
  })

  //Build results
  val finalResult = splittedData.map(row => buildResult(row))

 //Print results
  printDataWindow(finalResult)


}


