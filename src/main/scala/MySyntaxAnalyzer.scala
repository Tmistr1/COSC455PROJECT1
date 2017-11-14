package edu.towson.cosc.cosc455.tmistr1.project1

/*


This file contains the methods to determine which type of node to add to the parse tree
 */
import scala.collection.mutable.Map
  class MySyntaxAnalyzer extends SyntaxAnalyzer{
  	var variables = Map[String,String]()
    override def gittex(): Unit = {
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      var documentBegin = new MyDocumentObject
      documentBegin.htmlText = "<html>"
      Compiler.parseTree2 += documentBegin
        // add to parse tree / stack
	Compiler.Scanner.getNextToken()

        variableDefine()
        title()
	heading()
        body()
	var documentEnd = new MyDocumentObject
	documentEnd.htmlText = "</html>"
	Compiler.parseTree2 += documentEnd

      }
      else {
        println("Error")
        System.exit(1)
      }
    }

    override def paragraph(): Unit = {
        if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARB) ){
	   
	    var paragraphBegin = new MyDocumentObject
	    paragraphBegin.htmlText = "<p>"
	    Compiler.parseTree2 += paragraphBegin
            Compiler.Scanner.getNextToken() // innerText
	    while (Compiler.currentToken != "\\PARAE") { 
	        variableUse()
		newline()
		bold()
		innerText()
		link()
		image()
                Compiler.Scanner.getNextToken() // \
            }
	    var paragraphEnd = new MyDocumentObject
	    paragraphEnd.htmlText = "</p>"
	    Compiler.parseTree2 += paragraphEnd
        }
    }


    override def innerItem(): Unit = ???

    override def innerText(): Unit = {
    	if (!CONSTANTS.keywords.contains(Compiler.currentToken)) {
            var innerText = new MyDocumentObject
	    innerText.htmlText = Compiler.currentToken
	    Compiler.parseTree2 += innerText
        }
    }

    override def link(): Unit = {
        if (Compiler.currentToken == "[") {

	    var href: String = ""
	    var textString: String = ""
	    var link = new MyDocumentObject
	    Compiler.Scanner.getNextToken()//text
	    textString = Compiler.currentToken
	    Compiler.Scanner.getNextToken()// ]
	    Compiler.Scanner.getNextToken()// (
	    Compiler.Scanner.getNextToken()// href
	    href = Compiler.currentToken
	    Compiler.Scanner.getNextToken() // )
	    link.htmlText = "<a href=\""+href+"\">"+textString+"</a>"
	    Compiler.parseTree2 += link
	}
    }

    override def italics(): Unit = ???

    override def body(): Unit = {
    	     
    	while (Compiler.currentToken != "\\END") {
            Compiler.Scanner.getNextToken() 

	    paragraph()
	    heading()
	    newline()
	    bold()
	    listItem()
	    image()
	    link()
	    variableUse()
	    innerText()	    
	}
    }

    override def bold(): Unit = {
        if(Compiler.currentToken == "*") {
	    var boldText = new MyDocumentObject
	    Compiler.Scanner.getNextToken()
	    boldText.htmlText = "<b>"+Compiler.currentToken+"</b>"
	    Compiler.Scanner.getNextToken()
	    Compiler.parseTree2 += boldText
	 
	 }
    }

    override def newline(): Unit = {
        if (Compiler.currentToken == "\\\\") {
	    var newLine = new MyDocumentObject
	    newLine.htmlText = "\n"
	    Compiler.parseTree2 += newLine
	    
	}
    }

    override def title(): Unit = {
    	if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
            var title = new MyDocumentObject
            Compiler.Scanner.getNextToken()// title text
            title.htmlText = "<head>\n\t<title>"+Compiler.currentToken+"</title>\n</head>"
            Compiler.Scanner.getNextToken()// ]
            Compiler.parseTree2 += title
        } 
    }

    override def variableDefine(): Unit = {
        if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARIABLE_DEFINITION) ){
            var tempKey: String = ""
            var tempValue: String = ""

            Compiler.Scanner.getNextToken()// key
            tempKey = Compiler.currentToken.replaceAll("\\s","")
            Compiler.Scanner.getNextToken()// =
            Compiler.Scanner.getNextToken()// value
            tempValue = Compiler.currentToken
            Compiler.Scanner.getNextToken()// ]

            Compiler.variables +=(tempKey -> tempValue)

	    Compiler.Scanner.getNextToken()
        } else {

        }
    }

    override def image(): Unit = {
        if (Compiler.currentToken == "![") {
	    var image = new MyDocumentObject
	    var altText: String = ""
	    var source: String = ""
	    Compiler.Scanner.getNextToken() //altText
	    altText = Compiler.currentToken
	    Compiler.Scanner.getNextToken() //]
	    Compiler.Scanner.getNextToken() //(
	    Compiler.Scanner.getNextToken() //source
	    source = Compiler.currentToken
	    Compiler.Scanner.getNextToken() //)
	    image.htmlText = "<img src=\""+source+"\" alt=\""+altText+"\">"
	    Compiler.parseTree2 += image
	}
    }

    override def variableUse(): Unit = {
    	if (Compiler.currentToken == "\\USE[") {
            var variableUse = new MyDocumentObject
	    Compiler.Scanner.getNextToken() // key
	    //variableUse.htmlText = Compiler.currentToken//variables(Compiler.currentToken)
	    //variableUse.typeString = "variableUse"
	    var tempText: String = Compiler.currentToken
	    tempText.replaceAll("\\s", "")
	    variableUse.htmlText = Compiler.variables(tempText)
	    Compiler.parseTree2 += variableUse
	    Compiler.Scanner.getNextToken() // ]
	}
    }

    override def heading(): Unit = {
        if (Compiler.currentToken == "#") {
	   var headerStart = new MyDocumentObject
	   headerStart.htmlText = "<h1>"
	   Compiler.parseTree2 += headerStart
	   Compiler.Scanner.getNextToken()
	   variableUse()
	   innerText()
	   Compiler.Scanner.getNextToken()
	   var headerEnd = new MyDocumentObject
	   headerEnd.htmlText = "</h1>"
	   Compiler.parseTree2 += headerEnd
	}
    }

    override def listItem(): Unit = {
        if (Compiler.currentToken == "+") {
	   var listItemBegin = new MyDocumentObject
	   listItemBegin.htmlText = "<li>"
	   Compiler.parseTree2 += listItemBegin
	   Compiler.Scanner.getNextToken() // item text
	   innerText()
	   variableUse()
	   Compiler.Scanner.getNextToken()	

	   var listItemEnd = new MyDocumentObject
	   listItemEnd.htmlText = "</li>"
	   Compiler.parseTree2 += listItemEnd

	}

    }
  }


