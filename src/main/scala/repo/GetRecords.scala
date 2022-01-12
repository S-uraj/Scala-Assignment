package repo

import csv.CSVReaderMovie
import models.Movie

object GetRecords {


  def readRecords(fileName:String): List[Movie] ={

    //Reading the file BY CSVReader
    val mapListOption :Option[List[Map[String, String]]] = CSVReaderMovie.read(fileName)
    val mapList:List[Map[String, String]] = mapListOption.getOrElse(List())
    if(mapList.isEmpty){
      return List()
    }

    val moviesList:List[Movie] = mapList.map(m => {
      var title="";
      var year="";
      var budget="";
      var reviews_from_users="";
      var country = "";
      var genre="";
      var duration="";
      var director=""
      var language=""

      m.map(x => {
        //println(x._1 + "->" + x._2)
        if(x._1 == "title")
          title = x._2
        else if(x._1 == "year")
          year = x._2
        else if(x._1 == "budget")
          budget = x._2
        else if(x._1 == "reviews_from_users")
          reviews_from_users = x._2
        else if(x._1 == "country")
          country = x._2
        else if(x._1 == "genre")
          genre = x._2
        else if(x._1 == "duration")
          duration = x._2
        else if(x._1 == "director")
          director = x._2
        else if(x._1 == "language")
          language = x._2
      })
      val  movie:Movie = Movie(title,year,budget,reviews_from_users,country,genre,duration,director,language)
      movie
    })

    //top 10000 records
    val listMovies10000 = moviesList.take(10000)
    listMovies10000

  }


}
