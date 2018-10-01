package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Test")
public class Test implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idTest;
	private String libelle;
	private String description;
	private int duree;
	private int seuil_haut;
	private int seuil_bas;
	public int getIdTest() {
		return idTest;
	}
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public int getSeuil_haut() {
		return seuil_haut;
	}
	public void setSeuil_haut(int seuil_haut) {
		this.seuil_haut = seuil_haut;
	}
	public int getSeuil_bas() {
		return seuil_bas;
	}
	public void setSeuil_bas(int seuil_bas) {
		this.seuil_bas = seuil_bas;
	}
	
	
}
