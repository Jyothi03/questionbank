package org.questionBank.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.questionBank.data.Answer;
import org.questionBank.data.Question;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class QuestionairePDFGenerator {
	
    public static final String DEST_PATH = "tmp/pdfs/";
	private static final String QA_NAME = "study_guide";
    private static final String Q_NAME = "study_guide";
    private static final String Q_AND_A_HEADER = "Study Guide";
    private static final String Q_HEADER = "Question Sheet";
    private static final String DELIMITER = ".) ";
    private static final Font FILL_IN_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    private static final Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC);
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
    private static final Font QUESTION_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    private static final Font ANSWER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
    
	public QuestionairePDFGenerator(){
		
	}
    
    public File createQAndAPdfForMap(String title, List<Map<String,String>> questions) throws IOException, DocumentException {
    	return createPdfFileFromMap(title, questions, true);
    }
    
    public File createQuestionPDFForMap(String title, List<Map<String,String>> questions) throws IOException, DocumentException {
    	return createPdfFileFromMap(title, questions, false);
    }
    
    public File createQAndAPdfForQuestions(String title, List<Question> questions) throws IOException, DocumentException {
    	return createPdfFileFromQuestions(title, questions, true);
    }
    
    public File createQuestionPDFForQuestions(String title, List<Question> questions) throws IOException, DocumentException {
    	return createPdfFileFromQuestions(title, questions, false);
    }
    
    public File createPdfFileFromMap(String title, List<Map<String,String>> questions, boolean includeAnswers) throws IOException, DocumentException {
    	String filename = getFileName(includeAnswers);
    	File file = createFile(filename);
    	writePDFDocument(file, title, questions, includeAnswers);
    	return file;
    }
    
    public File createPdfFileFromQuestions(String title, List<Question> questions, boolean includeAnswers) throws IOException, DocumentException {
    	String filename = getFileName(includeAnswers);
    	File file = createFile(filename);
    	List<Map<String,String>> questionsMap = getQuestionsMap(questions);
    	writePDFDocument(file, title, questionsMap, includeAnswers);
        return file;
    }
    
    private List<Map<String,String>> getQuestionsMap(List<Question> questions){
    	List<Map<String,String>> questionMaps = new ArrayList<Map<String,String>>();
    	for(Question q : questions){
    		Map<String,String> questionMap = new HashMap<String,String>();
    		String question = q.getQuestion();
    		String answer = getAnswer(q);
    		questionMap.put("question", question);
    		questionMap.put("answer", answer);
    		questionMaps.add(questionMap);
    	}
    	return questionMaps;
    }
    
    private void writePDFDocument(File file, String title, List<Map<String,String>> questions, boolean includeAnswers) throws FileNotFoundException, DocumentException{
    	Document doc = new Document();
    	String filename = file.getPath();
    	PdfWriter.getInstance(doc, new FileOutputStream(filename));
    	doc.open();
    	Chapter titleChapter = getChapterTitle(title, includeAnswers);
    	int count = 0;
    	for(Map<String,String> qAndA : questions){
    		titleChapter.add(Chunk.NEWLINE);
    		if(count != 0){
    			titleChapter.add(Chunk.NEWLINE);
    			titleChapter.add(Chunk.NEWLINE);
    		}
    		count++;
    		titleChapter.add(getQuestionTextFromMap(count, qAndA));
    		if(includeAnswers){
    			titleChapter.add(Chunk.NEWLINE);
    			titleChapter.add(getAnswerTextFromMap(qAndA));
    			titleChapter.add(Chunk.NEWLINE);
    		}else{
        		titleChapter.add(Chunk.NEWLINE);
        		titleChapter.add(Chunk.NEWLINE);
        		titleChapter.add(Chunk.NEWLINE);	
    		}
    	}
    	doc.add(titleChapter);
    	doc.close();
    }
    
    private String getFileName(boolean includeAnswers){
    	String filename = DEST_PATH;
    	filename += includeAnswers ? QA_NAME : Q_NAME;
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHMMssSSS");
    	String dateStr = sdf.format(new Date());
    	filename += "_" + dateStr + ".pdf";
    	return filename;
    }
    
    private File createFile(String fileName){
    	// Create data
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        return file;
    }
    
    private Chapter getChapterTitle(String title, boolean includeAnswers){
    	String header = includeAnswers ? Q_AND_A_HEADER : Q_HEADER;
    	Paragraph fillInSection = getFillInSection();

        Chapter chapter = new Chapter(fillInSection, 1);
        chapter.setNumberDepth(0);
        chapter.add(Chunk.NEWLINE);
        
    	Chunk titleChunk = new Chunk(title, HEADER_FONT);
        Paragraph titleParagraph = new Paragraph(titleChunk);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        
        chapter.add(titleParagraph);
        
        Chunk headerChunk = new Chunk(header, TITLE_FONT);
        Paragraph headerParagraph = new Paragraph(headerChunk);
        headerParagraph.setAlignment(Element.ALIGN_CENTER);
        
        chapter.add(headerParagraph);
    	return chapter;
    }
    
    private Paragraph getFillInSection(){
    	String enterDataStr = "Name:______________________________\r\n";
    	enterDataStr += 	  "Student #:___________________________\r\n";
    	enterDataStr += 	  "Date:_______________________________\r\n";
    	Chunk enterDataChunk = new Chunk(enterDataStr , FILL_IN_FONT);
        Paragraph enterDataParagraph = new Paragraph(enterDataChunk);
        return enterDataParagraph;
    }
    
    private Paragraph getQuestionTextFromMap(int count, Map<String,String> qAndA){
    	String q = qAndA.get("question");
    	String question = count + DELIMITER + q;
    	Paragraph qParagraph = new Paragraph(question, QUESTION_FONT);
    	return qParagraph;
    }
    
    private Paragraph getAnswerTextFromMap(Map<String,String> qAndA){
    	String answer = qAndA.get("answer");
    	Paragraph aParagraph = new Paragraph(answer, ANSWER_FONT);
    	aParagraph.setIndentationLeft(40);
    	return aParagraph;
    }
    
    private String getAnswer(Question qAndA){
    	Set<Answer> answers = qAndA.getAnswers();
    	Iterator<Answer> i = answers.iterator();
    	String answer = "";
    	if(i.hasNext()){
    		Answer a = i.next();
    		answer = a.getAnswerText();
    	}
    	return answer;
    }
}
