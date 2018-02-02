package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;
import com.ramitechs.sentimentanalysis.arabic.msa.utils.TextProcessor;

public class DictionaryAppender {
	
	private Map<String, Word> dictionary;
	
	public DictionaryAppender(Map<String, Word> dictionary){
		this.dictionary = dictionary;
	}
	
	public Map<String, Word> execute(){
		try{
			Map<String, Word> updatedDictionary = new HashMap<String, Word>();
			
			for(Entry<String, Word> entry : dictionary.entrySet()){
				String key = entry.getKey();
				updatedDictionary.put(key,  entry.getValue());
				
				String normAKey = TextProcessor.normalizeA(key);
				if(!dictionary.containsKey(normAKey)){
					updatedDictionary.put(normAKey, entry.getValue());
				}
				String normPKey = TextProcessor.normalizeP(key);
				if(!dictionary.containsKey(normPKey)){
					updatedDictionary.put(normPKey, entry.getValue());
				}
				String normAPKey = TextProcessor.normalizeP(TextProcessor.normalizeA(key));
				if(!dictionary.containsKey(normAPKey)){
					updatedDictionary.put(normAPKey, entry.getValue());
				}
				String normYKey = TextProcessor.normalizeY(key);
				if(!dictionary.containsKey(normYKey)){
					updatedDictionary.put(normYKey, entry.getValue());
				}
				String normAYKey = TextProcessor.normalizeY(TextProcessor.normalizeA(key));
				if(!dictionary.containsKey(normAYKey)){
					updatedDictionary.put(normAYKey, entry.getValue());
				}
			}
		}catch(Exception e){
			System.out.println("An unexpected error has occured!");
		}
		return dictionary;
	}

	public Map<String, Word> getDictionary() {
		return dictionary;
	}

}
