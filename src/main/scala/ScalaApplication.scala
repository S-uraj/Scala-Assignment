import models.Movie
import repo.{GetRecords, Queries}

import scala.util.Try

object ScalaApplication extends App{

  val fileName:String = "imdb_movies.csv";
  val moviesList:List[Movie] = GetRecords.readRecords(fileName)


  /** 1.  Titles directed by  given director in the given year range e.g :
          generate titles report for director D.W. Griffith and year range 2010 to 2020
   * */
  print("Enter the director name :")
 try {
   val directorName: String = scala.io.StdIn.readLine
   println("Enter year Range:")
   val startRange: Int = scala.io.StdIn.readLine.toInt
   val endRange: Int = scala.io.StdIn.readLine.toInt
   val listOfTitles:Try[List[String]] = Queries.getTitleByGivenDirectorAndYearRange(moviesList, directorName, startRange, endRange)
   if(listOfTitles.isSuccess)
     listOfTitles.map(_.foreach(println))
   else
     throw new RuntimeException
 }
 catch {
   case inputMatch:NumberFormatException => println("OOPS ! Input Type Mismatch")
   case runTimeException:RuntimeException => println("The Given inputs are invalid for given dataset. Please enter valid details")
   case _ => println("Something went wrong")
 }

  /*** 2.Generate report of English titles which have user reviews more than given user review
         filter and sort the report with user reviews by descending
   * */
  println("Enter Number of user review :")
  try {
    val givenUserReview: Int = scala.io.StdIn.readLine.toInt
    val checkFailure = Try{Queries.getTitleByGivenUserReview(moviesList, givenUserReview).foreach(movie => println(movie.title))}
    if(checkFailure.isFailure)
      throw new RuntimeException("Data is not present in dataset ,After applying filter")
  }
  catch {
    case e:NumberFormatException => println("Input Type mismatch")
    case r:RuntimeException => println(r.getMessage)
    case _ => println("Something went wrong")
  }

  /** 3. Generate highest budget titles for the given year and country filters
   *
   * */

  println("Enter given Year :")
  try {
    val givenYear = scala.io.StdIn.readLine.toInt
    println("Enter Country Name :")
    val countryName = scala.io.StdIn.readLine
    val checkFailure = Try { println(Queries.getHighestBudgetTitle(moviesList, givenYear, countryName)) }
    if(checkFailure.isFailure)
      throw new RuntimeException("The Given inputs are invalid for given dataset. Please enter valid details")
  }
  catch {
    case numberFormatException:NumberFormatException => println("Input Type mismatch Exception")
    case runTimeExpection:RuntimeException =>println(runTimeExpection.getMessage)
    case _ => println("Something went wrong")
  }



}
