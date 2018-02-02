package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ramitechs.sentimentanalysis.arabic.msa.constants.Constants;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;

@SuppressWarnings("serial")
public class ResourceHolder extends HttpServlet {
	
	public static int COUNT = 0;
	public static final int MAXIMIM_COUNT = 10100;
	
	private static SentenceProcessor sentenceProcessor = null;
	
    public synchronized void init() throws ServletException{
    	try{
    		Map<String, Word> dictionary = new DictionaryReader(Constants.DICTIONARY_PATH).execute();
    		Map<String, Word> lexicon = new LexiconReader(Constants.LEXICON_PATH).execute();
			dictionary = new DictionarySentimentHandler(dictionary, lexicon).execute();
			dictionary = new DictionaryAppender(dictionary).execute();
			sentenceProcessor = new SentenceProcessor(dictionary);
    	}catch(Exception e){
    		System.out.println("Error loading the resources!");
    	}
    }

	public synchronized static SentenceProcessor getSentenceProcessor() {
		return sentenceProcessor;
	}
    
}