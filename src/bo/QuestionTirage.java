package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QuestionTirage")
public class QuestionTirage implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private boolean estMarquee;
	private int numordre;
	private Epreuve epreuve;
	private Question question;
	
	public boolean isEstMarquee() {
		return estMarquee;
	}
	public void setEstMarquee(boolean estMarquee) {
		this.estMarquee = estMarquee;
	}
	public int getNumordre() {
		return numordre;
	}
	public void setNumordre(int numordre) {
		this.numordre = numordre;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionTirage(boolean estMarquee, int numordre, Epreuve epreuve, Question question) {
		this.estMarquee = estMarquee;
		this.numordre = numordre;
		this.epreuve = epreuve;
		this.question = question;
	}
	public QuestionTirage() {
	}

}
