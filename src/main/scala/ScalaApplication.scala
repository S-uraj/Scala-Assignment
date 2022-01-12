import models.Movie
import repo.{GetRecords, Queries}

import scala.util.Try

object ScalaApplication extends App{

  val fileName:String = "imdb_movies.csv";
  val moviesList:List[Movie] = GetRecords.readRecords(fileName)

  //output
//  moviesList.foreach(x=>println(x.title + " | "+x.publishedYear+" | "+ x.budget+" | "+x.reviews+" | "+x.country+" | "+x.genre+" | "+x.duration))
//  println("Total Records :"+moviesList.length)

  /** 1.  Titles directed by  given director in the given year range e.g :
          generate titles report for director D.W. Griffith and year range 2010 to 2020
   * */
//  print("Enter the director name :")
// try {
//   val directorName: String = scala.io.StdIn.readLine
//   println("Enter year Range:")
//   val r1: Int = scala.io.StdIn.readLine.toInt
//   val r2: Int = scala.io.StdIn.readLine.toInt
//   val listOfTitles:Try[List[String]] = Queries.getTitleByGivenDirectorAndYearRange(moviesList, directorName, r1, r2)
//   if(listOfTitles.isSuccess)
//     listOfTitles.map(_.foreach(println))
//   else
//     throw new RuntimeException
// }
// catch {
//   case e:NumberFormatException => println("OOPS ! Input Type Mismatch")
//   case r:RuntimeException => println("The Given inputs are invalid for given dataset. Please enter valid details")
//   case _ => println("Something went wrong")
// }

  /*** 2.Generate report of English titles which have user reviews more than given user review
         filter and sort the report with user reviews by descending
   * */
//  println("Enter Number of user review :")
//  try {
//    val givenUserReview: Int = scala.io.StdIn.readLine.toInt
//    val checkFailure = Try{Queries.getTitleByGivenUserReview(moviesList, givenUserReview).foreach(movie => println(movie.title))}
//    if(checkFailure.isFailure)
//      throw new RuntimeException("Data is not present in dataset ,After applying filter")
//  }
//  catch {
//    case e:NumberFormatException => println("Input Type mismatch")
//    case r:RuntimeException => println(r.getMessage)
//    case _ => println("Something went wrong")
//  }

  /** 3. Generate highest budget titles for the given year and country filters
   *
   * */

//  println("Enter given Year :")
//  try {
//    val givenYear = scala.io.StdIn.readLine.toInt
//    println("Enter Country Name :")
//    val countryName = scala.io.StdIn.readLine
//    val checkFailure = Try { println(Queries.getHighestBudgetTitle(moviesList, givenYear, countryName)) }
//    if(checkFailure.isFailure)
//      throw new RuntimeException("The Given inputs are invalid for given dataset. Please enter valid details")
//  }
//  catch {
//    case e:NumberFormatException => println("Input Type mismatch Exception")
//    case r:RuntimeException =>println(r.getMessage)
//    case _ => println("Something went wrong")
//  }

  /**5.     Generate language wise report to count the titles for the given budget range,
   *        country filter and sort with count descending
   *        Optional output: language, count
   * */

  val countryName = "USA"
  val budgetStartRange = 0
  val budgetEndRange=1000000


}
