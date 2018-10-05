package bo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReponseTirage")
public class ReponseTirage {

	int idProposition;
	int idQuestion;
	int idEpreuve;
	
	public int getIdProposition() {
		return idProposition;
	}
	public void setIdProposition(int idProposition) {
		this.idProposition = idProposition;
	}
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public int getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(int idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public ReponseTirage() {
		super();
	}
	public ReponseTirage(int idProposition, int idQuestion, int idEpreuve) {
		super();
		this.idProposition = idProposition;
		this.idQuestion = idQuestion;
		this.idEpreuve = idEpreuve;
	}
	
}
