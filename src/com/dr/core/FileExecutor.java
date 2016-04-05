package com.dr.core;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.dr.common.DataFile;
import com.dr.common.FileParameter;

/*
 * FileExecutor class reads the list of files
 * from a input filepath and submits the task to 
 * completionService.
 */
public class FileExecutor {
	final ThreadPoolExecutor m_threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    final CompletionService<DataFile> m_completionService = new ExecutorCompletionService<DataFile>(m_threadPool);

    /* This method submits the task to completionService
     * @return - set of DataFile object which are submitted
     * to the executor.
     */
    public Set<DataFile> submit() {
		File dir = new File(FileParameter.getInputFilePath());
		int length ;
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			length = files.length;
			for (File file : files) {
				FileProcessor filProcessor = new FileProcessor(file);
				m_completionService.submit(filProcessor);
			}
		}
		else {
			length = 1;
			FileProcessor filProcessor = new FileProcessor(dir);
			m_completionService.submit(filProcessor);

		}
	    Set<DataFile> set = new HashSet<DataFile>();
	    for (int i = 0; i < length; i++) {
	    	try {
	    		Future<DataFile> f = m_completionService.take();
	    		DataFile resultObject = f.get();
	    		System.out.println("result Object " + resultObject.getName());
	    		set.add(resultObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	finally {
	    		m_threadPool.shutdown();
	    	}
	      }
	    return set;
	}
}
