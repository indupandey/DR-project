package com.dr.common;

/*
 * Word class created by the Sentence class to 
 * hold the word object data struture. 
 */
public class Word extends Token {
	private String m_word;
	private String m_name="Word";

	public void setWord(String word) {
		this.m_word = word;
	}
	
	public String getWord() {
		return m_word;
	}
	
	public String getName() {
		return m_name;
	}
}
