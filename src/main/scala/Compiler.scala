package edu.towson.cosc.cosc455.tmistr1.project1
//example
import scala.collection.mutable.{ListBuffer, Map}





object Compiler {
    var currentToken: String = ""
    var fileContents: String = ""
    var parseTree =ListBuffer[String]()
    var parseTree2 = ListBuffer[MyDocumentObject]()
    val Scanner = new MyLexicalAnalyzer
    val Parser = new MySyntaxAnalyzer
    val SemanticAnalyzer = new MySemanticAnalyzer
    var length: Int = 0
    val testObj = new MyDocumentObject
    testObj.htmlText = "YOOOO"
    //parseTree2 += testObj
    var variables = Map[String,String]()

    def main(args: Array[String]): Unit = {
      checkFile(args)
      readFile(args(0))





      Scanner.getNextToken()
      Parser.gittex()
      SemanticAnalyzer.semanticize()



    }

    def readFile(file: String) = {
      val source = scala.io.Source.fromFile(file)
      fileContents = try source.mkString finally source.close()
     // fileContents = "\\BEGIN\n\t\\TITLE[The Simpsons]\n\t# The Simpsons\n\t\\PARAB\n\t\tThe members of the [The Simpsons](https://en.wikipedia.org/wiki/The_Simpsons) are:\n\t\\PARAE\n\t+ Homer Simpson\n\t+ Marge Simpson\n\t+ Bart Simpson\n\t+ Lisa Simpson\n\t+ Maggie Simpson\n\tHere is a picture:\n\t\\\\\n\t![The Simpsons] (https://upload.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png)\n\\END\t"
      //fileContents = "\\BEGIN\n\t\\DEF[lastname = Simpson]\n\t\\TITLE[Variables] \n\t\n\t\\PARAB\n\t\tThe members of the \\USE[lastname] family are:\n\t\\PARAE\n\t\n\t+ Homer \\USE[lastname] \n\t+ Marge \\USE[lastname]\n\t+ Bart \\USE[lastname]\n\t+ Lisa \\USE[lastname]\t\n\t+ Maggie \\USE[lastname]\n\\END "
    }

    def checkFile(args: Array[String]) = {
      if (args.length != 1) {
        println("USAGE ERROR: wrong number of args fool!")
        System.exit(1)
      }
      else if (!args(0).endsWith(".gtx")) {
        println("USAGE ERROR: wrong extension fool!")
        System.exit(1)
      }
    }
  }


