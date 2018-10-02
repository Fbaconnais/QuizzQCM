package dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Question;
import bo.QuestionTirage;
import bo.SectionTest;
import bo.Theme;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOEpreuve;
import dal.DAOFactory;
import dal.DAOQuestion;
import dal.DAOQuestionTirage;
import dal.DAOSectionTest;

public class DAOQuestionTirageJdbcImpl implements DAOQuestionTirage {
	private Connection conn = null;
	private String generationTest = "{call genererTest(?,?,?)}";
	private String getQuestionsDansLordre = "SELECT * FROM QUESTION_TIRAGE ORDER BY numordre ASC WHERE idEpreuve=?";
	
	
	@Override
	public QuestionTirage add(QuestionTirage data) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionTirage selectOne(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionTirage> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(QuestionTirage data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void generationTest(int idEpreuve) throws DALException {
		CallableStatement call = null;
		Epreuve epreuve = null;
		Theme theme = null;
		SectionTest sectionTest = null;
		DAOEpreuve DAOEpreuve = DAOFactory.getDAOEpreuve();
		DAOSectionTest DAOSectionTest = DAOFactory.getDAOSectionTest();

		
		try {
			conn = ConnectionProvider.getCnx();
			call = conn.prepareCall(generationTest);
			epreuve = DAOEpreuve.selectOne(idEpreuve);
			theme = DAOSectionTest.getThemeViaIdTest(epreuve.getTest().getIdTest());
			sectionTest = DAOSectionTest.getSectionTestViaIdThemeAndIdTest(epreuve.getTest().getIdTest(), theme.getIdTheme());
			call.setInt(1, theme.getIdTheme());
			call.setInt(2, sectionTest.getNbQuestionsATirer());
			call.setInt(3, idEpreuve);
			call.executeUpdate();
			

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select one " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
	}

	@Override
	public List<QuestionTirage> getQuestionTirageDansLOrdre(int idEpreuve) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Epreuve epreuve = null;
		Question question = null;
		QuestionTirage questionTirage = null;
		List<QuestionTirage> questionsTirage = new ArrayList<QuestionTirage>();
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(getQuestionsDansLordre);
			rqt.setInt(1, idEpreuve);
			rs = rqt.executeQuery();
			DAOEpreuve DAOEpreuve = DAOFactory.getDAOEpreuve();
			DAOQuestion DAOQuestion = DAOFactory.getDAOQuestion();
			while (rs.next()) {
				questionTirage = new QuestionTirage();
				epreuve = DAOEpreuve.selectOne(rs.getInt("idEpreuve"));
				question = DAOQuestion.selectOne(rs.getInt("idQuestion"));
				questionTirage.setEpreuve(epreuve);
				questionTirage.setQuestion(question);
				questionTirage.setEstMarquee(rs.getBoolean("estMarquee"));
				questionTirage.setNumordre(rs.getInt("numordre"));
				questionsTirage.add(questionTirage);
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
		
		return questionsTirage;
	}

}

