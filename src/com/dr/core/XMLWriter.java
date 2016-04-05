package com.dr.core;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import com.dr.common.DataFile;
import com.dr.common.FileParameter;
import com.dr.common.Paragraph;
import com.dr.common.ProperNoun;
import com.dr.common.Punctuation;
import com.dr.common.Sentence;
import com.dr.common.Token;
import com.dr.common.Word;

/*
 * XMLWriter class is a static singleton helper class to create a DOM object
 * from the DataFile object. It also writes the DOM object 
 * to a output file.
 */

public class XMLWriter {
	private org.w3c.dom.Document document;
	private Element rootElement;
	private static XMLWriter m_XMLWriter = new XMLWriter();
	
	public static XMLWriter getInstance() {
		return m_XMLWriter;
	}

	/*
	 * Constructor to initialize the DOM object.
	 */
	
	private XMLWriter() {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
			rootElement = document.createElement("Files");
			document.appendChild(rootElement);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This method takes DataFile object and creates the DOM 
	 * object.
	 * @param doc - DataFile
	 */
	
	public void process(DataFile doc) {
		Element fileElement = document.createElement("File");
		fileElement.setAttribute("name", doc.getName());
		rootElement.appendChild(fileElement);
		for (Paragraph paragraph : doc.getParagraph()) { //add paragraph
			Element paragraphElement = document.createElement(paragraph.getName());
			fileElement.appendChild(paragraphElement);

			for (Sentence sentence : paragraph.getSentence()) { //add each sentence
				Element sentenceElement = document.createElement(sentence.getName());
				paragraphElement.appendChild(sentenceElement);
				
				List<Token> list = sentence.getToken();
				//System.out.println("ThreadName " + Thread.currentThread().getName());
				for (Token item : list) { //loop through items
					
					if (item instanceof Word) { // add Words
						Word word = (Word)item;
						Element wordElement = document.createElement(word.getName());
						wordElement.appendChild(document.createTextNode(word.getWord()));
						sentenceElement.appendChild(wordElement);
					}
					else if (item instanceof Punctuation) { // add Punctuations
						Punctuation punctuation = (Punctuation)item;
						Element punctuationElement = document.createElement(punctuation.getName());
						punctuationElement.appendChild(document.createTextNode(punctuation.getPunctuation()));
						sentenceElement.appendChild(punctuationElement);
					}
					else if (item instanceof ProperNoun) { // add ProperNouns
						ProperNoun properNoun = (ProperNoun)item;
						Element properNounElement = document.createElement(properNoun.getName());
						properNounElement.appendChild(document.createTextNode(properNoun.getProperNoun()));
						sentenceElement.appendChild(properNounElement);
					}
				}
			}
		}
	}
	/*
	 * This method writes the DOM object to a output file.
	 */
	public void write() {
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer;
		try {
			transformer = tFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		    DOMSource    source = new DOMSource(document);
		    StreamResult result = new StreamResult(FileParameter.getOutputFile());

		    transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
