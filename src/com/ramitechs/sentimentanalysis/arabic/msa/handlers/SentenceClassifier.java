package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import com.ramitechs.sentimentanalysis.arabic.msa.constants.SentimentClass;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Sentence;

public class SentenceClassifier {
	
	public static SentimentClass classify(String text){
		SentimentClass sentimentClass = SentimentClass.NA;
		try{
			Sentence sentence = ResourceHolder.getSentenceProcessor().execute(text);
			System.out.println(sentence.getText());
			if(sentence.getWords().size()==0){
				sentimentClass = SentimentClass.NA;
			}else if(sentence.getAveragePositiveNegativeScoreDiff()>0.05){
				sentimentClass = SentimentClass.POSITIVE;
			}else{
				sentimentClass = SentimentClass.NEGATIVE;
			}
			
		}catch(Exception e){
			System.out.println("Error analyzing the sentence!");
		}
		return sentimentClass;
	}

}
