package com.ramitechs.sentimentanalysis.arabic.msa.main;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import com.ramitechs.sentimentanalysis.arabic.msa.handlers.SentenceClassifier;
import com.ramitechs.sentimentanalysis.arabic.msa.utils.TextProcessor;
 
@Path("/sentiment")
public class WebEndPoint extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
		
        Enumeration<String> paramNames = request.getParameterNames();

        int index = 0;
        
        while(paramNames.hasMoreElements()) {
        	index++;
        	
            String paramName = (String)paramNames.nextElement();
            
            if(paramName.equals("submit")){
            	break;
            }

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() > 0){
                	System.out.println(paramValue);
                	String arabicSentence = TextProcessor.convertToArabic(paramValue);
                	System.out.println(arabicSentence);
                	String sentiment = SentenceClassifier.classify(arabicSentence).toString();
                	System.out.println(sentiment);
                	request.setAttribute("text"+index, paramValue);
                	request.setAttribute("result"+index, sentiment);
                }
           }       
        }
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
	
}