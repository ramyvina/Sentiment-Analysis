package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.util.Map;
import java.util.Map.Entry;

import com.ramitechs.sentimentanalysis.arabic.msa.constants.Constants;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Sentiment;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;

public class DictionarySentimentHandler {
	
	private Map<String, Word> dictionary;
	private Map<String, Word> lexicon;
	
	public DictionarySentimentHandler(Map<String, Word> dictionary, Map<String, Word> lexicon){
		this.dictionary = dictionary;
		this.lexicon = lexicon;
	}
	
	public Map<String, Word> execute(){
		try{
			
			for(Entry<String, Word> entry : dictionary.entrySet()){
				String lemma = entry.getValue().getLemma();
				String pos = entry.getValue().getPos();
				if(!pos.matches("^.*(noun|adj|verb).*$") || pos.equals("noun_prop")){
					continue;
				}
        		if(pos.contains("noun")){
        			pos = "noun";
        		}	        		
        		if(pos.contains("verb")){
        			pos = "verb";
        		}
        		if(pos.contains("adj")){
        			pos = "adj";
        		}
        		
				if(lemma.matches(Constants.SPECIAL_LEMMA)){ //special frequent cases
					entry.getValue().setSentiment(new Sentiment(0, 0));
				}else{
					String key = lemma+"-"+pos;
					if(!lexicon.containsKey(key)){
						key = lemma.replaceAll("\\_.*$", "").replaceAll("o", "")+"-"+pos;
						if(!lexicon.containsKey(key)){
							key = lemma.replaceAll("\\_.*$", "").replaceAll("o", "");
							if(!lexicon.containsKey(key)){
								key = lemma.replaceAll("\\_.*$", "").replaceAll("o", "").replaceAll("^[\\<\\>\\|]", "A");
								if(!lexicon.containsKey(key)){
									key = lemma.replaceAll("\\_.*$", "").replaceAll("o", "").replaceAll("^[\\<\\>\\|]", "A").replaceAll("[aiouFKN~`]", "");
									if(!lexicon.containsKey(key)){
										key = entry.getValue().getSurface();
										if(!lexicon.containsKey(key)){
											key = entry.getValue().getSurface().replaceAll("o", "");
											if(!lexicon.containsKey(key)){
												key = entry.getValue().getSurface().replaceAll("o", "").replaceAll("^[\\<\\>\\|]", "A");
											}
										}
									}
								}
							}
						}
					}
					if(lexicon.containsKey(key)){
						entry.getValue().setSentiment(lexicon.get(key).getSentiment());
					}
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
	
	public Map<String, Word> getLexicon() {
		return lexicon;
	}

}
