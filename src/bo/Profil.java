package bo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Profil")
public class Profil {
	private int id;
	private String libelle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Profil(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	public Profil() {
		super();
	}
	public Profil(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	
}
