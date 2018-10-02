package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Theme")
public class Theme implements Serializable{


	private static final long serialVersionUID = 1L;
	private int idTheme;
	private String libelle;
	
	public int getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Theme(String libelle) {
	
		this.libelle = libelle;
	}
	public Theme(int idTheme, String libelle) {
		
		this.idTheme = idTheme;
		this.libelle = libelle;
	}
	public Theme() {
	
	}
	
	
}
