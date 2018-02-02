package com.ramitechs.sentimentanalysis.arabic.msa.utils;


public class TextProcessor {
	
	public static String removeNonArabicContent(String text){
		if(text == null){
			return "";
		}
		//text =  text.replaceAll("[^0-9\u0621-\u064A\u0660-\u0669\\s]", "");
		text =  text.replaceAll("[^\u0621-\u064A\\s\\t]", "");
		text = text.replaceAll("[\\t\\s]+", " ").trim();
		return text;
	}
	
	public static String romanize(String text){
		if(text == null){
			return "";
		}
		text = Transliterator.romanize(text).trim();
		return text;
	}
	
	public static String normalizeA(String text){
		if(text == null){
			return "";
		}
		text = text.replaceAll("^[\\<\\>\\|\\{]", "A");
		return text;
	}
	
	public static String normalizeP(String text){
		if(text == null){
			return "";
		}
		text = text.replaceAll("[p]", "h");
		return text;
	}
	
	public static String normalizeY(String text){
		if(text == null){
			return "";
		}
		text = text.replaceAll("[Y]", "y");
		return text;
	}
	
	public static String convertToArabic(String value) {
		try{
	        if(value.indexOf("&#")==-1)
	            return value;
	
	        String newString ="";
	        value = value.replaceAll("&#","");
	        String[] characters = value.split(";");
	        for(int i=0; i<characters.length; i++){
	            if(characters[i].startsWith(" "))
	                newString +=" ";
	
	            if(characters[i].trim().length()!=4)
	                newString += characters[i].trim();
	            else{
	            	try{
	            		newString +=(char)Integer.valueOf(characters[i].trim()).intValue()+"";
	            	}catch(Exception e){}
	            }
	        }
	
	        return newString;
		}catch(Exception e){
			return value;
		}
    }

}
