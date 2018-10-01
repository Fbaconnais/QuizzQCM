package bo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Collaborateur")
public class Collaborateur extends Utilisateur {
	
	private static final long serialVersionUID = 1L;
	private String profil;

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public Collaborateur() {
		super();
	}

	public Collaborateur(String profil) {
		super();
		this.profil = profil;
	}

	public Collaborateur(int idUtilisateur, String nom, String prenom, String email, String password) {
		super(idUtilisateur, nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}

	public Collaborateur(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}
	
	
}
