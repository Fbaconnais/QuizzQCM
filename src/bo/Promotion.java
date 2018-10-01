package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Promotion")
public class Promotion implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	private String id;
	private String libelle;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Promotion() {
		super();
	}
	public Promotion(String libelle) {
		super();
		this.libelle = libelle;
	}
	public Promotion(String id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	
	
}
