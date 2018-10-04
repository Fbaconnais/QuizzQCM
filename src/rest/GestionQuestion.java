package rest;

import javax.xml.bind.annotation.XmlRootElement;

import bo.Question;

@XmlRootElement
public interface GestionQuestion {

	public Question getQuestion(int id);
	
	public Question getAllQuestion();
	
	public Question addQuestion(Question data);
	
	public Question deleteQuestion(int id);
	
	public Question getPropsQuestion(int id);
}
