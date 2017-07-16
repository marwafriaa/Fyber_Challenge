

import scala.collection.mutable.ListBuffer


/**
  * Created by m.friaa on 12/07/17.
  */
object Utils {
  //Period limit
  val T = 60
  //Previous rows List
  var previousRows = new ListBuffer[(Long, Double)]()
  // Mapping data into (timestemp,ratio) as (key,value)
  def mapLine(line: String) = {
    val data_input = line.split("\t")
    (data_input(0).toLong, data_input(1).toDouble)
  }


  // Return the final result map
  def mapLineOutput(line: String) = {
    val data_input = line.split("\t")
    (data_input(0).toLong, data_input(1).toDouble,data_input(2).toInt,data_input(3).toDouble,data_input(4).toDouble,data_input(5).toDouble)
  }


  // Print Results
  def printDataWindow(result: List[(Long, Double, Int, Double, Double, Double)]): Unit = {

    println(
      """T           V       N    RS      MinV      MaxV
        |------------------------------------------------""".stripMargin)
    result.foreach(row => println(row._1 + " " + "%.5f".format(row._2) +
      " " + row._3 + " " + row._4 +
      " " + "%.5f".format(row._5) + " " + "%.5f".format(row._6)))

  }


  // Build results by filtring previous rows by criteria
  def buildResult(row: (Long, Double)) = {
    // Filter previous rows to save only the first rows matching the criteria (<60s)
    previousRows = previousRows.takeWhile(r => (row._1 - r._1).toInt <= T)
    // Add current row at the biggining of the List
    // List will be sorted by Timestamp DESC to avoid Takewhile making Full Scan
    previousRows.prepend(row)
    //Initialise the rolling sum of measurements in the window to 0
    var RS = 0D
    //Initialize the number of measurements in the window
    var N = 0
    //Initialise the  measurement of price ratio at time T Max value
    var MaxV = 0D
    //Initialise the  measurement of price ratio at time T Min value
    var MinV = Double.MaxValue

    for (pr <- previousRows) {
      // Get the a rolling sum of measurements
      RS += pr._2
      // Get the max  measurement of price ratio at time T
      if (MaxV<pr._2)  MaxV =  pr._2
      // Get the max and min measurement of price ratio at time T
      if (MinV>pr._2)  MinV =  pr._2
    }
    //Set the number of measurements in the window to the number of previous rows matching the criteria
    N = previousRows.length
    //Change Double Format
    RS = ((RS * 100000).round /100000.toDouble)
    // Return Final Results
    (row._1, row._2,N, RS, MaxV, MinV)

  }


}