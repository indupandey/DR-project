package com.dr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * This class processes the paragraph from 
 * the data file and creates the sentence object list.
 */
public class Paragraph {
	private String m_name = "Paragraph";
	
	private List<Sentence> m_sentenceList = new ArrayList<Sentence>();
	    Pattern re = Pattern.compile(
	                "# Match a sentence ending in punctuation or EOS.\n" +
	                "[^.!?\\s]    # First char is non-punct, non-ws\n" +
	                "[^.!?]*      # Greedily consume up to punctuation.\n" +
	                "(?:          # Group for unrolling the loop.\n" +
	                "  [.!?]      # (special) inner punctuation ok if\n" +
	                "  (?!['\"]?\\s|$)  # not followed by` ws or EOS.\n" +
	                "  [^.!?]*    # Greedily consume up to punctuation.\n" +
	                ")*           # Zero or more (special normal*)\n" +
	                "[.!?]?       # Optional ending punctuation.\n" +
	                "['\"]?       # Optional closing quote.\n" +
	                "(?=\\s|$)", 
	                Pattern.MULTILINE | Pattern.COMMENTS);
			
	/*
	 * processes the paragraph and creates the
	 * sentence object. 
	 */
	public void process(String para) {
        Matcher matcher = re.matcher(para);
		while (matcher.find()) {
			String sent = matcher.group();
            System.out.println("Sentence : " + sent);
			Sentence sentence = new Sentence();
			sentence.process(sent);
			m_sentenceList.add(sentence);
		}
	}
	
	public void setName(String name) {
		this.m_name = name;
	}
	
	public String getName() {
		return this.m_name;
	}
	
	/*
	 * helper method to access the sentence list.
	 */
	public List<Sentence> getSentence() {
		return m_sentenceList;
	}
}
