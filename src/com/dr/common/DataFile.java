package com.dr.common;

import java.util.ArrayList;
import java.util.List;
/*
 * This class process the file and creates the paragraph.
 */
public class DataFile {

	private String m_name = "Document";
	private List<Paragraph> m_paragraphList = new ArrayList<Paragraph>();
	private final String REGEX = "\\n\\n";
	
	/* processes the paragraph 
	 * @param - doc type String.
	 */
	public void process(String doc) {
		String [] paralist = doc.split(REGEX);
		for (int i = 0; i < paralist.length; i++) {
			Paragraph paragraph = new Paragraph();
			paragraph.process(paralist[i]);
			m_paragraphList.add(paragraph);
		}
	}

	public void setName(String name) {
		this.m_name = name;
	}
	
	public String getName() {
		return this.m_name;
	}
	
	/*
	 * helper method to access the paragraph list.
	 */
	public List<Paragraph> getParagraph() {
		return m_paragraphList;
	}
	
}
