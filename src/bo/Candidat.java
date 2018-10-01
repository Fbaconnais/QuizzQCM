package bo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Candidat")
public class Candidat extends Utilisateur {

	
	private static final long serialVersionUID = 1L;
	private static final String PROFIL = "candidat";
	
	public static String getProfil() {
		return PROFIL;
	}

	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidat(int idUtilisateur, String nom, String prenom, String email, String password) {
		super(idUtilisateur, nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}

	public Candidat(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}
	
	

}
