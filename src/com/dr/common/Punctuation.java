package com.dr.common;

/*
 * This class hold the punctuation object.
 */
public class Punctuation extends Token {
	private String punctuation;
	private String m_name = "Punctuation";
	public void setPunctuation(String punctuation) {
		this.punctuation = punctuation;
	}

	public String getPunctuation() {
		return punctuation;
	}

	public String toString() {
		return punctuation;
	}
	public String getName() {
		return m_name;
	}
	public void setName(String name) {
		this.m_name = name;
	}
}
