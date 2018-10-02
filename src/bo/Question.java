package bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Question")
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idQuestion;
	private String enonce;
	private String media;
	private int points;
	private Theme theme;
	
	
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public Question(String enonce, String media, int points, Theme theme) {
		this.enonce = enonce;
		this.media = media;
		this.points = points;
		this.theme = theme;

	}
	public Question(int idQuestion, String enonce, String media, int points, Theme theme) {
		this.idQuestion = idQuestion;
		this.enonce = enonce;
		this.media = media;
		this.points = points;
		this.theme = theme;

	}
	public Question() {
	}
	
	
	
	
}
