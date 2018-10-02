package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Proposition")
public class Proposition implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idProposition;
	private String enonce;
	private Question question;
	private Boolean estBonne;

	public int getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(int idProposition) {
		this.idProposition = idProposition;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getEstBonne() {
		return estBonne;
	}

	public void setEstBonne(Boolean estBonne) {
		this.estBonne = estBonne;
	}

	public Proposition(String enonce, Question question, Boolean estBonne) {
		this.enonce = enonce;
		this.question = question;
		this.estBonne = estBonne;
	}

	public Proposition(int idProposition, String enonce, Question question, Boolean estBonne) {
		this.idProposition = idProposition;
		this.enonce = enonce;
		this.question = question;
		this.estBonne = estBonne;
	}

	public Proposition() {
	}

}
