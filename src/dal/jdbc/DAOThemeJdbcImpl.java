package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Candidat;
import bo.Collaborateur;
import bo.Profil;
import bo.Promotion;
import bo.Theme;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOTheme;

public class DAOThemeJdbcImpl implements DAOTheme {
	String selectOne = "SELECT libelle FROM THEME WHERE idTheme=?";
	String selectAll = "SELECT * FROM THEME";
	
	@Override
	public Theme add(Theme data) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Theme selectOne(int id) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Theme theme = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				theme = new Theme();
				theme.setIdTheme(id);
				theme.setLibelle(rs.getString("libelle"));
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

		return theme;
	}

	@Override
	public List<Theme> selectAll() throws DALException {
		Connection conn = null;
		Statement rqt = null;
		ResultSet rs = null;

		List<Theme> liste = new ArrayList<Theme>();
		Theme theme = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.createStatement();
			rs = rqt.executeQuery(selectAll);
			while (rs.next()) {

				theme = new Theme();
				theme.setIdTheme(rs.getInt("idTheme"));
				theme.setLibelle(rs.getString("libelle"));
				liste.add(theme);
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select all themes " + e.getMessage() + e.getStackTrace().toString(), e);
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
	public void update(Theme data) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
