package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bo.Proposition;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOProposition;

public class DAOPropositionJdbcImpl implements DAOProposition {
	String add = "INSERT INTO Proposition (enonce, idQuestion, estBonne) VALUES (?, ?, ?)";
	

	@Override
	public Proposition add(Proposition data) throws DALException {
		PreparedStatement rqt = null;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getEnonce());
			rqt.setInt(2, data.getIdQuestion());;
			rqt.setBoolean(3, data.getEstBonne());;
			rqt.executeUpdate();
		
		} catch (SQLException e) {
			throw new DALException("Erreur DAL-add", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Proposition selectOne(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Proposition data) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
