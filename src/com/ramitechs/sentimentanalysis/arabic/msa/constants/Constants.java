package com.ramitechs.sentimentanalysis.arabic.msa.constants;

public class Constants {
	
	public static String CONFIRMATION_LEMMA = "^(gid\\~)\\_\\d$";
	public static String SPECIAL_LEMMA = "^(qad|bayon)\\_\\d$";
	public static String NEGATION_LEMMA = "^(lan|lam|lA|layos|gayor|duwn|Eadam|nafaY|nahaY)\\_\\d$";
	public static String CONTINUATION_LEMMA = "^(zAl)\\_\\d$";
	
	public static String DICTIONARY_PATH = "../resources/dict.txt";
	public static String LEXICON_PATH = "../resources/slsa-v2.0.txt";
	
	

}
