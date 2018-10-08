package bo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Transfert")
public class BeanGeneral {
	private Utilisateur utilisateur;
	private List<Promotion> listePromo;
	private List<Profil> listeProfils;
	private List<ReponseTirage> listeReponseTirage;
	private Question question;
	
	public BeanGeneral() {
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Promotion> getListePromo() {
		return listePromo;
	}

	public void setListePromo(List<Promotion> listePromo) {
		this.listePromo = listePromo;
	}

	public List<Profil> getListeProfils() {
		return listeProfils;
	}

	public void setListeProfils(List<Profil> listeProfils) {
		this.listeProfils = listeProfils;
	}

	public List<ReponseTirage> getListeReponseTirage() {
		return listeReponseTirage;
	}

	public void setListeReponseTirage(List<ReponseTirage> listeReponseTirage) {
		this.listeReponseTirage = listeReponseTirage;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
