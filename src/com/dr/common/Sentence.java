package com.dr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dr.core.NamedEntitiesCache;
/*
 * Sentence class is a data structure to hold
 * the sentence which is read from the file.
 * Sentence class processes all type of token or elements
 * in the sentence.
 */
public class Sentence {
	private String m_name = "Sentence";
	private List<Token> m_tokenList = new ArrayList<Token>();
	private final String REGEX = "\\W";
	
	/*
	 * Creates the list of Tokens of type Word, Punctuation
	 * and ProperNoun.
	 * @str - takes a sentence object  
	 */
	public void process(String str ) {
		int beginIndex = 0;
		String regex  ;
		if (NamedEntitiesCache.getRegex().isEmpty())
			regex  = REGEX;
		else 
			regex = NamedEntitiesCache.getRegex()+"|"+REGEX;
		Matcher matcher = Pattern.compile(regex).matcher(str);
		while (matcher.find()) {
			if (beginIndex != matcher.start()) {
				String word = str.substring(beginIndex, matcher.start());
				//System.out.println("WORD2 " + word);
					Word w = new Word();
					w.setWord(word);
					m_tokenList.add(w);
				//beginIndex = matcher.end();
			} else {
				beginIndex++;
			}
			String g = matcher.group();
			
			if (isProperNoun(g)) {
				ProperNoun p = new ProperNoun();
				p.setProperNoun(g);
				m_tokenList.add(p);
			}
			else {
				Punctuation p = new Punctuation();
				//System.out.println("Punctuation " + g);
				p.setPunctuation(g);
				m_tokenList.add(p);
			}
			beginIndex = matcher.end();			
		}
	}	
	
	public String getName() {
		return m_name;
	}

	/*
	 * helper method to get the list of tokens
	 */
	public List<Token> getToken() {
		return m_tokenList;
	}
	
	/*
	 * helper method to find if the word is named entities
	 */
	public boolean isProperNoun(String p) {
		return NamedEntitiesCache.getProperNounList().contains(p);
	}
}
	
