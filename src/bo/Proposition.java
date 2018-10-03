package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Proposition")
public class Proposition implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idProposition;
	private String enonce;
	private int idQuestion;
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

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Boolean getEstBonne() {
		return estBonne;
	}

	public void setEstBonne(Boolean estBonne) {
		this.estBonne = estBonne;
	}

	public Proposition(String enonce, int idQuestion, Boolean estBonne) {
		super();
		this.enonce = enonce;
		this.idQuestion = idQuestion;
		this.estBonne = estBonne;
	}

	public Proposition() {
	}

}
