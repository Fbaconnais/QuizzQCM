package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Utilisateur")
public abstract class Utilisateur implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int idUtilisateur;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Utilisateur() {
		super();
	}
	public Utilisateur(String nom, String prenom, String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}
	public Utilisateur(int idUtilisateur, String nom, String prenom, String email, String password) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}
	
	
}
