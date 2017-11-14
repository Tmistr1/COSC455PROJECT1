package edu.towson.cosc.cosc455.tmistr1.project1
//package example

import scala.collection.mutable.ListBuffer
/*

This file contains the methods to parse out the tokens


 */

class MyLexicalAnalyzer extends LexicalAnalyzer {
    var testCount : Int = 0
    var position : Int = 0
    var word = ListBuffer[Char]()
    var c = ' '
    override def addChar(): Unit = {
      word += c
    }

    // override def lookup(): Boolean = ???
    override def lookup() : Boolean = {

      testCount += 1
      var string_word = word.mkString("")

      if (CONSTANTS.keywords.contains(string_word))
      {
        return true
      } else {
        return false
      }

    }


    override def getNextToken(): Unit = {
      if (checkForEnd()) {
        Compiler.currentToken = "\\END"
        Compiler.parseTree += "\\END"
        word.clear()
      } else {
        c = getChar()

        while (c == '\r' || c == '\n' || c == '\t' || c == ' ' || c == '\b' || c == '\f') {
          c = getChar()
        }

        addChar()
        var a = 0

        if (Compiler.currentToken == "#" || Compiler.currentToken == "\\PARAB" || Compiler.currentToken == ")") {
          while (nextChar() != '\\' && nextChar != '[') {

            c = getChar()
            addChar()
          }
        } else if (Compiler.currentToken == "\\USE[" || Compiler.currentToken == "=" || Compiler.currentToken == "\\TITLE[" || Compiler.currentToken == "[" || Compiler.currentToken == "![") {
          var loopCount = 0
          while (nextChar() != ']') {

            c = getChar()
            addChar()
          }
          c = getChar()
          addChar()
        } else if (Compiler.currentToken == "(") {
          var loopCount = 0
          while (nextChar() != ')') {
            c = getChar()
            addChar()
          }
          c = getChar()
          addChar()

        } else if (Compiler.currentToken == "+") {
          var loopCount = 0
          while (nextChar() != '\n' && nextChar() != '\\') {
            c = getChar()
            addChar()
          }
          c = getChar()
          addChar()
        } else if (Compiler.currentToken == "\\DEF[") {
          var loopCount = 0
          while (nextChar() != '=') {
            c = getChar()
            addChar()
          }
          c = getChar()
          addChar()
        }else {
          var loopCount = 0
          while (!lookup() && nextChar() != '\\' && loopCount < 100) {
            loopCount += 1

            c = getChar()
            addChar()
          }

        }


        var string_word = word.mkString("")
        Compiler.currentToken = string_word
        Compiler.parseTree += string_word
        word.clear()
      }
    }
    def nextChar(): Char = {
      Compiler.fileContents.charAt(position+1)
    }

  def checkForEnd() : Boolean = {
    var end = ""
    end += Compiler.fileContents.charAt(position+1)
    end += Compiler.fileContents.charAt(position+2)
    end += Compiler.fileContents.charAt(position+3)
    if  (end == "\\EN") {
      return true
    } else {
      return false
    }
  }
    override def getChar(): Char = {
      if (position < Compiler.fileContents.length) {
          c = Compiler.fileContents.charAt(position)
          position += 1

      }
      c
    }


  }


