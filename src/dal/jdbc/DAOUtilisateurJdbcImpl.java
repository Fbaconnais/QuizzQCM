package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bo.Candidat;
import bo.Collaborateur;
import bo.Profil;
import bo.Promotion;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DALException;
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
	String update = "UPDATE UTILISATEUR SET nom=?,prenom=?,email=?,password=?,codeProfil=?,codePromo=? WHERE idUtilisateur=?";
	String authentification = "SELECT libelle FROM PROFIL p JOIN UTILISATEUR u ON (u.codeProfil = p.codeProfil) WHERE (u.email=? AND u.password=?)";

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
				rqt.setInt(6, 0);
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

			rqt.setInt(7, data.getIdUtilisateur());

			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getPrenom());
			rqt.setString(3, data.getEmail());
			rqt.setString(4, data.getPassword());
			rqt.setInt(5, data.getProfil().getId());
			if (data.getProfil().getLibelle().equals("stagiaire")) {
				rqt.setString(6, ((Candidat) data).getPromotion().getId());
			} else {
				rqt.setInt(6, 0);
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

}
