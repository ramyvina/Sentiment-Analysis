package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.ramitechs.sentimentanalysis.arabic.msa.models.Sentiment;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;

public class LexiconReader {
	
	private String lexiconFile;
	private Map<String, Word> lexicon;
	
	public LexiconReader(String lexiconFile){
		this.lexiconFile = lexiconFile;
	}
	
	public Map<String, Word> execute(){
		try{
			lexicon = new HashMap<String, Word>();
						
			InputStream is = this.getClass().getResourceAsStream(lexiconFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String line;
	        while ((line = br.readLine()) != null){
	        	if(!line.equals("") && !line.startsWith("#")){
	        		String[] columns = line.split("\\t");
	        		String lemma = columns[0];
	        		String pos = columns[1].toLowerCase();
	        		float positiveScore = Float.parseFloat(columns[2]);
	        		//positiveScore = positiveScore>0.75F?1F:(positiveScore>0.5?0.75F:(positiveScore>0.25?0.5F:(positiveScore>0?0.25F:0)));
	        		
	        		float negativeScore = Float.parseFloat(columns[3]);
	        		//negativeScore =  negativeScore>0.75F?1F:(negativeScore>0.5?0.75F:(negativeScore>0.25?0.5F:(negativeScore>0?0.25F:0)));
	        		
	        		Sentiment sentiment = new Sentiment(positiveScore, negativeScore);
	        		
	        		//Sentiment sentiment = new Sentiment(positiveScore>=0.8F?1F:(positiveScore>=0.6?0.8F:(positiveScore>=0.4?0.6F:(positiveScore>=0.2?0.4F:(positiveScore>0?0.2F:0)))), negativeScore>=0.8F?1F:(negativeScore>=0.6?0.8F:(negativeScore>=0.4?0.6F:(negativeScore>=0.2?0.4F:(negativeScore>0?0.2F:0)))));
	        		//Sentiment sentiment = new Sentiment(positiveScore>=0.75F?1F:(positiveScore>=0.5?0.75F:(positiveScore>=0.25?0.5F:(positiveScore>0?0.25F:0))), negativeScore>=0.75F?1F:(negativeScore>=0.5?0.75F:(negativeScore>=0.25?0.5F:(negativeScore>0?0.25F:0))));
	        		//Sentiment sentiment = new Sentiment(positiveScore>=0.66F?1F:(positiveScore>=0.33?0.66F:(positiveScore>0?0.33F:0)), negativeScore>=0.66F?1F:(negativeScore>=0.33?0.66F:(negativeScore>0?0.33F:0)));
	        		//Sentiment sentiment = new Sentiment(positiveScore>=0.5F?1F:(positiveScore>0?0.5F:0), negativeScore>=0.5F?1F:(negativeScore>0?0.5F:0));
	        		
	        		//System.out.println(lemma+"\t"+pos.toUpperCase()+"\t"+positiveScore+"\t"+negativeScore+"\t"+(1-positiveScore-negativeScore)+"\t"+columns[5]);
	        		
	        		String key1 = lemma+"-"+pos;
	        		String key2 = lemma.replaceAll("\\_.*$", "").replaceAll("o", "")+"-"+pos;
	        		String key3 = lemma.replaceAll("\\_.*$", "").replaceAll("o", "");
	        		String key4 = lemma.replaceAll("\\_.*$", "").replaceAll("o", "").replaceAll("^[\\<\\>\\|]", "A");
	        		String key5 = lemma.replaceAll("\\_.*$", "").replaceAll("o", "").replaceAll("^[\\<\\>\\|]", "A").replaceAll("[aiouFKN~`]", "");
	        		
	   
	        		lexicon.put(key1, new Word(lemma, lemma, pos, sentiment));
	        		lexicon.put(key2, new Word(lemma, lemma, pos, sentiment));
	        		lexicon.put(key3, new Word(lemma, lemma, pos, sentiment));
	        		lexicon.put(key4, new Word(lemma, lemma, pos, sentiment));
	        		lexicon.put(key5, new Word(lemma, lemma, pos, sentiment));
	        	}
	        }
	        
	        is.close();
	        
		}catch(Exception e){
			System.out.println("An unexpected error has occured!");
		}
		return lexicon;
	}
	
	public Map<String, Word> getLexicon(){
		return lexicon;
	}

}
