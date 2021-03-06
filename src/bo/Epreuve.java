package bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Epreuve")
public class Epreuve implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idEpreuve;
	private Candidat candidat;
	private Test test;
	private Timestamp dateDebutValidite;
	private Timestamp dateFinValidite;
	private int tempsEcoule;
	private String etat;
	private float noteCandidat;
	private String niveauCandidat;

	private final int NOTE_MAXIMUM = 20;

	public int getIdEpreuve() {
		return idEpreuve;
	}

	public void setIdEpreuve(int idEpreuve) {
		this.idEpreuve = idEpreuve;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Timestamp getDateDebutValidite() {
		return dateDebutValidite;
	}

	public void setDateDebutValidite(Timestamp dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public Timestamp getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(Timestamp dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public int getTempsEcoule() {
		return tempsEcoule;
	}

	public void setTempsEcoule(int tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public float getNoteCandidat() {
		return noteCandidat;
	}

	public void setNoteCandidat(float f) {
		this.noteCandidat = f;
	}

	public int getNOTE_MAXIMUM() {
		return NOTE_MAXIMUM;
	}

	public String getNiveauCandidat() {
		return niveauCandidat;
	}

	public void setNiveauCandidat(String niveauCandidat) {
		this.niveauCandidat = niveauCandidat;
	}

	public Epreuve() {

	}

	public Epreuve(int idEpreuve, Candidat candidat, Test test, Timestamp dateDebutValidite, Timestamp dateFinValidite) {

		this.idEpreuve = idEpreuve;
		this.candidat = candidat;
		this.test = test;
		this.dateDebutValidite = dateDebutValidite;
		this.dateFinValidite = dateFinValidite;
	}

	public Epreuve(Candidat candidat, Test test, Timestamp dateDebutValidite, Timestamp dateFinValidite) {

		this.candidat = candidat;
		this.test = test;
		this.dateDebutValidite = dateDebutValidite;
		this.dateFinValidite = dateFinValidite;
	}

}
