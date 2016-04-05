package com.dr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;

import com.dr.common.FileParameter;

/*
 * NamedEntitiesCache class caches the list of the named entities in the set.
 * This class also creates the regular expression of the named entities.
 * 
 */

public class NamedEntitiesCache {
	private static HashSet<String> m_properNouns = new HashSet<String>();
	private static StringBuffer m_regexNamedEnity = new StringBuffer();
	
	/*
	 * load the named entities from the file to a hashset.
	 */
	
	public static void loadProperNouns() {
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null; 
	    try {
	    	if (FileParameter.getNamedEntityFileName() == null)
	    		return;
	    	
	    	String line;
	    	inputStreamReader = new InputStreamReader(new FileInputStream(new File(FileParameter.getNamedEntityFileName())), 
	        								Charset.forName("UTF-8"));
	    	bufferedReader = new BufferedReader(inputStreamReader);

	        while ((line = bufferedReader.readLine()) != null) {
	        	m_properNouns.add(line);
	        	m_regexNamedEnity.append("\\b").append(line).append("\\b|");
	        }
	        m_regexNamedEnity.delete(m_regexNamedEnity.lastIndexOf("|\\b"), m_regexNamedEnity.length());
	    }
	    catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    finally {
	    	try {
		    	if(inputStreamReader != null)
		    		inputStreamReader.close();
		    	if (bufferedReader != null)
		    		bufferedReader.close();
	    	}
	    	catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    }
	}
	/*
	 * Static helper method to access named entities
	 */
	public static HashSet<String> getProperNounList() {
		return m_properNouns;
	}
	
	/*
	 * Static helper method to access named entities regular expression.
	 */
	public static String getRegex() {
		return m_regexNamedEnity.toString();
	}

}
