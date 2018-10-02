package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SectionTest")
public class SectionTest implements Serializable{
	


	private static final long serialVersionUID = 1L;
	private Test test;
	private Theme theme;
	private int nbQuestionsATirer;
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public int getNbQuestionsATirer() {
		return nbQuestionsATirer;
	}
	public void setNbQuestionsATirer(int nbQuestionsATirer) {
		this.nbQuestionsATirer = nbQuestionsATirer;
	}
	public SectionTest(Test test, Theme theme, int nbQuestionsATirer) {
		
		this.test = test;
		this.theme = theme;
		this.nbQuestionsATirer = nbQuestionsATirer;
	}
	public SectionTest() {
	
	}
	
	
	
	
}
