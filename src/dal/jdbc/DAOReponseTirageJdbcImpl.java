package dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.ReponseTirage;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOReponseTirage;

public class DAOReponseTirageJdbcImpl implements DAOReponseTirage{
	private Connection conn = null;
	private String gestionReponse = "{call gererReponseTirage(?,?,?)}";
	private String selectAllByIDEpreuve = "SELECT * FROM Reponse_Tirage WHERE (idEpreuve=?)";
	private String selectAllByIDQuestionIDEpreuve = "SELECT * FROM Reponse_Tirage WHERE (idQuestion =?) AND (idEpreuve=?)";
	
	@Override
	public ReponseTirage add(ReponseTirage data) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReponseTirage selectOne(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReponseTirage> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ReponseTirage data) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void gererReponseTirage(int idProposition, int idQuestion, int idEpreuve) throws DALException{
		CallableStatement call = null;

		try {
			conn = ConnectionProvider.getCnx();
			call = conn.prepareCall(gestionReponse);
			
			call.setInt(1, idProposition);
			call.setInt(2, idQuestion);
			call.setInt(3, idEpreuve);
			call.executeUpdate();
			

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- Gestion des réponses " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
	}

	@Override
	public List<ReponseTirage> selectAllByIDQuestionIDEpreuve(int idQuestion, int idEpreuve) throws DALException {
		ReponseTirage reponseTirage = null;
		List<ReponseTirage> listeReponses = new ArrayList<ReponseTirage>();
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectAllByIDQuestionIDEpreuve);
			rqt.setInt(1, idQuestion);
			rqt.setInt(2, idEpreuve);
			rs = rqt.executeQuery();
			while (rs.next()) {
				reponseTirage = new ReponseTirage();
				reponseTirage.setIdQuestion(idQuestion);
				reponseTirage.setIdEpreuve(idEpreuve);
				reponseTirage.setIdProposition(rs.getInt("idProposition"));
				listeReponses.add(reponseTirage);
			}
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- Récupération des réponses du candidat" + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return listeReponses;
	}
	
	@Override
	public List<ReponseTirage> selectAllByIDEpreuve(int idEpreuve) throws DALException {
		ReponseTirage reponseTirage = null;
		List<ReponseTirage> listeReponses = new ArrayList<ReponseTirage>();
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectAllByIDEpreuve);
			rqt.setInt(1, idEpreuve);
			rs = rqt.executeQuery();
			while (rs.next()) {
				reponseTirage = new ReponseTirage();
				reponseTirage.setIdEpreuve(idEpreuve);
				reponseTirage.setIdProposition(rs.getInt("idProposition"));
				reponseTirage.setIdQuestion(rs.getInt("idQuestion"));
				listeReponses.add(reponseTirage);
			}
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- Récupération des réponses du candidat" + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return listeReponses;
	}

}
