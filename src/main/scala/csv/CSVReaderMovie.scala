package csv

import com.github.tototoshi.csv.CSVReader

import java.io.FileNotFoundException
import scala.util.Try

object CSVReaderMovie {

 //val FILE_NAME : String= "imdb_movies.csv"

  def read(fileName:String):  Option[List[Map[String, String]]] ={
    try{
      val reader:CSVReader = com.github.tototoshi.csv.CSVReader.open(fileName)
      val it :  Iterator[Seq[String]] = reader.iterator
      val mapList  : List[Map[String, String]] = reader.allWithHeaders()
      reader.close()
      Some(mapList)
    }
    catch {

      case f : FileNotFoundException => {
        println("oops ! File not found")
        None
      }
      case _ =>None
    }
  }

}
