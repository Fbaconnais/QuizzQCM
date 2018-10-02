package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.SectionTest;
import bo.Test;
import bo.Theme;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOSectionTest;
import dal.DAOTest;
import dal.DAOTheme;

public class DAOSectionTestJdbcImpl implements DAOSectionTest {
	private Connection conn = null;
	
	String selectThemeViaIdTest = "SELECT idTheme FROM SECTION_TEST where idTest=?";
	String selectSectionTestViaIdTestAndIdTheme = "SELECT nbQuestionATirer FROM SECTION_TEST where (idTest=? AND idTheme=?)";
	
	
	
	
	@Override
	public SectionTest add(SectionTest data) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SectionTest selectOne(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SectionTest> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SectionTest data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Theme getThemeViaIdTest(int idTest) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Theme theme = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectThemeViaIdTest);
			rqt.setInt(1, idTest);
			rs = rqt.executeQuery();
			while (rs.next()) {
				DAOTheme dao = DAOFactory.getDAOTheme();
				theme = dao.selectOne(rs.getInt("idTheme"));
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL - select theme via idTest " + e.getMessage(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return theme;
	}

	@Override
	public SectionTest getSectionTestViaIdThemeAndIdTest(int idTest, int idTheme) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Theme theme = null;
		Test test = null;
		SectionTest sectionTest = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectThemeViaIdTest);
			rqt.setInt(1, idTest);
			rqt.setInt(2, idTheme);
			rs = rqt.executeQuery();
			while (rs.next()) {
				DAOTheme daoTheme = DAOFactory.getDAOTheme();
				theme = daoTheme.selectOne(idTheme);
				DAOTest daoTest = DAOFactory.getDAOTest();
				test = daoTest.selectOne(idTest);
				sectionTest = new SectionTest();
				sectionTest.setNbQuestionsATirer(rs.getInt("nbQuestionsATirer"));
				sectionTest.setTest(test);
				sectionTest.setTheme(theme);
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL - select sectionTest via idTest & idTheme " + e.getMessage(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return sectionTest;
	}

}