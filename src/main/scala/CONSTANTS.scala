package edu.towson.cosc.cosc455.tmistr1.project1

object CONSTANTS {
  val DOCB : String = 	"\\BEGIN"
  val DOCE : String = 	"\\END"
  val TITLEB : String = "\\TITLE["
  val BRACKETE : String = "]"
  val PARE : String = "\\PARAE"
  val PARB : String = "\\PARAB"
  val EQUAL : String = "="
  val HEADINGS : String = "#"
  val BOLD : String = "*"
  val UNORDERED_LIST : String = "+"
  val NEW_LINE : String = "\\\\"
  val LINK : String = "["
  var SOURCEB : String = "("
  var SOURCEE : String = ")"
  val IMAGE : String = "!["
  val VARIABLE_DEFINITION : String = "\\DEF["
  val VARIABLE_USAGE : String = "\\USE["

  val keywords : List[String] = List(DOCB,DOCE,TITLEB, BRACKETE, PARB, PARE, HEADINGS, BOLD, UNORDERED_LIST, NEW_LINE, LINK, IMAGE, VARIABLE_DEFINITION, VARIABLE_USAGE,SOURCEB,SOURCEE,EQUAL)
  val letters : List[String] = List("a","b","c","d","e","f","g","h","i","j","k","l","m",
    "n","o","p","q","r","s","t","u","v","w","x","y","z")
  val numbersEtc : List[String] = List("1","2","3","4","5","6","7","8","9","0",
    ",",".","\"",":","?","_","/", "'", "")
  val whiteSpace : List[String] = List(" ", "\t", "\n", "\b","\f","\r")
  val validText : List[String] = whiteSpace ::: letters ::: numbersEtc
}