package com.ramitechs.sentimentanalysis.arabic.msa.models;

public class Word {
	
	private String surface;
	private String lemma;
	private String pos;
	private Sentiment sentiment;
	
	public Word(String surface, String lemma, String pos) {
		this.surface = surface;
		this.lemma = lemma;
		this.pos = pos;
		this.sentiment = new Sentiment(0, 0);
	}
	
	public Word(String surface, String lemma, String pos, Sentiment sentiment) {
		this.surface = surface;
		this.lemma = lemma;
		this.pos = pos;
		this.sentiment = sentiment;
	}

	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getLemma() {
		return lemma;
	}
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}
	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

}
