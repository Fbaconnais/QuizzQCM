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
	private Epreuve epreuve;
	private List<Test> tests;
	private List<Epreuve> epreuves;
	private QuestionTirage questiontirage;
	private Map<Integer,List<Epreuve>> mapIdTestResultatPromo;
	private List<Theme> themes;

	
	public BeanGeneral() {
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public List<Test> getTests() {
		return tests;
	}

	


	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public Map<Integer, List<Epreuve>> getMapIdTestResultatPromo() {
		return mapIdTestResultatPromo;
	}

	public void setMapIdTestResultatPromo(Map<Integer, List<Epreuve>> mapIdTestResultatPromo) {
		this.mapIdTestResultatPromo = mapIdTestResultatPromo;
	}

	public List<Epreuve> getEpreuves() {
		return epreuves;
	}

	public void setEpreuves(List<Epreuve> epreuves) {
		this.epreuves = epreuves;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
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

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public QuestionTirage getQuestiontirage() {
		return questiontirage;
	}

	public void setQuestiontirage(QuestionTirage questiontirage) {
		this.questiontirage = questiontirage;
	}
	
	
}
