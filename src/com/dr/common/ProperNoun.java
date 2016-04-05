package com.dr.common;

/*
 * This class holds the named entity in the sentence.
 */
public class ProperNoun extends Token{
	private String m_properNoun;
	private String m_name="ProperNoun";

	public void setProperNoun(String properNoun) {
		this.m_properNoun = properNoun;
	}

	public String getProperNoun() {
		return m_properNoun;
	}
	
	public String getName() {
		return m_name;
	}
}
