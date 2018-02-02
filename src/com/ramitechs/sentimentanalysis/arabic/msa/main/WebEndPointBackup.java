package com.ramitechs.sentimentanalysis.arabic.msa.main;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.ramitechs.sentimentanalysis.arabic.msa.handlers.SentenceClassifier;
import com.ramitechs.sentimentanalysis.arabic.msa.utils.TextProcessor;
 
@Path("/sentiment")
public class WebEndPointBackup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@POST
	@Path("/analyze")
	public HttpServletResponse doPost(@FormParam("sentence1") String sentence1,
			@FormParam("sentence2") String sentence2,
			@FormParam("sentence3") String sentence3,
			@FormParam("sentence4") String sentence4,
			@FormParam("sentence5") String sentence5){
		try{

			String sentiment1 = SentenceClassifier.classify(TextProcessor.convertToArabic(sentence1)).toString();
			String sentiment2 = SentenceClassifier.classify(TextProcessor.convertToArabic(sentence2)).toString();
			String sentiment3 = SentenceClassifier.classify(TextProcessor.convertToArabic(sentence3)).toString();
			String sentiment4 = SentenceClassifier.classify(TextProcessor.convertToArabic(sentence4)).toString();
			String sentiment5 = SentenceClassifier.classify(TextProcessor.convertToArabic(sentence5)).toString();
			
		   java.net.URI location = new java.net.URI("../main.html?"+
				   										"s1="+sentiment1+"&"+
				   										"s2="+sentiment2+"&"+
				   										"s3="+sentiment3+"&"+
				   										"s4="+sentiment4+"&"+
				   										"s5="+sentiment5);
		   HttpServletResponse response = (HttpServletResponse)Response.temporaryRedirect(location).build();
		   return response;
		      
		}catch(Exception e){
			return null;
		}
	}
	
}