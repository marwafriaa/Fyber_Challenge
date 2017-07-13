//import Launcher.previousRows

import scala.collection.mutable.ListBuffer

/**
  * Created by mFRIAA on 12/07/17.
  */
object Utils {
  //Period limit
  val T = 60
  //Previous rows List
  var previousRows = new ListBuffer[(Long, Double)]()
  // Mapping data into (timestemp,ratio) as (key,value)
  def mapLine(line: String) = {
    val couple = line.split("\t")
    (couple(0).toLong, couple(1).toDouble)
  }


  // Return the final result map
    def mapLineOutput(line: String) = {
  val couple = line.split("\t")
  (couple(0).toLong, couple(1).toDouble,couple(2).toInt,couple(3).toDouble,couple(4).toDouble,couple(5).toDouble)
}

  // Print Results
  def printDataWindow(result: List[(Long, Double, Int, Double, Double, Double)]): Unit = {

    println(
      """T           V       N    RS      MinV      MaxV
        |------------------------------------------------""".stripMargin)
    result.foreach(row => println(row._1 + " " + "%.5f".format(row._2) +
      " " + row._3 + " " + "%.5f".format(row._4) +
      " " + "%.5f".format(row._5) + " " + "%.5f".format(row._6)))

  }


  // Build results by filtring previous rows by criteria
  def buildResult(row: (Long, Double)) = {
    // Filter previous rows to save only the one matches the criteria (<60s)
    previousRows = previousRows.filter(r => (row._1 - r._1).toInt <= T)
    // Add current row
    previousRows.append(row)
    //Initialise the rolling sum of measurements in the window to 0
    var RS = 0D
    //Initialise the  measurement of price ratio at time T Max value
    var MaxV = 0D
    //Initialise the  measurement of price ratio at time T Min value
    var MinV = Double.MaxValue
    // Get the max and min measurement of price ratio at time T
    for (pr <- previousRows) {
      RS += pr._2
      if (MaxV<pr._2)  MaxV =  pr._2
      if (MinV>pr._2)  MinV =  pr._2
    }
    (row._1, row._2, previousRows.length, RS, MaxV, MinV)

  }
}
