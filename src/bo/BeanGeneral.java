package bo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Transfert")
public class BeanGeneral {
	private Utilisateur utilisateur;
	private List<Promotion> promotions;
	private List<Profil> profils;
	private List<ReponseTirage> reponsetirages;
	private Question question;
	
	public BeanGeneral() {
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Profil> getProfils() {
		return profils;
	}

	public void setProfils(List<Profil> profils) {
		this.profils = profils;
	}

	public List<ReponseTirage> getReponsetirages() {
		return reponsetirages;
	}

	public void setReponsetirages(List<ReponseTirage> reponsetirages) {
		this.reponsetirages = reponsetirages;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
