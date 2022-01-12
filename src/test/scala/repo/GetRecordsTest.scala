package repo
import csv.CSVReaderMovie
import models.Movie
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.util.{Failure, Success, Try}

class GetRecordsTest extends FunSuite with BeforeAndAfter{

  var testMoviesList: List[Movie] = _

  before {
    testMoviesList = GetRecords.readRecords("imdb_movies.csv")
  }

  test("Reading right csv file") {
    val testCSVReaderMovie=CSVReaderMovie.read("imdb_movies.csv")
    assert(testCSVReaderMovie.isEmpty==false)
  }

  test("Reading wrong CSV File ") {
    val testMovieList=GetRecords.readRecords("imdb_movies1.csv")
    assert(testMovieList.isEmpty)
  }


  test("Testing Query 1 For Failure"){
    val listOfTitles :Try[List[String]]= Queries.getTitleByGivenDirectorAndYearRange(testMoviesList, "abc-Director-Name", 1907, 1971)
    assert(listOfTitles.isFailure)
  }
  test("Testing Query 1 For Success"){
    val listOfTitles :Try[List[String]]= Queries.getTitleByGivenDirectorAndYearRange(testMoviesList, "Charles Tait", 1906, 1971)
    assert(listOfTitles.isSuccess)
    assert(!listOfTitles.get.isEmpty)
    assert(listOfTitles == Success(List("The Story of the Kelly Gang")))

    val listOfTitles1 :Try[List[String]]= Queries.getTitleByGivenDirectorAndYearRange(testMoviesList, "D.W. Griffith", 1913, 1914)
    assert(listOfTitles1.isSuccess)
    assert(!listOfTitles1.get.isEmpty)
    assert(listOfTitles1 == Success(List("Home, Sweet Home","The Avenging Conscience: or 'Thou Shalt Not Kill'","Judith of Bethulia")))
  }


  test("Testing Query 2 For Failure"){

    val listOfMovie :Try[List[Movie]] = Try{Queries.getTitleByGivenUserReview(testMoviesList,1500)}
    assert(listOfMovie.isFailure)

  }

  test("Testing Query 2 For Success"){

    val listOfMovie :Try[List[Movie]] = Try{Queries.getTitleByGivenUserReview(testMoviesList,1300)}
    assert(listOfMovie.isSuccess)
    assert(!listOfMovie.get.isEmpty)

    val listOfTitles = listOfMovie.map(x=>x.map(y=>y.title))
    assert(listOfTitles == Success(List("Citizen Kane", "12 Angry Men")))
  }



  test("Testing Query 3 For Failure") {
    val testMovieTitle = Try{Queries.getHighestBudgetTitle(testMoviesList,1971,"USA")}
    assert(testMovieTitle.isFailure)
  }

  test("Testing Query 3 For Success") {
    val testMovieTitle:Try[String] = Try{Queries.getHighestBudgetTitle(testMoviesList,1914,"USA")}
    assert(testMovieTitle.isSuccess)
    assert(testMovieTitle.isSuccess && testMovieTitle.get == "Tillie's Punctured Romance")

    val testMovieTitle1:Try[String] = Try{Queries.getHighestBudgetTitle(testMoviesList,1916,"USA")}
    assert(testMovieTitle1.isSuccess)
    assert(testMovieTitle1.isSuccess && testMovieTitle1.get == "Intolerance: Love's Struggle Throughout the Ages")
  }


}