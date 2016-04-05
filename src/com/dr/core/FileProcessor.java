package com.dr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import com.dr.common.DataFile;
/*
 * FileProcessor class implements callable interface. This class 
 * reads the file data file and returns the object of @DataFile.
 */
public class FileProcessor implements Callable<DataFile>{

	private File m_file;
	private DataFile m_doc;

	/*
	 * @param file - take the file object of data file
	 */
	public FileProcessor(File file) {
		this.m_file = file;
        this.m_doc = new DataFile();

    }

	@Override
	public DataFile call() {
	    StringBuffer fileContent = new StringBuffer();
	    String line;
	    InputStream fis = null;
	    BufferedReader br = null;
	    try {
	        fis = new FileInputStream(m_file);
	        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

	        while ((line = br.readLine()) != null)
	        	fileContent.append(line);
            //System.out.println(fileContent);
            m_doc.setName(m_file.getName());
            m_doc.process(fileContent.toString());

	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	    finally{
	    	try {
	    		if (fis != null)
	    			fis.close();
	    		if(br != null)
	    			br.close();
	    	}
	    	catch(Exception ex){}
	    }
	    return m_doc;
	}
	/*
	 * access method to get the DataFile object.
	 */
	public DataFile getDoc() {
		return m_doc;
	}
	
}
