package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bo.Candidat;
import bo.Collaborateur;
import bo.Promotion;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DALException;
import dal.DAOUtilisateur;

public class DAOUtilisateurJdbcImpl implements DAOUtilisateur {

	private Connection conn = null;
	String selectOne = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email," + "p.codeProfil,p.libelle,"
			+ "pr.codePromo,pr.libelle" + " from UTILISATEUR u join PROFIL p on (u.codeProfil = p.codeProfil) "
			+ "join PROMOTION pr on (u.codePromo = pr.codePromo)" + " where u.idUtilisateur=?";

	String selectAll = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email," + "p.libelle,"
			+ "pr.libelle" + " from UTILISATEUR u join PROFIL p on (u.codeProfil = p.codeProfil) "
			+ "join PROMOTION pr on (u.codePromo = pr.codePromo)";

	String remove = "DELETE FROM UTILISATEUR where idUtilisateur=?";
	String add = "INSERT INTO UTILISATEUR (nom,prenom,email,password,codeProfil,codePromo) VALUES (?,?,?,?,?,?)";
	String update = "UPDATE UTILISATEUR SET nom=?,prenom=?,email=?,password=?,codeProfil=?,codePromo=? WHERE idUtilisateur=?";
	String authentification = "SELECT p.libelle FROM UTILISATEUR u JOIN PROFIL p ON (u.codeProfil = p.codeProfil) WHERE (u.email=? AND u.password=?)";
	
	public DAOUtilisateurJdbcImpl() {
	}

	private void closeConnection(Connection conn) throws DALException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- Fermeture connection", e);
		}
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
			if (data.getProfil().getLibelle().equals("candidat libre")
					|| data.getProfil().getLibelle().equals("stagiaire")) {
				rqt.setInt(6, ((Candidat) data).getPromotion().getId());
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
			closeConnection(conn);
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
				if (rs.getString("p.libelle").equals("candidat libre")
						|| rs.getString("p.libelle").equals("stagiaire")) {
					user = new Candidat();
					Promotion p = new Promotion();
					p.setId(rs.getInt(rs.getInt("pr.codePromo")));
					p.setLibelle(rs.getString(rs.getString("pr.libelle")));
				} else {
					user = new Collaborateur();
				}

				user.setIdUtilisateur(rs.getInt("u.idUtilisateur"));
				user.setNom(rs.getString("u.nom"));
				user.setPrenom(rs.getString("u.prenom"));
				user.setEmail(rs.getString("u.email"));
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- select one", e);
		} finally {
			closeConnection(conn);
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
			closeConnection(conn);
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
			if (data.getProfil().getLibelle().equals("candidat libre")
					|| data.getProfil().getLibelle().equals("stagiaire")) {
				rqt.setInt(6, ((Candidat) data).getPromotion().getId());
			} else {
				rqt.setInt(6, 0);
			}
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur DAL - updata", e);
		} finally {
			closeConnection(conn);
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
				message = rs.getString("p.libelle");
			} else {
				message = "Erreur";
			}

		} catch (SQLException e) {
			throw new DALException("ERREUR DAL- authentification", e);
		} finally {
			closeConnection(conn);
		}
		return message;
	}

}
