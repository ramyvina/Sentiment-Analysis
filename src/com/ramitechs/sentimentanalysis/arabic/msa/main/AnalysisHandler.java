package com.ramitechs.sentimentanalysis.arabic.msa.main;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONObject;

import com.ramitechs.sentimentanalysis.arabic.msa.constants.SentimentClass;
import com.ramitechs.sentimentanalysis.arabic.msa.handlers.ResourceHolder;
import com.ramitechs.sentimentanalysis.arabic.msa.handlers.SentenceClassifier;

 
@Path("/sentiment")
public class AnalysisHandler {
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final SentenceJaxBean input) {
		
		JSONObject json = new JSONObject();
		
		try{
			
			ResourceHolder.COUNT++;
			json.put("count", ResourceHolder.COUNT);
			
			String sentence = input.sentence;
			json.put("sentence",sentence);
			
			if(ResourceHolder.COUNT <= ResourceHolder.MAXIMIM_COUNT){
				String sentiment = SentenceClassifier.classify(sentence).toString();
				json.put("sentiment", sentiment);
			}else{
				json.put("sentiment", SentimentClass.NONE);
			}
			
		} catch(Exception e){}
		
		return Response.status(200).entity(json.toString()).build();
	}
	
	@XmlRootElement
	public static class SentenceJaxBean {
	    @XmlElement public String sentence;
	    @XmlElement public String sentiment;
	    
	    public SentenceJaxBean(){
	    	
	    }
		
	    public SentenceJaxBean(String sentence, String sentiment){
	    	this.sentence = sentiment;
	    	this.sentence = sentiment;
	    }

		public String getSentence() {
			return sentence;
		}

		public void setSentence(String sentence) {
			this.sentence = sentence;
		}

		public String getSentiment() {
			return sentiment;
		}

		public void setSentiment(String sentiment) {
			this.sentiment = sentiment;
		}   
	}

}