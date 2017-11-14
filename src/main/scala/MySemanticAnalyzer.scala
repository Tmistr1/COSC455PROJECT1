package edu.towson.cosc.cosc455.tmistr1.project1
//package example
import java.io._

/*
This file contains the method to output the parse tree to html from the parse tree


 */

class  MySemanticAnalyzer{
  	def semanticize(): Unit = {
	    //val writer = new PrintWriter(new File("out.txt"))
	    val writer = new PrintWriter(new File("output.html"))
	    for (node <- Compiler.parseTree2) {
		if (node.htmlText != "<html>" && !node.htmlText.contains("</")) {
		     if(node.htmlText == "<li>") {
		         writer.write("\t"+node.htmlText)
		     } else if (!node.htmlText.contains("<")) {
		         writer.write(node.htmlText)
		     } else {
		         writer.write("\t"+node.htmlText+"\n")
		     }
                } else {
	             writer.write(node.htmlText+"\n")
		}
	    }
	    writer.close()
	}
}