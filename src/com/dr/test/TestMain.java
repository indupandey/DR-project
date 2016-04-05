package com.dr.test;

import java.util.Set;

import junit.framework.TestCase;

import com.dr.common.DataFile;
import com.dr.common.FileParameter;
import com.dr.core.FileExecutor;
import com.dr.core.NamedEntitiesCache;
import com.dr.core.XMLWriter;

/*
 * Main class to run the program
 * It takes 3 parameter
 * 1) InputFile Directory
 * 2) NER.txt file full path
 * 3) Output filename
 */
public class TestMain {
	
	public static void main(String args[]) {
		init(args);
		FileExecutor executor = new FileExecutor();
		Set<DataFile> docFileSet = executor.submit();
		for (DataFile doc : docFileSet)
			XMLWriter.getInstance().process(doc);
		XMLWriter.getInstance().write();
	}

	public static void init(String args[]) {
		if (args.length != 3) {
			System.out.println("Please pass 3 parameters");
			System.out.println("1) InputFile Directory or file");
			System.out.println("2) NER.txt file");
			System.out.println("3) Output filename");
		}
		FileParameter.setInputFilePath(args[0]);
		FileParameter.setNamedEntityFileName(args[1]);
		FileParameter.setOutputFile(args[2]);
		NamedEntitiesCache.loadProperNouns();
	}
}
