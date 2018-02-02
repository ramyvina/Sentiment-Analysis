package com.ramitechs.sentimentanalysis.arabic.msa.models;

public class Sentiment {
	
	private float positiveScore;
	private float negativeScore;
	
	public Sentiment(float positiveScore, float negativeScore){
		this.positiveScore = positiveScore;
		this.negativeScore = negativeScore;
	}

	public float getPositiveScore() {
		return positiveScore;
	}
	public void setPositiveScore(float positiveScore) {
		this.positiveScore = positiveScore;
	}

	public float getNegativeScore() {
		return negativeScore;
	}
	public void setNegativeScore(float negativeScore) {
		this.negativeScore = negativeScore;
	}
	
	public float getObjectiveScore() {
		return (float)(2-(positiveScore+negativeScore))/(float)2;
	}
	
}
