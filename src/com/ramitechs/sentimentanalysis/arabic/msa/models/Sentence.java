package com.ramitechs.sentimentanalysis.arabic.msa.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Sentence {
	
	private String text;
	private List<Word> words;
	
	public Sentence(String text) {
		this.text = text;
		words = new ArrayList<Word>();
		if(text.length()>0){
			String[] splits = text.split("\\s");
			for(int i=0; i<splits.length; i++){
				Word word = new Word(splits[i], "none", "none", new Sentiment(0, 0));
				words.add(word);
			}
		}
	}
	
	public Sentence(List<Word> words) {
		this.words = words;
	}
	
	public double getTotalPositiveScore(){
		double score = 0;
		for(int i=0; i<words.size(); i++){
			score+=words.get(i).getSentiment().getPositiveScore();
		}
		return roundDouble(score);
	}

	public double getTotalNegativeScore(){
		double score = 0;
		for(int i=0; i<words.size(); i++){
			score+=words.get(i).getSentiment().getNegativeScore();
		}
		return roundDouble(score);
	}
	
	public double getTotalPositiveNegativeScoreDiff(){
		double count = getTotalPositiveScore() - getTotalNegativeScore();
		return roundDouble(count);
	}
	
	public double getTotalNormalizedObjectiveScore(){
		double score = 0;
		for(Word word : words){
			score+=word.getSentiment().getObjectiveScore();
		}
		return roundDouble(score);
	}
	
	public double getAveragePositiveScore(){
		if(words.size()-getObjectiveCount() == 0)
			return 0;
		double score = (double)getTotalPositiveScore()/(double)(words.size()-getObjectiveCount());
		return roundDouble(score);
	}
	
	public double getAverageNegativeScore(){
		if(words.size()-getObjectiveCount() == 0)
			return 0;
		double score = (double)getTotalNegativeScore()/(double)(words.size()-getObjectiveCount());
		return roundDouble(score);
	}
	
	public double getAveragePositiveNegativeScoreDiff(){
		double count = getAveragePositiveScore() - getAverageNegativeScore();
		return roundDouble(count);
	}
		
	public double getAverageNormalizedObjectiveScore(){
		double score = (double)getTotalNormalizedObjectiveScore()/(double)words.size();
		return roundDouble(score);
	}
	
	public double getPositiveCount(){
		double count = 0;
		for(Word word : words){
			count+= word.getSentiment().getPositiveScore()>0? 1 : 0;
		}
		return count;
	}
	
	public double getNegativeCount(){
		double count = 0;
		for(Word word : words)
			count+= word.getSentiment().getNegativeScore()>0? 1 : 0;
		return count;
	}
	
	public double getPositiveNegativeCountDiff(){
		double count = getPositiveCount() - getNegativeCount();
		return count;
	}
	
	public double getObjectiveCount(){
		double count = 0;
		for(Word word : words){
			count+= word.getSentiment().getObjectiveScore()==1? 1 : 0;
		}
		return count;
	}
	
	public double getAveragePositiveCount(){
		if(words.size()-getObjectiveCount() == 0)
			return 0;
		double score = (double)getPositiveCount()/(double)(words.size()-getObjectiveCount());
		return roundDouble(score);
	}
	
	public double getAverageNegativeCount(){
		if(words.size()-getObjectiveCount() == 0)
			return 0;
		double score = (double)getNegativeCount()/(double)(words.size()-getObjectiveCount());
		return roundDouble(score);
	}
	
	public double getAveragePositiveNegativeCountDiff(){
		double count = getAveragePositiveCount() - getAverageNegativeCount();
		return roundDouble(count);
	}
		
	public double getAverageObjectiveCount(){
		double score = (double)getObjectiveCount()/(double)words.size();
		return roundDouble(score);
	}
	
	
	public String getText() {
		return text;
	}

	public List<Word> getWords() {
		return words;
	}
	public void setWords(List<Word> words) {
		this.words = words;
	}
	
	public int getPostivieNegativeCount(){
		int count=0;
		for(Word word : words)
			count+= word.getSentiment().getPositiveScore()>0 || word.getSentiment().getNegativeScore()>0 ? 1 : 0;
		return count;
	}
	
	public double roundDouble(double f){
		try{
			DecimalFormat df = new DecimalFormat("0.###");
			df.setRoundingMode(RoundingMode.HALF_UP);
			return df.parse(df.format(f)).doubleValue();
		}catch(Exception e){
			return f;
		}
	}
	
	public double getQuarter(int i, int j){
		double divide = (double)i/(double)j;
		if(divide > 0.75){
			divide = 0;
		}else if(divide > 5){
			divide = 0.25;
		}else if(divide > 0.25){
			divide = 0.5;
		}else{
			divide = 75;
		}
		return divide;
	}

}
