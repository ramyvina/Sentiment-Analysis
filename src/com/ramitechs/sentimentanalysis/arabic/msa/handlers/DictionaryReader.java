package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;

public class DictionaryReader {
	
	private String dictionaryFile;
	private Map<String, Word> dictionary;
	
	public DictionaryReader(String dictionaryFile){
		this.dictionaryFile = dictionaryFile;
	}
	
	public Map<String, Word> execute(){
		try{
			dictionary = new HashMap<String, Word>();
			
			InputStream is = this.getClass().getResourceAsStream(dictionaryFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String line;
	        while ((line = br.readLine()) != null){
	        	if(!line.equals("")){
	        		String[] columns = line.split("\\t");
	        		String surface = columns[0];
	        		String lemma = columns[1].replaceAll("[aiuo]\\_", "_");
	        		String pos = columns[2];
	        		dictionary.put(surface, new Word(surface, lemma, pos));
	        	}
	        }
	        
	        is.close();
	        
		}catch(Exception e){
			System.out.println("An unexpected error has occured!");
		}
		return dictionary;
	}
	
	public Map<String, Word> getDictionary(){
		return dictionary;
	}

}
