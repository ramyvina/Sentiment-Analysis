package com.ramitechs.sentimentanalysis.arabic.msa.handlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

import com.ramitechs.sentimentanalysis.arabic.msa.models.Sentence;
import com.ramitechs.sentimentanalysis.arabic.msa.models.Word;
import com.ramitechs.sentimentanalysis.arabic.msa.utils.Transliterator;

public class TrainingBuilder {
	
	private String dataFile;
	private Map<String, Word> dictionary;
	private String outputFile;
	
	public TrainingBuilder(String dataFile, Map<String, Word> dictionary, String outputFile){
		this.dataFile = dataFile;
		this.dictionary = dictionary;
		this.outputFile = outputFile;
	}
	
	public void execute(int size){
		try{
			int correct = 0;
			
			SentenceProcessor sentenceProcessor = new SentenceProcessor(dictionary);
			PrintWriter pw_train = new PrintWriter(outputFile);
			PrintWriter pw_test = new PrintWriter(outputFile.replace("data", "data_test"));
			appendHeader(pw_train);
			appendHeader(pw_test);
			
			PrintWriter pw = pw_train;
			
			InputStream is = new FileInputStream(dataFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String line;
	        int count = 0;
	        while ((line = br.readLine()) != null){
	        	if(!line.equals("")){
	        		count++;
	        		if(count > size){
	        			pw = pw_test;
	        		}
	        		String[] columns = line.split("\\t");
	        		String text = columns[0];
	        		int category = Integer.parseInt(columns[1]);
	        		if(category == 0)
	        			continue;
	        		Sentence sentence = sentenceProcessor.execute(text);
	        	
	        		String values =
//							sentence.getTotalPositiveScore()+" "+
//							sentence.getTotalNegativeScore()+" "+
//							sentence.getTotalPositiveNegativeScoreDiff()+" "+
//							sentence.getTotalNormalizedObjectiveScore()+" "+
//							sentence.getAveragePositiveScore()+" "+
//							sentence.getAverageNegativeScore()+" "+
							sentence.getAveragePositiveNegativeScoreDiff()+" "+
//							sentence.getAverageNormalizedObjectiveScore()+" "+
//							sentence.getPositiveCount()+" "+
//							sentence.getNegativeCount()+" "+
//							sentence.getPositiveNegativeCountDiff()+" "+
//							sentence.getObjectiveCount()+" "+
//							sentence.getAveragePositiveCount()+" "+
//							sentence.getAverageNegativeCount()+" "+
//							sentence.getAveragePositiveNegativeCountDiff()+" "+
//							sentence.getAverageObjectiveCount()+" "+
							category;
	        		
	        		int prediction = 1;
	        		
					if(sentence.getAveragePositiveNegativeScoreDiff()>0.05){
						prediction = 1;
					}else{
						prediction = 2;
					}
	        		
	        		pw.write(values+"\n");
	        		
	        		if(prediction == category)
	        			correct++;
	        		
	        		if(prediction != category*7){
		        		System.out.println(Transliterator.arabize(sentence.getText())+"\t"+"==========\t"+category+"\t"+prediction);
//		        		for(Word word : sentence.getWords()){
//		        			System.out.println((word.getLemma().equals("none")?"none":Transliterator.arabize(word.getLemma().replaceAll("\\_.*", "")))+"\t"+word.getPos()+"\t"+word.getSentiment().getPositiveScore()+"\t"+word.getSentiment().getNegativeScore());
//		        		}
//		        		
//		        		System.out.println("==========\t"+category+"\t"+prediction);
	        		}		
	        	}
	        }
	        
	        System.out.println("Correct: " + correct);
	        
	        is.close();
	        pw_train.close();
	        pw_test.close();
	        
		}catch(Exception e){
			System.out.println("An unexpected error has occured!");
		}
	}
	
	private void appendHeader(PrintWriter pw){
		pw.write("@RELATION sentiment"+"\n");
//		pw.write("@ATTRIBUTE total_positive_score  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_negative_score   NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_positive_negative_score_diff  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_objective_score  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_positive_score  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_negative_score   NUMERIC"+"\n");
		pw.write("@ATTRIBUTE ave_positive_negative_score_diff  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_objective_score  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_positive_count  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_negative_count   NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_positive_negative_count_diff  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE total_objective_count  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_positive_count  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_negative_count   NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_positive_negative_count_diff  NUMERIC"+"\n");
//		pw.write("@ATTRIBUTE ave_objective_count  NUMERIC"+"\n");
		pw.write("@ATTRIBUTE class	{1,2}"+"\n");
		pw.write(""+"\n");
		pw.write("@DATA"+"\n");
	}
	
	public double roundFloat(float f){
		try{
			DecimalFormat df = new DecimalFormat("0.##");
			df.setRoundingMode(RoundingMode.HALF_UP);
			return df.parse(df.format(f)).doubleValue();
		}catch(Exception e){
			return f;
		}
	}

}
