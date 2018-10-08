package bo;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Transfert")
public class BeanGeneral {
	private Utilisateur utilisateur;
	private List<Promotion> promotions;
	private List<Profil> profils;
	private Map<Integer, ReponseTirage> reponsetirages;
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

	public Map<Integer, ReponseTirage> getReponsetirages() {
		return reponsetirages;
	}

	public void setReponsetirages(Map<Integer, ReponseTirage> reponsetirages) {
		this.reponsetirages = reponsetirages;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
