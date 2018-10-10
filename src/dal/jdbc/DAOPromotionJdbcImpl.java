package dal.jdbc;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import bo.Candidat;
import bo.Promotion;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOPromotion;
import dal.DAOUtilisateur;

public class DAOPromotionJdbcImpl implements DAOPromotion {

	Connection conn;
	private String add = "INSERT INTO PROMOTION (codePromo,libelle) VALUES (?,?)";
	private String remove = "DELETE FROM PROMOTION where codePromo=?";
	private String update = "UPDATE PROMOTION SET libelle=? where codePromo=?";
	private String selectOne = "Select libelle from PROMOTION where codePromo=?";
	private String selectAll = "Select * from PROMOTION ORDER BY codePromo";
	private String inscrirePromoATest = "{call inscrirePromoATest(?,?,?,?)}";
	private String VerifPromoDejaInscriteATest = "SELECT * FROM EPREUVE WHERE (idTest=? AND idUtilisateur=?)";
	private String recherchePromo = "SELECT codePromo,libelle FROM PROMOTION WHERE codePromo LIKE ?";
	
	
	@Override
	public Promotion add(Promotion data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(add);
			rqt.setString(1, data.getId());
			rqt.setString(2, data.getLibelle().trim());
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
				promo.setId(rs.getString("codePromo").trim());
				promo.setLibelle(rs.getString("libelle"));
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
			if (liste.size() > 0) {
				for (Utilisateur user : liste) {
					((Candidat) user).setPromotion(null);
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
				promo.setLibelle(rs.getString("libelle").trim());
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

	@Override
	public void inscrirePromoATest(String codePromo, int idTest, Timestamp dateDebut, Timestamp dateFin)
			throws DALException {
		CallableStatement call = null;

		try {
			conn = ConnectionProvider.getCnx();
			call = conn.prepareCall(inscrirePromoATest);
			call.setString(1, codePromo);
			call.setInt(2, idTest);
			call.setTimestamp(3, dateDebut);
			call.setTimestamp(4, dateFin);
			call.executeUpdate();

		} catch (SQLException e) {
			throw new DALException(
					"ERREUR DAL- inscription promotion a test " + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

	}

	@Override
	public Boolean verifPromoInscriteATest(String codePromo, int idTest) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Boolean result = false;
		try {
			conn = ConnectionProvider.getCnx();
			DAOUtilisateur DAOUtil = DAOFactory.getDAOUtilisateur();
			List<Utilisateur> liste = DAOUtil.getUsersByCodePromo(codePromo);
			if (liste.size() > 0) {

				List<Integer> in = new ArrayList<Integer>();

				for (Utilisateur user : liste) {
					in.add(user.getIdUtilisateur());
				}
				rqt = conn.prepareStatement(VerifPromoDejaInscriteATest);
				rqt.setInt(1, idTest);
				for (int i : in) {
					
					rqt.setInt(2, i);
					rs = rqt.executeQuery();
					if (rs.next()) {
						result = true;
					}

				}
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- verif promo inscrite " + e.getMessage(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

		return result;
	}

	@Override
	public List<Promotion> recherchePromos(String codePromo) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Promotion promo = null;
		List<Promotion> liste = new ArrayList<Promotion>();
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(recherchePromo);
			String req = "%" + codePromo + "%";
			rqt.setString(1, req);
			rs = rqt.executeQuery();
			while (rs.next()) {
				promo = new Promotion();
				promo.setId(rs.getString("codePromo").trim());
				promo.setLibelle(rs.getString("libelle"));
				liste.add(promo);
			}

		} catch (SQLException e) {
			throw new DALException(
					"ERREUR DAL- recherche promo par nom" + e.getMessage() + e.getStackTrace().toString(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}
		return liste;
	}

}
