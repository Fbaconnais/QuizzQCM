package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bo.Candidat;
import bo.Collaborateur;
import bo.Profil;
import bo.Promotion;
import bo.SectionTest;
import bo.Test;
import bo.Theme;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOUtilisateur;

public class DAOUtilisateurJdbcImpl implements DAOUtilisateur {

	private Connection conn = null;
	String selectOne = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email,"
			+ "p.codeProfil,p.libelle as plibelle," + "u.codePromo"
			+ " from UTILISATEUR u join PROFIL p on (u.codeProfil = p.codeProfil) " + " where u.idUtilisateur=?";

	String selectAll = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email," + "p.libelle as plibelle,"
			+ "u.codePromo" + " from UTILISATEUR u join PROFIL p on (u.codeProfil = p.codeProfil) ";

	String selectUserByEmail = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email,"
			+ "p.codeProfil,p.libelle as plibelle," + "u.codePromo"
			+ " from UTILISATEUR as u join PROFIL as p on (u.codeProfil = p.codeProfil) " + " where u.email=?";

	String remove = "DELETE FROM UTILISATEUR where idUtilisateur=?";
	String add = "INSERT INTO UTILISATEUR (nom,prenom,email,password,codeProfil,codePromo) VALUES (?,?,?,?,?,?)";
	String update = "UPDATE UTILISATEUR SET nom=?,prenom=?,email=?,codeProfil=?,codePromo=? WHERE idUtilisateur=?";
	String authentification = "SELECT libelle FROM PROFIL p JOIN UTILISATEUR u ON (u.codeProfil = p.codeProfil) WHERE (u.email=? AND u.password=?)";
	String selectUsersByCodePromo = "SELECT u.idUtilisateur,u.nom,u.prenom,u.email,u.codeProfil,p.libelle as plibelle,pr.libelle as prlibelle FROM UTILISATEUR u JOIN PROFIL p on (u.codeProfil = p.codeProfil) JOIN PROMOTION pr ON (u.codePromo = pr.codePromo) WHERE u.codePromo=?";
	String setPassword = "UPDATE UTILISATEUR SET password=? where idUtilisateur=?";
	String selectUsersByNameAndMail = "SELECT idUtilisateur,nom,prenom,email FROM UTILISATEUR WHERE codeProfil IN (1,2) AND (nom LIKE ? OR email LIKE ?)";
	String verifCandidatInscrit = "SELECT * from EPREUVE WHERE idTest=? AND idUtilisateur=?";
	String inscrireCandidatATest = "INSERT INTO EPREUVE(dateDedutValidite,dateFinValidite,etat,idTest,idUtilisateur) VALUES (?,?,'EA',?,?)";

	public DAOUtilisateurJdbcImpl() {
	}

	public Utilisateur add(Utilisateur data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getPrenom());
			rqt.setString(3, data.getEmail());
			rqt.setString(4, data.getPassword());
			rqt.setInt(5, data.getProfil().getId());
			if (data.getProfil().getLibelle().equals("stagiaire")) {
				rqt.setString(6, ((Candidat) data).getPromotion().getId());
			} else {
				rqt.setString(6, null);
			}
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					data.setIdUtilisateur(id);
				}
			}

		} catch (SQLException e) {
			throw new DALException("Erreur DAL-add", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

		return data;
	}

	public Utilisateur selectOne(int id) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur user = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectOne);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				if (rs.getString("plibelle").equals("candidat libre") || rs.getString("plibelle").equals("stagiaire")) {
					user = new Candidat();
					if (rs.getString("plibelle").equals("stagiaire")) {
						Promotion p = new Promotion();
						p.setId(rs.getString("codePromo"));
						((Candidat) user).setPromotion(p);
					}
				} else {
					user = new Collaborateur();
				}
				Profil p = new Profil(rs.getInt("codeProfil"), rs.getString("plibelle"));
				user.setProfil(p);
				user.setIdUtilisateur(rs.getInt("idUtilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
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

		return user;
	}

	public List<Utilisateur> selectAll() {

		return null;
	}

	public void remove(int id) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(remove);
			rqt.setInt(1, id);
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

	public void update(Utilisateur data) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(update);

			rqt.setInt(6, data.getIdUtilisateur());

			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getPrenom());
			rqt.setString(3, data.getEmail());
			rqt.setInt(4, data.getProfil().getId());
			if (data.getProfil().getLibelle().equals("stagiaire")) {
				rqt.setString(5, ((Candidat) data).getPromotion().getId());
			} else {
				rqt.setString(5, null);
			}
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

	public String Authentification(String email, String password) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		String message = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(authentification);
			rqt.setString(1, email);
			rqt.setString(2, password);
			rs = rqt.executeQuery();
			if (rs.next()) {
				message = rs.getString("libelle");
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- authentification" + e.getMessage(), e);
		}
		return message;
	}

	public Utilisateur getUserByEmail(String email) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur user = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectUserByEmail);
			rqt.setString(1, email);
			rs = rqt.executeQuery();
			while (rs.next()) {
				if (rs.getString("plibelle").equals("candidat libre") || rs.getString("plibelle").equals("stagiaire")) {
					user = new Candidat();
					if (rs.getString("plibelle").equals("stagiaire")) {
						Promotion p = new Promotion();
						p.setId(rs.getString("codePromo"));
						((Candidat) user).setPromotion(p);
					}
				} else {
					user = new Collaborateur();
				}
				Profil p = new Profil(rs.getInt("codeProfil"), rs.getString("plibelle"));
				user.setProfil(p);
				user.setIdUtilisateur(rs.getInt("idUtilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(email);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("ERREUR DAL- select one " + e.getMessage(), e);

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

		return user;
	}

	@Override
	public List<Utilisateur> getUsersByCodePromo(String codePromo) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Candidat user = null;
		Profil profil = null;
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		Promotion promo = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectUsersByCodePromo);
			rqt.setString(1, codePromo);
			rs = rqt.executeQuery();
			while (rs.next()) {
				user = new Candidat();
				promo = new Promotion();
				promo.setId(codePromo);
				promo.setLibelle(rs.getString("prlibelle"));
				user.setPromotion(promo);
				profil = new Profil(rs.getInt("codeProfil"), rs.getString("plibelle"));
				user.setProfil(profil);
				user.setIdUtilisateur(rs.getInt("idUtilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				liste.add(user);
			}

		} catch (SQLException e) {
			throw new DALException(
					"ERREUR DAL- selectuser by code promo" + e.getMessage() + e.getStackTrace().toString(), e);
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
	public void updatePassword(int id, String password) throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(setPassword);

			rqt.setString(1, password);
			rqt.setInt(2, id);

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
	public List<Utilisateur> getUserByEmailOrName(String namemail) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Candidat user = null;
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(selectUsersByNameAndMail);
			String req = "%" + namemail + "%";
			rqt.setString(1, req);
			rqt.setString(2, req);
			rs = rqt.executeQuery();
			while (rs.next()) {
				user = new Candidat();
				user.setIdUtilisateur(rs.getInt("idUtilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				liste.add(user);
			}

		} catch (SQLException e) {
			throw new DALException(
					"ERREUR DAL- select candidats by mail & name" + e.getMessage() + e.getStackTrace().toString(), e);
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
	public Boolean verifCandidatInscrit(int idTest, int idUtilisateur) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			conn = ConnectionProvider.getCnx();

			rqt = conn.prepareStatement(verifCandidatInscrit);
			rqt.setInt(1, idTest);
			rqt.setInt(2,idUtilisateur);
			rs = rqt.executeQuery();
			if (rs.next()) {
				result = true;
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
	public void inscrireCandidatAEpreuve(int idTest, int idUtilisateur, Timestamp dateDebutValid, Timestamp dateFinVald)
			throws DALException {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(inscrireCandidatATest);
			rqt.setTimestamp(1, dateDebutValid);
			rqt.setTimestamp(2, dateFinVald);
			rqt.setInt(3, idTest);
			rqt.setInt(4, idUtilisateur);
			
			rqt.executeUpdate();
			

		} catch (SQLException e) {
			throw new DALException("Erreur DAL-add", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Erreur fermeture de connection", e);
			}
		}

	}

}
