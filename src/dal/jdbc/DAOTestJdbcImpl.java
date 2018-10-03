package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.Test;
import bo.Theme;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOTest;

public class DAOTestJdbcImpl implements DAOTest {
	private Connection conn = null;
	String selectOne = "SELECT * FROM TEST WHERE idTest=?";
	
	
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
		// TODO Auto-generated method stub
		return null;
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
