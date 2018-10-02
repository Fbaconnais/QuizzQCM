package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Candidat;
import bo.Epreuve;
import bo.Test;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOEpreuve;

public class DAOEpreuveJdbcImpl implements DAOEpreuve {
	private Connection conn = null;
	String selectOne = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.niveau_obtenu," + "t.idTest," + "t.libelle," + "t.description," + "t.duree,"
			+ "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email,"
			+ "FROM Epreuve e join Test t on (e.idTest = t.idTest)"
			+ "join Utilisateur u on (e.idUtilisateur = u.idUtilisateur)" + "where e.idEpreuve=?";
	String selectAll = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.niveau_obtenu," + "t.idTest," + "t.libelle," + "t.description," + "t.duree,"
			+ "u.nom," + "u.prenom," + "u.email," + "FROM Epreuve e join Test t on (e.idTest = t.idTest)"
			+ "join Utilisateur u on (e.idUtilisateur = u.idUtilisateur)";

	String remove = "DELETE FROM Epreuve WHERE idEpreuve = ?";
	String add = "INSERT INTO Epreuve (dateDedutValidite, dateFinValidite, tempsEcoule, etat, note_obtenue, niveau_obtenu, idTest, idUtilisateur) VALUES (?, ?, ?, ?, ?, ? , ?, ?, ?)";
	String update = "UPDATE Epreuve SET dateDedutValidite=?, dateFinValidite=?, tempsEcoule=?, etat=?, note_obtenue=?, niveau_obtenu=?, idTest=?, idUtilisateur=?";

	private void closeConnection(Connection conn) throws DALException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- Fermeture connection", e);
		}
	}

	public Epreuve add(Epreuve data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
			rqt.setDate(1, data.getDateDebutValidite());
			rqt.setDate(2, data.getDateFinValidite());
			rqt.setInt(3, data.getTempsEcoule());
			rqt.setString(4, data.getEtat());
			rqt.setFloat(5, data.getNoteCandidat());
			rqt.setString(6, data.getNiveauCandidat());
			rqt.setInt(7, data.getTest().getIdTest());
			rqt.setInt(8, data.getCandidat().getIdUtilisateur());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					data.setIdEpreuve(id);
					;
				}
			}
		} catch (SQLException e) {
			throw new DALException("Erreur DAL-add", e);
		} finally {
			closeConnection(conn);
		}
		return data;
	}

	public Epreuve selectOne(int id) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Epreuve epreuve = null;
		Test test = null;
		Candidat user = null;

		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				test = new Test();
				user = new Candidat();
				epreuve = new Epreuve();

				test.setIdTest(rs.getInt("t.idTest"));
				test.setLibelle(rs.getString("t.libelle"));
				test.setDescription(rs.getString("t.description"));
				test.setDuree(rs.getInt("t.duree"));

				user.setIdUtilisateur(rs.getInt("u.idUtilisateur"));
				user.setNom(rs.getString("u.nom"));
				user.setPrenom(rs.getString("u.prenom"));
				user.setEmail(rs.getString("u.email"));

				epreuve.setIdEpreuve(rs.getInt("e.idEpreuve"));
				epreuve.setDateDebutValidite(rs.getDate("e.dateDedutValidite"));
				epreuve.setDateFinValidite(rs.getDate("e.dateFinValidite"));
				epreuve.setTempsEcoule(rs.getInt("e.tempsEcoule"));
				epreuve.setEtat(rs.getString("e.etat"));
				epreuve.setNoteCandidat(rs.getFloat("e.note_obtenue"));
				epreuve.setNiveauCandidat(rs.getString("e.niveau_obtenu"));
				epreuve.setTest(test);
				epreuve.setCandidat(user);
			}
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select one " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

		return epreuve;
	}

	public List<Epreuve> selectAll() throws DALException {
		ResultSet rs = null;
		Statement stmt = null;
		List<Epreuve> listeEpreuves = new ArrayList<Epreuve>();
		Epreuve epreuve = new Epreuve();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectAll);
			while(rs.next()){
				epreuve.setIdEpreuve(rs.getInt("e.idEpreuve"));
				epreuve.setDateDebutValidite(rs.getDate("e.dateDedutValidite"));
				epreuve.setDateFinValidite(rs.getDate("e.dateFinValidite"));
				epreuve.setTempsEcoule(rs.getInt("e.tempsEcoule"));
				epreuve.setEtat(rs.getString("e.etat"));
				epreuve.setNoteCandidat(rs.getFloat("e.note_obtenue"));
				epreuve.setNiveauCandidat(rs.getString("e.niveau_obtenu"));
				listeEpreuves.add(epreuve);
			}
			
		} catch (SQLException e) {
			throw new DALException("Erreur DAL- select all" + e.getMessage() + e.getStackTrace().toString(), e);
		}
		return listeEpreuves;
	}

	public void remove(int id) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(remove);
			rqt.setInt(1, id);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL - Remove", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

	}

	public void update(Epreuve data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(update);
			rqt.setDate(1, data.getDateDebutValidite());
			rqt.setDate(1, data.getDateFinValidite());
			rqt.setInt(3, data.getTempsEcoule());
			rqt.setString(4, data.getEtat());
			rqt.setFloat(5, data.getNoteCandidat());
			rqt.setString(6, data.getNiveauCandidat());
			rqt.setInt(7, data.getTest().getIdTest());
			rqt.setInt(8, data.getCandidat().getIdUtilisateur());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur DAL - updata", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
	}
}
