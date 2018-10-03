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
import dal.DAOFactory;
import dal.DAOTest;

public class DAOEpreuveJdbcImpl implements DAOEpreuve {
	private Connection conn = null;
	String selectOne = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.tempsEcoule," + "e.niveau_obtenu," + "t.idTest," + "t.libelle," + "t.description," + "t.logo_langage," + "t.duree,"
			+ "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email"
			+ " FROM EPREUVE e join TEST t on (e.idTest = t.idTest)"
			+ "join Utilisateur u on (e.idUtilisateur = u.idUtilisateur)" + "where e.idEpreuve=?";
	String selectAll = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.niveau_obtenu," + "t.idTest," + "t.libelle," + "t.logo_langage," + "t.description," + "t.duree,"
			+ "u.nom," + "u.prenom," + "u.email " + "FROM EPREUVE e join TEST t on (e.idTest = t.idTest)"
			+ "join Utilisateur u on (e.idUtilisateur = u.idUtilisateur)";
	
	String selectAllByIDProfil = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.niveau_obtenu," + "t.idTest," + "t.logo_langage," + "t.libelle," + "t.description," + "t.duree,"
			+ "u.nom," + "u.prenom," + "u.email " + "FROM EPREUVE e join TEST t on (e.idTest = t.idTest)"
			+ "join Utilisateur u on (e.idUtilisateur = u.idUtilisateur) WHERE u.idUtilisateur=?";

	String remove = "DELETE FROM Epreuve WHERE idEpreuve = ?";
	String add = "INSERT INTO Epreuve (dateDedutValidite, dateFinValidite, tempsEcoule, etat, note_obtenue, niveau_obtenu, idTest, idUtilisateur, logo_langage) VALUES (?, ?, ?, ?, ?, ? , ?, ?, ?, ?)";
	String update = "UPDATE Epreuve SET dateDedutValidite=?, dateFinValidite=?, tempsEcoule=?, etat=?, note_obtenue=?, niveau_obtenu=?, idTest=?, idUtilisateur=?";
	String selectIdTestViaIdEpreuve = "SELECT idTest FROM EPREUVE where idEpreuve=?";
	
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

				test.setIdTest(rs.getInt("idTest"));
				test.setLibelle(rs.getString("libelle"));
				test.setDescription(rs.getString("description"));
				test.setDuree(rs.getInt("duree"));
				test.setLogoLangage(rs.getString("logo_langage"));

				user.setIdUtilisateur(rs.getInt("idUtilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));

				epreuve.setIdEpreuve(rs.getInt("idEpreuve"));
				epreuve.setDateDebutValidite(rs.getDate("dateDedutValidite"));
				epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
				epreuve.setTempsEcoule(rs.getInt("tempsEcoule"));
				epreuve.setEtat(rs.getString("etat"));
				epreuve.setNoteCandidat(rs.getFloat("note_obtenue"));
				epreuve.setNiveauCandidat(rs.getString("niveau_obtenu"));
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
		Epreuve epreuve = null;
		Test test = null;
		
		try {
			conn = ConnectionProvider.getCnx();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectAll);
			while(rs.next()){
				DAOTest DAOtest = DAOFactory.getDAOTest();
				test = new Test();
				epreuve = new Epreuve();
				test = DAOtest.selectOne(rs.getInt("idTest"));
				epreuve.setIdEpreuve(rs.getInt("idEpreuve"));
				epreuve.setDateDebutValidite(rs.getDate("dateDedutValidite"));
				epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
				epreuve.setEtat(rs.getString("etat"));
				epreuve.setNoteCandidat(rs.getFloat("note_obtenue"));
				epreuve.setNiveauCandidat(rs.getString("niveau_obtenu"));
				epreuve.setTest(test);
				listeEpreuves.add(epreuve);
			}
			
		} catch (SQLException e) {
			throw new DALException("Erreur DAL- select all" + e.getMessage() + e.getStackTrace().toString(), e);
		}
		return listeEpreuves;
	}
	
	public List<Epreuve> selectAllByIDProfil(int id) throws DALException {
		ResultSet rs = null;
		PreparedStatement rqt = null;
		List<Epreuve> listeEpreuves = new ArrayList<Epreuve>();
		Epreuve epreuve = null;
		Test test = null;
		
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectAllByIDProfil);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while(rs.next()){
				DAOTest DAOtest = DAOFactory.getDAOTest();
				test = new Test();
				epreuve = new Epreuve();
				test = DAOtest.selectOne(rs.getInt("idTest"));
				epreuve.setIdEpreuve(rs.getInt("idEpreuve"));
				epreuve.setDateDebutValidite(rs.getDate("dateDedutValidite"));
				epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
				epreuve.setEtat(rs.getString("etat"));
				epreuve.setNoteCandidat(rs.getFloat("note_obtenue"));
				epreuve.setNiveauCandidat(rs.getString("niveau_obtenu"));
				epreuve.setTest(test);
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

	@Override
	public int getIdTestViaIdEpreuve(int idEpreuve) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int idTest = 0;

		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectIdTestViaIdEpreuve);
			rqt.setInt(1, idEpreuve);
			rs = rqt.executeQuery();
			if (rs.next()) {
		
				idTest = rs.getInt("idTest");
			}
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- getIdTestViaIdEpreuve " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return idTest;
	}
}
