package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Candidat;
import bo.Profil;
import bo.Promotion;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOPromotion;
import dal.DAOUtilisateur;

public class DAOPromotionJdbcImpl implements DAOPromotion{
	
	Connection conn;
	String add = "INSERT INTO PROMOTION (codePromo,libelle) VALUES (?,?)";
	String remove = "DELETE FROM PROMOTION where codePromo=?";
	String update = "UPDATE PROMOTION SET libelle=? where codePromo=?";
	String selectOne = "Select libelle from PROMOTION where codePromo=?";
	String selectAll = "Select * from PROMOTION";
	
	@Override
	public Promotion add(Promotion data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(add);
			rqt.setString(1, data.getId());
			rqt.setString(2, data.getLibelle());
			rqt.executeUpdate();
			

		} catch (SQLException e) {
			throw new DALException("Erreur DAL-add promo", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return data;
	}

	@Override
	public Promotion selectOne(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> selectAll() throws DALException {
		Statement rqt = null;
		ResultSet rs = null;

		List<Promotion> liste = new ArrayList<Promotion>();
		Promotion promo = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.createStatement();
			rs = rqt.executeQuery(selectAll);
			while (rs.next()) {
				
				promo = new Promotion();
				promo.setId(rs.getString("codePromo"));
				promo.setLibelle(rs.getString("prlibelle"));
				liste.add(promo);
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select all promo " + e.getMessage() + e.getStackTrace().toString(), e);
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
	public void update(Promotion data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(update);

			rqt.setString(1, data.getLibelle());
			rqt.setString(2, data.getId());

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
	public void removePromo(String codePromo) throws DALException {
		PreparedStatement rqt = null;
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		DAOUtilisateur DAOUser = DAOFactory.getDAOUtilisateur();
		try {
			liste = DAOUser.getUsersByCodePromo(codePromo);
			if (liste.size()>0) {
				for (Utilisateur user : liste) {
					((Candidat)user).setPromotion(null);
					DAOUser.update(user);
				}
			}
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(remove);
			rqt.setString(1, codePromo);
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

	@Override
	public Promotion selectPromo(String codePromo) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Promotion promo = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setString(1, codePromo);
			rs = rqt.executeQuery();
			if (rs.next()) {
				promo = new Promotion();
				promo.setId(codePromo);
				promo.setLibelle(rs.getString("libelle"));
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select one promo " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return promo;
	}

}
