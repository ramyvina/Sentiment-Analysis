package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.util.Map;

import com.ramitechs.sentimentanalysis.arabic.msa.constants.Constants;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Sentence;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Sentiment;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;
import com.ramitechs.sentimentanalysis.arabic.msa.utils.TextProcessor;

public class SentenceProcessor {
	
	private Map<String, Word> dictionary;
	
	public SentenceProcessor(Map<String, Word> dictionary){
		this.dictionary = dictionary;
	}
	
	public Sentence execute(String text){
		Sentence sentence = null;
		try{
			text = TextProcessor.removeNonArabicContent(text);
			text = TextProcessor.romanize(text);
			sentence = new Sentence(text);
			
			for(Word word : sentence.getWords()){
				String key = word.getSurface();
				Word match = dictionary.get(key);
				if(match == null){
					String normAKey = TextProcessor.normalizeA(key);
					match = dictionary.get(normAKey);
					if(match == null){
						String normPKey = TextProcessor.normalizeP(key);
						match = dictionary.get(normPKey);
						if(match == null){
							String normAPKey = TextProcessor.normalizeP(TextProcessor.normalizeA(key));
							match = dictionary.get(normAPKey);
							if(match == null){
								String normYKey = TextProcessor.normalizeY(key);
								match = dictionary.get(normYKey);
								if(match == null){
									String normAYKey = TextProcessor.normalizeY(TextProcessor.normalizeA(key));
									match = dictionary.get(normAYKey);
								}
							}
						}
					}													
				}
				
				if(match != null){
					word.setLemma(match.getLemma());
					word.setPos(match.getPos());
					word.setSentiment(match.getSentiment());
				}
			}
			
			//Post-processing
			for(int i=0; i<sentence.getWords().size(); i++){
				Word word1 = sentence.getWords().get(i);
				
//				if(word1.getPos().matches(".*adj.*") && i>0 && (word1.getSentiment().getPositiveScore()>0 || word1.getSentiment().getNegativeScore()>0)){
//					Word word2 = sentence.getWords().get(i-1);
//					if(word2.getPos().matches(".*noun.*") && word1.getSentiment().getPositiveScore() > 0){
//						word2.setSentiment(new Sentiment(word2.getSentiment().getPositiveScore(), 0));
//					}
//					if(word2.getPos().matches(".*noun.*") && word1.getSentiment().getNegativeScore() > 0){
//						word2.setSentiment(new Sentiment(0, word2.getSentiment().getNegativeScore()));
//					}
//				}
				
				if(word1.getLemma().matches(Constants.CONFIRMATION_LEMMA) && i>0){
					Word word2 = sentence.getWords().get(i-1);
					if(word2.getPos().matches(".*adj.*")){
						float positiveScore = word2.getSentiment().getPositiveScore()*2;
						float negativeScore = word2.getSentiment().getNegativeScore()*2;
						word2.setSentiment(new Sentiment(positiveScore>1?1:positiveScore, negativeScore>1?1:negativeScore));
						word1.setSentiment(new Sentiment(0, 0));
					}
				}
			}
			
			
			int next = 4;
			for(int i=0; i<sentence.getWords().size(); i++){
				Word word1 = sentence.getWords().get(i);
				if(word1.getLemma().matches(Constants.NEGATION_LEMMA)){
					boolean change = false;
					for(int j=i+1; j<=Math.min(i+next, sentence.getWords().size()-1); j++){
						Word word2 = sentence.getWords().get(j);
						if(word2.getLemma().matches(Constants.NEGATION_LEMMA)){
							break;
						}
						if(word2.getLemma().matches(Constants.CONTINUATION_LEMMA) && j==i+1){
							break;
						}
						if(word2.getSentiment().getPositiveScore() > 0 || word2.getSentiment().getNegativeScore() > 0){
							float positiveScore = word2.getSentiment().getNegativeScore();
							float negativeScore = word2.getSentiment().getPositiveScore();
							word2.setSentiment(new Sentiment(positiveScore, negativeScore));
							change = true;
						}
					}
					if(change){
						i=i+next+1;
					}
				}
			}
			
		}catch(Exception e){
			System.out.println("An unexpected error has occured!");
		}
		return sentence;
	}
	
}
