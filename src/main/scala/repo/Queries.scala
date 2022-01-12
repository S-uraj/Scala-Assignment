package repo

import models.Movie

import scala.util.{Failure, Success, Try}

object Queries {

  //1
  def getTitleByGivenDirectorAndYearRange(moviesList:List[Movie],directorName:String,startRange:Int,endRange:Int):Try[List[String]]= {
    val titleList: List[String] = for {
      record <- moviesList if (record.director == directorName && record.publishedYear.toInt >= startRange && record.publishedYear.toInt <= endRange)
    } yield record.title

    if(titleList.isEmpty) {
      Failure(new RuntimeException("OOPS !got an problem..."))
    }
    else
      Success(titleList)
  }


  //2
  def getTitleByGivenUserReview(moviesList:List[Movie],userReview:Int):List[Movie]={
      val resultList = for{
        movie <- moviesList if(movie.reviews != "" && movie.language !="" && movie.reviews.toInt > userReview && movie.language.contains("English"))
      } yield movie

      if(resultList.isEmpty)
        throw new RuntimeException

      implicit val sortListBasedOnUserReviews:Ordering[Movie] = Ordering.fromLessThan((a,b)=>a.reviews.toInt > b.reviews.toInt)
      resultList.sorted
  }


  //3
  def getHighestBudgetTitle(moviesList:List[Movie],givenYear:Int,countryName:String):String={
    val list3 :List[Movie] = for{
      movie <- moviesList if(movie.publishedYear.toInt == givenYear && movie.country.toLowerCase == countryName.toLowerCase)
    } yield movie

    if(list3.isEmpty)
      throw new RuntimeException

    val highestBudgetByGivenFilters:Movie = list3.reduceLeft(max)
    highestBudgetByGivenFilters.title
  }

  def max(s1: Movie, s2: Movie): Movie = if ( getCurrencyInInteger(s1.budget) > getCurrencyInInteger(s2.budget)) s1 else s2

  def getCurrencyInInteger(currency:String): Int ={
    if(currency == "")
      return 0
    val str = for{
      ch <- currency if(ch.isDigit)
    }yield ch
    str.toInt
    //Try(str.toInt).toOption
  }

}
