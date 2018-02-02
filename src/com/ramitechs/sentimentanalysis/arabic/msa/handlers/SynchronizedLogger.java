package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

public class SynchronizedLogger {
	
	//Synchronized logging
	//This method is mainly used to print the log for the threaded model download process.
	public static synchronized void print(String out){
		System.out.println(out);
	}

}
