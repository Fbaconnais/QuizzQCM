package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bo.Epreuve;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOEpreuve;

public class DAOEpreuveJdbcImpl implements DAOEpreuve {
	private Connection conn = null;
	String selectOne = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.niveau_obtenu," + "t.libelle," + "t.description," + "t.duree," + "u.nom,"
			+ "u.prenom," + "u.email," + "FROM Epreuve e join Test t on (e.idTest = t.idTest)" + "where e.idEpreuve=?";
	String selectAll = "SELECT " + "e.idEpreuve," + "e.dateDedutValidite," + "e.dateFinValidite," + "e.etat,"
			+ "e.note_obtenue," + "e.niveau_obtenu," + "t.libelle," + "t.description," + "t.duree," + "u.nom,"
			+ "u.prenom," + "u.email," + "FROM Epreuve e join Test t on (e.idTest = t.idTest)";

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
					data.setIdEpreuve(id);;
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
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				
			}
		}
		return null;
	}

	public List<Epreuve> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	public void update(Epreuve data) throws DALException {
		// TODO Auto-generated method stub

	}

}
