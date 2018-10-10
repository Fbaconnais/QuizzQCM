package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Test;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOTest;

public class DAOTestJdbcImpl implements DAOTest {
	String selectOne = "SELECT * FROM TEST WHERE idTest=?";
	String selectAll = "SELECT * FROM TEST";
	
	
	@Override
	public Test add(Test data) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test selectOne(int id) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Test test = null;
		Connection conn = null;

		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				test = new Test();
				test.setIdTest(id);
				test.setLibelle(rs.getString("libelle"));
				test.setDescription(rs.getString("description"));
				test.setDuree(rs.getInt("duree"));
				test.setLogoLangage(rs.getString("logo_langage"));
				test.setSeuil_bas(rs.getInt("seuil_bas"));
				test.setSeuil_haut(rs.getInt("seuil_haut"));
			
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL - select one " + e.getMessage(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

		return test;
	}

	@Override
	public List<Test> selectAll() throws DALException {
		Statement rqt = null;
		ResultSet rs = null;
		List<Test> liste = new ArrayList<Test>();
		Test test = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.createStatement();
			rs = rqt.executeQuery(selectAll);
			while (rs.next()) {
				
				test = new Test();
				test.setIdTest(rs.getInt("idTest"));
				test.setLibelle(rs.getString("libelle"));
				test.setDescription(rs.getString("description"));
				test.setDuree(rs.getInt("duree"));
				test.setLogoLangage(rs.getString("logo_langage"));
				test.setSeuil_bas(rs.getInt("seuil_bas"));
				test.setSeuil_haut(rs.getInt("seuil_haut"));
				liste.add(test);
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select all test " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return liste;
	}

	@Override
	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Test data) throws DALException {
		// TODO Auto-generated method stub

	}

}
