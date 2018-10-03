package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.Candidat;
import bo.Epreuve;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.Test;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOQuestion;

public class DAOQuestionJdbcImpl implements DAOQuestion{
	private Connection conn = null;
	private String selectAllByIDQuestion = "SELECT * FROM Proposition WHERE idQuestion=?";
	private String selectOne = "SELECT * FROM Question WHERE idQuestion=?";
	
	@Override
	public Question add(Question data) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question selectOne(int id) throws DALException {
		Question question = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				question = new Question();

				question.setIdQuestion(rs.getInt("idQuestion"));
				question.setEnonce(rs.getString("enonce"));
				question.setMedia(rs.getString("media"));
				question.setTypeMedia(rs.getString("type_media"));
				question.setPoints(rs.getInt("points"));
				question.setTheme();
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
		
		return question;
	}

	@Override
	public List<Question> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Question data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proposition> selectAllByIDQuestion(int id) throws DALException {
		Proposition prop = null;
		List<Proposition> propositions = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectAllByIDQuestion);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				prop = new Proposition();
				prop.setEnonce(rs.getString("enonce"));
				prop.setEstBonne(rs.getBoolean("estBonne"));
				prop.setIdProposition(rs.getInt("idProposition"));
				prop.setIdQuestion();
				propositions.add(prop);
			}
		}catch (SQLException e) {
			throw new DALException ("ERREUR DAL- Select All By ID Question " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		
		return propositions;
	}

}
