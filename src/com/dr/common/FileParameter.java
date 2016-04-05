package com.dr.common;

/*
 * Helper class to set the parameters needed 
 * to run this project.
 */
public class FileParameter {
	static String m_outputFileName ="out.xml";
	static String m_inputFilePath;
	static String m_namedEntitiesFileName = "NER.txt";
	
	public static void setOutputFile(String args) {
		m_outputFileName= args;
	}
	
	public static String getOutputFile() {
		return m_outputFileName;
	}
	public static void setInputFilePath(String args) {
		m_inputFilePath= args;
	}
	public static String getInputFilePath() {
		return m_inputFilePath;
	}
	
	public static void setNamedEntityFileName(String args) {
		m_namedEntitiesFileName = args;
	}
	public static String getNamedEntityFileName() {
		return m_namedEntitiesFileName;
	}
	
}
